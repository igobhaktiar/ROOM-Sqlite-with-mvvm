package rizkyfadilah.binar.synrgy6.android.challengechapter4.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "register_users_table")
data class RegisterEntity(

    @PrimaryKey(autoGenerate = true)
    var userId: Int = 0,

    @ColumnInfo(name = "user_name")
    var userName: String,

    @ColumnInfo(name = "user_email")
    var userEmail: String,

    @ColumnInfo(name = "user_password")
    var userPassword: String,

    @ColumnInfo(name = "user_confirm_password")
    var userConfirmPassword: String,

    )