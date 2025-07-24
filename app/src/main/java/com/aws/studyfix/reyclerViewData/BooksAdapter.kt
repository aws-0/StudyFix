package com.aws.studyfix.reyclerViewData

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.aws.studyfix.R

class BooksAdapter(private val bookList : ArrayList<BooksDataClass>) :
    RecyclerView.Adapter<BooksAdapter.BooksViewHolder>(), Filterable {



    val fullBookList = ArrayList<BooksDataClass>()

    init {
        fullBookList.addAll(bookList)
    }

    var onItemClick : ((BooksDataClass) -> Unit)? = null


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BooksViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_in_list, parent, false)
        return BooksViewHolder(view)
    }

    override fun getItemCount(): Int {
        return bookList.size
    }

    override fun onBindViewHolder(holder: BooksViewHolder, position: Int) {
        val currentItem = bookList[position]
        holder.imageInItem.setImageResource(currentItem.imageBook)
        holder.titleInItem.text = currentItem.titleBook
        holder.gradeInItem.text = currentItem.gradeBook
        holder.chapterInItemDesign.text = currentItem.chapterBook
        holder.branchInItem.text = currentItem.branchBook

        holder.itemView.setOnClickListener {
            onItemClick?.invoke(currentItem)
        }
    }


    class BooksViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageInItem: ImageView = itemView.findViewById(R.id.imageInItemDesign)
        val titleInItem: TextView = itemView.findViewById(R.id.titleInItemDesign)
        val gradeInItem: TextView = itemView.findViewById(R.id.gradeInItemDesign)
        val chapterInItemDesign: TextView = itemView.findViewById(R.id.chapterInItemDesign)
        val branchInItem : TextView = itemView.findViewById(R.id.branch)

    }



    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val query = constraint?.toString()?.lowercase() ?: ""

                val filteredList = ArrayList<BooksDataClass>()

                if (query.isEmpty()) {
                    filteredList.addAll(fullBookList)
                } else {
                    val filteredPattern = constraint.toString().lowercase().trim()
                    for (item in fullBookList) {

                        if (item.titleBook.lowercase().contains(filteredPattern)) {
                            filteredList.add(item)
                        }
                    }

                }
                val results = FilterResults()
                results.values = filteredList
                return results
            }


            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {

                bookList.clear()
                bookList.addAll(results?.values as ArrayList<BooksDataClass>)
                notifyDataSetChanged()

            }

        }



    }



}