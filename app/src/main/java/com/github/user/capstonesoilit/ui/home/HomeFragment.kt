package com.github.user.capstonesoilit.ui.home

import android.content.Intent
import android.os.Bundle
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.github.user.capstonesoilit.databinding.FragmentHomeBinding
import com.github.user.capstonesoilit.ui.fullcampaign.FullCampaign

@Suppress("DEPRECATION")
class HomeFragment : Fragment() {
    private lateinit var campaignAdapter: CampaignAdapter
    private lateinit var viewModel: HomeViewModel
    private lateinit var binding: FragmentHomeBinding
    private var recyclerViewState: Parcelable? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
        campaignAdapter = CampaignAdapter()
        viewModel.getCampaigns().observe(this) { campaigns ->
            campaignAdapter.setList(campaigns)
        }

        // Retrieve the saved state from the ViewModel
        savedInstanceState?.let { viewModel.restoreState(it) }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        setupRecyclerView()
        binding.seeall.setOnClickListener {
            val intent = Intent(requireContext(), FullCampaign::class.java)
            startActivity(intent)
        }
        return binding.root
    }

    private fun setupRecyclerView() {
        binding.rvcampaign.apply {
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            adapter = campaignAdapter
            setHasFixedSize(true)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.fetchCampaigns()

        viewModel.isLoading.observe(viewLifecycleOwner) { isLoading ->
            if (isLoading) {
                binding.campaignloader.visibility = View.VISIBLE
            } else {
                binding.campaignloader.visibility = View.GONE
            }
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        // Save the state to the ViewModel
        viewModel.saveState(outState)
        // Save the state of the RecyclerView
        recyclerViewState = binding.rvcampaign.layoutManager?.onSaveInstanceState()
        outState.putParcelable(KEY_RECYCLER_VIEW_STATE, recyclerViewState)
    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)
        // Restore the state of the RecyclerView
        recyclerViewState = savedInstanceState?.getParcelable(KEY_RECYCLER_VIEW_STATE)
        recyclerViewState?.let {
            binding.rvcampaign.layoutManager?.onRestoreInstanceState(it)
        }
    }

    companion object {
        private const val KEY_RECYCLER_VIEW_STATE = "recycler_view_state"
    }
}
