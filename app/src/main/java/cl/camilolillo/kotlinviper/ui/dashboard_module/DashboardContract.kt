package cl.camilolillo.kotlinviper.ui.dashboard_module

interface DashboardContract {

    interface View {
        fun setEmail(email: String)
    }

    interface Interactor {
        fun getUser()
        fun removeStoredUser()
        fun signOut()
    }

    interface InteractorOutput {
        fun onGetUserSuccess(email: String)
        fun onGetUserFailure(failureMessage: String)
        fun onRemoveStoredUserSuccess()
        fun onRemoveStoredUserFailure(failureMessage: String)
        fun onSignOut()
    }

    interface Presenter {
        fun onCreate()
        fun onSignOutButtonPressed()
        fun onSystemBackButtonPressed()
        fun onDestroy()
    }

    interface Router {
        fun displayAlert(title: String, message: String, bar: (() -> Unit)?)
        fun finish()
    }

}