package com.myapp.firebasesample.data.remote

import com.myapp.firebasesample.domain.model.entity.Employee
import com.myapp.firebasesample.domain.model.entity.Memo

/**
 * FireBaseアクセス
 *
 */
interface FireBaseService {

    // region 認証系

    /**
     * ログイン状態チェック
     *
     */
    fun firstCheck(): Boolean

    /**
     * メールアドレス取得
     *
     * @return メールアドレス
     */
    fun getEmail(): String

    /**
     * ログイン
     *
     */
    suspend fun signIn(email: String, password: String)

    /**
     * ログアウト
     *
     */
    suspend fun signOut()

    /**
     * 新規登録
     *
     */
    suspend fun signUp(email: String, password: String)

    /**
     * アカウント削除
     *
     */
    suspend fun delete()

    // endregion

    // region Cloud Firestore

    /**
     * 全アカウント共有データ登録
     *
     * @param employee 社員情報（全アカウント共有のデータ）
     */
    suspend fun addEmployeeData(employee: Employee)

    /**
     * 全アカウント共有データ取得
     *
     * @return 社員情報リスト（全アカウント共有のデータ）
     */
    suspend fun getEmployeeData(): List<Map<String, Any>>

    /**
     * アカウント別データ取得
     *
     * @param memo メモ(アカウント毎のデータ）
     * @param email メールアドレス
     */
    suspend fun addMemoDataByUser(memo: Memo, email: String)

    /**
     * アカウント別データ取得
     *
     * @param email メールアドレス
     * @return メモリスト（アカウント毎のデータ）
     */
    suspend fun getMemoDataByUser(email: String): List<Map<String, Any>>

    // endregion
}