package rizkyfadilah.binar.synrgy6.android.challengechapter4.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import rizkyfadilah.binar.synrgy6.android.challengechapter4.R
import rizkyfadilah.binar.synrgy6.android.challengechapter4.database.resgister_database.RegisterDatabase
import rizkyfadilah.binar.synrgy6.android.challengechapter4.databinding.FragmentLoginBinding
import rizkyfadilah.binar.synrgy6.android.challengechapter4.repository.RegisterRepository
import rizkyfadilah.binar.synrgy6.android.challengechapter4.viewmodel.LoginViewModel
import rizkyfadilah.binar.synrgy6.android.challengechapter4.viewmodel.LoginViewModelFactory

class LoginFragment : Fragment() {

    private lateinit var loginViewModel: LoginViewModel
    private lateinit var binding: FragmentLoginBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentLoginBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val application = requireNotNull(this.activity).application
        val dao = RegisterDatabase.getInstance(application).registerDatabaseDao
        val repository = RegisterRepository(dao)
        val factory = LoginViewModelFactory(repository, application)

        loginViewModel = ViewModelProvider(this,factory).get(LoginViewModel::class.java)

        binding.myLoginViewModel = loginViewModel

        binding.lifecycleOwner = this

        loginViewModel.errorToast.observe(viewLifecycleOwner, { hasError ->
            if (hasError == true) {
                Toast.makeText(requireContext(), "Login Failed", Toast.LENGTH_SHORT)
                    .show()
                loginViewModel.donetoast()
            }
        })

        loginViewModel.navigateToHome.observe(viewLifecycleOwner, { hasFinished ->
            if (hasFinished == true) {
                Log.i("MYTAG", "insidi observe")
                navigateToHome()
                loginViewModel.doneNavigating()
                Toast.makeText(requireContext(), "Login Success", Toast.LENGTH_SHORT).show()
            }
        })

        loginViewModel.navigatetoRegister.observe(viewLifecycleOwner, { hasFinished ->
            if (hasFinished == true) {
                Log.i("MYTAG", "insidi observe")
                navigateToRegister()
                loginViewModel.doneNavigating()
            }
        })
    }

    private fun navigateToHome() {
        Log.i("MYTAG", "insidi navigate")
        NavHostFragment.findNavController(this).navigate(R.id.action_loginFragment_to_notesFragment)
    }
    private  fun navigateToRegister(){
        NavHostFragment.findNavController(this).navigate(R.id.action_loginFragment_to_registerFragment)
    }

}