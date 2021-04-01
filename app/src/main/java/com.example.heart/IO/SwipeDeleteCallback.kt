package com.example.heart.IO

import android.content.Context
import android.graphics.*
import android.graphics.drawable.ColorDrawable
import android.text.style.BackgroundColorSpan
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.heart.R

abstract class SwipeDeleteCallback(context: Context):
    ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT){
    private val iconDelete = ContextCompat.getDrawable(context, R.drawable.id_delete)
    private val intrinsicH = iconDelete?.intrinsicHeight
    private val instrisicW = iconDelete?.intrinsicWidth
    private val backgraund = ColorDrawable()
    private val backgraundColor = Color.parseColor("#f44336")
    private val clearPaint = Paint().apply { xfermode = PorterDuffXfermode(PorterDuff.Mode.CLEAR) }

    override fun onMove(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, target: RecyclerView.ViewHolder): Boolean {
        return false
    }

    override fun onChildDraw(c: Canvas, recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, dX: Float, dY: Float, actionState: Int, isCurrentlyActive: Boolean) {
        val itemView = viewHolder.itemView
        val itemHeight = itemView.bottom - itemView.top

        val isCanceled = dX == 0f && !isCurrentlyActive

        if(isCanceled){
            clearCanvas(c, itemView.right + dX, itemView.top.toFloat(), itemView.right.toFloat(), itemView.bottom.toFloat())
            super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)
            return
        }

        // Dibujar el color rojo de eliminacion de fondo
        backgraund.color = backgraundColor
        backgraund.setBounds(itemView.right + dX.toInt(), itemView.top, itemView.right, itemView.bottom)
        backgraund.draw(c)

        // Calcular posicion donde colocar el (item delete)
        val deleteIconTop = itemView.top + (itemHeight - intrinsicH!!) / 2
        val deleteIconMargin = (itemHeight - intrinsicH) / 2
        val deleteIconLeft = itemView.right - deleteIconMargin - instrisicW!!
        val deleteIconRich = itemView.right - deleteIconMargin
        val deleteIconBottom = deleteIconTop + intrinsicH

        // Dibujar el icono
        iconDelete?.setBounds(deleteIconLeft, deleteIconTop, deleteIconRich, deleteIconBottom)
        iconDelete?.draw(c)

        super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)
    }

    private fun clearCanvas(c: Canvas?, left: Float, top: Float, right: Float, bottom: Float){
        c?.drawRect(left, top, right, bottom, clearPaint)
    }
}