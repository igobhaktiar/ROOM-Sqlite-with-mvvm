package rizkyfadilah.binar.synrgy6.android.challengechapter4.viewmodel

import android.app.Application
import androidx.databinding.Observable
import android.util.Log
import androidx.databinding.Bindable
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import rizkyfadilah.binar.synrgy6.android.challengechapter4.database.resgister_database.RegisterEntity
import rizkyfadilah.binar.synrgy6.android.challengechapter4.repository.RegisterRepository


class RegisterViewModel(private val repository: RegisterRepository, application: Application) :
    AndroidViewModel(application), Observable {

    @Bindable
    val userName = MutableLiveData<String>()

    @Bindable
    val userEmail = MutableLiveData<String>()

    @Bindable
    val userPassword = MutableLiveData<String>()

    @Bindable
    val userConfirmPassword = MutableLiveData<String>()

    private val viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    private val _navigateto = MutableLiveData<Boolean>()

    val navigateto: LiveData<Boolean>
        get() = _navigateto

    private val _errorToast = MutableLiveData<Boolean>()

    val errorToast: LiveData<Boolean>
        get() = _errorToast

    private val _errorToastUsername = MutableLiveData<Boolean>()


    fun submitButton() {
        if (userName.value.isNullOrEmpty() || userEmail.value.isNullOrEmpty() || userPassword.value.isNullOrEmpty() || userConfirmPassword.value.isNullOrEmpty() || userPassword.value != userConfirmPassword.value) {
            _errorToast.value = true
        } else {
            uiScope.launch {
                val userNames = repository.getUsername(userName.value!!)
                if (userNames != null) {
                    _errorToastUsername.value = true
                } else {
                    val userNames = userName.value!!
                    val userEmails = userEmail.value!!
                    val userPasswords = userPassword.value!!
                    val userConfirmPasswords = userConfirmPassword.value!!
                    insert(RegisterEntity(0, userNames,userEmails,userPasswords,userConfirmPasswords))
                    userNames.isEmpty()
                    userEmails.isEmpty()
                    userPasswords.isEmpty()
                    userConfirmPasswords.isEmpty()
                    _navigateto.value = true
                }
            }
        }
    }

    fun donetoast() {
        _errorToast.value = false
        Log.i("MYTAG", "Done taoasting ")
    }
    private fun insert(user: RegisterEntity): Job = viewModelScope.launch {
        repository.insert(user)
    }


    override fun addOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {
    }

    override fun removeOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {
    }

}