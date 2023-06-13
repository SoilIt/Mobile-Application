package com.github.user.soilitouraplication.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.github.user.soilitouraplication.api.History

@Database(entities = [History::class], version = 4)
abstract class HistoryDatabase : RoomDatabase() {
    abstract fun historyDao(): HistoryDao

}
