package com.toygar.tasks.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.toygar.tasks.R
import com.toygar.tasks.models.Task

class TaskListAdapter(
    context: Context,
    var taskList: List<Task>,
    private val deleteMethod: (Task) -> Unit
): RecyclerView.Adapter<TaskListAdapter.TaskViewHolder>() {

    private val inflater: LayoutInflater = LayoutInflater.from(context)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): TaskViewHolder {

        val itemView : View = inflater.inflate(R.layout.task_card, parent, false)
        return TaskViewHolder(itemView, this)
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        val current : Task = taskList[position]
        holder.bind(current, deleteMethod)
    }

    override fun getItemCount(): Int = taskList.size

    class TaskViewHolder(
        itemView: View,
        val adapter : TaskListAdapter
    ) : RecyclerView.ViewHolder(itemView) {

        val nameItemView : TextView = itemView.findViewById(R.id.taskName)
        val descriptionItemView : TextView = itemView.findViewById(R.id.taskDescription)
        val priorityItemView : TextView = itemView.findViewById(R.id.taskPriority)
        val deleteButton : ImageButton = itemView.findViewById(R.id.deleteButton)

        fun bind(task: Task, deleteMethod: (Task) -> Unit){
            nameItemView.text = task.name
            descriptionItemView.text = task.description
            priorityItemView.text = task.priority.name

            deleteButton.setOnClickListener {
                deleteMethod(task)
            }
        }
    }

}
