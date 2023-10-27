package rizkyfadilah.binar.synrgy6.android.challengechapter4.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import rizkyfadilah.binar.synrgy6.android.challengechapter4.R
import rizkyfadilah.binar.synrgy6.android.challengechapter4.databinding.FragmentUserDetailsBinding
import rizkyfadilah.binar.synrgy6.android.challengechapter4.pref.SharedPref

class UserDetailsFragment : Fragment() {
    private lateinit var binding: FragmentUserDetailsBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentUserDetailsBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val sharedPref = SharedPref(requireContext())

        binding.btnLogout.setOnClickListener {
            sharedPref.clearData()
            findNavController().navigate(R.id.action_userDetailsFragment_to_loginFragment)
        }
    }

}