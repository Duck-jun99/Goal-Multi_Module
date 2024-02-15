package com.goalapp.presentation.view.goaldetail

import android.graphics.drawable.AnimationDrawable
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import com.goalapp.domain.model.GoalRepo
import com.goalapp.presentation.MainActivity
import com.goalapp.presentation.R
import com.goalapp.presentation.databinding.FragmentRunningGoalDetailBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Date
import javax.inject.Inject


@AndroidEntryPoint
class RunningGoalDetailFragment : Fragment() {

    private var _binding :FragmentRunningGoalDetailBinding? = null
    private val binding get() = _binding!!

    private val mFormat = SimpleDateFormat("yyyy/M/d") // 날짜 포맷
    private var date = Date()
    private lateinit var currentTime:String

    private lateinit var userAnimation: AnimationDrawable
    private lateinit var ballAnimation: AnimationDrawable
    private lateinit var roadAnimation: AnimationDrawable

    private var id:Int = 0

    @Inject
    lateinit var runnigGoalViewModelFactory: RunningGoalDetailViewModel.RunningGoalDetailViewModelFactory

    private val runningGoalDetailViewModel by viewModels<RunningGoalDetailViewModel>{
        RunningGoalDetailViewModel.provideFactory(runnigGoalViewModelFactory, id)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val mainAct = activity as MainActivity
        mainAct.HideBottomNavi(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentRunningGoalDetailBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        id = requireArguments().getString("id")!!.toInt()

        Log.e("RunningGoalDetailFragment","id: ${id.toString()}")

        currentTime = mFormat.format(date)

        val userAni = binding.user1.apply {
            setBackgroundResource(R.drawable.user_run)
            userAnimation = background as AnimationDrawable
            setOnClickListener {

                userAnimation.start()
                ballAnimation.start()
                roadAnimation.start()
            }
        }


        val ballAni = binding.ball1.apply {
            setBackgroundResource(R.drawable.ball_ani)
            ballAnimation = background as AnimationDrawable
        }
        val roadAni = binding.road.apply {
            setBackgroundResource(R.drawable.road_ani)
            roadAnimation = background as AnimationDrawable
        }


        lifecycleScope.launch {

            runningGoalDetailViewModel.item.flowWithLifecycle(lifecycle).collect{
                if(it != null){
                    Log.e("RunningGoalDetailFragment","Goal: ${it.toString()}")
                    diffDay(it,currentTime)
                    initUI(it)

                }
            }
        }

        initButtonLogic(id)


    }

    override fun onDestroy() {
        super.onDestroy()

        val mainAct = activity as MainActivity
        mainAct.HideBottomNavi(false)

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun initUI(item: GoalRepo){
        binding.tvBigGoal.text = item.bigGoal
        updateSmallGoal(item)
    }

    private fun updateSmallGoal(item: GoalRepo){
        val stage = item.stage
        binding.tvSmallGoal.text = item.smallGoal[stage-1]
        binding.btnCompleteGoal.isClickable = false

        if(stage == 1){
            if(item.smallGoal.size == 1){
                binding.tvNextSmallGoal.visibility = View.INVISIBLE
                binding.btnPlusStage.isClickable = false
                binding.btnCompleteGoal.isClickable = true
                completeGoal(item)
            }
            else{
                binding.tvNextSmallGoal.visibility = View.VISIBLE
                binding.tvNextSmallGoal.text = item.smallGoal[stage]
            }
            binding.tvCompSmallGoal.visibility = View.INVISIBLE
            binding.btnMinusStage.isClickable = false
        }
        else if(stage > 1 && stage < item.smallGoal.size){
            binding.tvCompSmallGoal.visibility = View.VISIBLE
            binding.tvCompSmallGoal.text = item.smallGoal[stage-2]
            binding.tvNextSmallGoal.visibility = View.VISIBLE
            binding.tvNextSmallGoal.text = item.smallGoal[stage]
            binding.btnPlusStage.isClickable = true
            binding.btnMinusStage.isClickable = true
        }

        else if(stage == item.smallGoal.size){
            binding.tvCompSmallGoal.text = item.smallGoal[stage-2]
            binding.tvNextSmallGoal.visibility = View.INVISIBLE
            binding.btnPlusStage.isClickable = false

            binding.btnCompleteGoal.isClickable = true
            completeGoal(item)
        }
    }

    private fun initButtonLogic(id: Int){
        binding.btnPlusStage.setOnClickListener {
            runningGoalDetailViewModel.plusStage(id)
        }
        binding.btnMinusStage.setOnClickListener {
            runningGoalDetailViewModel.minusStage(id)
        }
    }

    private fun completeGoal(goal:GoalRepo){
        binding.btnCompleteGoal.setOnClickListener {
            binding.btnMinusStage.isClickable = false
            binding.btnPlusStage.isClickable = false
            binding.btnCompleteGoal.isClickable = false
            runningGoalDetailViewModel.completeGoal(
                GoalRepo(
                    id = goal.id,
                    bigGoal = goal.bigGoal,
                    smallGoal = goal.smallGoal,
                    stage = goal.stage,
                    makeTime = goal.makeTime,
                    completeTime = currentTime,
                    completion = true,
                )
            )
        }
    }

    private fun diffDay(goal: GoalRepo, currentDate:String){
        // 문자열을 LocalDate 객체로 변환
        val oldDate = mFormat.parse(goal.makeTime)
        val currentDate = mFormat.parse(currentDate)

        val daysDifference = ((currentDate.time - oldDate.time) / (24 * 60 * 60 * 1000)).toInt()

        if(goal.completion == false){
            binding.tvDay.text = "${daysDifference}일째 진행중..!"
        }
        else
            binding.tvDay.text = "${daysDifference}일 동안 고생하셨습니다!"

    }

}