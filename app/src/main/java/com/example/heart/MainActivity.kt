package com.example.heart

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.widget.AdapterView
import android.widget.ListView
import androidx.appcompat.widget.Toolbar
import com.example.hear.DataSet
import com.example.heart.Model.DataModel

class MainActivity : AppCompatActivity() {
    lateinit var listView:ListView
    lateinit var Models:ArrayList<DataModel>

    companion object {
        lateinit var adapter:DataSet
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        listView = findViewById(R.id.list)
        val dataM = ArrayList<DataModel>()

        dataM.add(DataModel(90,150,50))
        dataM.add(DataModel(90,150,50))
        dataM.add(DataModel(90,150,50))
        dataM.add(DataModel(90,150,50))

        adapter = DataSet(dataM, applicationContext)
        listView.adapter = adapter



        /*
        setSupportActionBar(findViewById(R.id.toolbar))

        findViewById<FloatingActionButton>(R.id.fab).setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
        }*/
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }
}