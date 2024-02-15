package com.goalapp.presentation.view.goallist

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.goalapp.presentation.R
import com.goalapp.presentation.adapter.RunningGoalAdapter
import com.goalapp.presentation.databinding.FragmentRunningGoalListBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch


@AndroidEntryPoint
class RunningGoalListFragment : Fragment() {

    private var _binding: FragmentRunningGoalListBinding? = null
    private val binding get() = _binding!!
    //private lateinit var runningGoalListViewModel: RunningGoalListViewModel
    private val runningGoalListViewModel: RunningGoalListViewModel by viewModels()
    private lateinit var runningGoalAdapter: RunningGoalAdapter



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRunningGoalListBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        runningGoalAdapter = RunningGoalAdapter()

        binding.listGoal.adapter = runningGoalAdapter
        binding.listGoal.layoutManager = GridLayoutManager(requireContext(),2, GridLayoutManager.VERTICAL, false)

        binding.btnMakeGoal.setOnClickListener {
            findNavController().navigate(R.id.RunningGoalListFragmentToAddGoalFragment)
        }

        //viewModel.insertItem(GoalRepo(bigGoal = "TEST", stage = 0, makeTime = "", completeTime = "", completion = false))

        lifecycleScope.launch {
            runningGoalListViewModel.items.flowWithLifecycle(lifecycle).collect{
                if (it != null) {
                    runningGoalAdapter.submitList(it)
                    Log.e("RunningGoalListFragment",it.toString())
                }
            }
        }

    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}