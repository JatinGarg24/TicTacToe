package com.aditya.tictactoe

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.WindowManager
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN)
        setContentView(R.layout.activity_main)

        var imageView: ImageView = findViewById(R.id.imageView)
        var textView: TextView = findViewById(R.id.textView)

        var topAnim = AnimationUtils.loadAnimation(this, R.anim.top_anim)
        var bottomAnim = AnimationUtils.loadAnimation(this, R.anim.bottom_anim)

        imageView.startAnimation(topAnim)
        textView.startAnimation(bottomAnim)

        Handler().postDelayed({
            val intent = Intent(this, Dashboard::class.java)
            startActivity(intent)
            finish()
        }, 3600)

    }
}
