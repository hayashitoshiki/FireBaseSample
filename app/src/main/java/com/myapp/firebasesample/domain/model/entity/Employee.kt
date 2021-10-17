package com.myapp.firebasesample.domain.model.entity

/**
 * 社員情報
 *
 * @property first 苗字
 * @property last 名前
 * @property born 生まれた年
 */
data class Employee(val first: String, val last: String, val born: Int) {

    fun toHashMap(): HashMap<String,Any>{
        return hashMapOf(
            "first" to first,
            "last" to last,
            "born" to born
        )
    }

}