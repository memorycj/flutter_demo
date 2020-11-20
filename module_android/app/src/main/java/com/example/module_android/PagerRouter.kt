package com.example.module_android

import android.app.Activity
import android.content.Context
import android.util.Log
import com.idlefish.flutterboost.containers.BoostFlutterActivity

/**
 * Create by neng.wang on 2020/11/20
 */

object PagerRouter {
    const val FLUTTER_FIRST_PAGE = "sample://first"
    const val FLUTTER_THIRD_PAGE = "sample://third"


    val pageName: Map<String, String> = mutableMapOf(
        Pair(FLUTTER_FIRST_PAGE,"first"),
        Pair(FLUTTER_THIRD_PAGE,"third")

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
            if (pageName.containsKey(path)) {
                val intent = pageName[path]?.let {
                    BoostFlutterActivity.withNewEngine()
                        .url(it)
                        .params(urlParams?: mutableMapOf())
                        .backgroundMode(BoostFlutterActivity.BackgroundMode.opaque).build(context!!)
                }
                if (context is Activity) {
                    context.startActivityForResult(intent, 0)
                } else {
                    context?.startActivity(intent)
                }
                return true
            }
            return false
        } catch (t: Throwable) {
            false
        }
    }
}