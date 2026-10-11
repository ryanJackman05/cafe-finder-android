package org.setu.baristafinder

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

import org.setu.baristafinder.models.BaristaModel

class PlacedMarkAdapter(
    private var marks: List<BaristaModel>,
    private val onEdit: (BaristaModel) -> Unit,
    private val onDelete: (BaristaModel) -> Unit
) : RecyclerView.Adapter<PlacedMarkAdapter.MarkViewHolder>() {

    class MarkViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {

        val titleText: TextView =
            itemView.findViewById(R.id.titleText)

        val addressText: TextView =
            itemView.findViewById(R.id.addressText)

        val coordinatesText: TextView =
            itemView.findViewById(R.id.coordinatesText)

        val editButton: Button =
            itemView.findViewById(R.id.editButton)

        val deleteButton: Button =
            itemView.findViewById(R.id.deleteButton)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MarkViewHolder {

        val view = LayoutInflater.from(parent.context)
            .inflate(
                R.layout.item_placed_mark,
                parent,
                false
            )

        return MarkViewHolder(view)
    }

    override fun onBindViewHolder(
        holder: MarkViewHolder,
        position: Int
    ) {

        val mark = marks[position]

        holder.titleText.text =
            "${mark.id}: ${mark.name}"

        holder.addressText.text =
            mark.address

        holder.coordinatesText.text =
            "X: ${mark.x}, Y: ${mark.y}"

        holder.editButton.setOnClickListener {
            onEdit(mark)
        }

        holder.deleteButton.setOnClickListener {
            onDelete(mark)
        }
    }

    override fun getItemCount(): Int {
        return marks.size
    }

    fun updateMarks(newMarks: List<BaristaModel>) { // updates the currently loaded UI (represented in the adapter by "marks")
        marks = newMarks
        notifyDataSetChanged()
    }
}
