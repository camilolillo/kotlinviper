package cl.camilolillo.kotlinviper.ui.login_module

import android.content.Intent
import cl.camilolillo.kotlinviper.ui.register_module.RegisterActivity
import cl.camilolillo.kotlinviper.utils.customAlert
import cl.camilolillo.kotlinviper.utils.functionCustomAlert

class LoginRouter(var activity: LoginActivity): LoginContract.Router {

    override fun intentRegister() {
        activity.startActivity(Intent(activity, RegisterActivity::class.java))
    }
    override fun displayAlert(title: String, message: String, bar: (() -> Unit)?) {
        if(bar != null) {
            activity.functionCustomAlert(
                title,
                message,
                bar!!
            )
        } else {
            activity.customAlert(
                title,
                message
            )
        }
    }
    override fun finish() {
        activity.finish()
    }

}