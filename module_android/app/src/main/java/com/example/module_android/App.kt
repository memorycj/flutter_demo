package com.example.module_android

import android.app.Application
import android.content.Context
import android.os.Build
import android.util.Log
import com.idlefish.flutterboost.FlutterBoost
import com.idlefish.flutterboost.FlutterBoost.BoostLifecycleListener
import com.idlefish.flutterboost.interfaces.INativeRouter
import io.flutter.BuildConfig
import io.flutter.embedding.android.FlutterView
import io.flutter.plugin.common.MethodCall
import io.flutter.plugin.common.MethodChannel

/**
 * Create by neng.wang on 2020/11/20
 */

class App : Application() {

    val TAG = "Flutte_BOOST"

    override fun onCreate() {
        super.onCreate()
        val boostLifecycleListener: BoostLifecycleListener = object : BoostLifecycleListener {
            override fun beforeCreateEngine() {
                Log.d(
                    TAG,
                    "beforeCreateEngine"
                )
            }

            override fun onEngineCreated() {
                Log.d(
                    TAG,
                    "onEngineCreated"
                )
                // 注册MethodChannel，监听flutter侧的getPlatformVersion调用
                val methodChannel = MethodChannel(
                    FlutterBoost.instance().engineProvider().dartExecutor,
                    "flutter_native_channel"
                )
                methodChannel.setMethodCallHandler { call: MethodCall, result: MethodChannel.Result ->
                    if (call.method == "getPlatformVersion") {
                        result.success(Build.VERSION.RELEASE)
                    } else {
                        result.notImplemented()
                    }
                }

            }

            override fun onPluginsRegistered() {
                Log.d(
                    TAG,
                    "onPluginsRegistered"
                )
            }

            override fun onEngineDestroy() {
                Log.d(
                    TAG,
                    "onEngineDestroy"
                )
            }
        }

        //初始化flutter
        FlutterBoost.instance().init(
            FlutterBoost.ConfigBuilder(this, object : INativeRouter {
                override fun openContainer(
                    context: Context?,
                    url: String?,
                    urlParams: MutableMap<String, Any>?,
                    requestCode: Int,
                    exts: MutableMap<String, Any>?
                ) {
                    PagerRouter.open(context, url, urlParams)
                }

            })
                .isDebug(BuildConfig.DEBUG)
                .whenEngineStart(FlutterBoost.ConfigBuilder.ANY_ACTIVITY_CREATED)
                .renderMode(FlutterView.RenderMode.texture)
                .lifecycleListener(boostLifecycleListener)
                .build()
        )

    }
}