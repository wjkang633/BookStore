package woojin.project.android.aop_part3_chapter04.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import woojin.project.android.aop_part3_chapter04.model.History

@Dao
interface HistoryDao {
    @Query("SELECT * FROM history")
    fun getAll():List<History>

    @Insert
    fun insertHistory(history:History)

    @Query("DELETE FROM history WHERE keyword == :keyword")
    fun delete(keyword:String)
}