package cl.camilolillo.kotlinviper.ui.dashboard_module

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import cl.camilolillo.kotlinviper.R
import kotlinx.android.synthetic.main.activity_dashboard.*

class DashboardActivity :
    AppCompatActivity(),
    DashboardContract.View {

    private lateinit var presenter: DashboardContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)
        presenterInit()
        configComponents()
    }

    private fun presenterInit() {
        presenter = DashboardPresenter(this)
        presenter.onCreate()
    }
    private fun configComponents() {
        signOutButton.setOnClickListener {
            presenter.onSignOutButtonPressed()
        }
    }

    //View Contract
    override fun setEmail(email: String) {
        emailField.text = email
    }

    //Android back button
    override fun onBackPressed() {
        presenter.onSystemBackButtonPressed()
    }

}
