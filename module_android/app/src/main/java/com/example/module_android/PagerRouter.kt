package com.example.module_android

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.util.Log
import com.idlefish.flutterboost.containers.BoostFlutterActivity

/**
 * Create by neng.wang on 2020/11/20
 */

object PagerRouter {
    const val FLUTTER_FIRST_PAGE = "sample://first"
    const val FLUTTER_THIRD_PAGE = "sample://third"
    const val FLUTTER_DEFAULT_PAGE = "sample://default"

    //
    const val NATIVE_DEFAULT_PAGE = "sample://activity_a"


    val toFlutterPageName: Map<String, String> = mutableMapOf(
        Pair(FLUTTER_FIRST_PAGE, "first"),
        Pair(FLUTTER_THIRD_PAGE, "third"),
        Pair(FLUTTER_DEFAULT_PAGE, "default")

    )


    val toNativePageName: Map<String, Class<out Activity>> = mutableMapOf(
        Pair(NATIVE_DEFAULT_PAGE, MainAActivity::class.java)

    )
    const val NATIVE_PAGE_URL = "sample://nativePage"
    const val FLUTTER_PAGE_URL = "sample://flutterPage"
    const val FLUTTER_FRAGMENT_PAGE_URL = "sample://flutterFragmentPage"


    fun open(
        context: Context?,
        url: String?,
        urlParams: MutableMap<String, Any>?
    ): Boolean {
        val path = url!!.split("\\?").toTypedArray()[0]
        Log.i("openPageByUrl", path)
        return try {
            if (toFlutterPageName.containsKey(path)) {
                val intent = toFlutterPageName[path]?.let {
                    BoostFlutterActivity.withNewEngine()
                        .url(it)
                        .params(urlParams ?: mutableMapOf())
                        .backgroundMode(BoostFlutterActivity.BackgroundMode.opaque).build(context!!)
                }
                if (context is Activity) {
                    context.startActivityForResult(intent, 0)
                } else {
                    context?.startActivity(intent)
                }
                return true
            } else if(toNativePageName.containsKey(path)){
                val actyCls = toNativePageName[path]
                val intent = Intent(context,actyCls)
                if (context is Activity) {
                    context.startActivityForResult(intent, 0)
                } else {
                    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
                    context?.startActivity(intent)
                }
            }
            return false
        } catch (t: Throwable) {
            false
        }
    }
}