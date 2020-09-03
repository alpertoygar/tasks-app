package com.toygar.tasks

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.toygar.tasks.models.Task
import com.toygar.tasks.viewmodels.TaskListViewModel

class MainActivity : AppCompatActivity() {

    private val viewModel: TaskListViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.toolbar))

        findViewById<FloatingActionButton>(R.id.fab).setOnClickListener {
            startActivity(Intent(this, CreateTaskActivity::class.java))
        }

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

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }
}
