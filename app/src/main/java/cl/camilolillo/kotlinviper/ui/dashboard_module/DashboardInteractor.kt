package cl.camilolillo.kotlinviper.ui.dashboard_module

import cl.camilolillo.kotlinviper.dataManagers.FireBase.AuthDataManager
import cl.camilolillo.kotlinviper.dataManagers.Local.UserDataManager
import cl.camilolillo.kotlinviper.utils.Constants

class DashboardInteractor(var interactorOutput: DashboardContract.InteractorOutput):
    DashboardContract.Interactor,
    AuthDataManager.Output,
    UserDataManager.Output {

    private var authDataManager = AuthDataManager(this)
    private var userDataManager = UserDataManager(this)

    //Interactor Contract
    override fun getUser() {
        if(userDataManager.getStoredUser()?.email != null) {
            interactorOutput.onGetUserSuccess(userDataManager.getStoredUser()?.email!!)
        } else {
            interactorOutput.onGetUserFailure("DasboardInteractor: Couldn't get stored user.")
        }
    }
    override fun removeStoredUser() {
        userDataManager.removeUser()
    }
    override fun signOut() {
        authDataManager.signOut()
    }

    //Manager Outputs
    override fun onSignOut() {
        interactorOutput.onSignOut()
    }
    override fun onRemoveUserSuccess() {
        interactorOutput.onRemoveStoredUserSuccess()
    }
    override fun onRemoveUserFailure() {
        interactorOutput.onRemoveStoredUserFailure(Constants.genericErrorMessage)
    }

}