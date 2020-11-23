package com.example.module_android

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class MainAActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun openFirstPage(view: View) {
        PagerRouter.open(this,PagerRouter.FLUTTER_FIRST_PAGE,null)
    }
    fun openThirdPage(view: View) {

        PagerRouter.open(this,PagerRouter.FLUTTER_THIRD_PAGE,null)
    }

    fun openDefaultPage(view: View) {

        PagerRouter.open(this,PagerRouter.FLUTTER_DEFAULT_PAGE,null)
    }
}