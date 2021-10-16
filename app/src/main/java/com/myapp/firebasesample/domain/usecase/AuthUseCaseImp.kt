package com.myapp.firebasesample.domain.usecase

import com.myapp.firebasesample.domain.repository.RemoteAccountRepository

// 認証系ビジネスロジック
class AuthUseCaseImp(private val remoteUserRepository: RemoteAccountRepository) : AuthUseCase {

    // 自動認証
    override fun autoAuth(): Boolean = remoteUserRepository.autoAuth()

    // ログイン
    override suspend fun signIn(email: String, password: String) {
        remoteUserRepository.signIn(email, password)
    }

    // ログアウト
    override suspend fun signOut() {
        remoteUserRepository.signOut()
    }

    // アカウント作成
    override suspend fun signUp(email: String, password: String) {
        remoteUserRepository.signUp(email, password)
    }

    // アカウント削除
    override suspend fun delete() {
        remoteUserRepository.delete()
    }
}