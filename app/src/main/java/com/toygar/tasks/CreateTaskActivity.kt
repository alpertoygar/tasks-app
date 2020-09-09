package com.toygar.tasks

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import android.widget.TimePicker
import androidx.appcompat.app.AppCompatActivity
import com.toygar.tasks.models.Priority
import com.toygar.tasks.models.Task
import java.text.SimpleDateFormat
import java.util.*

class CreateTaskActivity : AppCompatActivity() {
    private lateinit var editTextTaskName : EditText
    private lateinit var spinnerTaskPriority : Spinner
    private lateinit var editTextTaskDescription : EditText
    private lateinit var textViewDueDate : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_task)
        editTextTaskName = findViewById(R.id.editTextTaskName)
        spinnerTaskPriority = findViewById(R.id.spinnerPriority)
        editTextTaskDescription = findViewById(R.id.editTextDescription)
        textViewDueDate = findViewById(R.id.textViewDueDate)
        textViewDueDate.text = "%s 12:00".format(SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(Date()))
    }

    fun create(view: View) {
        val taskName = editTextTaskName.text.toString()
        val taskPriority = spinnerTaskPriority.selectedItem.toString()
        val taskDescription = editTextTaskDescription.text.toString()
        val taskDueDate = textViewDueDate.text.toString()
        val task = Task(
            UUID.randomUUID().toString(),
            taskName,
            Priority.valueOf(taskPriority.toUpperCase(Locale.ROOT)),
            taskDescription,
            Date.parse(taskDueDate))

        val resultIntent = Intent()
        resultIntent.putExtra("TASK", task)
        setResult(1, resultIntent)
        finish()
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
            textViewDueDate.text = textViewDueDate.text.toString() + " " + hour + ":" + minute

        }, hour, minute, true)

        val dpd = DatePickerDialog(this, { view, year, monthOfYear, dayOfMonth ->

            // Display Selected date in textbox
            textViewDueDate.text = "" + dayOfMonth + "/" + monthOfYear + "/" + year

            tpd.show()
        }, year, month, day)

        dpd.show()


    }
}
