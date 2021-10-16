package com.myapp.firebasesample.domain.usecase

interface AuthUseCase {

    /**
     * 自動認証
     *
     * @return 自動ログイン判定結果
     */
    fun autoAuth(): Boolean

    /**
     * ログイン
     *
     * @param email メールアドレス
     * @param password パスワード
     */
    suspend fun signIn(email: String, password: String)

    /**
     * 新規登録
     *
     * @param email メールアドレス
     * @param password パスワード
     */
    suspend fun signUp(email: String, password: String)

    /**
     * ログアウト
     *
     */
    suspend fun signOut()

    /**
     * アカウント削除
     *
     */
    suspend fun delete()
}