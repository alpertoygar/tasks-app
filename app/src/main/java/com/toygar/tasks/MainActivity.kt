package com.toygar.tasks

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.toygar.tasks.adapters.TaskListAdapter
import com.toygar.tasks.databinding.ActivityMainBinding
import com.toygar.tasks.util.SpinnerSortItemSelectedListener
import com.toygar.tasks.viewmodels.TaskListViewModel

class MainActivity : AppCompatActivity() {

    private val viewModel: TaskListViewModel by viewModels()

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        setSupportActionBar(binding.toolbar)

        binding.fab.setOnClickListener {
            startActivity(Intent(this, CreateTaskActivity::class.java))
        }

        val taskListAdapter = TaskListAdapter(viewModel::deleteTask)
        binding.recyclerview.adapter = taskListAdapter
        binding.recyclerview.layoutManager = LinearLayoutManager(this)

        val spinnerAdapter: ArrayAdapter<String> = ArrayAdapter(
            this,
            R.layout.support_simple_spinner_dropdown_item,
            viewModel.spinnerEntries)

        binding.spinnerSort.adapter = spinnerAdapter

        binding.spinnerSort.onItemSelectedListener =
            SpinnerSortItemSelectedListener(taskListAdapter, viewModel.spinnerEntries)

        setDefaultSort("Due Date")

        viewModel.tasks.observe(this, {
            taskListAdapter.setData(it)
            binding.recyclerview.smoothScrollToPosition(it.size)
        })
    }

    private fun setDefaultSort(field: String) {
        viewModel.getSpinnerPosition(field)
        binding.spinnerSort.setSelection(viewModel.spinnerPosition)
    }
}
