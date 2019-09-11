package site.leojay.tools

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.imageURI
import org.jetbrains.anko.sdk27.coroutines.onClick
import org.jetbrains.anko.toast
import site.leojay.app.tools.result_callback.OnActivityResultCallBack
import site.leojay.app.tools.result_callback.OnPermissionsResultCallBack
import site.leojay.app.tools.result_callback.ResultCallBackTools


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        button.onClick {
            ResultCallBackTools(supportFragmentManager).registerStartActivityForResult(
                Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI),
                123,
                service = object : OnActivityResultCallBack {
                    override fun result(resultCode: Int, data: Intent?) {
                        when {
                            resultCode == Activity.RESULT_OK && data != null -> {
                                val uri = data.data!!
                                imageView.imageURI = uri
                                toast(uri.toString())
                            }
                        }
                    }
                })

            ResultCallBackTools(supportFragmentManager).registerRequestPermissions(
                123,
                arrayOf(Manifest.permission.CAMERA),
                object : OnPermissionsResultCallBack {
                    override fun result(permissions: Array<out String>, grantResults: IntArray) {

                    }
                })
        }
    }

}

class HomeFragment : Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ResultCallBackTools(childFragmentManager).registerStartActivityForResult(
            Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI),
            123,
            service = object : OnActivityResultCallBack {
                override fun result(resultCode: Int, data: Intent?) {
                    when {
                        resultCode == Activity.RESULT_OK && data != null -> {
                            val uri = data.data!!
                            imageView.imageURI = uri
                        }
                    }
                }
            })
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.activity_main, container, false)
    }
}
