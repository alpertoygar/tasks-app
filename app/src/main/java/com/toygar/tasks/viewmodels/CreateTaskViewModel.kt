package com.toygar.tasks.viewmodels

import android.app.Application
import androidx.lifecycle.*
import com.toygar.tasks.models.Task
import com.toygar.tasks.data.LocalDatabase
import com.toygar.tasks.models.Priority
import com.toygar.tasks.repositories.LocalDbTaskRepository
import com.toygar.tasks.repositories.TaskRepository
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

class CreateTaskViewModel(application: Application) : AndroidViewModel(application) {
    private val taskRepository: TaskRepository =
        LocalDbTaskRepository(LocalDatabase.getInstance(application).taskDao())

    var taskName = ""
    var taskDescription = ""
    var taskPriorityIndex = 0
    var taskDueDate = "%s 12:00".format(
        SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(
            Date()
        ))
    val spinnerEntries = Priority.values().map(Priority::toHumanString).toList()

    var inserted: MutableLiveData<Boolean> = MutableLiveData(false)

    fun insertTask() {
        val task = Task(
            UUID.randomUUID().toString(),
            taskName,
            Priority.values()[taskPriorityIndex],
            taskDescription,
            Date.parse(taskDueDate))
        viewModelScope.launch {
            taskRepository.insertTask(task)
        }
        inserted.value = true
    }
}
