package com.example.listfruit20.helper

import android.app.Activity
import android.app.AlertDialog
import android.content.Intent
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.listfruit20.MainActivity
import com.example.listfruit20.R

class FruitItemTouchHelperCallback(private val fruitRecyclerAdapter: com.example.listfruit20.ListAdapter)
    : ItemTouchHelper.Callback() {

    override fun getMovementFlags(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder
    ): Int {
        val drag = ItemTouchHelper.UP or ItemTouchHelper.DOWN
        val swipe = ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT

        return makeMovementFlags(drag, swipe)
    }

    override fun onMove(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        target: RecyclerView.ViewHolder
    ): Boolean {
        val initPosition = viewHolder.adapterPosition
        val targetPosition = target.adapterPosition
        fruitRecyclerAdapter.swap(initPosition, targetPosition)
        return true
    }

    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
        val position = viewHolder.adapterPosition
        fruitRecyclerAdapter.remove(position)



    }
}