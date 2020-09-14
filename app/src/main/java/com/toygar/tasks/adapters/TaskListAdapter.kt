package com.toygar.tasks.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.toygar.tasks.databinding.TaskCardBinding
import com.toygar.tasks.models.Task
import com.toygar.tasks.util.TaskDiffCallback

class TaskListAdapter(
    private val deleteMethod: (Task) -> Unit
): ListAdapter<Task, TaskListAdapter.TaskViewHolder>(TaskDiffCallback()) {

    private var comparator: Comparator<Task> = compareBy(Task::dueDate)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): TaskViewHolder {
        val inflater: LayoutInflater = LayoutInflater.from(parent.context)
        val itemBinding : TaskCardBinding = TaskCardBinding.inflate(inflater, parent, false)
        return TaskViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        val current : Task = currentList[position]
        holder.bind(current, deleteMethod)
    }

    override fun getItemCount(): Int = currentList.size

    fun setData(newTasks: List<Task>) {
        val newSortedTasks = newTasks.sortedWith(comparator)
        submitList(newSortedTasks)
    }

    fun sortData(sortField: String) {
        val hm = hashMapOf<String, Comparator<Task>>()
        hm.put("Name", compareBy(Task::name))
        hm.put("Due Date", compareBy(Task::dueDate))
        hm.put("Priority", compareBy(Task::priority))
        hm.withDefault { compareBy(Task::dueDate) }

        comparator = hm[sortField]!!

        setData(currentList)
    }

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
