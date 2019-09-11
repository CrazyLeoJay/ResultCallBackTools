package site.leojay.app.tools.result_callback

import android.content.Intent

interface OnActivityResultCallBack : Service {
    fun result(resultCode: Int, data: Intent?)
}