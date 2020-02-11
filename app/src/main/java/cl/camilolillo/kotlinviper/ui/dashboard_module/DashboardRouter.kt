package cl.camilolillo.kotlinviper.ui.dashboard_module

import cl.camilolillo.kotlinviper.utils.customAlert
import cl.camilolillo.kotlinviper.utils.functionCustomAlert

class DashboardRouter(var activity: DashboardActivity): DashboardContract.Router {

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