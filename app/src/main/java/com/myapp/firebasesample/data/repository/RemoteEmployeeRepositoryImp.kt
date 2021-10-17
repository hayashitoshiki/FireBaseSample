package com.myapp.firebasesample.data.repository

import com.myapp.firebasesample.data.remote.FireBaseService
import com.myapp.firebasesample.domain.model.entity.Employee
import com.myapp.firebasesample.domain.repository.RemoteEmployeeRepository

class RemoteEmployeeRepositoryImp(private val fireBaseService: FireBaseService) : RemoteEmployeeRepository {

    override suspend fun addData(employee: Employee) {
        fireBaseService.addEmployeeData(employee)
    }

    override suspend fun getData(): List<Map<String, Any>> {
        return fireBaseService.getEmployeeData()
    }

}