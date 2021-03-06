package com.myapp.firebasesample.domain.repository


/**
 * 外部に対するアカウント関連処理
 *
 */
interface RemoteAccountRepository {

    /**
     * 自動認証チェック
     *
     */
    fun autoAuth(): Boolean

    /**
     * メールアドレス取得
     *
     * @return メールアドレス
     */
    fun getEmail(): String

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