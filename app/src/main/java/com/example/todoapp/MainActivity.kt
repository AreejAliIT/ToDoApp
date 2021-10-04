package com.example.todoapp

import android.annotation.SuppressLint
import android.content.Context
import android.content.DialogInterface
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {

    private lateinit var myCL : ConstraintLayout
    private lateinit var rvMain : RecyclerView
    private lateinit var tasks : ArrayList<String>


    var myTaks: String = ""
   lateinit var sharedPreferences : SharedPreferences
   var myMessage = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // init Tasks Array
        myCL = findViewById(R.id.myCL)
        tasks = ArrayList()
        //set RV Adaptor
        rvMain = findViewById(R.id.rvMain)
        rvMain.adapter = Tasks(this, tasks)
        rvMain.layoutManager = LinearLayoutManager(this)

        val plusBtn = findViewById<FloatingActionButton>(R.id.plusBtn)
        plusBtn.setOnClickListener(){
            showAlert("Enter yor task: ")
        }
        //shared preference
        sharedPreferences = this.getSharedPreferences(
            getString(R.string.preference_file_key), Context.MODE_PRIVATE)
        myMessage = sharedPreferences.getString("myMessage", "").toString()  // --> retrieves data from Shared Preferences
// We can save data with the following code
        with(sharedPreferences.edit())
        {
            putString("myMessage", myMessage)
            apply()
        }

    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        myTaks = savedInstanceState.getString("myTasks", "No task")
    }
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        outState.putString("myTasks", myTaks)
    }
    private fun showAlert(title:String) {
        // 1 build alert dialog
        val dialogBuilder = AlertDialog.Builder(this)
        // 2 set message of alert dialog
        dialogBuilder.setMessage(title)
        .setCancelable(false)

        val layout = LinearLayout(this)
        val inputTask = EditText(this)
        layout.addView(inputTask)

        dialogBuilder.setPositiveButton("Add", DialogInterface.OnClickListener {
                    dialog, id -> tasks.add(inputTask.text.toString())
                          rvMain.adapter?.notifyDataSetChanged()

            })
            .setNegativeButton("Cancel", DialogInterface.OnClickListener {
                    dialog, id -> dialog.cancel()
            })
        // 5 create dialog box
        val alert = dialogBuilder.create()
        alert.setTitle("New task")
        alert.setView(layout)
        alert.show()
    }
}