package ly.mens.exampleapp.view.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.Keep
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.cell_item.view.*
import ly.mens.exampleapp.R
import ly.mens.exampleapp.model.Item
import ly.mens.exampleapp.util.appCompatActivity
import ly.mens.exampleapp.view.AbstractListAdapter
import ly.mens.exampleapp.view.item.ItemInfoFragment
import kotlin.properties.Delegates

@Keep
class ItemListAdapter : AbstractListAdapter<Item, ItemListAdapter.ViewHolder>() {
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var item by Delegates.observable<Item?>(null) { _, _, newItem ->
            if (newItem == null) {
                itemView.visibility = View.INVISIBLE
                return@observable
            }
            itemView.text.text = newItem.title
        }

        init {
            itemView.setOnClickListener {
                val item = this.item ?: return@setOnClickListener
                it.context.appCompatActivity()!!.supportFragmentManager.beginTransaction()
                    .replace(R.id.content, ItemInfoFragment().apply {
                        arguments = ItemInfoFragment.createArguments(item)
                    })
                    .addToBackStack(item.id)
                    .commit()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.cell_item, parent, false))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.item = items[position]
    }
}