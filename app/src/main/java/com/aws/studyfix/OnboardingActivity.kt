package com.aws.studyfix

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.viewpager2.widget.ViewPager2

class OnboardingActivity : AppCompatActivity() {

    private lateinit var onboardingAdapter: OnboardingAdapter
    private lateinit var indicatorsContainer: LinearLayout
    private lateinit var getStartedButton: Button
    private lateinit var viewPager: ViewPager2

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // فحص إذا المستخدم شاف الشاشة من قبل
        if (onboardingFinished()) {
            startActivity(Intent(this, SplashActivity::class.java))
            finish()
        }

        setContentView(R.layout.activity_onboarding)

        viewPager = findViewById(R.id.onboardingViewPager)
        indicatorsContainer = findViewById(R.id.layoutIndicators)
        getStartedButton = findViewById(R.id.buttonGetStarted)

        val items = listOf(
            OnboardingItem(R.drawable.books_two, "مرحبًا بك", "تطبيق بسيط بساعدك توصل للمعلومات ووحلول الأسئلة بطريقة سهلة."),
            OnboardingItem(R.drawable.books, "كل شيء بمكان واحد", "امتحانات يوفرها التطبيق مع الأجوبة لاختبار نفسك."),
            OnboardingItem(R.drawable.logo, "ابدأ الآن", "اضغط على 'ابدأ' وخلينا نسهل دراستك.")
        )

        onboardingAdapter = OnboardingAdapter(items)
        viewPager.adapter = onboardingAdapter

        setupIndicators(items.size)
        setCurrentIndicator(0)

        viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                setCurrentIndicator(position)
                getStartedButton.visibility = if (position == onboardingAdapter.itemCount - 1)
                    Button.VISIBLE else Button.GONE
            }
        })

        getStartedButton.setOnClickListener {
            saveOnboardingFinished()
            startActivity(Intent(this, SplashActivity::class.java))
            finish()
        }
    }

    private fun setupIndicators(count: Int) {
        val indicators = Array(count) { TextView(this) }
        val layoutParams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.WRAP_CONTENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )
        layoutParams.setMargins(8, 0, 8, 0)
        indicators.forEach {
            it.text = "●"
            it.textSize = 18f
            it.setTextColor(ContextCompat.getColor(this, R.color.grey))
            it.layoutParams = layoutParams
            indicatorsContainer.addView(it)
        }
    }

    private fun setCurrentIndicator(index: Int) {
        val count = indicatorsContainer.childCount
        for (i in 0 until count) {
            val textView = indicatorsContainer.getChildAt(i) as TextView
            textView.setTextColor(
                ContextCompat.getColor(
                    this,
                    if (i == index) R.color.black else R.color.grey
                )
            )
        }
    }

    private fun saveOnboardingFinished() {
        val pref = getSharedPreferences("onboarding", MODE_PRIVATE)
        pref.edit().putBoolean("finished", true).apply()
    }

    private fun onboardingFinished(): Boolean {
        val pref = getSharedPreferences("onboarding", MODE_PRIVATE)
        return pref.getBoolean("finished", false)
    }
}