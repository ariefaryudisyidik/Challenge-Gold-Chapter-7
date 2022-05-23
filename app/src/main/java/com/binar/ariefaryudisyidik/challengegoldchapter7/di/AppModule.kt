package com.binar.ariefaryudisyidik.challengegoldchapter7.di

import com.binar.ariefaryudisyidik.challengegoldchapter7.ui.home.HomeViewModel
import com.binar.ariefaryudisyidik.challengegoldchapter7.ui.login.LoginViewModel
import com.binar.ariefaryudisyidik.challengegoldchapter7.ui.register.RegisterViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { LoginViewModel(get(), get()) }
    viewModel { RegisterViewModel(get()) }
    viewModel { HomeViewModel(get(), get()) }
}