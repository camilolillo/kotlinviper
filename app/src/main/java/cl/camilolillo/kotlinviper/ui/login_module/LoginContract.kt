package cl.camilolillo.kotlinviper.ui.login_module

interface LoginContract {

    interface  View {
        fun loginButtonIsEnabled(isEnabled: Boolean)
        fun progressBarIsVisible(isVisible: Boolean)
        fun registerButtonIsEnabled(isEnabled: Boolean)
        fun verifyButtonPressedIsVisible(isVisible: Boolean)
    }

    interface Interactor {
        fun login(email: String, password: String)
        fun checkEmailVerification()
        fun sendEmailVerification()
        fun getEmail()
        fun storeUser(email: String)
        fun removeStoredUser()
        fun signOut()
    }

    interface InteracctorOutput {
        fun onLoginSuccess()
        fun onLoginFailure(failureMessage: String)
        fun isEmailVerified(isVerified: Boolean)
        fun onGetUserEmailSuccess(email: String)
        fun onGetUserEmailFailure()
        fun onStoreUserSuccess()
        fun onStoreUserFailure()
        fun onSendEmailVerificationSuccess()
        fun onSendEmailVerificationFailure(failureMessage: String)
        fun onRemoveStoredUserSuccess()
        fun onRemoveStoredUserFailure()
        fun onSignOut()
    }

    interface Presenter {
        fun onCreate()
        fun onLoginButtonPressed(email: String?, password: String?)
        fun onRegisterButtonPressed()
        fun onVerifyButtonPressed()
        fun onSystemBackButtonPressed()
        fun onDestroy()
    }

    interface Router {
        fun intentRegister()
        fun displayAlert(title: String, message: String, bar: (() -> Unit)?)
        fun finish()
    }

}