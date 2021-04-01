package heart.BD

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface BD_HeartDao {
    @Query("SELECT * FROM HeartBD ")
    fun getALL(): Flow<List<BD_Heart>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(Heart: BD_Heart)

    @Query("DELETE FROM HeartBD")
    suspend fun deleteAll()

    @Delete
    suspend fun delete(Heart:BD_Heart)
}