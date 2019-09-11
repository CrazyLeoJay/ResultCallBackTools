package site.leojay.app.tools.result_callback

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.FragmentManager

/**
 * 结果回调工具
 * @param fm 通过 Activity#getSupportFragmentManager 和 Fragment#getChildFragmentManager
 */
class ResultCallBackTools(fm: FragmentManager) {
    // 监听使用的fragment
    private var fragment = RegisterFragment()

    init {
        // 防止重复创建
        when (val tag = fm.findFragmentByTag(FRAGMENT_TAG)) {
            // 添加Fragment
            null, !is RegisterFragment -> fm.beginTransaction()
                .add(
                    fragment,
                    FRAGMENT_TAG
                )
                // 保证立刻添加入FM
                .commitNow()
            else -> fragment = tag
        }
    }

    companion object {
        const val FRAGMENT_TAG = "ServiceRegister"
    }

    /**
     *  通过注册的形式添加回调
     *
     *  @see OnActivityResultCallBack
     *  @see OnPermissionsResultCallBack
     */
    fun registerStartActivityForResult(
        intent: Intent,
        requestCode: Int,
        options: Bundle? = null,
        service: Service
    ) {
        register(requestCode, service)
        fragment.startActivityForResult(intent, requestCode, options)
    }

    /**
     *  权限 通过注册的形式添加回调
     *
     *  @see OnActivityResultCallBack
     *  @see OnPermissionsResultCallBack
     */
    fun registerRequestPermissions(
        requestCode: Int,
        permissions: Array<String>,
        service: Service
    ) {
        register(requestCode, service)
        fragment.requestPermissions(permissions, requestCode)
    }

    private fun register(requestCode: Int, service: Service) {
        when (service) {
            is OnActivityResultCallBack -> fragment.servicesActivity.put(requestCode, service)
            is OnPermissionsResultCallBack -> fragment.servicesPermission.put(requestCode, service)
            else -> {
            }
        }
    }

    fun unRegisterActivityResult(key: Int) {
        fragment.servicesActivity.remove(key)
    }

    fun unRegisterPermission(key: Int) {
        fragment.servicesPermission.remove(key)
    }
}