package com.toygar.tasks

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Spinner
import com.toygar.tasks.models.Priority
import com.toygar.tasks.models.TASKS
import com.toygar.tasks.models.Task
import com.toygar.tasks.repositories.InMemoryTaskRepository
import com.toygar.tasks.repositories.TASK_REPOSITORY
import java.util.*

class CreateTask : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_task)
    }

    fun create(view: View) {
        val taskName = findViewById<EditText>(R.id.editTextTaskName).text.toString()
        val taskPriority = findViewById<Spinner>(R.id.spinnerPriority).selectedItem.toString()
        val taskDescription = findViewById<EditText>(R.id.editTextDescription)?.text.toString()
        val task = Task(taskName, Priority.valueOf(taskPriority.toUpperCase(Locale.ROOT)), taskDescription)
        TASK_REPOSITORY.insertTask(task)
        startActivity(Intent(this, MainActivity::class.java))
    }
}