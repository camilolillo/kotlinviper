package cl.camilolillo.kotlinviper.ui.splash_module

import cl.camilolillo.kotlinviper.dataManagers.Local.UserDataManager

class SplashInteractor(var interactorOutput: SplashPresenter):
    SplashContract.Interactor,
    UserDataManager.Output {

    private var userDataManager = UserDataManager(this)

    //Interactor contract
    override fun getStoredUser() {
        if (userDataManager.getStoredUser() != null) {
            interactorOutput?.getStoredUserSuccess()
        } else {
            interactorOutput?.getStoredUserFailure()
        }
    }

}