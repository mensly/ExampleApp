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
class RestaurantListFragment : Fragment() {
    private val restaurantListViewModel: RestaurantListViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return FragmentListBinding.inflate(layoutInflater).apply {
            vm = restaurantListViewModel
        }.root
    }

    override fun onResume() {
        super.onResume()
        activity?.title = resources.getString(R.string.list_title)
    }

    // TODO: Implement parallax effect on images
}