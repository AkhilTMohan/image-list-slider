package com.braveheartcreations.myapplication.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.braveheartcreations.myapplication.R
import com.braveheartcreations.myapplication.`interface`.CallBack
import com.braveheartcreations.myapplication.models.ListItem
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_grid.view.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch

/** Recycler view adapter to list the images from raw file as grid */

class ImageAdapter(private val callBack: CallBack) :
    RecyclerView.Adapter<Holder>() {
    var data = ArrayList<ListItem>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        return getHolder(R.layout.item_grid, parent)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val itemView = holder.itemView
        val context = holder.itemView.context
        val positionData = data[position]
        CoroutineScope(Main).launch {
            Glide
                .with(context)
                .load(positionData.url)
                .override(400, 400)
                .into(itemView.image)
            itemView.text?.text = String.format("%s", positionData.title + "\n" + positionData.date)
        }
        itemView.image?.setOnClickListener {
            callBack.onClick(data[holder.adapterPosition])
        }
    }

    private fun getHolder(layout: Int, parent: ViewGroup): Holder {
        return Holder(LayoutInflater.from(parent.context).inflate(layout, parent, false))
    }

    fun updateList(item: ArrayList<ListItem>) {
        if (!data.containsAll(item)) {
            data.addAll(item)
            notifyDataSetChanged()
        }
    }
}