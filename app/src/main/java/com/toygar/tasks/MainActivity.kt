package com.toygar.tasks

import android.content.Intent
import android.os.Bundle
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.toygar.tasks.adapters.TaskListAdapter
import com.toygar.tasks.models.Task
import com.toygar.tasks.util.SpinnerSortItemSelectedListener
import com.toygar.tasks.util.StringUtil
import com.toygar.tasks.viewmodels.TaskListViewModel
import java.lang.reflect.Field

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: TaskListViewModel
    private lateinit var recyclerView: RecyclerView
    private lateinit var taskListAdapter: TaskListAdapter

    private lateinit var spinnerSort : Spinner

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


        spinnerSort = findViewById(R.id.spinnerSort)

        val spinnerEntries = Task::class.java.declaredFields
            .map(Field::getName)
            .map{StringUtil.camelCaseToTitleCase(it)}
            .filter { it != "Id" }

        val spinnerAdapter: ArrayAdapter<String> = ArrayAdapter(
            this,
            R.layout.support_simple_spinner_dropdown_item,
            spinnerEntries)

        spinnerSort.adapter = spinnerAdapter

        spinnerSort.onItemSelectedListener =
            SpinnerSortItemSelectedListener(taskListAdapter, spinnerEntries)
    }
}
