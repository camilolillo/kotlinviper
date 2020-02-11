package cl.camilolillo.kotlinviper.ui.login_module

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.view.isVisible
import cl.camilolillo.kotlinviper.R
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity(), LoginContract.View {

    private lateinit var presenter: LoginContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        presenterInit()
        configComponents()
    }

    private fun presenterInit(){
        presenter = LoginPresenter(this)
        presenter.onCreate()
    }

    private fun configComponents() {
        loginButton.setOnClickListener {
            presenter.onLoginButtonPressed(
                emailField.text.toString(),
                passwordField.text.toString()
            )
        }
        registerButton.setOnClickListener {
            presenter.onRegisterButtonPressed()
        }
        verifyButton.setOnClickListener {
            presenter.onVerifyButtonPressed()
        }
    }

    override fun loginButtonIsEnabled(isEnabled: Boolean) {
        loginButton.isEnabled = isEnabled
    }
    override fun progressBarIsVisible(isVisible: Boolean) {
        loginProgressBar.isVisible = isVisible
    }
    override fun registerButtonIsEnabled(isEnabled: Boolean) {
        registerButton.isEnabled = isEnabled
    }
    override fun verifyButtonPressedIsVisible(isVisible: Boolean) {
        verifyButton.isVisible = isVisible
    }

    //Android back button
    override fun onBackPressed() {
        presenter.onSystemBackButtonPressed()
    }

}
