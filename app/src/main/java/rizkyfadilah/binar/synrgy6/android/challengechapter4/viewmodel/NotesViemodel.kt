package rizkyfadilah.binar.synrgy6.android.challengechapter4.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import rizkyfadilah.binar.synrgy6.android.challengechapter4.database.note_database.NoteDatabase
import rizkyfadilah.binar.synrgy6.android.challengechapter4.database.note_database.NoteEntity
import rizkyfadilah.binar.synrgy6.android.challengechapter4.repository.NoteRepository
import rizkyfadilah.binar.synrgy6.android.challengechapter4.repository.RegisterRepository

// TODO 1 : Create NotesViemodel class
class NotesViemodel (private val application: Application) : AndroidViewModel(application) {

    // TODO 2 : Create variable for repository and allNote
    val allNote : LiveData<List<NoteEntity>>
    val repository : NoteRepository

    // TODO 3 : Initialize repository and allNote
    init {
        val dao = NoteDatabase.getDatabase(application).getNoteDao()
        repository = NoteRepository(dao)
        allNote = repository.allNotes
    }

    // TODO 4 : Create function for insert, update, and delete
    fun deleteNote(note: NoteEntity) = viewModelScope.launch(Dispatchers.IO){
        repository.delete(note)
    }

    fun updateNote(note: NoteEntity) = viewModelScope.launch(Dispatchers.IO){
        repository.update(note)
    }

    fun insert(note: NoteEntity) = viewModelScope.launch(Dispatchers.IO){
        repository.insert(note)
    }
}