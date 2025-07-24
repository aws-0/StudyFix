package com.aws.studyfix.mainFragments

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.aws.studyfix.R
import com.aws.studyfix.reyclerViewData.BooksAdapter
import com.aws.studyfix.reyclerViewData.BooksDataClass
import androidx.appcompat.widget.SearchView
import com.aws.studyfix.BookDetails
import org.w3c.dom.Text

class BooksFragment : Fragment() {
    private lateinit var booksRecyclerView : RecyclerView
    private lateinit var booksArrayList : ArrayList<BooksDataClass>
    private lateinit var booksImage: Array<Int>
    private lateinit var booksTitle: Array<String>
    private lateinit var booksGrade: Array<String>
    private lateinit var booksChapter: Array<String>
    private lateinit var adapter: BooksAdapter
    private lateinit var searchView: SearchView
    private lateinit var booksBranch: Array<String>



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_books, container, false)



        booksImage = arrayOf(
            R.drawable.book_english_12_ch0_sc ,
            R.drawable.book_math_11_ch1_sc_ind ,
            R.drawable.book_math_12_ch0_sc_ind ,
            R.drawable.book_physics_11_ch_1_sci_ind ,
            R.drawable.book_physics_12_ch0_sci_ind ,

            R.drawable.book_english_10_ch1 ,
            R.drawable.book_english_11_ch0_sci_ind ,
            R.drawable.book_geography_12_ch0_literary ,
            R.drawable.book_it_11_ch0_sci_ind ,
            R.drawable.book_it_12_ch0_sci_ind ,
            R.drawable.book_math_12_ch0_literary ,
            )

        booksBranch = arrayOf(
            getString(R.string.all_branches) , getString(R.string.branch_sci_and_indu) , getString(R.string.branch_sci_and_indu) , getString(R.string.branch_sci_and_indu) ,
            getString(R.string.branch_sci_and_indu) , "" , getString(R.string.all_branches) ,
            getString(R.string.branch_literary) , getString(R.string.branch_sci_and_indu) , getString(R.string.branch_sci_and_indu) , getString(R.string.branch_literary)

        )

        booksTitle = arrayOf(
            "English" ,
            getString(R.string.math) ,
            getString(R.string.math) ,
            getString(R.string.physics) ,
            getString(R.string.physics) ,
            "English" ,
            "English" ,
            getString(R.string.geography) ,
            getString(R.string.it) ,
            getString(R.string.it) ,
            getString(R.string.math)
            )

        booksGrade = arrayOf(
            getString(R.string.grade_12_in_item) ,
            getString(R.string.grade_11_in_item) ,
            getString(R.string.grade_12_in_item) ,
            getString(R.string.grade_11_in_item) ,
            getString(R.string.grade_12_in_item) ,
            getString(R.string.grade_10_in_item) ,
            getString(R.string.grade_11_in_item) ,
            getString(R.string.grade_12_in_item) ,
            getString(R.string.grade_11_in_item) ,
            getString(R.string.grade_12_in_item) ,
            getString(R.string.grade_12_in_item) ,

        )

        booksChapter = arrayOf(
            getString(R.string.chapter_1_and_2) ,
            getString(R.string.chapter_1) ,
            getString(R.string.chapter_1_and_2) ,
            getString(R.string.chapter_1) ,
            getString(R.string.chapter_1_and_2) ,
            getString(R.string.chapter_1) ,
            getString(R.string.chapter_1_and_2) ,
            getString(R.string.chapter_1_and_2) ,
            getString(R.string.chapter_1_and_2) ,
            getString(R.string.chapter_1_and_2) ,
            getString(R.string.chapter_1_and_2) ,
        )


        booksRecyclerView = view.findViewById(R.id.booksRecyclerView)
        booksRecyclerView.layoutManager = LinearLayoutManager(requireContext() , LinearLayoutManager.VERTICAL , false)
        booksRecyclerView.setHasFixedSize(true)




        booksArrayList = arrayListOf()
        loadBooksData()


        adapter = BooksAdapter(booksArrayList)
        booksRecyclerView.adapter = adapter

        adapter.onItemClick = { clickedItem ->
            val intent = Intent(requireContext(), BookDetails::class.java)
            intent.putExtra("ImageKey" , clickedItem.imageBook)
            intent.putExtra("TitleKey" , clickedItem.titleBook)
            intent.putExtra("GradeKey" , clickedItem.gradeBook)
            intent.putExtra("ChapterKey" , clickedItem.chapterBook)
            intent.putExtra("BranchKey" , clickedItem.branchBook)

            startActivity(intent)
        }


        searchView = view.findViewById(R.id.searchViewId)








        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean = false

            override fun onQueryTextChange(newText: String?): Boolean {
                adapter.filter.filter(newText)
                return true
            }

        })


        return view
    }





    fun loadBooksData(){
        for (i in booksImage.indices) {
            val item = BooksDataClass(booksImage[i] , booksTitle[i] , booksGrade[i] , booksChapter[i] , booksBranch[i])
            booksArrayList.add(item)
        }




    }





}