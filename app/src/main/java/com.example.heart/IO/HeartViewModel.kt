package heart.IO

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import heart.BD.BD_Heart
import kotlinx.coroutines.launch

class HeartViewModel (private val repositorio: HearRepositorio) : ViewModel(){

    val allHeart: LiveData<List<BD_Heart>> = repositorio.allHeart.asLiveData()

    fun insert(Heart:BD_Heart) = viewModelScope.launch{
        repositorio.insert(Heart)
    }

    fun delete(Heart: BD_Heart) = viewModelScope.launch {
        repositorio.delete(Heart)
    }
}

class HearViewModelFactory(private val repositorio: HearRepositorio): ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(HeartViewModel::class.java)){
            @Suppress("UNCHECKED_CAST")
            return HeartViewModel(repositorio) as T
        }

        throw IllegalArgumentException("Clase viewModel desconocida")
    }
}