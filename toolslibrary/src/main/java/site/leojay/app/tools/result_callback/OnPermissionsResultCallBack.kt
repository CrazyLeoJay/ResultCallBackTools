package site.leojay.app.tools.result_callback

interface OnPermissionsResultCallBack : Service {
    fun result(permissions: Array<out String>, grantResults: IntArray)
}