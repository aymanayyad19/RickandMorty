package com.dev.ayman.rickandmorty.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dev.ayman.rickandmorty.databinding.ItemMainBinding
import com.dev.ayman.rickandmorty.models.Result
import com.dev.ayman.rickandmorty.roomDB.entities.Characters
import com.squareup.picasso.Picasso

class MainAdapter(var data: List<Characters>) :
    RecyclerView.Adapter<MainAdapter.MyViewHolder>() {
    val TAG = "MainAdapter"

    var onItemClick: ((Characters) -> Unit)? = null

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
        val viewBinding = ItemMainBinding.inflate(view, viewGroup, false)

        return MyViewHolder(viewBinding)
    }

    override fun onBindViewHolder(myViewHolder: MyViewHolder, i: Int) {
        myViewHolder.bind(data[i])

    }

    override fun getItemCount(): Int {
        return data.size
    }

    inner class MyViewHolder(var viewBinding: ItemMainBinding) :
        RecyclerView.ViewHolder(viewBinding.root) {

        @SuppressLint("SetTextI18n")
        fun bind(response: Characters) {

            viewBinding.apply {

                txtName.text = "Name : ${response.name}"
                txtStatus.text = "Status : ${response.status}"

                rlContainer.setOnClickListener {

                    onItemClick?.invoke(response)
                }
                Picasso.get().load(response.image).into(imgChar) ;
                imgChar.contentDescription = response.name
            }

        }

    }
}