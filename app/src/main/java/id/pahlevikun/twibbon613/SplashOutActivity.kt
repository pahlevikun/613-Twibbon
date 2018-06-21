package id.pahlevikun.twibbon613

import android.os.Bundle
import android.os.Handler
import android.support.v7.app.AppCompatActivity

class SplashOutActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_out)
        Handler().postDelayed(object : Thread() {
            override fun run() {
                finish()
            }
        }, 3000L)
    }

    override fun onBackPressed() {

    }
}
