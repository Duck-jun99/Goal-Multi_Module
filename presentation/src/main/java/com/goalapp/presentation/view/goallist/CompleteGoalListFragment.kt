package com.goalapp.presentation.view.goallist

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.goalapp.presentation.R
import com.goalapp.presentation.adapter.CompleteGoalAdapter
import com.goalapp.presentation.adapter.RunningGoalAdapter
import com.goalapp.presentation.databinding.FragmentCompleteGoalListBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class CompleteGoalListFragment : Fragment() {

    private var _binding :FragmentCompleteGoalListBinding? = null
    private val binding get() = _binding!!

    private val completeGoalListViewModel:CompleteGoalListViewModel by viewModels()
    private lateinit var completeGoalAdapter: CompleteGoalAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCompleteGoalListBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        completeGoalAdapter = CompleteGoalAdapter()

        binding.listGoal.adapter = completeGoalAdapter
        binding.listGoal.layoutManager = GridLayoutManager(requireContext(),2, GridLayoutManager.VERTICAL, false)

        lifecycleScope.launch {
            completeGoalListViewModel.items.flowWithLifecycle(lifecycle).collect{
                if(it != null){
                    completeGoalAdapter.submitList(it)
                    Log.e("CompleteGoalListFragment",it.toString())

                }
            }
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}