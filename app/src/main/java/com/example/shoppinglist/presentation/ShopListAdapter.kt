package com.example.shoppinglist.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.shoppinglist.databinding.ItemShopEnabledBinding
import com.example.shoppinglist.domain.ShopItem

class ShopListAdapter: RecyclerView.Adapter<ShopListAdapter.ShopListViewHolder>() {

    inner class ShopListViewHolder(val binding: ItemShopEnabledBinding): RecyclerView.ViewHolder(binding.root)

    val list = listOf<ShopItem>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShopListViewHolder {
        val binding = ItemShopEnabledBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ShopListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ShopListViewHolder, position: Int) {
        val shopItem = list[position]
        with(holder.binding) {
            tvTitle.text = shopItem.name
            tvCount.text = shopItem.count.toString()
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }
}