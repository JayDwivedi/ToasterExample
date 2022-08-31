package com.mindorks.framework.toasterlibrary

import retrofit2.Call
import retrofit2.http.GET

interface ApiInterface {
    @GET("getjswidgetdata?domain=pep&slug=https://www.pep.ph/lifestyle/celebrations/167446/gma-thanksgiving-gala-glamorous-red-carpet-arrivals-a5128-20220730&clientid=123pep")
    fun getArticleData(): Call<ArticlesResponse>
}

