package cl.camilolillo.kotlinviper.ui.splash_module

import android.content.Intent
import cl.camilolillo.kotlinviper.ui.dashboard_module.DashboardActivity
import cl.camilolillo.kotlinviper.ui.login_module.LoginActivity

class SplashRouter(var activity: SplashActivity): SplashContract.Router {

    //Router contract
    override fun intentLogin() {
        activity.startActivity(Intent(activity, LoginActivity::class.java))
    }
    override fun intentDashboard() {
        activity.startActivity(Intent(activity, DashboardActivity::class.java))
    }

}