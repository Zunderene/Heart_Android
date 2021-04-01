package heart.BD

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

@Database(entities = [BD_Heart::class], version = 1)
abstract class BD_Heart_Database : RoomDatabase(){
    abstract  fun HeartDao(): BD_HeartDao

    companion object{
        @Volatile
        private var INSTANCE: BD_Heart_Database? = null

        fun getDatabase(context:Context, scope: CoroutineScope): BD_Heart_Database{
            return INSTANCE ?: synchronized(this){
                val instancce =
                        Room.databaseBuilder(context.applicationContext,
                                BD_Heart_Database::class.java, "HeartBD")
                                .fallbackToDestructiveMigration()
                                .addCallback(HeartDatabaseCallback(scope))
                                .build()
                INSTANCE = instancce
                instancce
            }
        }

        private class HeartDatabaseCallback(private  val scope:CoroutineScope): RoomDatabase.Callback(){
            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)
                INSTANCE.let {
                    scope.launch(Dispatchers.IO){
                        if (it != null) {
                            elementDatabase(it.HeartDao())
                        }
                    }
                }
            }
        }

        suspend fun elementDatabase(heartDao: BD_HeartDao){
            heartDao.deleteAll()
            heartDao.insert(BD_Heart(UUID.randomUUID().toString(), 150, 90, 50, "Normal", SimpleDateFormat("YYYY-MM-dd hh:mm").format(Date()).toString()))
        }
    }
}