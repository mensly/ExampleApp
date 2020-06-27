package ly.mens.exampleapp.view.item

import androidx.databinding.ObservableField
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import ly.mens.exampleapp.model.Item

class ItemInfoViewModel @ViewModelInject constructor(): ViewModel() {
    val item = ObservableField<Item>()
}