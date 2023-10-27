package rizkyfadilah.binar.synrgy6.android.challengechapter4.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import rizkyfadilah.binar.synrgy6.android.challengechapter4.repository.NoteRepository

class NotesFactory(
    private val application: Application,
) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(NotesViemodel::class.java)) {
            return NotesViemodel(application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}