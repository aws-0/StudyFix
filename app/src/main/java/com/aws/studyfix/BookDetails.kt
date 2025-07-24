package com.aws.studyfix

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class BookDetails : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_book_details)

        val imageBook: ImageView = findViewById(R.id.bookImage)
        val bookTitle: TextView = findViewById(R.id.bookTitle)
        val bookChapter: TextView = findViewById(R.id.bookChapter)
        val bookBranch: TextView = findViewById(R.id.bookBranch)
        val bookGrade: TextView = findViewById(R.id.bookGrade)

        val image = intent.getIntExtra("ImageKey", 0)
        val title = intent.getStringExtra("TitleKey")
        val grade = intent.getStringExtra("GradeKey")
        val chapter = intent.getStringExtra("ChapterKey")
        val branch = intent.getStringExtra("BranchKey")

        imageBook.setImageResource(image)
        bookTitle.text = title
        bookChapter.text = chapter
        bookGrade.text = grade
        bookBranch.text = branch
    }
}