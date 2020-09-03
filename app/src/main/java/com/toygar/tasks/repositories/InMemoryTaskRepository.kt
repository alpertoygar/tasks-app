package com.toygar.tasks.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.toygar.tasks.models.Task

object InMemoryTaskRepository : TaskRepository {

    private val _tasks: MutableList<Task> = mutableListOf()
    private val tasks: MutableLiveData<List<Task>> = MutableLiveData()

    override fun insertTask(task: Task) {
        _tasks.add(task)
        tasks.value = _tasks
    }

    override fun getTasks(): LiveData<List<Task>> {
        return tasks
    }
}
