package ly.mens.exampleapp.view

import androidx.databinding.BindingAdapter
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import ly.mens.exampleapp.util.lifecycleOwner

@BindingAdapter("app:adapterClass", "app:items")
fun <T>bindAdapter(view: RecyclerView, adapterClass: String, items: LiveData<List<T>>) {
    val adp = Class.forName(adapterClass).newInstance() as AbstractListAdapter<T, *>
    view.adapter = adp
    items.observe(view.context.lifecycleOwner()!!, Observer { adp.items = it ?: emptyList() })
}
