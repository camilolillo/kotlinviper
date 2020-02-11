package cl.camilolillo.kotlinviper.ui.register_module

import cl.camilolillo.kotlinviper.utils.customAlert
import cl.camilolillo.kotlinviper.utils.functionCustomAlert

class RegisterRouter(var activity: RegisterActivity) : RegisterContract.Router {

    override fun finish() {
        activity.finish()
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

}