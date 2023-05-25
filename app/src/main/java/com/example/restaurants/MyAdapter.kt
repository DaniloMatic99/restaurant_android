package com.example.restaurants


import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.findFragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment.Companion.findNavController

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.restaurants.model.Data
import kotlin.coroutines.coroutineContext

class MyAdapter(private val restaurantList: MutableList<Data>,  private val context: Context): RecyclerView.Adapter<MyAdapter.MyViewHolder>(){

    private lateinit var mListener: onItemClickListener

    interface onItemClickListener{
         fun onItemClick(position: Int)
     }

    fun setOnItemClickListener(listener: onItemClickListener){
        mListener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)

        return MyViewHolder(itemView,mListener)
    }

    @SuppressLint("SuspiciousIndentation")
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = restaurantList[position]
       // holder.titleImage.setImageURI(currentItem.images!![0].sizes.medium.url)
        holder.restaurantName.text = currentItem.name
        val number3digits:Double = String.format("%.3f", currentItem.score.toDouble()).toDouble()
        val number2digits:Double = String.format("%.2f", number3digits).toDouble()
        holder.restaurantScore.text = number2digits.toString()

        if(currentItem.images!!.isEmpty())
            Glide.with(context).load(R.drawable.ic_baseline_image_24).centerCrop().error(R.drawable.ic_baseline_image_24).into(holder.titleImage)
        else
            Glide.with(context).load(currentItem.images!![0].sizes?.thumbnail?.url).centerCrop().error(R.drawable.ic_baseline_image_24).into(holder.titleImage)



    }

    override fun getItemCount(): Int {
        return restaurantList.size
    }

    class MyViewHolder(itemView: View, listener: onItemClickListener) : RecyclerView.ViewHolder(itemView){
        val titleImage: ImageView = itemView.findViewById(R.id.MyimageView)
        val restaurantName : TextView = itemView.findViewById(R.id.restaurantName)
        val restaurantScore : TextView = itemView.findViewById(R.id.restaurantScore)

       init{
            itemView.setOnClickListener {
                listener.onItemClick(adapterPosition)
            }
        }
    }

}

