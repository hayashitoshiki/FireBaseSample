package com.myapp.firebasesample.domain.usecase

import com.myapp.firebasesample.domain.model.entity.Employee
import com.myapp.firebasesample.domain.model.entity.Memo
import com.myapp.firebasesample.domain.repository.RemoteAccountRepository
import com.myapp.firebasesample.domain.repository.RemoteEmployeeRepository
import com.myapp.firebasesample.domain.repository.RemoteMemoRepository

class CouldFireStoreUseCaseImp(
    private val remoteAccountRepository: RemoteAccountRepository,
    private val remoteEmployeeRepository: RemoteEmployeeRepository,
    private val remoteMemoRepository: RemoteMemoRepository) : CouldFireStoreUseCase {

    // 全アカウント共有データ取得
    override suspend fun addData(employee: Employee) {
        remoteEmployeeRepository.addData(employee)
    }

    // 全アカウント共有データ取得
    override suspend fun getData() : List<Employee> {
        return remoteEmployeeRepository.getData().map{
            val first = it["first"].toString()
            val last = it["last"].toString()
            val born = it["born"].toString().toInt()
            Employee(first, last, born)
        }
    }

    // アカウント別のデータ登録
    override suspend fun addDataByUser(memo: Memo) {
        if (!remoteAccountRepository.autoAuth()) throw IllegalAccessException("ログインしていないため登録できません")
        val email = remoteAccountRepository.getEmail()
        remoteMemoRepository.addDataByUser(memo, email)
    }

    // アカウント別のデータ取得
    override suspend fun getDataByUser(): List<Memo> {
        if (!remoteAccountRepository.autoAuth()) throw IllegalAccessException("ログインしていないため取得できません")
        val email = remoteAccountRepository.getEmail()
        return remoteMemoRepository.getDataByUser(email).map{
            val id = it["id"].toString().toLong()
            val text = it["text"].toString()
            Memo(id, text)
        }
    }
}