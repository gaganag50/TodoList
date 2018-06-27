package com.example.android.todo.db

import android.content.ContentValues
import android.database.sqlite.SQLiteDatabase
import com.example.android.todo.Todo


// this is just holding the commands to create the table
object TableCommands {
    val TABLE_NAME = "todos"

    object Columns {
        val ID = "id"
        val Task = "task"
        val done = "done"
    }





    val CMD_CREATE_TABLE = """
        CREATE TABLE IF NOT EXISTS ${TABLE_NAME}
        (
        ${Columns.ID} INTEGER PRIMARY KEY AUTOINCREMENT,
        ${Columns.Task} TEXT,
        ${Columns.done} BOOLEAN
        );
    """.trimIndent()





    fun insertTodo(db: SQLiteDatabase, todos: Todo) {
        val row = ContentValues()
        row.put(Columns.Task, todos.task)
        row.put(Columns.done, todos.done)
        db.insert(TABLE_NAME, null, row)

    }

    fun getAllTodos(db: SQLiteDatabase): ArrayList<Todo> {
        val Todos = ArrayList<Todo>()
        val query = db.query(
                TABLE_NAME,
                arrayOf(Columns.ID, Columns.Task, Columns.done),
                null, null,
                null, null, null)
        while (query.moveToNext()) {
            Todos.add(Todo(
                    query.getString(1),
                    query.getInt(2) == 1))
        }
        query.close()
        return Todos
    }

//    fun updateTodo(db: SQLiteDatabase?, done: Boolean) {
//        val row = ContentValues()
//        row.put(Columns.done, done)
//
//        db?.update(TABLE_NAME, row, null ,null)
//    }
}