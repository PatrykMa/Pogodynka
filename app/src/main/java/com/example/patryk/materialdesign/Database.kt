package com.example.patryk.materialdesign

import androidx.room.Entity
import androidx.room.*
/*
@Entity
data class CityDatabase(
    @PrimaryKey var uid: Int,
    @ColumnInfo(name = "cityName") var cityName: String?
)

@Dao
interface UserDao {
    @Query("SELECT * FROM CityDatabase")
    fun getAll(): List<CityDatabase>

    @Query("SELECT * FROM CityDatabase WHERE uid IN (:userIds)")
    fun loadAllByIds(userIds: IntArray): List<User>

    @Query("SELECT * FROM user WHERE first_name LIKE :first AND " +
            "last_name LIKE :last LIMIT 1")
    fun findByName(first: String, last: String): User

    @Insert
    fun insertAll(vararg users: User)

    @Delete
    fun delete(user: User)
}

@Database(entities = arrayOf(User::class), version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
}
       */