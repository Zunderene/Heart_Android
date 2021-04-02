package heart.IO

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.heart.R
import heart.BD.BD_Heart

class HeatListAdapter : ListAdapter<BD_Heart, HeatListAdapter.HeartViewHolder>(HEAR_COMPARATOR){

    companion object{
        private val HEAR_COMPARATOR = object : DiffUtil.ItemCallback<BD_Heart>(){
            override fun areItemsTheSame(oldItem: BD_Heart, newItem: BD_Heart): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: BD_Heart, newItem: BD_Heart): Boolean {
                return oldItem.id == newItem.id
            }
        }
    }

    class HeartViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val id_img = arrayListOf<Int>(R.drawable.img_pul, R.drawable.img_ult, R.drawable.img_alt,
        R.drawable.img_alt_up, R.drawable.img_equ)

        private val SYS: TextView = itemView.findViewById(R.id.TSYS)
        private val DYS: TextView = itemView.findViewById(R.id.TDYS)
        private val PUL: TextView = itemView.findViewById(R.id.TPUL)
        private val EST: TextView = itemView.findViewById(R.id.Status)
        private val SAV: TextView = itemView.findViewById(R.id.Fecha)
        private val ISYS: ImageView = itemView.findViewById(R.id.Sys)
        private val IDYS: ImageView = itemView.findViewById(R.id.Dys)
        private val IPUL: ImageView = itemView.findViewById(R.id.Pul)
        private val row: RelativeLayout = itemView.findViewById(R.id.row)


        fun bind(Heart:BD_Heart, position: Int) {
            SYS.text = Heart.SYS.toString()
            DYS.text = Heart.DYS.toString()
            PUL.text = Heart.PUL.toString()
            EST.text = Heart.EST
            SAV.text = Heart.SAV
            IPUL.setImageResource(R.drawable.img_pul)


            when(Heart.SYS){
               in 0..99 -> ISYS.setImageResource(id_img[3])
                in 100..129 -> ISYS.setImageResource(id_img[4])
                in 130..139 -> ISYS.setImageResource(id_img[2])
                in 140..300 -> ISYS.setImageResource(id_img[1])
            }
            when(Heart.DYS){
                in 0..79 -> IDYS.setImageResource(id_img[3])
                in 80..85 -> IDYS.setImageResource(id_img[4])
                in 86..90 -> IDYS.setImageResource(id_img[2])
                in 91..300 -> IDYS.setImageResource(id_img[1])
            }
        }


        companion object {
            fun create(parent: ViewGroup): HeartViewHolder {
                val view: View = LayoutInflater.from(parent.context)
                        .inflate(R.layout.row_itme, parent, false)
                return HeartViewHolder(view)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HeartViewHolder {
        return HeartViewHolder.create(parent)
    }


    override fun onBindViewHolder(holder: HeartViewHolder, position: Int) {
        val current = getItem(position)
        holder.bind(current, position)
    }


}