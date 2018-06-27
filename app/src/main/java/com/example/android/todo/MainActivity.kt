package com.example.android.todo

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import com.example.android.todo.db.MyDbHelper
import com.example.android.todo.db.TableCommands.getAllTodos
import com.example.android.todo.db.TableCommands.insertTodo
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    val todos = ArrayList<Todo>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val db = MyDbHelper(this).writableDatabase
        // this is acquiring all the existing todos
        val adapter = RecyclerListAdapter(this, todos)



        rvTodo.layoutManager = LinearLayoutManager(this)

        fun refreshTodoList() {
            // we will delete the existing work list acquired in the val todos and replace with the inserted work

            val allTodos = getAllTodos(db)
            Log.d("MainAct", allTodos.toList().toString())
            todos.clear()

            // next the work is added in the arrayList
            todos.addAll(allTodos)
            Log.d("MainAct", todos.toList().toString())


            // at last the data is set to the view
            adapter.notifyDataSetChanged()


        }
        refreshTodoList()

        rvTodo.adapter = adapter

        refreshTodoList()



        btnAdd.setOnClickListener {
            Log.d("MainAct", "Button clicked")
            val nexWorkToDo = etNewTodo.text.toString()

            // first the work to be done is added in the database
            insertTodo(db, Todo(
                    nexWorkToDo,
                    false
            ))

            refreshTodoList()

        }

    }


}
