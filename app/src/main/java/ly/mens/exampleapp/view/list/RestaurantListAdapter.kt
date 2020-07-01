package ly.mens.exampleapp.view.list

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.Keep
import androidx.databinding.Observable
import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.RecyclerView
import ly.mens.exampleapp.databinding.CellItemBinding
import ly.mens.exampleapp.model.Restaurant
import ly.mens.exampleapp.services.FavouritesManager
import ly.mens.exampleapp.view.AbstractListAdapter
import kotlin.properties.Delegates

@Keep
class RestaurantListAdapter : AbstractListAdapter<Restaurant, RestaurantListAdapter.ViewHolder>() {
    class ViewHolder(adp: RestaurantListAdapter, binding: CellItemBinding) : RecyclerView.ViewHolder(binding.root) {
        private val vm = RestaurantItemViewModel()
        init {
            binding.vm = vm
            vm.isFavourite.addOnPropertyChangedCallback(object : Observable.OnPropertyChangedCallback() {
                override fun onPropertyChanged(sender: Observable?, propertyId: Int) {
                    val restaurant = this@ViewHolder.item ?: return
                    adp.favouritesManager.setFavourite(restaurant, vm.isFavourite.get())
                }
            })
        }

        var item by Delegates.observable<Restaurant?>(null) { _, _, newItem ->
            if (newItem == null) {
                itemView.visibility = View.INVISIBLE
                return@observable
            }
            itemView.visibility = View.VISIBLE
            vm.restaurant.set(newItem)
            vm.isFavourite.set(adp.favouritesManager.isFavourite(newItem))
        }
    }

    lateinit var favouritesManager: FavouritesManager

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(this, CellItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.item = items[position]
    }
}

class RestaurantItemViewModel: ViewModel() {
    val restaurant = ObservableField<Restaurant>()
    val isFavourite = ObservableBoolean()
}