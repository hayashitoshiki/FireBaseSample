package com.myapp.firebasesample

import android.app.Application
import com.myapp.firebasesample.data.local.preferences.PreferenceManager
import com.myapp.firebasesample.data.remote.FireBaseService
import com.myapp.firebasesample.data.remote.FireBaseServiceImp
import com.myapp.firebasesample.data.repository.LocalSettingRepositoryImp
import com.myapp.firebasesample.data.repository.RemoteAccountRepositoryImp
import com.myapp.firebasesample.data.repository.RemoteEmployeeRepositoryImp
import com.myapp.firebasesample.data.repository.RemoteMemoRepositoryImp
import com.myapp.firebasesample.domain.repository.LocalSettingRepository
import com.myapp.firebasesample.domain.repository.RemoteAccountRepository
import com.myapp.firebasesample.domain.repository.RemoteEmployeeRepository
import com.myapp.firebasesample.domain.repository.RemoteMemoRepository
import com.myapp.firebasesample.domain.usecase.*
import com.myapp.firebasesample.presentation.ui.dashboard.DashboardViewModel
import com.myapp.firebasesample.presentation.ui.home.HomeViewModel
import com.myapp.firebasesample.presentation.ui.notifications.NotificationsViewModel
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
        viewModel { NotificationsViewModel(get()) }

        // UseCase
        factory<AuthUseCase> { AuthUseCaseImp(get()) }
        factory<CouldFireStoreUseCase> { CouldFireStoreUseCaseImp(get(), get(), get()) }
        //factory<NotificationUseCase> { NotificationUseCase(get()) }
        factory<NotificationUseCase> { NotificationUseCase() }


        // Repository
        factory<RemoteMemoRepository> { RemoteMemoRepositoryImp(get()) }
        factory<RemoteAccountRepository> { RemoteAccountRepositoryImp(get()) }
        factory<RemoteEmployeeRepository> { RemoteEmployeeRepositoryImp(get()) }
        factory<LocalSettingRepository> { LocalSettingRepositoryImp(get()) }

        single { PreferenceManager(applicationContext) }

        // service
        single<FireBaseService> { FireBaseServiceImp() }

    }
}
