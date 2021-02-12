package com.example.recyclerviewexample

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.example_item.view.*

// Here MainActivity implements OnItemClickListener and also defined onItemClick fun in it. And here it is passed as listener

class ExampleAdapter(
        private val exampleList : List<ExampleItem>,
        private val listener : OnItemClickListener
) : RecyclerView.Adapter<ExampleAdapter.ExamplerViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExamplerViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.example_item, parent, false)

        return ExamplerViewHolder(itemView)
    }


    override fun onBindViewHolder(holder: ExamplerViewHolder, position: Int) {
        val currentItem = exampleList[position];

        holder.imageView.setImageResource(currentItem.imageResource)
        holder.textView1.text = currentItem.text1
        holder.textView2.text = currentItem.text2
    }

    override fun getItemCount() = exampleList.size

    // inner is used, so that we can use listener(argument received ExampleAdapter) inside this class
   inner class ExamplerViewHolder(itemView: View) : RecyclerView.ViewHolder (itemView),
    View.OnClickListener{
        val imageView: ImageView = itemView.imgv1
        val textView1: TextView = itemView.tv1
        val textView2 : TextView = itemView.tv2

        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            val position = adapterPosition           // adapterPosition will give the row number clicked by user

            //When position is NOT -1 (i.e. a valid position), then onItemClick fun is called for the row which is clicked
            if(position != RecyclerView.NO_POSITION)
                listener.onItemClick(position)
        }
    }

    //This interface is made by us to make this adapter independent of any onClick function and activity
    //Any activity can use this adapter and define onItemClick fun as they want
    interface OnItemClickListener{
        fun onItemClick(position : Int)
    }

}