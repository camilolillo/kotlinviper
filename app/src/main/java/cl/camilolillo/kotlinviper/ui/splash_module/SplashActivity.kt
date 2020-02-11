package cl.camilolillo.kotlinviper.ui.splash_module

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import cl.camilolillo.kotlinviper.R
import kotlinx.android.synthetic.main.activity_splash.*

class SplashActivity : AppCompatActivity(), SplashContract.View {

    private lateinit var presenter: SplashContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        presenter = SplashPresenter(this)
    }

    override fun onResume() {
        super.onResume()
        presenter.onResume()
    }

    //View contract
    override fun showProgressBar() {
        splashProgressBar.visibility = View.VISIBLE
    }
    override fun hideProgressBar() {
        splashProgressBar.visibility = View.GONE
    }

}
