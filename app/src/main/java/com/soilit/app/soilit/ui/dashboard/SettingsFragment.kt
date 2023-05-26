import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.soilit.app.soilit.R
import com.soilit.app.soilit.api.sensorapi.awsGetApi
import com.soilit.app.soilit.databinding.FragmentProfileBinding
import com.soilit.app.soilit.databinding.FragmentSettingsBinding
import com.soilit.app.soilit.ui.login.LoginActivity
import kotlinx.coroutines.*
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class SettingsFragment : Fragment() {
    private lateinit var binding: FragmentSettingsBinding
    

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentSettingsBinding.inflate(inflater, container, false)
        
        return binding.root
    }
    
    override fun onStart() {
        super.onStart()

        binding.btnLogout.setOnClickListener {
            FirebaseAuth.getInstance().signOut()
            val intent = Intent(activity, LoginActivity::class.java)
            startActivity(intent)
            activity?.finish()
        }
    }
    
    
}
