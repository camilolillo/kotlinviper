package cl.camilolillo.kotlinviper.ui.splash_module

class SplashPresenter(activity: SplashActivity):
    SplashContract.Presenter,
    SplashContract.InteractorOutput {

    private val view: SplashContract.View = activity
    private var interactor: SplashContract.Interactor = SplashInteractor(this)
    private var router: SplashContract.Router = SplashRouter(activity)

    //MARK:- Presenter contract
    override fun onResume() {
        view.showProgressBar()
        interactor.getStoredUser()
    }

    //MARK:- Interactor Output
    override fun getStoredUserSuccess() {
        view.hideProgressBar()
        router.intentDashboard()
    }
    override fun getStoredUserFailure() {
        view.hideProgressBar()
        router.intentLogin()
    }

}