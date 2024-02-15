package com.goalapp.presentation

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.AttributeSet
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatDelegate
import androidx.datastore.dataStore
import androidx.fragment.app.viewModels
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.goalapp.presentation.databinding.ActivityMainBinding
import com.goalapp.presentation.view.goallist.RunningGoalListViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlin.properties.Delegates


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val mainViewModel: MainViewModel by viewModels()
    lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //앱이 실행될 때 MainViewModel에서 DataStore에 담겨있는 themeId를 가져옴.
        val themePreferences = runBlocking {
            if(mainViewModel.themeId.first()!=null){
                mainViewModel.themeId.first()

            }
            else{
                R.style.Theme_gray_Goal
            }
        }
        setTheme(themePreferences)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fragment_container_view) as NavHostFragment
        navController = navHostFragment.navController

        binding.navBar.setupWithNavController(navController)


    }
    //자식 프래그먼트에서 BottomNavi 가릴 수 있게 지원 함수
    fun HideBottomNavi(state: Boolean){
        if(state) binding.navBar.visibility = View.GONE else binding.navBar.visibility = View.VISIBLE
    }

}