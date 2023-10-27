package rizkyfadilah.binar.synrgy6.android.challengechapter4.repository

import androidx.lifecycle.LiveData
import rizkyfadilah.binar.synrgy6.android.challengechapter4.database.note_database.NoteEntity
import rizkyfadilah.binar.synrgy6.android.challengechapter4.database.note_database.NotesDao

class NoteRepository(private val noteDao: NotesDao) {

    // TODO 11 : Create function to insert, delete, and update note

    val allNotes: LiveData<List<NoteEntity>> = noteDao.getAllNotes()
    suspend fun insert(note: NoteEntity) {
        noteDao.insert(note)
    }

    suspend fun delete(note: NoteEntity) {
        noteDao.delete(note)
    }

    suspend fun update(note: NoteEntity) {
        noteDao.update(note)
    }

    suspend fun getNoteById(noteId: Int): NoteEntity {
        return noteDao.getNoteById(noteId)
    }
}