package com.github.user.soilitouraplication.ui.history

import android.annotation.SuppressLint
import android.content.res.Resources
import android.graphics.*
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.github.user.soilitouraplication.R
import com.github.user.soilitouraplication.api.History
import com.github.user.soilitouraplication.api.HistoryApi
import com.github.user.soilitouraplication.database.HistoryDao
import com.github.user.soilitouraplication.database.HistoryDatabase
import com.github.user.soilitouraplication.databinding.FragmentHistoryBinding
import com.google.android.material.textfield.TextInputEditText
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Suppress("DEPRECATION")
class HistoryFragment : Fragment() {

    private lateinit var historyViewModel: HistoryViewModel
    private lateinit var historyApi: HistoryApi
    private lateinit var binding: FragmentHistoryBinding
    lateinit var historyAdapter: HistoryAdapter

    private lateinit var itemTouchHelper: ItemTouchHelper
    private var isNullOrEmpty: Boolean = true
    val historyList: MutableList<History> = mutableListOf()

    // Dependency Injection
    internal lateinit var historyDao: HistoryDao

    private lateinit var swipeRefreshLayout: SwipeRefreshLayout
    private var isRefreshing = false

    @SuppressLint("NotifyDataSetChanged")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentHistoryBinding.inflate(inflater, container, false)
        val view = binding.root

        swipeRefreshLayout = binding.swipeRefreshLayout
        swipeRefreshLayout.setOnRefreshListener {
            if (!isRefreshing) {
                fetchHistory()
            }
        }

        val recyclerView: RecyclerView = binding.history
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        historyAdapter = HistoryAdapter()
        recyclerView.adapter = historyAdapter

        val historyDatabase = HistoryDatabase.getInstance(requireContext())
        historyDao = historyDatabase.historyDao()

        lifecycleScope.launch {
            historyDao.getAllHistory().collect { historyList ->
                historyAdapter.setData(historyList)
            }
        }

        val itemSwipeCallback = object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {

            private val paint = Paint()

            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder,
            ): Boolean {
                return false
            }

            override fun onChildDraw(
                c: Canvas,
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                dX: Float,
                dY: Float,
                actionState: Int,
                isCurrentlyActive: Boolean,
            ) {
                val icon: Bitmap
                if (actionState == ItemTouchHelper.ACTION_STATE_SWIPE) {
                    val itemView: View = viewHolder.itemView
                    val height = itemView.bottom.toFloat() - itemView.top.toFloat()
                    val width = height / 3
                    val cornerRadius = 20.dpToPx().toFloat() // Convert dp to pixels

                    val cardRect = RectF(
                        itemView.left.toFloat(),
                        itemView.top.toFloat() + 15.dpToPx().toFloat(),
                        itemView.left.toFloat() + 350.dpToPx().toFloat(),
                        itemView.top.toFloat() + 10.dpToPx().toFloat() + 150.dpToPx().toFloat()
                    )

                    paint.color = Color.parseColor("#B33A3A") // Set color to red

                    c.drawRoundRect(cardRect, cornerRadius, cornerRadius, paint)

                    if (dX > 0) {
                        // Draw icon and background when swiping to the right
                        val background = RectF(
                            itemView.left.toFloat(),
                            itemView.top.toFloat(),
                            dX,
                            itemView.bottom.toFloat()
                        )
                        c.drawRect(background, paint)

                        icon = BitmapFactory.decodeResource(resources,
                            R.drawable.delete) // Customize the icon

                        val scaleFactor =
                            1 - (dX / itemView.width) // Calculate scale factor based on dX
                        val scaledWidth = icon.width * scaleFactor
                        icon.height * scaleFactor
                        val iconDest = RectF(
                            itemView.left.toFloat() + width,
                            itemView.top.toFloat() + width,
                            itemView.left.toFloat() + width + scaledWidth,
                            itemView.bottom.toFloat() - width
                        )
                        c.drawBitmap(icon, null, iconDest, paint)
                    } else {
                        // Draw icon when swiping to the left without background
                        icon = BitmapFactory.decodeResource(resources,
                            R.drawable.delete) // Customize the icon

                        val scaleFactor =
                            1 - (-dX / itemView.width) // Calculate scale factor based on dX
                        val scaledWidth = icon.width * scaleFactor
                        icon.height * scaleFactor
                        val iconDest = RectF(
                            itemView.right.toFloat() - width - scaledWidth,
                            itemView.top.toFloat() + width,
                            itemView.right.toFloat() - width,
                            itemView.bottom.toFloat() - width
                        )
                        c.drawBitmap(icon, null, iconDest, paint)
                    }

                }
                super.onChildDraw(c,
                    recyclerView,
                    viewHolder,
                    dX,
                    dY,
                    actionState,
                    isCurrentlyActive)
            }

            // Extension function to convert dp to pixels
            private fun Int.dpToPx(): Int {
                return (this * Resources.getSystem().displayMetrics.density).toInt()
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition
                val deletedItem = historyList[position]

                lifecycleScope.launch {
                    historyDao.deleteHistory(deletedItem)
                }

                historyList.removeAt(position)
                historyAdapter.notifyItemRemoved(position)

            }
        }

        itemTouchHelper = ItemTouchHelper(itemSwipeCallback)
        itemTouchHelper.attachToRecyclerView(recyclerView)

        val editText: TextInputEditText = binding.editText
        editText.addTextChangedListener(object : TextWatcher {
            private val DELAY_MS: Long = 300
            private var searchRunnable: Runnable? = null

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                // Not needed for this implementation
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                // Not needed for this implementation
            }

            override fun afterTextChanged(s: Editable?) {
                val searchText = s?.toString()?.trim()
                isNullOrEmpty = searchText.isNullOrEmpty()

                if (searchRunnable != null) {
                    editText.removeCallbacks(searchRunnable)
                }

                searchRunnable = Runnable {
                    if (isNullOrEmpty) {
                        editText.hint = getString(R.string.SearchHistory)
                    } else {
                        editText.hint = null
                    }
                }

                editText.postDelayed(searchRunnable, DELAY_MS)
            }
        })

        val retrofit = Retrofit.Builder()
            .baseUrl("https://private-7a3f77-soilit.apiary-mock.com/history/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        historyApi = retrofit.create(HistoryApi::class.java)

        historyViewModel = HistoryViewModel(historyApi)

        // Fetch history using view model
        fetchHistory()

        return view
    }

    private fun fetchHistory() {
        isRefreshing = true
        swipeRefreshLayout.isRefreshing = true

        try {
            historyViewModel.fetchHistory()
            historyViewModel.historyList.observe(viewLifecycleOwner) { historyList ->
                this.historyList.clear()
                this.historyList.addAll(historyList)
                historyAdapter.notifyDataSetChanged()

                // Save the fetched data to the local database
                // Save the fetched data to the local database only if it has more rows than the current data
                lifecycleScope.launch {
                    val currentHistoryCount = historyDao.getHistoryCount()
                    if (historyList.size > currentHistoryCount) {
                        historyDao.deleteAllHistory()
                        historyDao.insertHistory(historyList)
                    }
                }
            }
        } catch (e: Exception) {
            // Log or show an error message here
            Log.e("HistoryFragment", "Failed to fetch history", e)
        } finally {
            isRefreshing = false
            swipeRefreshLayout.isRefreshing = false
        }
    }

    companion object
}
