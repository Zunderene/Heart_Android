package com.example.hear

import com.example.heart.Model.DataModel
import android.content.Context
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.RequiresApi
import com.example.heart.R
import java.lang.String.format
import java.text.SimpleDateFormat
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle
import java.util.*
import kotlin.collections.ArrayList

class DataSet(data: ArrayList<DataModel>, context: Context?) : ArrayAdapter<DataModel?>(context!!, R.layout.row_itme, data as List<DataModel?>), View.OnClickListener {
    var mContext: Context? = null
    var lastPosition:Int = -1

    private class ViewHolde {
        var DYS: TextView? = null
        var SYS: TextView? = null
        var PUL: TextView? = null
        var STATE: TextView? = null
        var DATE: TextView? = null
        var IDYS: ImageView? = null
        var ISYS: ImageView? = null
        var IPUL: ImageView? = null
    }

    @Override
    override fun onClick(v: View) {}

    @RequiresApi(Build.VERSION_CODES.O)
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val dm: DataModel? = getItem(position)
        val viewHolder:ViewHolde
        val result:View
        val ctw: View?

        if (convertView == null){
            viewHolder = ViewHolde()
            val inflater:LayoutInflater = LayoutInflater.from(context)
            ctw = inflater.inflate(R.layout.row_itme, parent, false)
            viewHolder.DATE     = ctw.findViewById(R.id.Fecha)
            viewHolder.DYS      = ctw.findViewById(R.id.TDYS)
            viewHolder.PUL      = ctw.findViewById(R.id.TPUL)
            viewHolder.SYS      = ctw.findViewById(R.id.TSYS)
            viewHolder.STATE    = ctw.findViewById(R.id.Status)
            viewHolder.IDYS     = ctw.findViewById(R.id.Dys)
            viewHolder.ISYS     = ctw.findViewById(R.id.Sys)
            viewHolder.IPUL     = ctw.findViewById(R.id.Pul)

            result = ctw
            ctw.tag = viewHolder
        }

        else{
            viewHolder = convertView.tag as ViewHolde
            result = convertView
        }

        val animacion:Animation = if (position > lastPosition)
            AnimationUtils.loadAnimation(context,R.anim.up_from_botton)
        else
            AnimationUtils.loadAnimation(context, R.anim.down_from_top)

        result.startAnimation(animacion)
        lastPosition = position

        if (dm != null) {
            viewHolder.SYS?.text    = dm.DYS.toString()
            viewHolder.STATE?.text  = dm.STATUS
            viewHolder.PUL?.text    = dm.PUL.toString()
            viewHolder.DYS?.text    = dm.DYS.toString()
            viewHolder.DATE?.text   = SimpleDateFormat("YYYY-MM-dd hh:mm:ss").format(dm.SAVE)
        }


        return result
    }
}