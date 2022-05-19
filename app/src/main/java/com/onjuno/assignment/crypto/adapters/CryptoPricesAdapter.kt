package com.onjuno.assignment.crypto.adapters

import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.github.twocoffeesoneteam.glidetovectoryou.GlideToVectorYou
import com.onjuno.assignment.crypto.R
import com.onjuno.assignment.crypto.databinding.ListItemCryptoPricesBinding
import com.onjuno.assignment.crypto.models.CryptoPricesListItem
import com.onjuno.assignment.crypto.models.CryptoPricesListItemDiffCallback
import com.onjuno.assignment.crypto.models.StateType

class CryptoPricesAdapter(private val onBuyClicked: (CryptoPricesListItem) -> Unit) :
    ListAdapter<CryptoPricesListItem, CryptoPricesViewHolder>(
        CryptoPricesListItemDiffCallback()
    ) {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CryptoPricesViewHolder {
        return CryptoPricesViewHolder.newInstance(parent, onBuyClicked)
    }

    override fun onBindViewHolder(holder: CryptoPricesViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}

class CryptoPricesViewHolder(
    private val mBinding: ListItemCryptoPricesBinding,
    private val onBuyClicked: (CryptoPricesListItem) -> Unit
) :
    RecyclerView.ViewHolder(mBinding.root) {
    companion object {
        fun newInstance(
            parent: ViewGroup,
            onBuyClicked: (CryptoPricesListItem) -> Unit
        ): CryptoPricesViewHolder =
            CryptoPricesViewHolder(
                ListItemCryptoPricesBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                ),
                onBuyClicked
            )
    }

    fun bind(item: CryptoPricesListItem) {
        with(mBinding) {
            GlideToVectorYou.init().with(root.context).load(Uri.parse(item.logo), logoImg)
            cryptoNameTv.text = item.title
            cryptoPriceTv.text = root.context.getString(R.string.valueInUsd, item.currentPriceInUsd)
            when(item.stateType) {
                StateType.EMPTY_STATE -> {
                    buyBtn.setOnClickListener {
                        onBuyClicked(item)
                    }
                }
                StateType.VALUE_STATE -> {
                }
                StateType.INVALID -> {
                }
            }
        }
    }
}