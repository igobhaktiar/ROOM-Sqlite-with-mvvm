package rizkyfadilah.binar.synrgy6.android.challengechapter4.pref

import android.content.Context

class SharedPref (context: Context) {
    private val preference = context.getSharedPreferences("MYPREF", Context.MODE_PRIVATE)

    fun saveUser(username: String, email: String){
        val editor = preference.edit()
        editor.putString("USERNAME", username)
        editor.putString("EMAIL", email)
        editor.putBoolean("ISLOGIN", true)
        editor.apply()
    }

    fun checkLogin(): Boolean{
        return preference.getBoolean("ISLOGIN", false)
    }

    fun clearData(){
        val editor = preference.edit()
        editor.clear()
        editor.apply()
    }

    fun getUsername(): String?{
        return preference.getString("USERNAME", null)
    }

    fun getEmail(): String?{
        return preference.getString("EMAIL", null)
    }

}
