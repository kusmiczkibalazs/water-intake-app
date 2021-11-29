package com.example.assignmentapp.fluidintake

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.assignmentapp.R
import com.example.assignmentapp.database.FluidIntake
import com.example.assignmentapp.databinding.ListItemFluidIntakeBinding
import java.text.SimpleDateFormat

class FluidIntakeAdapter: ListAdapter<FluidIntake, FluidIntakeAdapter.ViewHolder>(FluidIntakeDiffCallback()) {

    @SuppressLint("SetTextI18n", "SimpleDateFormat")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)

        /*holder.quantity.text = "${item.intakeQuantity}dl fogyasztás"
        holder.dateTime.text = SimpleDateFormat("yyyy-MM-dd HH:mm").format(item.intakeTimeMilli).toString()*/
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
/*        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.list_item_fluid_intake, parent, false)
        return ViewHolder(view)*/
        return ViewHolder.from(parent)
    }

//    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
//        val quantity: TextView = itemView.findViewById(R.id.quantity)
//        val dateTime: TextView = itemView.findViewById(R.id.dateTime)
//    }

    class ViewHolder private constructor(val binding: ListItemFluidIntakeBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: FluidIntake) {
            binding.fluidIntake = item
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ListItemFluidIntakeBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }
        }
    }
}

class FluidIntakeDiffCallback : DiffUtil.ItemCallback<FluidIntake>() {
    override fun areItemsTheSame(oldItem: FluidIntake, newItem: FluidIntake): Boolean {
        return oldItem.intakeId == newItem.intakeId
    }

    override fun areContentsTheSame(oldItem: FluidIntake, newItem: FluidIntake): Boolean {
        return oldItem == newItem
    }

}

