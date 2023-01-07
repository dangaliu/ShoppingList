package com.example.shoppinglist.presentation

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.shoppinglist.R
import com.example.shoppinglist.databinding.ActivityMainBinding
import com.example.shoppinglist.domain.ShopItem

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel

    private val TAG = "MainActivity"

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this)[MainViewModel::class.java]

        viewModel.shopList.observe(this) {
            showList(it)
            Log.d(TAG, it.toString())
        }
    }

    private fun showList(shopList: List<ShopItem>) {
        binding.llShoppingList.removeAllViews()
        for (shopItem in shopList) {
            val layoutId = if (shopItem.enabled) {
                R.layout.item_shop_enabled
            } else {
                R.layout.item_shop_disabled
            }
            val view = layoutInflater.inflate(layoutId, binding.llShoppingList, false)
            view.setOnLongClickListener {
                viewModel.changeEnabledState(shopItem)
                true
            }
            val tvName = view.findViewById<TextView>(R.id.tvTitle)
            val tvCount = view.findViewById<TextView>(R.id.tvCount)
            tvName.text = shopItem.name
            tvCount.text = shopItem.count.toString()
            binding.llShoppingList.addView(view)
        }
    }
}