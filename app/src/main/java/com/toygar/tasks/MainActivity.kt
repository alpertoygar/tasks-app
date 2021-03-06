package com.toygar.tasks

import android.content.Intent
import android.os.Bundle
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.toygar.tasks.models.Task
import com.toygar.tasks.viewmodels.TaskListViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: TaskListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.toolbar))

        findViewById<FloatingActionButton>(R.id.fab).setOnClickListener {
            startActivity(Intent(this, CreateTaskActivity::class.java))
        }

        viewModel = TaskListViewModel(applicationContext)

        val layout = findViewById<LinearLayout>(R.id.mainLayout)
        viewModel.tasks.observe(this, Observer<List<Task>> { tasks ->
            tasks.forEach { task ->
                val view = TextView(this)
                val layoutParams = LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
                )
                layoutParams.setMargins(
                    resources.getDimension(R.dimen.task_left_margin).toInt(),
                    resources.getDimension(R.dimen.task_top_margin).toInt(),
                    resources.getDimension(R.dimen.task_right_margin).toInt(),
                    resources.getDimension(R.dimen.task_bottom_margin).toInt())
                view.layoutParams = layoutParams
                val viewText = "%s - %s \n%s".format(task.name, task.priority, task.description)
                view.text = viewText
                layout.addView(view)
            }
        })

    }
}
