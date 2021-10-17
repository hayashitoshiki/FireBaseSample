package com.myapp.firebasesample.data.repository

import com.myapp.firebasesample.data.remote.FireBaseService
import com.myapp.firebasesample.domain.model.entity.Memo
import com.myapp.firebasesample.domain.repository.RemoteMemoRepository

class RemoteMemoRepositoryImp(private val fireBaseService: FireBaseService)  : RemoteMemoRepository {

    override suspend fun addDataByUser(memo: Memo, email: String) {
        fireBaseService.addMemoDataByUser(memo, email)
    }

    override suspend fun getDataByUser(email: String): List<Map<String, Any>> {
        return fireBaseService.getMemoDataByUser(email)
    }
}