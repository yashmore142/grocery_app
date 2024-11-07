package com.example.ecommers.ui.adapter

import com.example.ecommers.data.data_models.login.response.ProductDetailsResponse

class DataItem (val viewType: Int) {

    var recyclerItemList: List<ProductDetailsResponse>? = null
    var banner: Banner? = null

    constructor(viewType: Int, recyclerItemList: List<ProductDetailsResponse>) : this(viewType) {
        this.recyclerItemList = recyclerItemList
    }

    constructor(viewType: Int, banner: Banner) : this(viewType) {
        this.banner = banner
    }

}

data class Banner(val image: Int)