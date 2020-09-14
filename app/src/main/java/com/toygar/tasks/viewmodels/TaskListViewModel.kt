package com.toygar.tasks.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.toygar.tasks.models.Task
import com.toygar.tasks.data.LocalDatabase
import com.toygar.tasks.models.SortableTaskFields
import com.toygar.tasks.repositories.LocalDbTaskRepository
import com.toygar.tasks.repositories.TaskRepository
import kotlinx.coroutines.launch

class TaskListViewModel(application: Application) : AndroidViewModel(application) {
    private val taskRepository: TaskRepository =
        LocalDbTaskRepository(LocalDatabase.getInstance(getApplication()).taskDao())

    val tasks: LiveData<out List<Task>> = taskRepository.tasks

    val spinnerEntries = SortableTaskFields.values().map(SortableTaskFields::toHumanString).toList()

    var spinnerPosition = 1

    fun deleteTask(task: Task) {
        viewModelScope.launch {
            taskRepository.deleteTask(task)
        }
    }

    fun getSpinnerPosition(field: String) {
        spinnerPosition = spinnerEntries.indexOf(field)
    }
}
