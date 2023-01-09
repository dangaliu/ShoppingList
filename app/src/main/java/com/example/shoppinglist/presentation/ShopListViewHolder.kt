package com.example.shoppinglist.presentation

import android.view.View
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.shoppinglist.R

class ShopListViewHolder(view: View) :
    RecyclerView.ViewHolder(view) {
    val cvShopItem = view.findViewById<CardView>(R.id.cvShopItem)
    val tvTitle = view.findViewById<TextView>(R.id.tvTitle)
    val tvCount = view.findViewById<TextView>(R.id.tvCount)
}