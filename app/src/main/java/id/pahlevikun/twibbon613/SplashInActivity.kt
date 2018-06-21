package id.pahlevikun.twibbon613

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.support.v7.app.AppCompatActivity
import id.voela.actrans.AcTrans

class SplashInActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_in)
        Handler().postDelayed(object : Thread() {
            override fun run() {
                val intent = Intent(this@SplashInActivity, MainActivity::class.java)
                startActivity(intent)
                AcTrans.Builder(this@SplashInActivity).performFade()
                finish()
            }
        }, 2500L)
    }

    override fun onBackPressed() {

    }
}
