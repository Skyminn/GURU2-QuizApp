package com.example.quizapp

import android.provider.BaseColumns

object LocalDatas { //  로컬 데이터 베이스의 자료형태 정의된 object
    object userData : BaseColumns {  //  users 라는 DB 테이블의 데이터 컬럼 내용 정리
        const val TABLE_NAME = "users"
        const val COLUMN_NAME_ID = "id"
        const val COLUMN_NAME_PASSWORD = "password"
    }
}