package com.myapp.firebasesample.data.remote

import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.myapp.firebasesample.domain.model.entity.Employee
import com.myapp.firebasesample.domain.model.entity.Memo
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

// Firebaseアクセス
class FireBaseServiceImp :FireBaseService {

    // region 認証

    // Firebaseインスタンス
    private val auth: FirebaseAuth = FirebaseAuth.getInstance()

    // Database
    val db = Firebase.firestore

    // 自動ログイン認証
    override fun firstCheck(): Boolean {
        return auth.currentUser != null
    }

    // Email取得
    override fun getEmail(): String {
        return auth.currentUser?.email ?: throw IllegalAccessError("ログインしていない為、メールアドレスを取得できません")
    }

    // ログイン機能
    override suspend fun signIn(email: String, password: String) = suspendCoroutine<Unit> { continuation ->
        auth.signInWithEmailAndPassword(email, password).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                continuation.resume(Unit)
            } else {
                val exception = task.exception ?: IllegalAccessError("ログイン失敗")
                continuation.resumeWithException(exception)
            }
        }
    }

    // ログアウト
    override suspend fun signOut() {
        auth.signOut()
    }

    // アカウント作成
    override suspend fun signUp(email: String, password: String) = suspendCoroutine<Unit> { continuation ->
        auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                continuation.resume(Unit)
            } else {
                val exception = task.exception ?: IllegalAccessError("アカウント作成失敗")
                continuation.resumeWithException(exception)
            }
        }
    }

    // アカウント削除
    override suspend fun delete() = suspendCoroutine<Unit> { continuation ->
        auth.currentUser?.let {
            it.delete().addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    continuation.resume(Unit)
                } else {
                    val exception = task.exception ?: IllegalAccessError("アカウント削除失敗")
                    continuation.resumeWithException(exception)
                }
            }
        } ?: run { throw IllegalAccessError("アカウント削除失敗") }
    }

    // endregion

    // region Cloud Store

    enum class Table(val tableName: String) {
        USER_TABLE("users"),
        MEMO_TABLE("memo")
    }

    // 社員データ登録
    override suspend fun addEmployeeData(employee: Employee){
        addData(Table.USER_TABLE, employee.toHashMap())
    }

    // 社員データ取得
    override suspend fun getEmployeeData() = suspendCoroutine<List<Map<String, Any>>> { continuation ->
        db.collection(Table.USER_TABLE.tableName)
            .get()
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val resultDat = task.result
                        ?.map { document -> document.data }
                        ?.toList()
                        ?: listOf<Map<String,Any>>()
                    continuation.resume(resultDat)
                } else {
                    val exception = task.exception ?: IllegalAccessError("データ取得失敗")
                    continuation.resumeWithException(exception)
                }
            }
    }

    // アカウント毎のデータ登録
    override suspend fun addMemoDataByUser(memo: Memo, email: String){
        addData(Table.MEMO_TABLE, memo.toHashMap(email))
    }

    // アカウント毎のデータ取得
    override suspend fun getMemoDataByUser(email: String) = suspendCoroutine<List<Map<String, Any>>> { continuation ->
        db.collection(Table.MEMO_TABLE.tableName)
            .whereEqualTo("user", email)
            .get()
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val resultDat = task.result
                        ?.map { document -> document.data }
                        ?.toList()
                        ?: listOf<Map<String,Any>>()
                    continuation.resume(resultDat)
                } else {
                    val exception = task.exception ?: IllegalAccessError("データ取得失敗")
                    continuation.resumeWithException(exception)
                }
        }
    }

    // データ登録
    private suspend fun addData(table: Table, data: HashMap<String,Any>) = suspendCoroutine<Unit> { continuation ->
        db.collection(table.tableName)
            .add(data)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    continuation.resume(Unit)
                } else {
                    val exception = task.exception ?: IllegalAccessError("データ登録失敗")
                    continuation.resumeWithException(exception)
                }
            }
    }

    // endregion
}