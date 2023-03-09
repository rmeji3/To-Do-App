package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.example.myapplication.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var todoAdapter: ToDoAdapter
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        todoAdapter = ToDoAdapter(mutableListOf())

        binding.rvToDoList.adapter = todoAdapter

        binding.rvToDoList.layoutManager = LinearLayoutManager(this)

        binding.addButton.setOnClickListener {
            val taskTitle = binding.ToDoListTitle.text.toString()
            if (taskTitle.isNotEmpty()) {
                val task = ToDo(taskTitle)
                todoAdapter.addTask(task)
                binding.ToDoListTitle.text.clear()
            }
        }

        binding.CompleteButton.setOnClickListener { todoAdapter.deleteTask() }
    }
}