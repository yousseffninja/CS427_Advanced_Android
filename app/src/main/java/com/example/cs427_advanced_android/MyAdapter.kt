package com.example.task

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.cs427_advanced_android.CarParser
import com.example.cs427_advanced_android.R
import com.google.android.material.imageview.ShapeableImageView

class MyAdapter(private val newsList: ArrayList<CarParser>): RecyclerView.Adapter<MyAdapter.MyViewHolder>() {

//    private lateinit var mListener: OnItemClickListener

    interface OnItemClickListener{
        fun onItemClick(position: Int)
    }

//    fun setItemClickListener(Listener: OnItemClickListener){
//        mListener = Listener
//    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.car_card, parent, false)
        return  MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = newsList[position]
        holder.tvHeading.text = currentItem.heading
        holder.tvPrice.text = currentItem.Price.toString()
        holder.textView_from.text = currentItem.to
        holder.textView2_to.text = currentItem.from

    }

    override fun getItemCount(): Int {
        return newsList.size
    }



    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView), View.OnClickListener {
        val tvHeading: TextView = itemView.findViewById(R.id.RestourantNameee)
        val tvPrice: TextView = itemView.findViewById(R.id.Pricee)
        val textView_from: TextView = itemView.findViewById(R.id.textView_from)
        val textView2_to: TextView = itemView.findViewById(R.id.textView2_to)

//        init {
//            itemView.setOnClickListener{
//                Listener.onItemClick(adapterPosition)
//            }
//        }

        override fun onClick(v: View?) {
            TODO("Not yet implemented")
        }
    }
}