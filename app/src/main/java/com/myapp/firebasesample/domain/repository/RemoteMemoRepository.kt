package com.myapp.firebasesample.domain.repository

import com.myapp.firebasesample.domain.model.entity.Memo

/**
 * 外部のメモテーブルに対するCRUD処置
 *
 */
interface RemoteMemoRepository {

    /**
     * アカウント別データ取得
     *
     * @param memo メモ(アカウント毎のデータ）
     */
    suspend fun addDataByUser(memo: Memo, email: String)

    /**
     * アカウント別データ取得
     *
     * @return メモリスト（アカウント毎のデータ）
     */
    suspend fun getDataByUser(email: String): List<Map<String, Any>>
}