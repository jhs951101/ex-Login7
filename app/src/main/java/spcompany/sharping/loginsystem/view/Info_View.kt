package spcompany.sharping.loginsystem.view

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import spcompany.sharping.loginsystem.R


class Info_View : AppCompatActivity() {

    private lateinit var userID: String
    private lateinit var userName: String
    private lateinit var tInfo: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.info_view)
        tInfo = findViewById<View>(R.id.info_tInfo) as TextView

        val intent = intent
        userID = intent.getStringExtra("username")!!
        userName = intent.getStringExtra("name")!!
        setProfile(userID, userName)
    }

    private fun setProfile(username: String, name: String) {
        tInfo.setText(name + "님(" + username + ") 환영합니다!")
    }

    fun backButtonPressed(view: View?) {
        super.onBackPressed()
    }
}