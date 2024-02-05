package com.example.application

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.graphics.Color
import android.widget.Button
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {
    private lateinit var todoListView: ListView
    private lateinit var todoList: MutableList<String>
    private lateinit var adapter: ArrayAdapter<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        todoList = mutableListOf()
        adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, todoList)
        todoListView = findViewById(R.id.todoListView)
        todoListView.adapter = adapter

    }
    fun changeButtonColor(view: View) {
        val button = view as Button

        // Change button color only when clicked
        button.setBackgroundColor(Color.BLUE)
    }

    fun onAddTodoClick(view: View) {
        val intent = Intent(this, AddTodoActivity::class.java)
        startActivityForResult(intent, ADD_TODO_REQUEST_CODE)
    }



    // MainActivity 내의 onActivityResult 함수 수정
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == ADD_TODO_REQUEST_CODE && resultCode == RESULT_OK) {
            val newTodo = data?.getStringExtra(AddTodoActivity.EXTRA_TODO)
            val isCompleted = data?.getBooleanExtra(AddTodoActivity.EXTRA_COMPLETED, false) ?: false

            newTodo?.let {
                val todoItem = if (isCompleted) {
                    "[Completed] $it"
                } else {
                    it
                }
                todoList.add(todoItem)
                adapter.notifyDataSetChanged()
            }
        }
    }

    companion object {
        const val ADD_TODO_REQUEST_CODE = 1
    }
}
