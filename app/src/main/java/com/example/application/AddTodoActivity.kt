package com.example.application
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.CheckBox
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

class AddTodoActivity : AppCompatActivity() {
    private lateinit var todoEditText: EditText
    private lateinit var todoCheckBox: CheckBox

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_todo)

        todoEditText = findViewById(R.id.todoEditText)
        todoCheckBox = findViewById(R.id.todoCheckBox)
    }

    fun onSaveClick(view: View) {
        val todoEditText = findViewById<EditText>(R.id.todoEditText)
        val todoCheckBox = findViewById<CheckBox>(R.id.todoCheckBox)

        val todo = todoEditText.text.toString()
        val isCompleted = todoCheckBox.isChecked

        val resultIntent = Intent()
        resultIntent.putExtra(EXTRA_TODO, todo)
        resultIntent.putExtra(EXTRA_COMPLETED, isCompleted)

        setResult(RESULT_OK, resultIntent)
        finish()
    }

    companion object {
        const val EXTRA_TODO = "extra_todo"
        const val EXTRA_COMPLETED = "extra_completed"
    }
}
