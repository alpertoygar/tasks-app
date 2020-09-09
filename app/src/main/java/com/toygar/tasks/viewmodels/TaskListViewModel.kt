package com.toygar.tasks.viewmodels

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.toygar.tasks.models.Task
import com.toygar.tasks.data.LocalDatabase
import com.toygar.tasks.repositories.LocalDbTaskRepository
import com.toygar.tasks.repositories.TaskRepository
import kotlinx.coroutines.launch

class TaskListViewModel(context: Context) : ViewModel() {
    private val taskRepository: TaskRepository =
        LocalDbTaskRepository(LocalDatabase.getInstance(context).taskDao())

    val tasks: LiveData<out List<Task>> = taskRepository.tasks

    fun deleteTask(task: Task){
        viewModelScope.launch {
            taskRepository.deleteTask(task)
        }
    }

    fun insertTask(task: Task) {
        viewModelScope.launch {
            taskRepository.insertTask(task)
        }
    }
}
