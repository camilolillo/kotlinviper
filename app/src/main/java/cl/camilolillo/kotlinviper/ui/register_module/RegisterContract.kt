package cl.camilolillo.kotlinviper.ui.register_module

interface RegisterContract {

    interface View {
        fun registerButtonIsEnabled(isEnabled: Boolean)
        fun progressBarIsVisible(isVisible: Boolean)
        fun backButtonIsEnabled(isEnabled: Boolean)
    }

    interface Interactor {
        fun register(email: String, password: String)
        fun login(email: String, password: String)
        fun sendEmailVerification()
        fun signOut()
    }

    interface InteractorOutput {
        fun onRegisterSuccess()
        fun onRegisterFailure(failureMessage: String)
        fun onLoginSuccess()
        fun onLoginFailure(failureMessage: String)
        fun onSendEmailVerificationSuccess()
        fun onSendEmailVerificationFailure(failureMessage: String)
        fun onSignOut()
    }

    interface Presenter {
        fun onCreate()
        fun onRegisterButtonPressed(email: String, password: String, verification: String)
        fun onBackButtonPressed()
        fun onDestroy()
    }

    interface Router {
        fun finish()
        fun displayAlert(title: String, message: String, bar: (() -> Unit)?)
    }

}