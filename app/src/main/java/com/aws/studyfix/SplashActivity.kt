package com.aws.studyfix

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        val logoImage = findViewById<ImageView>(R.id.logoImage)
        val sloganText = findViewById<TextView>(R.id.sloganText)

        val logoAnim = AnimationUtils.loadAnimation(this, R.anim.move_up)
        val textAnim = AnimationUtils.loadAnimation(this, R.anim.slide_in_left)

        logoImage.startAnimation(logoAnim)

        // نظهر النص بعد تأخير بسيط
        Handler(Looper.getMainLooper()).postDelayed({
            sloganText.visibility = View.VISIBLE
            sloganText.startAnimation(textAnim)

            // ننتقل لـ MainActivity بعد انتهاء الأنيميشن
            Handler(Looper.getMainLooper()).postDelayed({
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish() // حتى ما يرجع لـ Splash لما يضغط رجوع
            }, 2000) // وقت كافي لعرض النص قبل الانتقال

        }, 1500)
    }
}