package com.example.quizapp

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.provider.BaseColumns

class LocalDB (
    context: Context?,
    name: String?,
    factory: SQLiteDatabase.CursorFactory?,
    version: Int
) : SQLiteOpenHelper(context, name, factory, version){

    override fun onCreate(db: SQLiteDatabase?) {
        // DB 생성시 실행
        if (db != null) {
            createDatabase(db)
        }
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        // DB 버전 변경시 실행됨
        val sql : String = "DROP TABLE if exists ${LocalDatas.userData.TABLE_NAME}"

        if (db != null) {
            db.execSQL(sql)
            onCreate(db)
        } //  버전이 변경되면 기존 Table을 삭제후 재생성함.
    }

    fun createDatabase(db: SQLiteDatabase) {
        // 테이블이 존재하지 않는 경우 생성
        var sql: String = "CREATE TABLE if not exists ${LocalDatas.userData.TABLE_NAME} (" +
                "${BaseColumns._ID} integer primary key autoincrement," +
                "${LocalDatas.userData.COLUMN_NAME_ID} varchar(15)," +
                "${LocalDatas.userData.COLUMN_NAME_PASSWORD} varchar(20)"+
                ");"
        db.execSQL(sql)
    }

    fun registerUser(id: String, password: String){
        val db =this.writableDatabase
        val values = ContentValues().apply {// insert될 데이터값
            put(LocalDatas.userData.COLUMN_NAME_ID, id)
            put(LocalDatas.userData.COLUMN_NAME_PASSWORD, password)
        }
        val newRowId = db?.insert(LocalDatas.userData.TABLE_NAME, null, values)
        // 인서트후 인서트된 primary key column의 값(_id) 반환.
    }

    fun checkIdExist(id: String): Boolean{
        val db = this.readableDatabase
        val projection = arrayOf(BaseColumns._ID)
        val selection = "${LocalDatas.userData.COLUMN_NAME_ID} = ?"
        val selectionArgs = arrayOf(id)

        val cursor = db.query(
            LocalDatas.userData.TABLE_NAME,   // 테이블
            projection,             // 리턴 받고자 하는 컬럼
            selection,              // where 조건
            selectionArgs,          // where 조건에 해당하는 값의 배열
            null,           // 그룹 조건
            null,             // having 조건
            null             // orderby 조건 지정
        )
        if(cursor.count > 0){
            return true;
        }else{
            return false;
        }
    }

    fun logIn(id: String, password: String): Boolean{
        val db = this.readableDatabase
        val projection = arrayOf(BaseColumns._ID)
        val selection = "${LocalDatas.userData.COLUMN_NAME_ID} = ? AND ${LocalDatas.userData.COLUMN_NAME_PASSWORD} = ?"
        val selectionArgs = arrayOf(id, password)

        val cursor = db.query(
            LocalDatas.userData.TABLE_NAME,
            projection,
            selection,
            selectionArgs,
            null,
            null,
            null
        )
        if(cursor.count > 0){
            return true;
        }else{
            return false
        }
    }
}