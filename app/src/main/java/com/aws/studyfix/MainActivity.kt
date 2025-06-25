package com.aws.studyfix

import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import com.aws.studyfix.R.id.stopTv
import com.aws.studyfix.mainFragments.BooksFragment
import com.aws.studyfix.mainFragments.HomeFragment
import com.aws.studyfix.mainFragments.SettingsFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val homeFragment : Fragment = HomeFragment()
        val settingFragment : Fragment = SettingsFragment()
        val booksFragment : Fragment = BooksFragment()

        supportFragmentManager.beginTransaction().replace(R.id.space_for_fragments,homeFragment).commit()


        val bottom_navigation = findViewById<BottomNavigationView>(R.id.bottom_navigation)


        bottom_navigation.selectedItemId = R.id.home_icon

        bottom_navigation.setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.home_icon -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.space_for_fragments,homeFragment).commit()
                    true
                }

                R.id.settings_icon -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.space_for_fragments,settingFragment).commit()
                    true
                }

                R.id.books_icon -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.space_for_fragments,booksFragment).commit()
                    true
                }

                else -> false
            }
        }


    }
}
