package com.mindorks.framework.toasterlibrary

import java.lang.Exception
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
class CustomRecyclerView {
    var articleList = ArrayList<ArticleResult>()

    fun fetchArticleDataFromApi(success: (articleList: ArrayList<ArticleResult>) -> Unit, failed: (e: Any) -> Unit) {
        val call: Call<ArticlesResponse> = ApiClient.create().getArticleData()
        call.enqueue(object : Callback<ArticlesResponse> {
            override fun onFailure(call: Call<ArticlesResponse>, t: Throwable) {
                failed(call)
            }

            override fun onResponse(call: Call<ArticlesResponse>?, response: Response<ArticlesResponse>?) {
                try {
                    if (response != null && response.isSuccessful && response.body() != null) {

                        articleList.addAll(response!!.body()!!.articleResult)
                        success(articleList)

                    }
                } catch (e: Exception) {
                    failed(e)
                    e.printStackTrace()
                }
            }
        })

    }



}