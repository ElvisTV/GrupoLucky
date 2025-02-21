package com.elvistezen.grupolucky
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class User(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo(name = "username") val username: String,
    @ColumnInfo(name = "password") val password: String
)


//@Entity(tableName = "users")
//data class User(
//    @PrimaryKey(autoGenerate = true)
//    val id: Int = 0,
//    val username: String,
//    val password: String
//)

