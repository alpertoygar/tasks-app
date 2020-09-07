package com.toygar.tasks

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity
import com.toygar.tasks.models.Priority
import com.toygar.tasks.models.Task
import com.toygar.tasks.repositories.InMemoryTaskRepository
import java.util.*

class CreateTaskActivity : AppCompatActivity() {
    private lateinit var editTextTaskName : EditText
    private lateinit var spinnerTaskPriority : Spinner
    private lateinit var editTextTaskDescription : EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_task)
        editTextTaskName = findViewById(R.id.editTextTaskName)
        spinnerTaskPriority = findViewById(R.id.spinnerPriority)
        editTextTaskDescription = findViewById(R.id.editTextDescription)
    }

    fun create(view: View) {
        val taskName = editTextTaskName.text.toString()
        val taskPriority = spinnerTaskPriority.selectedItem.toString()
        val taskDescription = editTextTaskDescription.text.toString()
        val task =
            Task(taskName, Priority.valueOf(taskPriority.toUpperCase(Locale.ROOT)), taskDescription)
        InMemoryTaskRepository.insertTask(task)
        startActivity(Intent(this, MainActivity::class.java))
    }
}
