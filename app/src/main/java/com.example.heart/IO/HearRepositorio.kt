package heart.IO

import androidx.annotation.WorkerThread
import heart.BD.BD_Heart
import heart.BD.BD_HeartDao
import kotlinx.coroutines.flow.Flow

class HearRepositorio(private val BD_HeartDAO:BD_HeartDao){
    val allHeart: Flow<List<BD_Heart>> = BD_HeartDAO.getALL()

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(BD_Heart: BD_Heart){
        BD_HeartDAO.insert(BD_Heart)
    }

    suspend fun delete(heart: BD_Heart) {
        BD_HeartDAO.delete(heart)
    }
}