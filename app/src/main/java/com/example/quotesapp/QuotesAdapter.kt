package com.example.quotesapp

import android.content.Context
import android.view.LayoutInflater
import android.view.TextureView
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class QuotesAdapter(val context: Context, val list: List<QuotesModel>, val listener: copyListener)
    : RecyclerView.Adapter<QuotesViewHolder>()
{
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuotesViewHolder {
        val layout = LayoutInflater.from(context).inflate(R.layout.sample_row_for_quotes,parent, false)
        return QuotesViewHolder(layout)
    }

    override fun onBindViewHolder(holder: QuotesViewHolder, position: Int) {
        holder.txt_quote.text = list.get(position).text
        holder.txt_author.text = list.get(position).author
        holder.btn_copy.setOnClickListener {
            listener.onCopyClicked(list.get(holder.adapterPosition).text)
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

}

class QuotesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    var txt_quote: TextView = itemView.findViewById(R.id.sampleRow_txt_quotes)
    var txt_author: TextView = itemView.findViewById(R.id.sampleRow_txt_authorName)
    var btn_copy : Button = itemView.findViewById(R.id.sampleRow_btn_copy)
}

/*
*
* 1. create the QuotesViewHolder first and get the element from sample Layout
* 2.the create the constructor
* 3. work on the onCreateViewHolder, which is only inflating the layout
* 4. work on the bind
* 5. declare the size of the list.
* */