package com.myapp.firebasesample.presentation.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.myapp.firebasesample.domain.usecase.AuthUseCase
import kotlinx.coroutines.launch

class HomeViewModel(private val authUseCase: AuthUseCase) : ViewModel() {

    // エラーメッセージ表示
    private val _errorEffect: MutableLiveData<String> = MutableLiveData("")
    val errorEffect: LiveData<String> = _errorEffect
    // 成功メッセージ表示
    private val _successEffect: MutableLiveData<String> = MutableLiveData("")
    val successEffect: LiveData<String> = _successEffect

    // ログインボタンの活性非活性
    private val _signInEnable: MutableLiveData<Boolean> = MutableLiveData(false)
    val signInEnable: LiveData<Boolean> = _signInEnable
    // ログインアウトボタンの活性非活性
    private val _signOutEnable: MutableLiveData<Boolean> = MutableLiveData(false)
    val signOutEnable: LiveData<Boolean> = _signOutEnable
    // アカウント作成ボタンの活性非活性
    private val _signUpEnable: MutableLiveData<Boolean> = MutableLiveData(false)
    val signUpEnable: LiveData<Boolean> = _signUpEnable
    // アカウント削除ボタンの活性非活性
    private val _deleteEnable: MutableLiveData<Boolean> = MutableLiveData(false)
    val deleteEnable: LiveData<Boolean> = _deleteEnable


    // メールアドレス
    private val email = "test123@com.jp"
    // パスワード
    private val password = "123456"

    init {
        if (authUseCase.autoAuth()) {
            _signInEnable.value = false
            _signOutEnable.value = true
            _signUpEnable.value = false
            _deleteEnable.value = true
        } else {
            _signInEnable.value = true
            _signOutEnable.value = false
            _signUpEnable.value = true
            _deleteEnable.value = true
        }
    }

    /**
     * ログイン
     *
     */
    fun signIn() { viewModelScope.launch {
        runCatching { authUseCase.signIn(email, password) }
            .onSuccess {
                _signInEnable.value = false
                _signOutEnable.value = true
                _signUpEnable.value = false
                _deleteEnable.value = true
                _successEffect.value = "ログイン成功"
            }
            .onFailure { _errorEffect.value = it.message }
    }}

    /**
     * 新規登録
     *
     */
    fun signUp() = viewModelScope.launch {
        runCatching { authUseCase.signUp(email, password) }
            .onSuccess {
                _signInEnable.value = false
                _signOutEnable.value = true
                _signUpEnable.value = false
                _deleteEnable.value = true
                _successEffect.value = "新規登録成功"
            }
            .onFailure { _errorEffect.value = it.message }
    }

    /**
     * ログアウト
     *
     */
    fun signOut() = viewModelScope.launch {
        runCatching { authUseCase.signOut() }
            .onSuccess {
                _signInEnable.value = true
                _signOutEnable.value = false
                _signUpEnable.value = false
                _deleteEnable.value = false
                _successEffect.value = "ログアウト成功"
            }
            .onFailure { _errorEffect.value = it.message }
    }

    /**
     * アカウント削除
     *
     */
    fun delete() = viewModelScope.launch {
        runCatching { authUseCase.delete() }
            .onSuccess {
                _signInEnable.value = false
                _signOutEnable.value = false
                _signUpEnable.value = true
                _deleteEnable.value = false
                _successEffect.value = "アカウント削除成功"
            }
            .onFailure { _errorEffect.value = it.message }
    }

    /**
     * 画面破棄処理
     *
     */
    fun onDestroy() {
        _successEffect.value = ""
        _errorEffect.value = ""
    }
}