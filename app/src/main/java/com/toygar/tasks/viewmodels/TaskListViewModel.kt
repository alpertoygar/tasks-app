package com.toygar.tasks.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.toygar.tasks.models.Task
import com.toygar.tasks.repositories.InMemoryTaskRepository

class TaskListViewModel : ViewModel(){
    private val tasks: MutableLiveData<List<Task>> by lazy {
        MutableLiveData<List<Task>>().also {
            loadTasks()
        }
    }

    fun getTasks() : LiveData<List<Task>> {
        return tasks
    }

    private fun loadTasks(): List<Task> {
        return InMemoryTaskRepository().getTasks()
    }
}