package spcompany.sharping.loginsystem.controller

import android.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity

class AlertController(val activity: AppCompatActivity) {

    fun show(title: String, message: String){
        val builder = AlertDialog.Builder(activity)
        builder.setPositiveButton("확인"){
                dialogInterface, i ->
        }

        builder.setCancelable(false)
        builder.setTitle(title)
        builder.setMessage(message)
        builder.show()
    }
}