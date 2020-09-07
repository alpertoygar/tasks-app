package com.toygar.tasks.viewmodels

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.toygar.tasks.models.Task
import com.toygar.tasks.repositories.LocalDatabase
import com.toygar.tasks.repositories.TaskRepository
import kotlinx.coroutines.launch

class CreateTaskViewModel(context: Context) : ViewModel() {
    private val taskRepository = TaskRepository(LocalDatabase.getInstance(context)!!.taskDao())

    fun insertTask(task: Task) {
        viewModelScope.launch {
            taskRepository.insertTask(task)
        }
    }
}
