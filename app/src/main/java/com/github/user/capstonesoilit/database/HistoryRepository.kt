package com.github.user.capstonesoilit.database

import com.github.user.capstonesoilit.api.History
import com.github.user.capstonesoilit.api.HistoryApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

class HistoryRepository(private val api: HistoryApi, private val dao: HistoryDao) {
    suspend fun fetchDataAndSaveToDatabase() {
        val response = api.getHistory().execute()
        if (response.isSuccessful) {
            val historyList = response.body()?.data
            historyList?.let {
                withContext(Dispatchers.IO) {
                    for (history in historyList) {
                        val historyEntity = History(
                            history.id,
                            history.user_id,
                            history.soil_type,
                            history.soil_moisture,
                            history.soil_temperature,
                            history.soil_condition,
                            history.created_at
                        )
                        dao.insertHistory(historyList)
                    }
                }
            }
        } else {
            // Handle error response
        }
    }
}
