package com.myapp.firebasesample

import android.app.Application
import com.myapp.firebasesample.data.remote.FireBaseService
import com.myapp.firebasesample.data.remote.FireBaseServiceImp
import com.myapp.firebasesample.data.repository.RemoteAccountRepositoryImp
import com.myapp.firebasesample.data.repository.RemoteEmployeeRepositoryImp
import com.myapp.firebasesample.data.repository.RemoteMemoRepositoryImp
import com.myapp.firebasesample.domain.repository.RemoteAccountRepository
import com.myapp.firebasesample.domain.repository.RemoteEmployeeRepository
import com.myapp.firebasesample.domain.repository.RemoteMemoRepository
import com.myapp.firebasesample.domain.usecase.*
import com.myapp.firebasesample.presentation.ui.dashboard.DashboardViewModel
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

        // viewModel
        viewModel { HomeViewModel(get()) }
        viewModel { DashboardViewModel(get()) }

        // UseCase
        factory<AuthUseCase> { AuthUseCaseImp(get()) }
        factory<CouldFireStoreUseCase> { CouldFireStoreUseCaseImp(get(), get(), get()) }

        // Repository
        factory<RemoteMemoRepository> { RemoteMemoRepositoryImp(get()) }
        factory<RemoteAccountRepository> { RemoteAccountRepositoryImp(get()) }
        factory<RemoteEmployeeRepository> { RemoteEmployeeRepositoryImp(get()) }

        // service
        single<FireBaseService> { FireBaseServiceImp() }
    }
}
