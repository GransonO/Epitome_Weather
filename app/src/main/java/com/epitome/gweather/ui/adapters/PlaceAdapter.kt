package com.epitome.gweather.ui.adapters

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.Navigation
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.epitome.gweather.R
import com.epitome.gweather.models.local.PlaceObj

class PlaceAdapter : ListAdapter<PlaceObj, PlaceAdapter.ItemViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.place_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(item: PlaceObj) = with(itemView) {

            itemView.findViewById<TextView>(R.id.placeText).text = item.name
            setOnClickListener {
                val arguments = Bundle()
                arguments.putString("place_id", item.placeId)
                arguments.putString("place_name", item.name)
                Navigation.findNavController(it).navigate(R.id.action_starterFragment_to_detailsFragment, arguments)
            }
        }
    }
}

class DiffCallback : DiffUtil.ItemCallback<PlaceObj>() {
    override fun areItemsTheSame(oldItem: PlaceObj, newItem: PlaceObj): Boolean {
        return oldItem.placeId == newItem.placeId
    }

    override fun areContentsTheSame(oldItem: PlaceObj, newItem: PlaceObj): Boolean {
        return oldItem == newItem
    }
}
