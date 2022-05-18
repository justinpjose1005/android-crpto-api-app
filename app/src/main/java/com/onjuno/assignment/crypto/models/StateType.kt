package com.onjuno.assignment.crypto.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
enum class StateType : Parcelable {
    EMPTY_STATE, VALUE_STATE, INVALID
}