package com.github.user.capstonesoilit.database

import androidx.room.*
import com.github.user.capstonesoilit.api.History
import kotlinx.coroutines.flow.Flow

@Dao
interface HistoryDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertHistory(historyList: List<History>)

    @Query("SELECT * FROM history")
    fun getAllHistory(): Flow<List<History>>

    @Delete
    suspend fun deleteHistory(history: History)

    @Query("DELETE FROM history")
    suspend fun deleteAllHistory()

    @Query("SELECT COUNT(*) FROM history")
    suspend fun getHistoryCount(): Int
}

