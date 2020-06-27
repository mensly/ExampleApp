package ly.mens.exampleapp.view.item

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import ly.mens.exampleapp.databinding.FragmentItemBinding
import ly.mens.exampleapp.model.Item

@AndroidEntryPoint
class ItemInfoFragment : Fragment() {
    companion object {
        const val ARG_ITEM = "item"
        fun createArguments(item: Item) = Bundle().apply {
            putParcelable(ARG_ITEM, item)
        }
    }

    private val itemInfoViewModel: ItemInfoViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        itemInfoViewModel.item.set(requireArguments().getParcelable(ARG_ITEM)!!)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return FragmentItemBinding.inflate(layoutInflater).apply {
            vm = itemInfoViewModel
        }.root
    }

    override fun onResume() {
        super.onResume()
        activity?.title = itemInfoViewModel.item.get()?.title
    }
}