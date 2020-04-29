package com.example.pokemon.utils

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.util.ArrayList

internal object RecyclerViewUtils {
    @JvmStatic
    fun getVisibleViewHolders(recyclerView: RecyclerView): List<RecyclerView.ViewHolder> {
        val viewHolders: ArrayList<RecyclerView.ViewHolder> = arrayListOf()
        val linearLayoutManager =
            recyclerView.layoutManager as LinearLayoutManager?
        val first = linearLayoutManager!!.findFirstVisibleItemPosition()
        val last = linearLayoutManager.findLastVisibleItemPosition()
        return if (first != -1 && last != -1) {
            for (i in first..last) {
                recyclerView.findViewHolderForAdapterPosition(i)?.let { viewHolders.add(it) }
            }
            viewHolders
        } else {
            viewHolders
        }
    }
}