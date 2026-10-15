package org.setu.baristafinder

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

import org.setu.baristafinder.models.BaristaModel

class SavedBaristaAdapter( // Handles active in-memory barista Models
    private var baristaModels: List<BaristaModel>, // currently loaded/created Baristas
    private var listener: BaristaItemListener
) : RecyclerView.Adapter<SavedBaristaAdapter.ItemViewHolder>() {

    class ItemViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) { // declare view class for each individual Item

        val titleText: TextView =
            itemView.findViewById(R.id.titleText)

        val addressText: TextView =
            itemView.findViewById(R.id.addressText)

        val coordinatesText: TextView =
            itemView.findViewById(R.id.coordinatesText)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ItemViewHolder {

        val view = LayoutInflater.from(parent.context)
            .inflate(
                R.layout.item_placed_mark,
                parent,
                false
            )

        return ItemViewHolder(view)
    }

    override fun onBindViewHolder(
        holder: ItemViewHolder,
        position: Int
    ) {

        val barista = baristaModels[position]

        holder.titleText.text =
            "${barista.id}: ${barista.name}"

        holder.addressText.text =
            barista.address

        holder.coordinatesText.text =
            "X: ${barista.x}, Y: ${barista.y}"

        holder.itemView.setOnClickListener {
            listener.onItemClick(barista)
        }

        holder.itemView.setOnLongClickListener {
            listener.onItemLongClick(barista)
        }
    }

    override fun getItemCount(): Int {
        return baristaModels.size
    }

    fun updateMarks(newMarks: List<BaristaModel>) { // updates the currently loaded UI (represented in the adapter by "marks")
        baristaModels = newMarks
        notifyDataSetChanged() // TODO OPT investigate at end if needed
    }
}
