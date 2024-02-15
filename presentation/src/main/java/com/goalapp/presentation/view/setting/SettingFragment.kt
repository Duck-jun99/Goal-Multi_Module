package com.goalapp.presentation.view.setting

import android.os.Bundle
import android.text.Layout
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.goalapp.presentation.R
import com.goalapp.presentation.databinding.FragmentSettingBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SettingFragment : Fragment() {

    private var _binding :FragmentSettingBinding? = null
    private val binding get() = _binding!!

    private val settingViewModel:SettingViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSettingBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding.btnBackground.setOnClickListener {
            binding.fragmentSetting.addView(createLayout())
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun createLayout() :View{
        val inflater = this.layoutInflater
        val layout = inflater.inflate(R.layout.list_background_theme, null) as LinearLayout

        val layoutBackground = layout.findViewById<LinearLayout>(R.id.layoutBackground)


        layout.findViewById<Button>(R.id.btnRed).setOnClickListener {
            lifecycleScope.launch {
                settingViewModel.setAppTheme(R.style.Theme_red_Goal)
            }
            requireActivity().recreate()
        }

        layout.findViewById<Button>(R.id.btnPink).setOnClickListener {
            lifecycleScope.launch {
                settingViewModel.setAppTheme(R.style.Theme_pink_Goal)
            }
            requireActivity().recreate()
        }

        layout.findViewById<Button>(R.id.btnYellow).setOnClickListener {
            lifecycleScope.launch {
                settingViewModel.setAppTheme(R.style.Theme_yellow_Goal)
            }
            requireActivity().recreate()
        }

        layout.findViewById<Button>(R.id.btnGreen).setOnClickListener {
            lifecycleScope.launch {
                settingViewModel.setAppTheme(R.style.Theme_green_Goal)
            }
            requireActivity().recreate()
        }

        layout.findViewById<Button>(R.id.btnBlue).setOnClickListener {
            lifecycleScope.launch {
                settingViewModel.setAppTheme(R.style.Theme_blue_Goal)
            }
            requireActivity().recreate()
        }

        layout.findViewById<Button>(R.id.btnPurple).setOnClickListener {
            lifecycleScope.launch {
                settingViewModel.setAppTheme(R.style.Theme_purple_Goal)
            }
            requireActivity().recreate()
        }

        layout.findViewById<Button>(R.id.btnWhite).setOnClickListener {
            lifecycleScope.launch {
                settingViewModel.setAppTheme(R.style.Theme_white_Goal)
            }
            requireActivity().recreate()
        }

        layout.findViewById<Button>(R.id.btnGray).setOnClickListener {
            lifecycleScope.launch {
                settingViewModel.setAppTheme(R.style.Theme_gray_Goal)
            }
            requireActivity().recreate()
        }

        layoutBackground.setOnClickListener {
            deleteLayout(layout)
        }

        return layout
    }


    private fun deleteLayout(layout: View){
        binding.fragmentSetting.removeView(layout)
    }
}