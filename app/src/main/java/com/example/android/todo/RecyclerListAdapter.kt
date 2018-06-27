package com.example.android.todo

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.android.todo.db.MyDbHelper
import com.example.android.todo.db.TableCommands
import kotlinx.android.synthetic.main.activity_show.view.*



// this is the adapter which is the pipe between the data and the view which is shown on the screen

class RecyclerListAdapter(val context: Context, val todos: ArrayList<Todo>) :
        RecyclerView.Adapter<RecyclerListAdapter.TodoViewHolder>() {
    override fun getItemCount(): Int = todos.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        val li = parent.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val itemView = li.inflate(R.layout.activity_show, parent, false)

        return TodoViewHolder(itemView)
    }


    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        holder.comleted.setOnCheckedChangeListener(null)

        holder.newTodo.text = todos[position].task
        // we will check the arraylist for the value of done
        holder.comleted.isChecked = todos[position].done

        holder.comleted.setOnCheckedChangeListener { _, isChecked ->
            // if changed we will first set it is the arraylist



//            val db = MyDbHelper(context).writableDatabase
//            TableCommands.updateTodo(
//                    db,
//                    isChecked
//            )

            todos[position].done = isChecked

        }
    }

    class TodoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val newTodo = itemView.tvTodo
        val comleted = itemView.checkbox
    }

}
