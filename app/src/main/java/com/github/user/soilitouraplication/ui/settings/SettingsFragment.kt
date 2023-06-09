
import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.github.user.soilitouraplication.databinding.FragmentSettingsBinding
import com.google.firebase.auth.FirebaseAuth
import com.github.user.soilitouraplication.R
import com.github.user.soilitouraplication.ui.change_password.ChangePasswordActivity
import com.github.user.soilitouraplication.ui.login.LoginActivity

class SettingsFragment : Fragment() {
    private lateinit var binding: FragmentSettingsBinding
    
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSettingsBinding.inflate(inflater, container, false)
        
        return binding.root
    }
    
    override fun onStart() {
        super.onStart()
        
        binding.btnLogout.setOnClickListener {
            showDialog()
        }
        
        binding.btnLanguage.setOnClickListener {
            startActivity(Intent(android.provider.Settings.ACTION_LOCALE_SETTINGS))
        }
        
        binding.btnToChangePasswordScreen.setOnClickListener {
            startActivity(Intent(activity, ChangePasswordActivity::class.java))
        }
    }
    
    private fun showDialog() {
        val alertDialogBuilder = AlertDialog.Builder(
            activity
        )
        
        alertDialogBuilder
            .setMessage(R.string.confirm_logout)
            .setCancelable(true)
            .setPositiveButton(
                R.string.logout
            ) { _, _ ->
                FirebaseAuth.getInstance().signOut()
                val intent = Intent(activity, LoginActivity::class.java)
                startActivity(intent)
                activity?.finish()
            }
            .setNegativeButton(
                R.string.cancel
            ) { dialog, _ ->
                dialog.cancel()
            }
        
        val alertDialog = alertDialogBuilder.create()
        
        alertDialog.show()
    }
}

