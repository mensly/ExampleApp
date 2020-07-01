package ly.mens.exampleapp.view.list

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.Keep
import androidx.recyclerview.widget.RecyclerView
import ly.mens.exampleapp.databinding.CellItemBinding
import ly.mens.exampleapp.model.Restaurant
import ly.mens.exampleapp.view.AbstractListAdapter
import kotlin.properties.Delegates

@Keep
class RestaurantListAdapter : AbstractListAdapter<Restaurant, RestaurantListAdapter.ViewHolder>() {
    class ViewHolder(binding: CellItemBinding) : RecyclerView.ViewHolder(binding.root) {
        var item by Delegates.observable<Restaurant?>(null) { _, _, newItem ->
            if (newItem == null) {
                itemView.visibility = View.INVISIBLE
                return@observable
            }
            itemView.visibility = View.VISIBLE
            binding.restaurant = newItem
            Log.d("Foobar", newItem.featuredImage ?: "No image")
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(CellItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.item = items[position]
    }
}