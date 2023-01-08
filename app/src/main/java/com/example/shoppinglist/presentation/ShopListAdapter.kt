package com.example.shoppinglist.presentation

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.shoppinglist.R
import com.example.shoppinglist.domain.ShopItem

class ShopListAdapter : RecyclerView.Adapter<ShopListAdapter.ShopListViewHolder>() {

    private val TAG = "ShopListAdapter"
    private var count = 0

    var onClickListener: ((ShopItem) -> Unit)? = null
    var onLongClickListener: ((ShopItem) -> Unit)? = null

    inner class ShopListViewHolder(val view: View) :
        RecyclerView.ViewHolder(view) {
        val cvShopItem = view.findViewById<CardView>(R.id.cvShopItem)
        val tvTitle = view.findViewById<TextView>(R.id.tvTitle)
        val tvCount = view.findViewById<TextView>(R.id.tvCount)
    }

    var shopList = listOf<ShopItem>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShopListViewHolder {
        Log.d(TAG, "onCreateViewHolder ${(++count)}")
        val layout = when (viewType) {
            VIEW_TYPE_ENABLED -> {
                R.layout.item_shop_enabled
            }
            VIEW_TYPE_DISABLED -> {
                R.layout.item_shop_disabled
            }
            else -> {
                throw  RuntimeException("Undefined view type")
            }
        }
        val view = LayoutInflater.from(parent.context).inflate(layout, parent, false)
        return ShopListViewHolder(view)
    }

    override fun onBindViewHolder(holder: ShopListViewHolder, position: Int) {
        val shopItem = shopList[position]
        with(holder) {
            tvTitle.text = shopItem.name
            tvCount.text = shopItem.count.toString()
            cvShopItem.apply {
                setOnClickListener {
                    onClickListener?.invoke(shopItem)
                }
                setOnLongClickListener {
                    onLongClickListener?.invoke(shopItem)
                    true
                }
            }
        }
    }

    override fun getItemCount(): Int {
        return shopList.size
    }

    override fun getItemViewType(position: Int): Int {
        val shopItem = shopList[position]
        return if (shopItem.enabled) {
            VIEW_TYPE_ENABLED
        } else {
            VIEW_TYPE_DISABLED
        }
    }

    companion object {
        const val VIEW_TYPE_ENABLED = 100
        const val VIEW_TYPE_DISABLED = 101

        const val MAX_POOL_SIZE = 15
    }
}