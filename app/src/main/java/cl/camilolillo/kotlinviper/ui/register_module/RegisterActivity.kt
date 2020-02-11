package cl.camilolillo.kotlinviper.ui.register_module

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.view.isVisible
import cl.camilolillo.kotlinviper.R
import kotlinx.android.synthetic.main.activity_register.*

class RegisterActivity : AppCompatActivity(), RegisterContract.View {

    private lateinit var presenter: RegisterContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        presenterInit()
        configComponents()
    }

    private fun presenterInit() {
        presenter = RegisterPresenter(this)
        presenter.onCreate()
    }
    private fun configComponents() {
        loginButton.setOnClickListener {
            presenter.onBackButtonPressed()
        }
        registerButton.setOnClickListener {
            presenter.onRegisterButtonPressed(
                emailField.text.toString(),
                passwordField.text.toString(),
                confirmField.text.toString()
            )
        }
    }

    //View Contract
    override fun registerButtonIsEnabled(isEnabled: Boolean) {
        registerButton.isEnabled = isEnabled
    }
    override fun progressBarIsVisible(isVisible: Boolean) {
        progressBar.isVisible = isVisible
    }
    override fun backButtonIsEnabled(isEnabled: Boolean) {
        loginButton.isEnabled = isEnabled
    }

}
