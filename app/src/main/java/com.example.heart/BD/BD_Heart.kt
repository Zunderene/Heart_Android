package heart.BD

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "HeartBD")
data class BD_Heart(
    @PrimaryKey
    @ColumnInfo(name = "Id")
    val id:String = UUID.randomUUID().toString(),

    @ColumnInfo(name = "SYS")
    val SYS:Int,
    @ColumnInfo(name = "DYS")
    val DYS:Int,
    @ColumnInfo(name = "PUL")
    val PUL:Int,
    @ColumnInfo(name = "EST")
    val EST:String,
    @ColumnInfo(name = "SAV")
    val SAV:String
)