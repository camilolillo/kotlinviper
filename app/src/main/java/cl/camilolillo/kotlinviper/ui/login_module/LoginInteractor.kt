package cl.camilolillo.kotlinviper.ui.login_module

import cl.camilolillo.kotlinviper.dataManagers.FireBase.AuthDataManager
import cl.camilolillo.kotlinviper.dataManagers.Local.UserDataManager

class LoginInteractor(var interactorOutput: LoginContract.InteracctorOutput) :
    LoginContract.Interactor,
    AuthDataManager.Output,
    UserDataManager.Output {

    private var authDataManager = AuthDataManager(this)
    private var userDataManager = UserDataManager(this)

    //Interactor
    override fun login(email: String, password: String) {
        authDataManager.Login(email, password)
    }
    override fun checkEmailVerification() {
        if(authDataManager.isEmailVerified() != null) {
            if(authDataManager.isEmailVerified()!!) {
                interactorOutput.isEmailVerified(true)
            } else {
                interactorOutput.isEmailVerified(false)
            }
        } else {
            interactorOutput.isEmailVerified(false)
        }
    }
    override fun sendEmailVerification() {
        authDataManager.sendEmailVerification()
    }
    override fun getEmail() {
        if(authDataManager.getUserEmail() != null){
            interactorOutput.onGetUserEmailSuccess(authDataManager.getUserEmail()!!)
        }else {
            interactorOutput.onGetUserEmailFailure()
        }
    }
    override fun storeUser(email: String) {
        userDataManager.storeUser(email)
    }
    override fun removeStoredUser() {
        userDataManager.removeUser()
    }
    override fun signOut() {
        authDataManager.signOut()
    }

    //Manager outputs
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
    override fun onStoreUserSuccess() {
        interactorOutput.onStoreUserSuccess()
    }
    override fun onStoreUserFailure() {
        interactorOutput.onStoreUserFailure()
    }
    override fun onRemoveUserSuccess() {
        interactorOutput.onRemoveStoredUserSuccess()
    }
    override fun onRemoveUserFailure() {
        interactorOutput.onRemoveStoredUserFailure()
    }
    override fun onSignOut() {
        interactorOutput.onSignOut()
    }

}