package rizkyfadilah.binar.synrgy6.android.challengechapter4.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import rizkyfadilah.binar.synrgy6.android.challengechapter4.R
import rizkyfadilah.binar.synrgy6.android.challengechapter4.databinding.FragmentSplashBinding
import rizkyfadilah.binar.synrgy6.android.challengechapter4.pref.SharedPref

class SplashFragment : Fragment() {

    private lateinit var binding: FragmentSplashBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentSplashBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val sharedPref = SharedPref(requireContext())
        val isLogin = sharedPref.checkLogin()
        if (isLogin){
            findNavController().navigate(R.id.action_splashFragment_to_userDetailsFragment)
        } else{
            findNavController().navigate(R.id.action_splashFragment_to_loginFragment)
        }
    }
}