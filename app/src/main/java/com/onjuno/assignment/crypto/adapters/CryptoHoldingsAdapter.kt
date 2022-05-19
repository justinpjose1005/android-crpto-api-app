package com.onjuno.assignment.crypto.adapters

import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.github.twocoffeesoneteam.glidetovectoryou.GlideToVectorYou
import com.onjuno.assignment.crypto.R
import com.onjuno.assignment.crypto.databinding.ListItemCryptoHoldingsBinding
import com.onjuno.assignment.crypto.models.StateType
import com.onjuno.assignment.crypto.models.YourCryptoHoldingsListItem
import com.onjuno.assignment.crypto.models.YourCryptoHoldingsListItemDiffCallback

class CryptoHoldingsAdapter : ListAdapter<YourCryptoHoldingsListItem, CryptoHoldingsViewHolder>(
    YourCryptoHoldingsListItemDiffCallback()
) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CryptoHoldingsViewHolder {
        return CryptoHoldingsViewHolder.newInstance(parent)
    }

    override fun onBindViewHolder(holder: CryptoHoldingsViewHolder, position: Int) {
        holder.bind(getItem(position), position == itemCount - 1)
    }
}

class CryptoHoldingsViewHolder(private val mBinding: ListItemCryptoHoldingsBinding) :
    RecyclerView.ViewHolder(mBinding.root) {
    companion object {
        fun newInstance(parent: ViewGroup): CryptoHoldingsViewHolder =
            CryptoHoldingsViewHolder(
                ListItemCryptoHoldingsBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
    }

    fun bind(item: YourCryptoHoldingsListItem, isLastItemInList: Boolean) {
        with(mBinding) {
            GlideToVectorYou.init().with(root.context).load(Uri.parse(item.logo), logoImg)
            cryptoNameTv.text = item.title
            when (item.stateType) {
                StateType.EMPTY_STATE -> {
                    buttonFlow.visibility = View.VISIBLE
                    totalCryptoTokensTv.visibility = View.GONE
                    totalCryptoUsdTv.visibility = View.GONE
                }
                StateType.VALUE_STATE -> {
                    buttonFlow.visibility = View.GONE
                    totalCryptoTokensTv.text =
                        root.context.getString(
                            R.string.valueInToken,
                            item.currentBalInToken,
                            item.title
                        )
                    totalCryptoTokensTv.visibility = View.VISIBLE
                    totalCryptoUsdTv.text =
                        root.context.getString(R.string.valueInUsd, item.currentBalInUsd)
                    totalCryptoUsdTv.visibility = View.VISIBLE
                }
                StateType.INVALID -> {
                }
            }
            if (isLastItemInList) {
                return@with
            }
            divider.visibility = View.VISIBLE
        }
    }
}