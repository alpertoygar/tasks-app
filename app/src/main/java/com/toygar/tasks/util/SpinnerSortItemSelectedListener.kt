package com.toygar.tasks.util

import android.view.View
import android.widget.AdapterView
import com.toygar.tasks.adapters.TaskListAdapter

class SpinnerSortItemSelectedListener(
    val taskListAdapter: TaskListAdapter,
    val spinnerEntries: List<String>
) : AdapterView.OnItemSelectedListener {
    override fun onItemSelected(parent: AdapterView<*>?, selectedItemView: View?, position: Int, id: Long) {
        val sortField = spinnerEntries[position]
       taskListAdapter.sortData(sortField)
    }

    override fun onNothingSelected(parent: AdapterView<*>?) { }

}