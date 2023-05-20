package com.chugunov.shoppinglist.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.databinding.adapters.ViewBindingAdapter.setOnLongClickListener
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.chugunov.shoppinglist.R
import com.chugunov.shoppinglist.databinding.ItemShopDisabledBinding
import com.chugunov.shoppinglist.databinding.ItemShopEnabledBinding
import com.chugunov.shoppinglist.domain.ShopItem

class ShopListAdapter : ListAdapter<ShopItem, ShopListViewHolder>(ShopItemDiffCallback()) {


    var onShopItemLongClickListener: ((ShopItem) -> Unit)? = null
    var onShopItemClickListener: ((ShopItem) -> Unit)? = null


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShopListViewHolder {
        val layout = when (viewType) {
            VIEW_TYPE_DISABLED -> R.layout.item_shop_disabled
            VIEW_TYPE_ENABLED -> R.layout.item_shop_enabled
            else -> throw RuntimeException("Unknown viewType $viewType")
        }
        val binding = DataBindingUtil.inflate<ViewDataBinding>(
            LayoutInflater.from(parent.context),
            layout,
            parent,
            false
        )
        return ShopListViewHolder(binding)
    }



    override fun onBindViewHolder(viewHolder: ShopListViewHolder, position: Int) {
        val shopItem = getItem(position)
        val binding = viewHolder.binding
        binding.root.setOnLongClickListener {
            onShopItemLongClickListener?.invoke(shopItem)
            true
        }
        binding.root.setOnClickListener {
            onShopItemClickListener?.invoke(shopItem)
        }
        when(binding) {
            is ItemShopDisabledBinding -> {
                binding.tvName.text = shopItem.name
                binding.tvCount.text = shopItem.count.toString()
            }
            is ItemShopEnabledBinding -> {
                binding.tvName.text = shopItem.name
                binding.tvCount.text = shopItem.count.toString()
            }
         }
    }

    override fun getItemViewType(position: Int): Int {
        val item = getItem(position)
        return if (item.enabled) {
            VIEW_TYPE_ENABLED
        } else {
            VIEW_TYPE_DISABLED
        }
    }




    companion object {
        const val VIEW_TYPE_ENABLED = 1
        const val VIEW_TYPE_DISABLED = 0

        const val MAX_POOL_SIZE = 30
    }
}