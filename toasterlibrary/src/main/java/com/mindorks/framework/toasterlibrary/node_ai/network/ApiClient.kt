package com.mindorks.framework.toasterlibrary

import com.mindorks.framework.toasterlibrary.Constants.BASE_URL
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {

        fun create(): ApiInterface {
            val retrofit = Retrofit.Builder().addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL).build()
            return retrofit.create(ApiInterface::class.java)
        }

}