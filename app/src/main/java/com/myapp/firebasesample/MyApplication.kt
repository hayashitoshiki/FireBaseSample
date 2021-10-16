package com.myapp.firebasesample

import android.app.Application
import com.myapp.firebasesample.data.remote.FireBaseService
import com.myapp.firebasesample.data.remote.FireBaseServiceImp
import com.myapp.firebasesample.data.repository.RemoteAccountRepositoryImp
import com.myapp.firebasesample.domain.repository.RemoteAccountRepository
import com.myapp.firebasesample.domain.usecase.AuthUseCase
import com.myapp.firebasesample.domain.usecase.AuthUseCaseImp
import com.myapp.firebasesample.presentation.ui.home.HomeViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.GlobalContext.startKoin
import org.koin.core.module.Module
import org.koin.dsl.module

class MyApplication : Application() {

    companion object {
        lateinit var shared: MyApplication
    }

    init {
        shared = this
    }

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(applicationContext)
            modules(module)
        }
    }

    // Koinモジュール
    private val module: Module = module {

        viewModel { HomeViewModel(get()) }

        factory<AuthUseCase> { AuthUseCaseImp(get()) }

        factory<RemoteAccountRepository> { RemoteAccountRepositoryImp(get()) }

        single<FireBaseService> { FireBaseServiceImp() }
    }
}
