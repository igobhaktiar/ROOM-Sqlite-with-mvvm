package rizkyfadilah.binar.synrgy6.android.challengechapter4.database.note_database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

// Create Entity for Notes
@Entity(tableName = "notes")

// Create NoteEntity class
class NoteEntity(
    // Create title, description, and timestamp columns
    @ColumnInfo(name = "title") val title: String,
    @ColumnInfo(name = "description") val description: String,
) {
    // Create id column
    @PrimaryKey(autoGenerate = true) var id = 0
}