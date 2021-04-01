package heart

import heart.BD.BD_Heart_Database
import android.app.Application
import heart.IO.HearRepositorio
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

class HeartApplication : Application() {
    val applicationScope = CoroutineScope(SupervisorJob())

    val database by lazy { BD_Heart_Database.getDatabase(this, applicationScope) }
    val repositorio by lazy { HearRepositorio(database.HeartDao()) }
}