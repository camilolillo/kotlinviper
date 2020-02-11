package cl.camilolillo.kotlinviper.ui.register_module

import cl.camilolillo.kotlinviper.dataManagers.FireBase.AuthDataManager

class RegisterInteractor(var interactorOutput: RegisterContract.InteractorOutput):
        RegisterContract.Interactor,
        AuthDataManager.Output {

    private var authDataManager = AuthDataManager(this)

    //Register interface
    override fun register(email: String, password: String) {
        authDataManager.Register(
            email,
            password
        )
    }
    override fun login(email: String, password: String) {
        authDataManager.Login(
            email,
            password
        )
    }
    override fun sendEmailVerification() {
        authDataManager.sendEmailVerification()
    }
    override fun signOut() {
        authDataManager.signOut()
    }

    //Interactor Output
    override fun onRegisterSuccess() {
        interactorOutput.onRegisterSuccess()
    }
    override fun onRegisterFailure(failureMessage: String) {
        interactorOutput.onRegisterFailure(failureMessage)
    }
    override fun onLoginSuccess() {
        interactorOutput.onLoginSuccess()
    }
    override fun onLoginFailure(failureMessage: String) {
        interactorOutput.onLoginFailure(failureMessage)
    }
    override fun onSendEmailVerificationSuccess() {
        interactorOutput.onSendEmailVerificationSuccess()
    }
    override fun onSendEmailVerificationFailure(failureMessage: String) {
        interactorOutput.onSendEmailVerificationFailure(failureMessage)
    }
    override fun onSignOut() {
        interactorOutput.onSignOut()
    }

}