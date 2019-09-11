### ResultCallBackTools

[![](https://jitpack.io/v/CrazyLeoJay/ResultCallBackTools.svg)](https://jitpack.io/#CrazyLeoJay/ResultCallBackTools)



导入

```groovy
implementation 'com.github.CrazyLeoJay:ResultCallBackTools:0.0.3-dev'
```



回调监听，简化`startActivityForResult`和`requestPermissions`方法回调。

正常使用：

```kotlin
// Activity Star
fragment.startActivityForResult(intent, requestCode, options)
activity.startActivityForResult(intent, requestCode, options)

// fragment 
 override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        // 处理
}



// Request
fragment.requestPermissions(permissions, requestCode)
activity.requestPermissions(permissions, requestCode)

override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        // 权限请求
}
```



使用工具后：

```kotlin
// 获取一个FragmentManager
ResultCallBackTools(supportFragmentManager).registerStartActivityForResult(
    // 相册选择
    Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI),
    // 请求码
    123,
    // 回调
    service = object : OnActivityResultCallBack {
        override fun result(resultCode: Int, data: Intent?) {
            when {
                resultCode == Activity.RESULT_OK && data != null -> {
                    // 处理结果
                    val uri = data.data!! 
                    toast(uri.toString())
                }
            }
        }
    })

// 请求权限
ResultCallBackTools(supportFragmentManager).registerRequestPermissions(
    123,
    arrayOf(Manifest.permission.CAMERA),
    object : OnPermissionsResultCallBack {
        override fun result(permissions: Array<out String>, grantResults: IntArray) {
        
        }
    })
```



