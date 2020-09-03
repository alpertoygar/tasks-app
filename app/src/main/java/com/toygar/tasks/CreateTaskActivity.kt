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
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_task)
    }

    fun create(view: View) {
        val taskName = findViewById<EditText>(R.id.editTextTaskName).text.toString()
        val taskPriority = findViewById<Spinner>(R.id.spinnerPriority).selectedItem.toString()
        val taskDescription = findViewById<EditText>(R.id.editTextDescription)?.text.toString()
        val task =
            Task(taskName, Priority.valueOf(taskPriority.toUpperCase(Locale.ROOT)), taskDescription)
        InMemoryTaskRepository.insertTask(task)
        startActivity(Intent(this, MainActivity::class.java))
    }
}
