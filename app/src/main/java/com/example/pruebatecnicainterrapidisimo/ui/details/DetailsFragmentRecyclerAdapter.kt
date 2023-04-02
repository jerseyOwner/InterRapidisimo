package com.example.pruebatecnicainterrapidisimo.ui.details

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.pruebatecnicainterrapidisimo.data.local.Table
import com.example.pruebatecnicainterrapidisimo.databinding.DetailsFragmentItemBinding

class DetailsFragmentRecyclerAdapter(
    private val tableList: List<Table>
): RecyclerView.Adapter<DetailsFragmentRecyclerAdapter.ItemViewHolder>() {

    class ItemViewHolder(private val binding: DetailsFragmentItemBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(table: Table) {
            with(binding) {
                DetailsFragmentItemCardText.text = table.name
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(
            DetailsFragmentItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val table = tableList[position]
        holder.bind(table)
    }

    override fun getItemCount(): Int {
        return tableList.size
    }
}