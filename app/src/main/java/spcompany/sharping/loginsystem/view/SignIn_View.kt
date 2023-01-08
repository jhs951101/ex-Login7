package spcompany.sharping.loginsystem.view

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import org.json.JSONObject
import spcompany.sharping.loginsystem.R
import spcompany.sharping.loginsystem.controller.AlertController
import spcompany.sharping.loginsystem.controller.HttpController


class SignIn_View : AppCompatActivity() {

    private lateinit var usernameEtext: EditText
    private lateinit var passwordEtext: EditText

    private val alertController = AlertController(this)
    private val httpController = HttpController()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.signin_view)
        usernameEtext = findViewById(R.id.signin_eTxtUsername)
        passwordEtext = findViewById(R.id.signin_eTxtPassword)
    }

    fun backButtonPressed(view: View?) {
        super.onBackPressed()
    }

    fun signinButtonPressed(view: View?) {
        val username = usernameEtext.getText().toString()
        val password = passwordEtext.getText().toString()

        if (username.equals("")) {
            alertController.show("Error", "아이디를 입력하십시오.")
        } else if (password.equals("")) {
            alertController.show("Error", "비밀번호를 입력하십시오.")
        } else {
            val response = httpController.post(
                "https://tails1101.cafe24.com/test/signin_post_json.php",
                mapOf("username" to username, "password" to password)
            )

            if(response != null){
                val success = response.getBoolean("success")

                if(success) {
                    val name = response.getString ("name")

                    val intent = Intent(applicationContext, Info_View::class.java)
                    intent.putExtra("username", username)
                    intent.putExtra("name", name)
                    startActivity(intent)
                }
                else{
                    alertController.show("Error", "아이디 또는 비밀번호가 일치하지 않습니다.")
                }
            }
            else{
                alertController.show("Error", "통신 중 오류가 발생하였습니다.")
            }

        }
    }

    fun toSignupButtonPressed(view: View?) {
        alertController.show("Error", "죄송합니다. 아직 준비중입니다.")

        /*
        val intent = Intent(applicationContext, SignUp_View::class.java)
        startActivity(intent)
        */
    }
}
