package com.example.passmng


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.View.*
import android.webkit.RenderProcessGoneDetail
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.view.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch




class MainActivity : AppCompatActivity() {
    private lateinit var database: myDatabase
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        database = Room.databaseBuilder(
            applicationContext, myDatabase::class.java, "Pwd_Manager"
        ).build()
        add.setOnClickListener {
            val intent = Intent(this, CreateCard::class.java)
            startActivity(intent)
        }
        EditDel.setOnClickListener {
            MaterialAlertDialogBuilder(this)
                .setTitle("Alert!")
                .setMessage("Are you sure you want to delete all passwords?")
                .setNeutralButton("Cancel"){ dialog, which ->}
                .setPositiveButton("CONFIRM"){dialog, which ->

                    DataObject.deleteAll()
                    GlobalScope.launch {
                        database.dao().deleteAll()
                    }
                    setRecycler()

                }
                .show()
//            EditDel.visibility = GONE


//            cancelBtn.visibility = VISIBLE
//            checkBox.visibility = VISIBLE

//            DataObject.deleteAll()
//            GlobalScope.launch {
//                database.dao().deleteAll()
//            }
//            setRecycler()
        }
/*        cancelBtn.setOnClickListener{
            EditDel.visibility = VISIBLE
            cancelBtn.visibility = GONE

        }*/

        setRecycler()

    }

    fun setRecycler() {
        recycler_view.adapter = Adapter(DataObject.getAllData())
        recycler_view.layoutManager = LinearLayoutManager(this)
    }



}
