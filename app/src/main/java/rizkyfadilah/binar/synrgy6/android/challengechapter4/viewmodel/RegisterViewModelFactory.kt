package rizkyfadilah.binar.synrgy6.android.challengechapter4.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import rizkyfadilah.binar.synrgy6.android.challengechapter4.repository.RegisterRepository

class RegisterViewModelFactory(
    private val repository: RegisterRepository,
    private val application: Application,
) : ViewModelProvider.Factory{
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(RegisterViewModel::class.java)) {
            return RegisterViewModel(repository, application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}