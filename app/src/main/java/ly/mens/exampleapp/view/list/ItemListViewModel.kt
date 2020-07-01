package ly.mens.exampleapp.view.list

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import ly.mens.exampleapp.model.Item
import ly.mens.exampleapp.services.ItemRepository

class ItemListViewModel @ViewModelInject constructor(
    private val repository: ItemRepository,
    @Assisted private val savedStateHandle: SavedStateHandle
) : ViewModel() {
    private var _items: MutableLiveData<List<Item>>? = null
    val items: LiveData<List<Item>>
        get() {
            if (_items == null) {
                _items = MutableLiveData()
                viewModelScope.launch {
                    repository.getItems().collect { _items!!.postValue(it) }
                }
            }
            return _items!!
        }
}