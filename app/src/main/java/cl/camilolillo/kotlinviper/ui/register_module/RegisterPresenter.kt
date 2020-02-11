package cl.camilolillo.kotlinviper.ui.register_module

import cl.camilolillo.kotlinviper.utils.Constants
import cl.camilolillo.kotlinviper.utils.LoadingStatus
import kotlin.properties.Delegates

class RegisterPresenter(activity: RegisterActivity):
    RegisterContract.Presenter,
    RegisterContract.InteractorOutput {

    private inner class Credentials(
        val email: String? = null,
        val password: String? = null
    )

    private var viewStatus: LoadingStatus by Delegates.observable(
        LoadingStatus.loaded
    ) {
            _, _, new ->
        when (new) {
            LoadingStatus.loaded -> {
                view.progressBarIsVisible(false)
                view.backButtonIsEnabled(true)
                view.registerButtonIsEnabled(true)
            }
            LoadingStatus.loading -> {
                view.backButtonIsEnabled(false)
                view.registerButtonIsEnabled(false)
                view.progressBarIsVisible(true)
            }
        }
    }

    private var credentials: Credentials by Delegates.observable(
        Credentials("","")
    ) {
        _, _, new ->
        if(new.email != null && new.password != null) {
            interactor.register(
                new.email,
                new.password
            )
        }
    }

    //Presenter interface
    private val view : RegisterContract.View = activity
    private val interactor : RegisterContract.Interactor = RegisterInteractor(this)
    private val router: RegisterContract.Router = RegisterRouter(activity)

    override fun onCreate() {
        this.viewStatus = LoadingStatus.loaded
    }
    override fun onRegisterButtonPressed(email: String, password: String, verification: String) {
        if(email.isNotEmpty() && password.isNotEmpty() && verification.isNotEmpty()) {
            if(password == verification) {
                val credentials = Credentials(
                    email,
                    password
                )
                this.credentials = credentials
            } else {
                router.displayAlert(
                    Constants.errorTitle,
                    Constants.fieldsNoMatch,
                    null
                )
            }
        } else {
            this.viewStatus = LoadingStatus.loaded
            router.displayAlert(
                Constants.errorTitle,
                Constants.emptyFields,
                null
            )
        }
    }
    override fun onBackButtonPressed() {
        router.finish()
    }
    override fun onDestroy() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    //Interactor Output
    override fun onRegisterSuccess() {
        if(this.credentials.email != null && this.credentials.password != null) {
            interactor.login(
                this.credentials.email!!,
                this.credentials.password!!
            )
        } else {
            router.displayAlert(
                Constants.errorTitle,
                Constants.genericErrorMessage,
                null
            )
        }
    }
    override fun onRegisterFailure(failureMessage: String) {
        router.displayAlert(
            Constants.errorTitle,
            failureMessage,
            null
        )
    }
    override fun onLoginSuccess() {
        interactor?.sendEmailVerification()
    }
    override fun onLoginFailure(failureMessage: String) {
        router.displayAlert(
            Constants.errorTitle,
            Constants.genericErrorMessage
        ) {
            interactor.signOut()
        }
    }
    override fun onSendEmailVerificationSuccess() {
        router.displayAlert(
            Constants.successTitle,
            Constants.registerSuccessMessage
        ) {
            interactor.signOut()
        }
    }
    override fun onSendEmailVerificationFailure(failureMessage: String) {
        router.displayAlert(
            Constants.errorTitle,
            failureMessage
        ) {
            interactor.signOut()
        }
    }
    override fun onSignOut() {
        router.finish()
    }

}