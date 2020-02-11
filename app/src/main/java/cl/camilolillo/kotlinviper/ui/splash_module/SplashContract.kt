package cl.camilolillo.kotlinviper.ui.splash_module

interface SplashContract {

    interface  View {
        fun showProgressBar()
        fun hideProgressBar()
    }
    interface Interactor {
        fun getStoredUser()
    }
    interface InteractorOutput {
        fun getStoredUserSuccess()
        fun getStoredUserFailure()
    }
    interface Presenter {
        fun onResume()
    }
    interface Router {
        fun intentLogin()
        fun intentDashboard()
    }

}