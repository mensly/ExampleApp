package ly.mens.exampleapp.view

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import ly.mens.exampleapp.util.lifecycleOwner

@BindingAdapter("app:adapterClass", "app:items")
fun <T> bindAdapter(view: RecyclerView, adapterClass: String, items: LiveData<List<T>>) {
    val adp = Class.forName(adapterClass).newInstance() as AbstractListAdapter<T, *>
    view.adapter = adp
    items.observe(view.context.lifecycleOwner()!!, Observer { adp.items = it ?: emptyList() })
}

@BindingAdapter("app:image_url")
fun bindImage(view: ImageView, url: String) {
    Picasso.get().cancelRequest(view)
    view.setImageDrawable(null)
    if (url.isNotEmpty()) {
        Picasso.get()
            .load(url)
            .into(view)
    }
}