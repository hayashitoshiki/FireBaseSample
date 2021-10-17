package com.myapp.firebasesample.data.repository

import com.myapp.firebasesample.data.remote.FireBaseService
import com.myapp.firebasesample.domain.repository.RemoteAccountRepository

class RemoteAccountRepositoryImp(private val fireBaseService: FireBaseService): RemoteAccountRepository {

    //　自動認証
    override fun autoAuth(): Boolean = fireBaseService.firstCheck()

    // メールアドレス取得
    override fun getEmail(): String = fireBaseService.getEmail()

    // ログイン
    override suspend fun signIn(email: String, password: String) {
        fireBaseService.signIn(email, password)
    }

    // ログアウト
    override suspend fun signOut() {
        fireBaseService.signOut()
    }

    // アカウント作成
    override suspend fun signUp(email: String, password: String) {
        fireBaseService.signUp(email, password)
    }

    // アカウント削除
    override suspend fun delete() {
        fireBaseService.delete()
    }
}