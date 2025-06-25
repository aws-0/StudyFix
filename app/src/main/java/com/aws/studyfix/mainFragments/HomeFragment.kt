package com.aws.studyfix.mainFragments

import android.os.Bundle
import android.os.CountDownTimer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.aws.studyfix.R

class HomeFragment : Fragment() {
    lateinit var titleTv: TextView
    lateinit var timerTv: TextView
    lateinit var pb: ProgressBar
    lateinit var startBtn: Button
    lateinit var resetTv: TextView

    val START_TIME_IN_MILLES: Long = 10 * 1000
    var remainingTime = START_TIME_IN_MILLES
    var timer: CountDownTimer? = null
    var isTimeRunning = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // 1. أولاً: جهّز الواجهة
        val view = inflater.inflate(R.layout.fragment_home,container,false)
        // 2. اربط الواجهات
        titleTv = view.findViewById(R.id.titleTv)
        timerTv = view.findViewById(R.id.timerTv)
        pb = view.findViewById(R.id.progressBar)
        startBtn = view.findViewById(R.id.startButton)
        resetTv = view.findViewById(R.id.resetTv)

        // 3. عيّن الأحداث
        startBtn.setOnClickListener {
            if (!isTimeRunning) {
                startTimer()
                titleTv.text = resources.getText(R.string.keep_going)
            }
        }

        resetTv.setOnClickListener {
            resetTimer()
        }

        updateTimeText() // عرض الوقت المبدئي

        // 4. أخيرًا: أرجع الواجهة
        return view
    }

    private fun startTimer() {
        timer = object : CountDownTimer(START_TIME_IN_MILLES, 1 * 1000) {
            override fun onTick(timeLeft: Long) {
                remainingTime = timeLeft
                updateTimeText()
                pb.progress = remainingTime.toDouble()
                    .div(START_TIME_IN_MILLES.toDouble())
                    .times(100).toInt()
            }

            override fun onFinish() {
                Toast.makeText(requireContext(), "انتهت مدة الجلسة، أحسنت!!", Toast.LENGTH_SHORT).show()
                isTimeRunning = false
                titleTv.text = resources.getText(R.string.pomodoro)
                pb.progress = 0
            }
        }.start()
        isTimeRunning = true
    }

    private fun updateTimeText() {
        val minute = remainingTime / 1000 / 60
        val second = (remainingTime / 1000) % 60
        val formattedTime = String.format("%02d:%02d", minute, second)
        timerTv.text = formattedTime
    }

    private fun resetTimer() {
        timer?.cancel()
        remainingTime = START_TIME_IN_MILLES
        updateTimeText()
        titleTv.text = resources.getText(R.string.pomodoro)
        isTimeRunning = false
        pb.progress = 100
    }

    override fun onDestroyView() {
        super.onDestroyView()
        timer?.cancel() // أمان: وقف المؤقت عند مغادرة الواجهة
    }
}

