package com.onjuno.assignment.crypto.viewmodels

import android.os.Bundle
import androidx.lifecycle.ViewModel
import com.onjuno.assignment.crypto.constants.IntentConstants
import com.onjuno.assignment.crypto.models.NetworkResponse
import com.onjuno.assignment.crypto.models.StateType
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class StateViewModel : ViewModel() {
    private val _feedData = MutableStateFlow(NetworkResponse.Loading)
    val getFeedData: StateFlow<NetworkResponse> = _feedData

    private val _stateType = MutableStateFlow(StateType.INVALID)
    val getStateType: StateFlow<StateType> = _stateType
    fun validateArguments(arguments: Bundle?) {
        _stateType.value =
            arguments?.getParcelable(IntentConstants.EXT_STATE_TYPE) ?: StateType.VALUE_STATE
    }
}