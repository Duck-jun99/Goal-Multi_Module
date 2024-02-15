package com.goalapp.presentation.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.goalapp.domain.model.GoalRepo
import com.goalapp.presentation.R
import com.goalapp.presentation.databinding.ListItemBinding

class RunningGoalAdapter : ListAdapter<GoalRepo, RunningGoalAdapter.MyViewHolder>(diffUtil) {
    class MyViewHolder(private val binding: ListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {



        fun bind(item: GoalRepo) {
            var percent = (item.stage.toDouble()
                    / item.smallGoal.size.toDouble() * 100).toInt()

            itemView.setOnClickListener{
                val id = bundleOf("id" to item.id.toString())
                itemView.findNavController().navigate(R.id.action_to_RunningGoalDetail, id)
            }
            binding.tvBig.text = item.bigGoal
            binding.tvSmall.text = "${percent}%"
            if (percent < 10) {
                binding.viewPercentLine.setColorFilter(Color.rgb(209, 178, 255))
            } else if (percent in 10..19) {
                binding.viewPercentLine.setColorFilter(Color.rgb(181, 178, 255))
            } else if (percent in 20..29) {
                binding.viewPercentLine.setColorFilter(Color.rgb(178, 204, 255))
            } else if (percent in 30..39) {
                binding.viewPercentLine.setColorFilter(Color.rgb(178, 235, 244))
            } else if (percent in 40..49) {
                binding.viewPercentLine.setColorFilter(Color.rgb(183, 240, 177))
            } else if (percent in 50..59) {
                binding.viewPercentLine.setColorFilter(Color.rgb(206, 242, 121))
            } else if (percent in 60..69) {
                binding.viewPercentLine.setColorFilter(Color.rgb(250, 237, 125))
            } else if (percent in 70..79) {
                binding.viewPercentLine.setColorFilter(Color.rgb(255, 224, 140))
            } else if (percent in 80..89) {
                binding.viewPercentLine.setColorFilter(Color.rgb(255, 193, 158))
            } else if (percent in 90..99) {
                binding.viewPercentLine.setColorFilter(Color.rgb(255, 178, 217))
            } else if (percent >= 100) {
                binding.viewPercentLine.setColorFilter(Color.rgb(255, 167, 167))
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = ListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(currentList[position])
    }

    companion object {
        val diffUtil = object : DiffUtil.ItemCallback<GoalRepo>() {
            override fun areItemsTheSame(oldItem: GoalRepo, newItem: GoalRepo): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: GoalRepo, newItem: GoalRepo): Boolean {
                return oldItem == newItem
            }
        }
    }

}