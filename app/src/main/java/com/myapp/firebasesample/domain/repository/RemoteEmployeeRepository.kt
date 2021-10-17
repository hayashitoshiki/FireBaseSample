package com.myapp.firebasesample.domain.repository

import com.myapp.firebasesample.domain.model.entity.Employee

/**
 * 外部の社員テーブルに対するCRUD処置
 *
 */
interface RemoteEmployeeRepository {

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
    suspend fun getData() : List<Map<String, Any>>

}