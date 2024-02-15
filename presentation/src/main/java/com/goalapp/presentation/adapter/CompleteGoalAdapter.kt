package com.goalapp.presentation.adapter

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

class CompleteGoalAdapter : ListAdapter<GoalRepo, CompleteGoalAdapter.MyViewHolder>(diffUtil) {
    class MyViewHolder(private val binding: ListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: GoalRepo) {
            itemView.setOnClickListener{
                val id = bundleOf("id" to item.id.toString())
                itemView.findNavController().navigate(R.id.action_to_RunningGoalDetail, id)
            }
            binding.tvBig.text = item.bigGoal
            binding.tvSmall.text = item.completeTime
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