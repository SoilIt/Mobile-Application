import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.github.user.soilit.R
import com.github.user.soilit.api.model.User_sensor
import com.github.user.soilit.api.sensorapi.awsGetApi
import kotlinx.coroutines.*
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class SettingsFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_settings, container, false)

        // Create Retrofit object
        val retro = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https:khogyrvwcc.execute-api.ap-south-1.amazonaws.com/sensor/")
            .build()
        
        val AwsGetApi = retro.create(awsGetApi::class.java)

        // Get data using Coroutine
        val recyclerView = view.findViewById<RecyclerView>(R.id.recycler)
        CoroutineScope(Dispatchers.Main).launch {
            try {
                val response = withContext(Dispatchers.IO) { AwsGetApi.getUsers().execute() }
                if (response.isSuccessful) {
                    recyclerView.apply {
                        layoutManager = LinearLayoutManager(requireContext())
                    }
                } else {
                    Log.e("ERROR", response.message())
                }
            } catch (e: Exception) {
                e.message?.let { Log.e("ERROR", it) }
            }
        }
        return view
    }
}
