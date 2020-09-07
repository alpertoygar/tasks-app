package com.toygar.tasks.viewmodels

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.toygar.tasks.models.Task
import com.toygar.tasks.repositories.LocalDatabase
import com.toygar.tasks.repositories.TaskRepository

class TaskListViewModel(context: Context) : ViewModel() {
    private val taskRepository = TaskRepository(LocalDatabase.getInstance(context)!!.taskDao())

    val tasks: LiveData<out List<Task>> = taskRepository.tasks
}
