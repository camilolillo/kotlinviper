package cl.camilolillo.kotlinviper.ui.dashboard_module

import cl.camilolillo.kotlinviper.utils.Constants

class DashboardPresenter(activity: DashboardActivity):
    DashboardContract.Presenter,
    DashboardContract.InteractorOutput {

    //Presenter Contract
    private val view : DashboardContract.View = activity
    private val interactor : DashboardContract.Interactor = DashboardInteractor(this)
    private val router: DashboardContract.Router = DashboardRouter(activity)

    override fun onCreate() {
        interactor.getUser()
    }
    override fun onSignOutButtonPressed() {
        interactor.removeStoredUser()
    }
    override fun onSystemBackButtonPressed() {
        interactor?.removeStoredUser()
    }
    override fun onDestroy() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    //Interactor Output
    override fun onGetUserSuccess(email: String) {
        view.setEmail(email)
    }
    override fun onGetUserFailure(failureMessage: String) {
        router.displayAlert(
            Constants.errorTitle,
            failureMessage
        ) {
            interactor.removeStoredUser()
        }
    }
    override fun onRemoveStoredUserSuccess() {
        interactor.signOut()
    }
    override fun onRemoveStoredUserFailure(failureMessage: String) {
        router.displayAlert(
            Constants.errorTitle,
            failureMessage,
            null
        )
    }
    override fun onSignOut() {
        router.finish()
    }

}