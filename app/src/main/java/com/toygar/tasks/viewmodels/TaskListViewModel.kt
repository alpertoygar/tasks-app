package com.toygar.tasks.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.toygar.tasks.models.Task
import com.toygar.tasks.repositories.InMemoryTaskRepository
import com.toygar.tasks.repositories.TaskRepository

class TaskListViewModel(
    taskRepository: TaskRepository = InMemoryTaskRepository
) : ViewModel() {
    val tasks: LiveData<out List<Task>> = taskRepository.getTasks()
}
