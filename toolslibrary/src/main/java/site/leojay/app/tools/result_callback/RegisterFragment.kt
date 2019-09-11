package site.leojay.app.tools.result_callback

import android.content.Intent
import androidx.collection.SparseArrayCompat
import androidx.fragment.app.Fragment

/**
 * 内部fragment，通过添加Fragment的形式对回调进行感知
 */
class RegisterFragment : Fragment() {
    /**
     * ActivityResult 的一个组
     */
    val servicesActivity =
        SparseArrayCompat<OnActivityResultCallBack>()
    /**
     *
     * 权限回调的一个组
     */
    val servicesPermission =
        SparseArrayCompat<OnPermissionsResultCallBack>()

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        servicesActivity.get(requestCode)?.result(resultCode, data)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        servicesPermission[requestCode]?.result(permissions, grantResults)
    }
}