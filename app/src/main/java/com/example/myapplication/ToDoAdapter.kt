package com.example.myapplication

import android.graphics.Paint.STRIKE_THRU_TEXT_FLAG
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.databinding.ItemTodoBinding

class ToDoAdapter(private val taskList: MutableList<ToDo>): RecyclerView.Adapter<ToDoAdapter.ToDoViewHolder>() {

    class ToDoViewHolder(val binding: ItemTodoBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ToDoViewHolder {
        val binding = ItemTodoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ToDoViewHolder(binding)
    }

    fun addTask(task: ToDo) {
        taskList.add(task)
        notifyItemInserted(taskList.size - 1)
    }

    fun deleteTask() {
        taskList.removeAll { task -> task.isCheck }
        notifyDataSetChanged()
    }

    private fun toggleStrikeThrough(tvToDoTitle: TextView, isCheck: Boolean) {
        if(isCheck) {
            tvToDoTitle.paintFlags = tvToDoTitle.paintFlags or STRIKE_THRU_TEXT_FLAG
        } else {
            tvToDoTitle.paintFlags = tvToDoTitle.paintFlags and STRIKE_THRU_TEXT_FLAG.inv()
        }
    }

    override fun onBindViewHolder(holder: ToDoViewHolder, position: Int) {
        val currentTask = taskList[position]
        holder.binding.apply {
            tvToDoTitle.text = currentTask.task
            doneButton.isChecked = currentTask.isCheck
            toggleStrikeThrough(tvToDoTitle, currentTask.isCheck)
            doneButton.setOnCheckedChangeListener { _, isCheck ->
                toggleStrikeThrough(tvToDoTitle, isCheck)
                currentTask.isCheck = !currentTask.isCheck
            }
        }
    }

    override fun getItemCount(): Int {
        return taskList.size
    }
}