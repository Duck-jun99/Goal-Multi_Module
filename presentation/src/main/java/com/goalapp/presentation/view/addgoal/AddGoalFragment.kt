package com.goalapp.presentation.view.addgoal

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isEmpty
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import com.goalapp.domain.model.GoalRepo
import com.goalapp.presentation.MainActivity
import com.goalapp.presentation.R
import com.goalapp.presentation.databinding.FragmentAddGoalBinding
import dagger.hilt.android.AndroidEntryPoint
import java.text.SimpleDateFormat
import java.util.Date

@AndroidEntryPoint
class AddGoalFragment : Fragment() {

    private var _binding: FragmentAddGoalBinding? = null
    private val binding get() = _binding!!

    private val addGoalViewModel: AddGoalViewModel by viewModels()

    var smallGoalList:MutableList<String> = ArrayList<String>()

    private val mFormat = SimpleDateFormat("yyyy/M/d") // 날짜 포맷


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val mainAct = activity as MainActivity
        mainAct.HideBottomNavi(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentAddGoalBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val date = Date()
        val makeTime = mFormat.format(date)

        var bigGoal = binding.etBigGoal
        var smallGoal = binding.etSmallGoal
        var smallList = binding.smallList
        var btnPlusSmallGoal = binding.btnPlusSmallGoal
        var btnDelSmallGoal = binding.btnDelSmallGoal
        var btnSaveGoal = binding.btnSaveGoal


        btnPlusSmallGoal.setOnClickListener {
            if(checkLogic(binding.etSmallGoal.text.toString())){
                smallGoalList.add(smallGoal.text.toString())
                Log.e("AddGoalFragment",smallGoalList.toString())
                smallGoal.text = null
            }

        }
        btnDelSmallGoal.setOnClickListener {
            if(!smallList.isEmpty()){
                smallGoalList.removeLast()
                Log.e("AddGoalFragment",smallGoalList.toString())
            }

        }

        btnSaveGoal.setOnClickListener {
            if(checkLogic(binding.etBigGoal.text.toString()) && smallGoalList.isNotEmpty()){
                val goalRepo = mappingGoalRepo(
                    bigGoal = bigGoal.text.toString(),
                    smallGoalList = smallGoalList,
                    makeTime = makeTime)
                addGoalViewModel.insertItem(goalRepo)
                (activity as MainActivity).navController.navigate(R.id.action_to_RunningGoalList)
            }
            else
                Toast.makeText(requireContext(),"목표를 입력해주세요.",Toast.LENGTH_SHORT).show()

        }

    }

    //TEST
    private fun mappingGoalRepo(bigGoal: String, smallGoalList: MutableList<String>, makeTime:String): GoalRepo {
        Log.e("mappingGoalRepo","bigGoal:${bigGoal}, smallGoalList:${smallGoalList.toString()}")
        return GoalRepo(
            bigGoal = bigGoal,
            smallGoal = smallGoalList,
            stage=1,
            makeTime = makeTime,
            completeTime = "",
            completion = false
        )

    }

    private fun checkLogic(value:String):Boolean{
        if(value.replace(" ","").equals("")){
            Toast.makeText(requireContext(),"목표를 입력해주세요.",Toast.LENGTH_SHORT).show()
            return false
        }
        else{
            return true
        }

    }

    override fun onDestroy() {
        super.onDestroy()

        val mainAct = activity as MainActivity
        mainAct.HideBottomNavi(false)
    }

    override fun onDestroyView() {
        Log.e("AddGoalFragment","onDestoryView()")
        super.onDestroyView()
        _binding = null
    }
}