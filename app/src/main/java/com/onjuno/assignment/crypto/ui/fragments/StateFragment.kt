package com.onjuno.assignment.crypto.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.onjuno.assignment.crypto.R
import com.onjuno.assignment.crypto.adapters.CryptoHoldingsAdapter
import com.onjuno.assignment.crypto.adapters.CryptoPricesAdapter
import com.onjuno.assignment.crypto.adapters.RecentTransactionsAdapter
import com.onjuno.assignment.crypto.constants.IntentConstants
import com.onjuno.assignment.crypto.databinding.FragmentStateBinding
import com.onjuno.assignment.crypto.models.*
import com.onjuno.assignment.crypto.viewmodels.StateViewModel
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class StateFragment : Fragment() {
    companion object {
        fun newInstance(stateType: StateType) = StateFragment().apply {
            arguments = bundleOf(IntentConstants.EXT_STATE_TYPE to stateType)
        }
    }

    private lateinit var mBinding: FragmentStateBinding
    private val mViewModel: StateViewModel by viewModel()
    private val mCryptoHoldingsAdapter: CryptoHoldingsAdapter by lazy { CryptoHoldingsAdapter() }
    private val mRecentTransactionsAdapter: RecentTransactionsAdapter by lazy { RecentTransactionsAdapter() }
    private val mCryptoPricesAdapter: CryptoPricesAdapter by lazy {
        CryptoPricesAdapter {
            addCryptoTransaction(it)
        }
    }

    private fun addCryptoTransaction(cryptoModel: CryptoPricesListItem) {
        with(mBinding) {
            val updatedList =
                mRecentTransactionsAdapter.currentList.toMutableList()
            updatedList.add(
                AllTransactionsListItem(
                    cryptoModel.logo,
                    getString(R.string.crypto_purchased, cryptoModel.title),
                    getString(R.string.just_now),
                    cryptoModel.currentPriceInUsd
                )
            )
            emptyTransactionsTv.visibility = View.GONE
            recentTransactionsRv.visibility = View.VISIBLE
            mRecentTransactionsAdapter.submitList(updatedList)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        mBinding = FragmentStateBinding.inflate(layoutInflater)
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mViewModel.validateArguments(arguments)
        setClickListeners()
        setObservers()
    }

    private fun setClickListeners() {
        with(mBinding) {
            retryBtn.setOnClickListener {
                mViewModel.loadScreen()
            }
        }
    }

    private fun setObservers() {
        with(mViewModel) {
            viewLifecycleOwner.lifecycleScope.launch {
                viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                    getStateFeed.collect {
                        when (it) {
                            NetworkResponse.Failure -> {
                                showFailureState()
                            }
                            NetworkResponse.Loading -> {
                                showProgressState()
                            }
                            is NetworkResponse.Success -> {
                                showCryptoFeed(it.feedDataModel)
                            }
                        }
                    }
                }
            }
        }
    }

    private fun showCryptoFeed(feedDataModel: FeedDataModel?) {
        with(mBinding) {
            rootFlow.visibility = View.VISIBLE
            progressBar.visibility = View.GONE
            errorFlow.visibility = View.GONE
            feedDataModel?.let {
                setupCryptoBalance(it.cryptoBalance)
                setupYourCryptoHoldings(formatCryptoHolderModel(it.yourCryptoHoldingsList))
                setupRecentTransactions(it.allTransactionsList)
                setupCryptoPrices(it.cryptoPricesList)
            }
        }
    }

    private fun setupCryptoBalance(cryptoBalance: CryptoBalance) {
        with(mBinding) {
            accountLabel1Tv.text = cryptoBalance.title
            accountLabel2Tv.text = cryptoBalance.subTitle
            when (mViewModel.stateType) {
                StateType.EMPTY_STATE -> {
                    accountBalanceTv.visibility = View.GONE
                    depositBtn.visibility = View.VISIBLE
                }
                StateType.VALUE_STATE -> {
                    accountBalanceTv.text =
                        getString(R.string.valueInUsd, cryptoBalance.currentBalInUsd)
                    accountBalanceTv.visibility = View.VISIBLE
                    depositBtn.visibility = View.GONE
                }
                StateType.INVALID -> {
                }
            }
        }
    }

    private fun setupYourCryptoHoldings(yourCryptoHoldingsList: YourCryptoHoldingsList) {
        with(mBinding) {
            cryptoHoldingsRv.adapter = mCryptoHoldingsAdapter
            mCryptoHoldingsAdapter.submitList(yourCryptoHoldingsList)
        }
    }

    private fun setupRecentTransactions(allTransactionsList: AllTransactionsList) {
        with(mBinding) {
            recentTransactionsRv.adapter = mRecentTransactionsAdapter
            if (allTransactionsList.isEmpty()) {
                emptyTransactionsTv.visibility = View.VISIBLE
                recentTransactionsRv.visibility = View.GONE
                return@with
            }
            emptyTransactionsTv.visibility = View.GONE
            recentTransactionsRv.visibility = View.VISIBLE
            mRecentTransactionsAdapter.submitList(allTransactionsList)
        }
    }

    private fun setupCryptoPrices(cryptoPricesList: CryptoPricesList) {
        with(mBinding) {
            cryptoPricesRv.adapter = mCryptoPricesAdapter
            mCryptoPricesAdapter.submitList(formatCryptoPricesModel(cryptoPricesList))
        }
    }

    private fun formatCryptoHolderModel(yourCryptoHoldingsList: YourCryptoHoldingsList): YourCryptoHoldingsList {
        val updatedList = YourCryptoHoldingsList()
        yourCryptoHoldingsList.forEach {
            updatedList.add(it.copy(stateType = mViewModel.stateType))
        }
        return updatedList
    }

    private fun formatCryptoPricesModel(cryptoPricesList: CryptoPricesList): CryptoPricesList {
        val updatedList = CryptoPricesList()
        cryptoPricesList.forEach {
            updatedList.add(it.copy(stateType = mViewModel.stateType))
        }
        return updatedList
    }

    private fun showFailureState() {
        with(mBinding) {
            rootFlow.visibility = View.GONE
            progressBar.visibility = View.GONE
            errorFlow.visibility = View.VISIBLE
        }
    }

    private fun showProgressState() {
        with(mBinding) {
            rootFlow.visibility = View.GONE
            progressBar.visibility = View.VISIBLE
            errorFlow.visibility = View.GONE
        }
    }
}