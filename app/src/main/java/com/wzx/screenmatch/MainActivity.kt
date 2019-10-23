package com.wzx.screenmatch

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.DisplayMetrics
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val displayMetrics = DisplayMetrics()
        windowManager.defaultDisplay.getMetrics(displayMetrics)

        textView.text =
            "device :" +
                    "\n    width = ${displayMetrics.widthPixels}px" +
                    "\n    height = ${displayMetrics.heightPixels}px" +
                    "\n    density = ${displayMetrics.density}" +
                    "\n    dpi = ${displayMetrics.densityDpi} " +
                    "\n    smallestWidth = ${displayMetrics.widthPixels / displayMetrics.density}dp"

        imageView.viewTreeObserver.addOnPreDrawListener {
            imageInfo.text = "image size: width is ${imageView.width}px height is ${imageView.height}px"
            return@addOnPreDrawListener true
        }
    }
}
