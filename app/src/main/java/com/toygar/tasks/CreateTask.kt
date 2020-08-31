package com.toygar.tasks

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Spinner
import com.toygar.models.Priority
import com.toygar.models.TASKS
import com.toygar.models.Task
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
        for (i in 0..50){
            TASKS.add(task)
        }
        startActivity(Intent(this, MainActivity::class.java))
    }
}