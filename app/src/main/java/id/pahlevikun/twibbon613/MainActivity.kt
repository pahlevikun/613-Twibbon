package id.pahlevikun.twibbon613

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.os.Bundle
import android.os.Environment
import android.os.Handler
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.bumptech.glide.Glide
import com.esafirm.imagepicker.features.ImagePicker
import com.esafirm.imagepicker.features.ReturnMode
import id.voela.actrans.AcTrans
import kotlinx.android.synthetic.main.activity_main.*
import permissions.dispatcher.NeedsPermission
import permissions.dispatcher.RuntimePermissions
import java.io.File
import java.io.FileNotFoundException
import java.io.FileOutputStream
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*


@RuntimePermissions
@SuppressLint("SimpleDateFormat")
class MainActivity : AppCompatActivity() {

    private var doubleBackToExitPressedOnce = false
    private var formatter: SimpleDateFormat? = null
    private var imagePicker: ImagePicker? = null
    private var isImageSelected = false
    private var output: FileOutputStream? = null
    private var dirPath = ""
    private var fileName = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        init()

        buttonSelect.setOnClickListener {
            showPicker()
        }


        buttonSave.setOnClickListener {
            saveImage()
        }
    }

    private fun init() {
        formatter = SimpleDateFormat("ddMMyyyy_HHmmss")
        imagePicker = ImagePicker.create(this)
                .returnMode(ReturnMode.ALL)
                .toolbarImageTitle("Tap to select")
                .toolbarArrowColor(Color.WHITE)
                .single()
                .showCamera(true)
                .enableLog(false)
    }

    @NeedsPermission(android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
    private fun saveImage() {
        try {
            if (isImageSelected) {
                val date = Date()
                dirPath = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES).toString() +
                        "/" + getString(R.string.app_name) + "/"
                val newDirectory = File(dirPath)
                newDirectory.mkdirs()

                fileName = "twb_img_${formatter!!.format(date)}.png"

                output = FileOutputStream("$dirPath$fileName")
                viewToBitmap(frameLayout).compress(Bitmap.CompressFormat.PNG, 90, output)
                output!!.close()
            } else {
                Snackbar.make(coordinatorLayout, getString(R.string.snackbar_main_pleasecapture), Snackbar.LENGTH_SHORT).show()
            }
        } catch (e: FileNotFoundException) {
            e.printStackTrace()
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    @NeedsPermission(android.Manifest.permission.CAMERA)
    private fun showPicker() {
        imagePicker!!.start()
    }

    private fun viewToBitmap(view: View): Bitmap {
        val bitmap = Bitmap.createBitmap(view.width, view.height, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(bitmap)
        view.draw(canvas)
        return bitmap
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (ImagePicker.shouldHandle(requestCode, resultCode, data)) {
            val image = ImagePicker.getFirstImageOrNull(data)
            if (image != null) {
                isImageSelected = true
                Glide.with(this).load(image.path).into(imageViewUser)
                Snackbar.make(coordinatorLayout, getString(R.string.snackbar_main_success), Snackbar.LENGTH_SHORT).show()
            } else {
                Snackbar.make(coordinatorLayout, getString(R.string.snackbar_main_pleasecapture), Snackbar.LENGTH_SHORT).show()
            }
        }
        super.onActivityResult(requestCode, resultCode, data)
    }

    override fun onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            val intent = Intent(this@MainActivity,SplashOutActivity::class.java)
            startActivity(intent)
            AcTrans.Builder(this).performFade()
            finish()
        }
        this.doubleBackToExitPressedOnce = true
        val snackBar = Snackbar.make(coordinatorLayout, getString(R.string.snackbar_double_click), Snackbar.LENGTH_SHORT)
        snackBar.show()
        Handler().postDelayed({ doubleBackToExitPressedOnce = false }, 2000)

    }
}
