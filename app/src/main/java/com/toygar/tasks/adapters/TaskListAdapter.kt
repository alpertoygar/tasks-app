package com.toygar.tasks.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.toygar.tasks.databinding.TaskCardBinding
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

        val itemBinding : TaskCardBinding = TaskCardBinding.inflate(inflater, parent, false)
        return TaskViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        val current : Task = taskList[position]
        holder.bind(current, deleteMethod)
    }

    override fun getItemCount(): Int = taskList.size

    class TaskViewHolder(
        private val itemBinding: TaskCardBinding
    ) : RecyclerView.ViewHolder(itemBinding.root) {

        fun bind(task: Task, deleteMethod: (Task) -> Unit){
            itemBinding.task = task

            itemBinding.deleteButton.setOnClickListener {
                deleteMethod(task)
            }
        }
    }

}
