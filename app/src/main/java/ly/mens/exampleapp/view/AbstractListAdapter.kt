package ly.mens.exampleapp.view

import androidx.recyclerview.widget.RecyclerView
import kotlin.properties.Delegates

abstract class AbstractListAdapter<T, VH : RecyclerView.ViewHolder> : RecyclerView.Adapter<VH>() {
    var items: List<T> by Delegates.observable(emptyList()) { _, _, _ -> notifyDataSetChanged() }

    override fun getItemCount() = items.size
}