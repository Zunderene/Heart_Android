package heart

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.get
import androidx.core.view.size
import androidx.lifecycle.observe
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.heart.IO.SwipeDeleteCallback
import com.example.heart.R
import com.google.android.material.floatingactionbutton.FloatingActionButton
import heart.BD.BD_Heart
import heart.IO.HearViewModelFactory
import heart.IO.HeartViewModel
import heart.IO.HeatListAdapter
import heart.NewHeart.Companion.EXTRA_REPLY
import java.text.SimpleDateFormat
import java.util.*

class HeartMain : AppCompatActivity() {

    private val newWordActivityRequestCode = 1
    private val hVM: HeartViewModel by viewModels {
        HearViewModelFactory((application as HeartApplication).repositorio)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerview)
        val adapter = HeatListAdapter()
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        hVM.allHeart.observe(owner = this) { list ->
            list.let { adapter.submitList(it) }
        }


        val swipeHandler = object : SwipeDeleteCallback(this){
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val ad = recyclerView.adapter as HeatListAdapter
                val ob = ad.currentList[viewHolder.adapterPosition]
                hVM.delete(ob)
                ad.notifyItemChanged(viewHolder.adapterPosition)
                var i = 0
                while (recyclerView.size > i){
                    if(i % 2 == 0 || i == 0)recyclerView[i].setBackgroundColor(Color.GRAY)
                    else recyclerView[i].setBackgroundColor(Color.DKGRAY)
                    i = i + 1
                }

            }
        }

        val itemTouchHelper = ItemTouchHelper(swipeHandler)
        itemTouchHelper.attachToRecyclerView(recyclerView)


        val fab = findViewById<FloatingActionButton>(R.id.fab)
        fab.setOnClickListener{
            val intent = Intent(this.applicationContext, NewHeart::class.java)
            startActivityForResult(intent, newWordActivityRequestCode)
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, intentData: Intent?) {
        super.onActivityResult(requestCode, resultCode, intentData)
        var sys:String = "0"
        var dys:String = "0"
        var pul:String = "0"
        var IDESTR = arrayOf("Bajo", "Normal", "Normal Alto", "Muy Alto")

        if (requestCode == newWordActivityRequestCode && resultCode == Activity.RESULT_OK && intentData != null) {
            intentData.getStringExtra(EXTRA_REPLY)?.let { reply ->
                reply.split("/").forEachIndexed{ac,v ->
                    when(ac){
                        0 -> sys = v
                        1 -> dys = v
                        2 -> pul = v

                    }
                }
            }

            val cal = (sys.toInt() + 2 * dys.toInt()) / 3.0f
            var STATUS:String = "NA"
            when{
                cal > 0  && cal < 69 -> STATUS = IDESTR[0]
                cal >= 70  && cal < 105 -> STATUS = IDESTR[1]
                cal >= 105  && cal < 250 -> STATUS = IDESTR[2]
                cal >= 250 -> STATUS = IDESTR[3]
            }

            hVM.insert(BD_Heart(UUID.randomUUID().toString(), sys.toInt(), dys.toInt(), pul.toInt(), STATUS, SimpleDateFormat("YYYY-MM-dd HH:mm").format(Date()).toString()))
        } else {
            Toast.makeText(
                    applicationContext,
                    "No guardado",
                    Toast.LENGTH_LONG
            ).show()
        }
    }
}