package com.goalapp.presentation.view

import androidx.appcompat.app.AppCompatActivity

class EXMakeGoal : AppCompatActivity() {
    /*


    private var adView: AdView? = null
    private val mFormat = SimpleDateFormat("yyyy/M/d") // 날짜 포맷
    private var todoViewModel: TodoViewModel? = null
    var small_goal_data = ArrayList<String>()
    var view_small_goal_data = ArrayList<String>()
    var et_small_goal: EditText? = null
    var et_big_goal: EditText? = null
    var btn_plus_small_goal: Button? = null
    var btn_del_small_goal: Button? = null
    var btn_save_goal: Button? = null
    protected override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ThemeUtils.onActivityCreateSetTheme(this)
        setContentView(R.layout.make_goal)
        MobileAds.initialize(this, object : OnInitializationCompleteListener() {
            fun onInitializationComplete(initializationStatus: InitializationStatus?) {}
        })
        adView = findViewById(R.id.adView)
        val adRequest: AdRequest = Builder().build()
        adView.loadAd(adRequest)
        val listView2 = findViewById<View>(R.id.listview2) as ListView
        et_big_goal = findViewById<EditText>(R.id.et_big_goal)
        et_small_goal = findViewById<EditText>(R.id.et_small_goal)
        btn_plus_small_goal = findViewById<Button>(R.id.btn_plus_small_goal)
        btn_del_small_goal = findViewById<Button>(R.id.btn_del_small_goal)
        btn_save_goal = findViewById<Button>(R.id.btn_save_goal)
        val adapter: ArrayAdapter<*> =
            ArrayAdapter<Any?>(this, R.layout.list_type1, view_small_goal_data)
        listView2.adapter = adapter
        todoViewModel = ViewModelProvider(this).get(TodoViewModel::class.java)
        todoViewModel.getAllTodos().observe(this, object : Observer<List<Todo?>?> {
            override fun onChanged(todos: List<Todo?>) {
                btn_plus_small_goal!!.setOnClickListener {
                    //소목표 10개 입력받아서 리스트에 저장하는 소목표 추가 버튼 부분
                    if (et_small_goal.getText().toString().isEmpty() || et_small_goal.getText()
                            .toString().length == 0 || et_small_goal.getText().toString()
                            .replace(" ", "") == ""
                    ) {
                        Toast.makeText(getApplicationContext(), "소목표를 작성해주세요.", Toast.LENGTH_SHORT)
                            .show()
                    } //소목표 작성 칸에 아무것도 적지 않았을 경우인데 수정 필요!
                    else {
                        if (small_goal_data.size <= 9) //원래 10인줄 알았는데 9를 입력해야 10개까지 입력됨.
                        {
                            val str: String = et_small_goal.getText().toString()
                            small_goal_data.add(str)
                            view_small_goal_data.add(small_goal_data.size.toString() + "단계 : " + str)
                            et_small_goal.setText("") //입력한 값은 지워줘야지
                            adapter.notifyDataSetChanged()
                        } else if (small_goal_data.size > 9) {
                            Toast.makeText(
                                getApplicationContext(),
                                "소목표는 최대 10개까지 입력 가능합니다.",
                                Toast.LENGTH_SHORT
                            ).show()
                        } // 10개 이상 입력 시 제한문구 나옴.
                    }
                } //소목표 추가 버튼
                btn_del_small_goal!!.setOnClickListener {
                    val count: Int ///,checked ;
                    count = adapter.getCount()
                    Log.e("갯수 : ", count.toString())
                    if (count > 0) {
                        small_goal_data.removeAt(count - 1)
                        view_small_goal_data.removeAt(count - 1)
                    } else if (count == 0) {
                        Toast.makeText(
                            getApplicationContext(),
                            "작성된 소목표가 존재하지 않습니다!",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                    adapter.notifyDataSetChanged()
                } //소목표 삭제 버튼
                btn_save_goal!!.setOnClickListener {
                    if (et_big_goal.getText().toString().isEmpty() || et_big_goal.getText()
                            .toString().length == 0 || et_big_goal.getText().toString()
                            .replace(" ", "") == ""
                    ) {
                        Toast.makeText(getApplicationContext(), "대목표를 작성해주세요.", Toast.LENGTH_SHORT)
                            .show()
                    } else if (small_goal_data.isEmpty()) {
                        Toast.makeText(getApplicationContext(), "소목표를 작성해주세요.", Toast.LENGTH_SHORT)
                            .show()
                    } else {
                        val date = Date()
                        val make_time = mFormat.format(date)
                        todoViewModel.insert(
                            Todo(
                                et_big_goal.getText().toString(),
                                small_goal_data,
                                0,
                                make_time,
                                null
                            )
                        )
                        val intent: Intent =
                            Intent(getApplicationContext(), MainActivity::class.java)
                        intent.putExtra("big_goal_name", et_big_goal.getText().toString())
                        intent.putStringArrayListExtra("small_goal_name", small_goal_data)
                        startActivity(intent)
                        finish()
                    }
                }
            }
        })
    } // oncreate()

    override fun onBackPressed() {
        val intent: Intent = Intent(this@MakeGoal, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

     */



} //MakeGoal class
