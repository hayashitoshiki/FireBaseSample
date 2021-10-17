package com.myapp.firebasesample.presentation.ui.dashboard

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.myapp.firebasesample.domain.model.entity.Employee
import com.myapp.firebasesample.domain.model.entity.Memo
import com.myapp.firebasesample.domain.usecase.CouldFireStoreUseCase
import kotlinx.coroutines.launch

class DashboardViewModel(private val couldFireStoreUseCase: CouldFireStoreUseCase) : ViewModel() {

    // エラーメッセージ表示
    private val _errorEffect: MutableLiveData<String> = MutableLiveData("")
    val errorEffect: LiveData<String> = _errorEffect

    // 社員リスト
    private val _employeeList: MutableLiveData<List<Employee>> = MutableLiveData()
    val employeeList: LiveData<List<Employee>> = _employeeList
    // メモリスト
    private val _myMemoList: MutableLiveData<List<Memo>> = MutableLiveData()
    val myMemoList: LiveData<List<Memo>> = _myMemoList

    // データ追加
    fun addEmployeeData() = viewModelScope.launch {
        val employee = Employee("山田", "太郎", 2000)
        runCatching { couldFireStoreUseCase.addData(employee) }
            .onFailure { _errorEffect.value = it.message }
    }

    // データ取得
    fun getEmployeeData() = viewModelScope.launch {
        runCatching { couldFireStoreUseCase.getData() }
            .onSuccess { _employeeList.value = it }
            .onFailure { _errorEffect.value = it.message }
    }

    // データ追加
    fun addMemoData() = viewModelScope.launch {
        val memo = Memo(1, "test1")
        runCatching { couldFireStoreUseCase.addDataByUser(memo) }
            .onFailure { _errorEffect.value = it.message }
    }

    // データ取得
    fun getMemoData() = viewModelScope.launch {
        runCatching { couldFireStoreUseCase.getDataByUser() }
            .onSuccess { _myMemoList.value = it }
            .onFailure { _errorEffect.value = it.message }
    }
}