package com.myapp.firebasesample.domain.model.entity

/**
 * メモ
 *
 * @property id ID
 * @property text メモの内容
 */
data class Memo (val id: Long, val text: String) {

    fun toHashMap(email: String): HashMap<String,Any>{
        return hashMapOf(
            "id" to id,
            "text" to text,
            "user" to email
        )
    }

}