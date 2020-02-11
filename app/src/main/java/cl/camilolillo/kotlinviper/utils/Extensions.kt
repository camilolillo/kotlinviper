package cl.camilolillo.kotlinviper.utils

import android.app.Activity
import android.app.AlertDialog

//Activity
fun Activity.buttonCustomAlert(title: String, message: String){
    val builder = AlertDialog.Builder(this)
    builder.setTitle(title)
    builder.setMessage(message)
    builder.setPositiveButton("OK"){dialog, which ->
        dialog.cancel()
    }
    val dialog : AlertDialog = builder.create()
    dialog.show()
}

fun Activity.functionCustomAlert(title: String, message: String, bar: () -> Unit){
    val builder = AlertDialog.Builder(this)
    builder.setTitle(title)
    builder.setMessage(message)
    builder.setPositiveButton("OK"){
            dialog,
            which ->
        bar()
        dialog.cancel()
    }
    val dialog : AlertDialog = builder.create()
    dialog.show()
}

fun Activity.customAlert(title: String, message: String){
    val builder = AlertDialog.Builder(this)
    builder.setTitle(title)
    builder.setMessage(message)
    val dialog : AlertDialog = builder.create()
    dialog.show()
}