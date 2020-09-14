package com.toygar.tasks

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.TimePicker
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.toygar.tasks.databinding.ActivityCreateTaskBinding
import com.toygar.tasks.viewmodels.CreateTaskViewModel
import java.util.*

class CreateTaskActivity : AppCompatActivity() {
    private val viewModel : CreateTaskViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel.inserted.observe(this, {
            if (it) startActivity(Intent(this, MainActivity::class.java))
        })

        val binding: ActivityCreateTaskBinding = DataBindingUtil.setContentView(this, R.layout.activity_create_task)
        binding.viewModel = viewModel
    }

    fun pickDate(view: View) {
        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)
        val hour = c.get(Calendar.HOUR_OF_DAY);
        val minute = c.get(Calendar.MINUTE);


        val tpd = TimePickerDialog(this, { timePicker: TimePicker, hour: Int, minute: Int ->

            // Display Selected date in textbox
            viewModel.taskDueDate += " " + hour + ":" + minute

        }, hour, minute, true)

        val dpd = DatePickerDialog(this, { view, year, monthOfYear, dayOfMonth ->

            // Display Selected date in textbox
            viewModel.taskDueDate = "" + dayOfMonth + "/" + monthOfYear + "/" + year

            tpd.show()
        }, year, month, day)

        dpd.show()
    }
}
