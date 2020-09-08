package com.toygar.tasks

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.toygar.tasks.adapters.TaskListAdapter
import com.toygar.tasks.viewmodels.TaskListViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: TaskListViewModel
    private lateinit var recyclerView: RecyclerView
    private lateinit var taskListAdapter: TaskListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.toolbar))

        findViewById<FloatingActionButton>(R.id.fab).setOnClickListener {
            startActivity(Intent(this, CreateTaskActivity::class.java))
        }

        viewModel = TaskListViewModel(applicationContext)
        recyclerView = findViewById(R.id.recyclerview)
        taskListAdapter = TaskListAdapter(this, listOf(), viewModel::deleteTask)
        recyclerView.adapter = taskListAdapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        viewModel.tasks.observe(this, { tasks ->
            taskListAdapter.setData(tasks)
            recyclerView.smoothScrollToPosition(tasks.size)
        })
    }
}
