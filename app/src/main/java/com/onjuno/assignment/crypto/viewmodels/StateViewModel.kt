package com.onjuno.assignment.crypto.viewmodels

import android.os.Bundle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.onjuno.assignment.crypto.constants.IntentConstants
import com.onjuno.assignment.crypto.models.NetworkResponse
import com.onjuno.assignment.crypto.models.StateType
import com.onjuno.assignment.crypto.repository.StateRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class StateViewModel(private val stateRepository: StateRepository) : ViewModel() {
    var stateType: StateType = StateType.INVALID
        private set(value) {
            field = value
            loadScreen()
        }

    fun validateArguments(arguments: Bundle?) {
        stateType =
            arguments?.getParcelable(IntentConstants.EXT_STATE_TYPE)
                ?: StateType.VALUE_STATE
    }

    private val _stateFeed: MutableStateFlow<NetworkResponse> =
        MutableStateFlow(NetworkResponse.Loading)
    val getStateFeed: StateFlow<NetworkResponse> = _stateFeed

    private fun getEmptyStateFeed() {
        viewModelScope.launch {
            stateRepository.getEmptyStateFeed().collect {
                _stateFeed.value = it
            }
        }
    }

    private fun getValueStateFeed() {
        viewModelScope.launch {
            stateRepository.getValueStateFeed().collect {
                _stateFeed.value = it
            }
        }
    }

    fun loadScreen() {
        when (stateType) {
            StateType.EMPTY_STATE -> {
                getEmptyStateFeed()
            }
            StateType.VALUE_STATE -> {
                getValueStateFeed()
            }
            StateType.INVALID -> {}
        }
    }
}