package com.onjuno.assignment.crypto.di

import com.onjuno.assignment.crypto.network.RetrofitClient
import com.onjuno.assignment.crypto.repository.StateRepository
import com.onjuno.assignment.crypto.viewmodels.StateViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModules = module {
    viewModel { StateViewModel(get()) }
}

val repositoryModules = module {
    single { StateRepository(get()) }
}

val apiServiceModules = module {
    single { RetrofitClient.getInstance() }
}