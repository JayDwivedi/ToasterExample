package com.mindorks.framework.toasterlibrary

import com.google.gson.annotations.SerializedName


data class ArticlesResponse(

    @SerializedName("status") var status: Int? = null,
    @SerializedName("targetid") var targetid: String? = null,
    @SerializedName("targetclass") var targetclass: String? = null,
    @SerializedName("currenttheme") var currenttheme: String? = null,
    @SerializedName("widgettitle") var widgettitle: String? = null,
    @SerializedName("itemcount") var itemcount: Int? = null,
    @SerializedName("result") var articleResult: ArrayList<ArticleResult> = arrayListOf()

)