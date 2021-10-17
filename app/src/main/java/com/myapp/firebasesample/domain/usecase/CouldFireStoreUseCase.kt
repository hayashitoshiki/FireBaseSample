package com.myapp.firebasesample.domain.usecase

import com.myapp.firebasesample.domain.model.entity.Employee
import com.myapp.firebasesample.domain.model.entity.Memo

/**
 * CloudFireStore練習用ビジネスロジック
 *
 */
interface CouldFireStoreUseCase {

    /**
     * 全アカウント共有データ登録
     *
     * @param employee 社員情報（全アカウント共有のデータ）
     */
    suspend fun addData(employee: Employee)

    /**
     * 全アカウント共有データ取得
     *
     * @return 社員情報リスト（全アカウント共有のデータ）
     */
    suspend fun getData(): List<Employee>

    /**
     * アカウント別データ取得
     *
     * @param memo メモ(アカウント毎のデータ）
     */
    suspend fun addDataByUser(memo: Memo)

    /**
     * アカウント別データ取得
     *
     * @return メモリスト（アカウント毎のデータ）
     */
    suspend fun getDataByUser(): List<Memo>

}