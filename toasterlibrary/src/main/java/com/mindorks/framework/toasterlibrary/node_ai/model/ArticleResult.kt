package com.mindorks.framework.toasterlibrary

import com.google.gson.annotations.SerializedName


data class ArticleResult(

    @SerializedName("article_old_id") var articleOldId: String? = null,
    @SerializedName("article_title") var articleTitle: String? = null,
    @SerializedName("article_url") var articleUrl: String? = null,
    @SerializedName("article_image") var articleImage: String? = null,
    @SerializedName("domain") var domain: String? = null,
    @SerializedName("article_title_id") var articleTitleId: String? = null,
    @SerializedName("article_section") var articleSection: String? = null

)