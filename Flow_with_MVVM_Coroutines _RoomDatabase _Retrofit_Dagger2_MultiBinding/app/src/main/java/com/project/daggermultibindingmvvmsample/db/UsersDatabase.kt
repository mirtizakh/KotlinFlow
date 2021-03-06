package com.project.daggermultibindingmvvmsample.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.project.daggermultibindingmvvmsample.Users

@Database(entities = [(Users::class)], version = 1)
abstract class UsersDatabase : RoomDatabase(){

    abstract fun usersDao() : UsersDao
}