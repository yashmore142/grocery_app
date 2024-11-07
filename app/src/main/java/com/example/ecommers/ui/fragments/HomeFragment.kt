package com.example.ecommers.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ecommers.databinding.FragmentHomeBinding
import com.example.ecommers.data.data_models.login.response.HomeResponse
import com.example.ecommers.data.product.Product
import com.example.ecommers.ui.adapter.*
import com.example.ecommers.ui.viewmodel.HomeViewModel
import com.example.ecommers.utils.GlobalSnackBar
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {
    private lateinit var mList: ArrayList<DataItem>
    private lateinit var mProductList: List<Product>
    private lateinit var mBinding: FragmentHomeBinding
    private val homeViewModel by viewModels<HomeViewModel>()
    private lateinit var productAdapter: ProductAdapter
    var layoutManager: LinearLayoutManager? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        mBinding = FragmentHomeBinding.inflate(layoutInflater)
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setObserver()
        homeViewModel.getHomeInfo()
        mBinding.mainRecyclerView.setHasFixedSize(true)
        mBinding.mainRecyclerView.layoutManager = LinearLayoutManager(requireContext())


        layoutManager = GridLayoutManager(requireContext(), 2)
        mBinding.productRecyclerView.layoutManager = layoutManager
        mList = ArrayList()



    }
    private fun setObserver() {
        homeViewModel._responseHome.observe(viewLifecycleOwner) {
            val viewPagerAdapter = BannerViewPagerAdapter(requireActivity(), it.sliderArray)

            prepareData(it)
            mBinding.viewPagerHome.adapter = viewPagerAdapter
            val adapter = CategryAdapter(mList,requireContext())
            mBinding.mainRecyclerView.adapter = adapter
            TabLayoutMediator(mBinding.tabDots, mBinding.viewPagerHome) { tab, position ->
                //tab.text = tabTitles[position]
            }.attach()

            productAdapter = ProductAdapter(requireContext(),mProductList,
                object : ProductClickListener {
                    override fun onAddProductClick(product: Product) {
                        homeViewModel.addProductToCart(product)
                    }
                })
            mBinding.productRecyclerView.adapter = productAdapter

        }

        homeViewModel.errorData.observe(viewLifecycleOwner) {
            GlobalSnackBar.showSnackBar(
                mBinding.root,
                it,
                false
            )
        }
        homeViewModel.successData.observe(viewLifecycleOwner){
            GlobalSnackBar.showSnackBar(
                mBinding.root,
                it,
                true
            )
        }
    }

    private fun prepareData(homeResponse: HomeResponse) {
        mList.add(DataItem(DataItemType.BEST_SELLING_ITEMS, homeResponse.topCategoryArray))
        mList.add(DataItem(DataItemType.OTHER_ITEMS, homeResponse.topBrandArray))
        mList.add(DataItem(DataItemType.BEST_SELLING_ITEMS, homeResponse.groceryItemsArray))



        mProductList = listOf(
            Product(
                id = 1,
                name = "Organic Bananas",
                description = "Fruits",
                price = 60.99,
                weight = "1 dozen",
                imageUrl = "https://upload.wikimedia.org/wikipedia/commons/8/8a/Banana-Single.jpg",
                inStock = true
            ),
            Product(
                id = 2,
                name = "Whole Wheat Bread",
                description = "Bakery",
                price = 50.0,
                weight = "1 loaf",
                imageUrl = "https://upload.wikimedia.org/wikipedia/commons/3/33/Fresh_made_bread_05.jpg",
                inStock = true
            ),
            Product(
                id = 3,
                name = "Organic Milk",
                description = "Dairy",
                price = 80.99,
                weight = "1 lit",
                imageUrl = "https://en.wikipedia.org/wiki/Oat_milk#/media/File:Oat_milk_glass_and_bottles.jpg",
                inStock = false
            ),
            Product(
                id = 4,
                name = "Brown Rice",
                description = "Grains",
                price = 600.0,
                weight = "10 kg",
                imageUrl = "https://upload.wikimedia.org/wikipedia/commons/e/e7/Brown_rice.jpg",
                inStock = true
            ),
            Product(
                id = 5,
                name = "Olive Oil",
                description = "Pantry",
                price = 200.0,
                weight = "1 kg",
                imageUrl = "https://upload.wikimedia.org/wikipedia/commons/c/cf/Olive-oil-1412361_1920.jpg",
                inStock = true
            )
        )


    }
}