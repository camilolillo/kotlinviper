package cl.camilolillo.kotlinviper.ui.login_module

import cl.camilolillo.kotlinviper.utils.Constants
import cl.camilolillo.kotlinviper.utils.LoadingStatus
import kotlin.properties.Delegates

class LoginPresenter(activity: LoginActivity) :
    LoginContract.Presenter,
    LoginContract.InteracctorOutput {

    private var viewStatus: LoadingStatus by Delegates.observable(
        LoadingStatus.loaded) {
            _, _, new ->
        when (new) {
            LoadingStatus.loaded -> {
                view.verifyButtonPressedIsVisible(false)
                view.loginButtonIsEnabled(true)
                view.registerButtonIsEnabled(true)
                view.progressBarIsVisible(false)
            }
            LoadingStatus.loading -> {
                view.verifyButtonPressedIsVisible(false)
                view.loginButtonIsEnabled(false)
                view.registerButtonIsEnabled(false)
                view.progressBarIsVisible(true)
            }
            LoadingStatus.emailNotVerified -> {
                view.loginButtonIsEnabled(false)
                view.registerButtonIsEnabled(false)
                view.progressBarIsVisible(false)
                view.verifyButtonPressedIsVisible(true)
            }
        }
    }

    //Presenter protocol
    private val view : LoginContract.View = activity
    private val interactor : LoginContract.Interactor = LoginInteractor(this)
    private val router: LoginContract.Router = LoginRouter(activity)

    override fun onLoginButtonPressed(email: String?, password: String?) {
        if(email?.length != 0 && password?.length != 0) {
            this.viewStatus = LoadingStatus.loading
            interactor.login(
                email!!,
                password!!
            )
        } else {
            router.displayAlert(
                Constants.errorTitle,
                Constants.emptyFields,
                null
            )
        }
    }
    override fun onRegisterButtonPressed() {
        router.intentRegister()
    }
    override fun onVerifyButtonPressed() {
        this.viewStatus = LoadingStatus.loading
        interactor.sendEmailVerification()
    }
    override fun onSystemBackButtonPressed() {
        router.finish()
    }
    override fun onDestroy() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    //Interactor Output
    override fun onLoginSuccess() {
        interactor.checkEmailVerification()
    }
    override fun onLoginFailure(failureMessage: String) {
        this.viewStatus = LoadingStatus.loaded
        router.displayAlert(
            Constants.errorTitle,
            failureMessage,
            null
        )
    }
    override fun isEmailVerified(isVerified: Boolean) {
        if(isVerified){
            interactor.getEmail()
        } else {
            this.viewStatus = LoadingStatus.emailNotVerified
            router.displayAlert(
                Constants.errorTitle,
                Constants.emailNotVerified,
                null
            )
        }
    }
    override fun onGetUserEmailSuccess(email: String) {
        interactor.storeUser(email)
    }
    override fun onGetUserEmailFailure() {
        this.viewStatus = LoadingStatus.loaded
        router.displayAlert(
            Constants.errorTitle,
            Constants.genericErrorMessage
        ) {
            interactor.signOut()
        }
    }
    override fun onStoreUserSuccess() {
        this.viewStatus = LoadingStatus.loaded
        router.finish()
    }
    override fun onStoreUserFailure() {
        this.viewStatus = LoadingStatus.loaded
        router.displayAlert(
            Constants.errorTitle,
            Constants.genericErrorMessage
        ) {
            interactor.signOut()
        }
    }
    override fun onSendEmailVerificationSuccess() {
        this.viewStatus = LoadingStatus.loaded
        router.displayAlert(
            Constants.successTitle,
            Constants.registerSuccessMessage
        ) {
            interactor.signOut()
        }
    }
    override fun onSendEmailVerificationFailure(failureMessage: String) {
        this.viewStatus = LoadingStatus.loaded
        router.displayAlert(
            Constants.errorTitle,
            Constants.genericErrorMessage
        ) {
            interactor.signOut()
        }
    }
    override fun onRemoveStoredUserSuccess() {
        interactor.signOut()
    }
    override fun onRemoveStoredUserFailure() {
        this.viewStatus = LoadingStatus.loaded
        router.displayAlert(
            Constants.errorTitle,
            Constants.genericErrorMessage,
            null
        )
    }
    override fun onSignOut() {
        this.viewStatus = LoadingStatus.loaded
        router.finish()
    }
    override fun onCreate() {
        this.viewStatus = LoadingStatus.loaded
    }

}