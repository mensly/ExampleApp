package ly.mens.exampleapp.view.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import ly.mens.exampleapp.R
import ly.mens.exampleapp.databinding.FragmentListBinding

@AndroidEntryPoint
class ItemListFragment: Fragment() {
    private val itemListViewModel: ItemListViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return FragmentListBinding.inflate(layoutInflater).apply {
            vm = itemListViewModel
        }.root
    }

    override fun onResume() {
        super.onResume()
        activity?.title = resources.getString(R.string.app_name)
    }
}