package woojin.project.android.aop_part3_chapter04.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import woojin.project.android.aop_part3_chapter04.model.Review

@Dao
interface ReviewDao {
    @Query("SELECT * FROM Review WHERE id == :id")
    fun getOneReview(id:Int): Review

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveReview(review:Review)
}