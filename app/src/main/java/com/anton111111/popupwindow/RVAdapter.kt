package com.anton111111.popupwindow

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class RVAdapter(private val popupItemClickCallback: (item: String) -> Unit) :
    RecyclerView.Adapter<RVAdapter.VH>() {

    override fun getItemCount() = 40

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        VH(RowView(parent.context, popupItemClickCallback))

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.bind("name_$position", List(position + 1) { "item_$it" })
    }


    class VH(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(name: String, popupItems: List<String>) {
            if (itemView is RowView) itemView.bind(name, popupItems)
        }
    }

}