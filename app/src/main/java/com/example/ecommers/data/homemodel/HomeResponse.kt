package com.example.ecommers.data.data_models.login.response

import com.google.gson.annotations.SerializedName

data class HomeResponse (

    @SerializedName("sliderArray"        ) var sliderArray        : ArrayList<SliderArray>       = arrayListOf(),
    @SerializedName("topCategoryArray"   ) var topCategoryArray   : ArrayList<ProductDetailsResponse>  = arrayListOf(),
    @SerializedName("topBrandArray"      ) var topBrandArray      : ArrayList<ProductDetailsResponse>     = arrayListOf(),
    @SerializedName("groceryItemsArray"  ) var groceryItemsArray  : ArrayList<ProductDetailsResponse> = arrayListOf(),
    @SerializedName("homePageCallStatus" ) var homePageCallStatus : Boolean?                     = null

)
data class SliderArray (

    @SerializedName("image" ) var image : String? = null

)

data class ProductDetailsResponse (

    @SerializedName("_id"   ) var Id    : String? = null,
    @SerializedName("name"  ) var name  : String? = null,
    @SerializedName("image" ) var image : String? = null

)





