package com.onjuno.assignment.crypto.adapters

import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.github.twocoffeesoneteam.glidetovectoryou.GlideToVectorYou
import com.onjuno.assignment.crypto.R
import com.onjuno.assignment.crypto.databinding.ListItemRecentTransactionsBinding
import com.onjuno.assignment.crypto.models.AllTransactionsListItem
import com.onjuno.assignment.crypto.models.AllTransactionsListItemDiffCallback

class RecentTransactionsAdapter :
    ListAdapter<AllTransactionsListItem, RecentTransactionsViewHolder>(
        AllTransactionsListItemDiffCallback()
    ) {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecentTransactionsViewHolder {
        return RecentTransactionsViewHolder.newInstance(parent)
    }

    override fun onBindViewHolder(holder: RecentTransactionsViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}

class RecentTransactionsViewHolder(private val mBinding: ListItemRecentTransactionsBinding) :
    RecyclerView.ViewHolder(mBinding.root) {
    companion object {
        fun newInstance(parent: ViewGroup): RecentTransactionsViewHolder =
            RecentTransactionsViewHolder(
                ListItemRecentTransactionsBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
    }

    fun bind(item: AllTransactionsListItem) {
        with(mBinding) {
            GlideToVectorYou.init().with(root.context).load(Uri.parse(item.txnLogo), logoImg)
            transactionTitleTv.text = item.title
            transactionTimeTv.text = item.txnTime
            transactionAmountTv.text = root.context.getString(R.string.valueInUsd, item.txnAmount)
            transactionSubAmountTv.text = item.txnSubAmount
        }
    }
}