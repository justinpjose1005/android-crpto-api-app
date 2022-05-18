package com.onjuno.assignment.crypto.di

import com.onjuno.assignment.crypto.viewmodels.StateViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { StateViewModel() }
}