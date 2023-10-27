package rizkyfadilah.binar.synrgy6.android.challengechapter4.viewmodel

import android.app.Application
import android.util.Log
import androidx.databinding.Bindable
import androidx.databinding.Observable
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import rizkyfadilah.binar.synrgy6.android.challengechapter4.pref.SharedPref
import rizkyfadilah.binar.synrgy6.android.challengechapter4.repository.RegisterRepository

class LoginViewModel(private val repository: RegisterRepository, private val application: Application) : AndroidViewModel(application), Observable {


    @Bindable
    val inputUsername = MutableLiveData<String>()

    @Bindable
    val inputPassword = MutableLiveData<String>()

    private val viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    private val _navigatetoRegister = MutableLiveData<Boolean>()
    val navigatetoRegister: LiveData<Boolean>
        get() = _navigatetoRegister

    private val _navigateToHome = MutableLiveData<Boolean>()
    val navigateToHome: LiveData<Boolean>
        get() = _navigateToHome

    private val _errorToast = MutableLiveData<Boolean>()
    val errorToast: LiveData<Boolean>
        get() = _errorToast

    //Function triggered When the Login Button is Clicked, Via Binding.
    fun loginBUtton() {

        if (inputUsername.value.isNullOrEmpty() || inputPassword.value.isNullOrEmpty()) {
            //Show Toast
            _errorToast.value = true
        } else {
            uiScope.launch {
                val userNames = repository.getUsername(inputUsername.value!!)
                if (userNames != null){
                    if (userNames.userPassword == inputPassword.value!!){
                        _navigateToHome.value = true
                        val sharedPref = SharedPref(application)
                        sharedPref.saveUser(userNames.userName, userNames.userEmail)
                    }else{
                        //Show Toast
                        _errorToast.value = true
                    }
                }
            }
        }
    }

    //navigate to the Register Fragment
    fun doneNavigating() {
        _navigateToHome.value = false
    }
    fun signUP() {
        _navigatetoRegister.value = true
    }

    fun donetoast() {
        _errorToast.value = false
        Log.i("MYTAG", "Done taoasting ")
    }


    override fun removeOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {}

    override fun addOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {}
}