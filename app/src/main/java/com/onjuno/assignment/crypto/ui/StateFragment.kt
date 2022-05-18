package com.onjuno.assignment.crypto.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.onjuno.assignment.crypto.constants.IntentConstants
import com.onjuno.assignment.crypto.databinding.FragmentStateBinding
import com.onjuno.assignment.crypto.models.NetworkResponse
import com.onjuno.assignment.crypto.models.StateType
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
        }
    }

    private fun setObservers() {
        with(mViewModel) {
            viewLifecycleOwner.lifecycleScope.launch {
                viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                    getStateType.collect {
                        when (it) {
                            StateType.EMPTY_STATE -> {
                            }
                            StateType.VALUE_STATE -> {
                            }
                            StateType.INVALID -> {
                            }
                        }
                    }
                }
            }
            viewLifecycleOwner.lifecycleScope.launch {
                viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                    getFeedData.collect {
                        when (it) {
                            NetworkResponse.Failure -> {
                            }
                            NetworkResponse.Loading -> {
                            }
                            is NetworkResponse.Success -> {
                            }
                        }
                    }
                }
            }
        }
    }
}