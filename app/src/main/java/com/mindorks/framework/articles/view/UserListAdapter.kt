package com.mindorks.framework.articles.view

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.mindorks.framework.toasterexample.R
import com.mindorks.framework.toasterlibrary.ArticleResult
import java.util.ArrayList

class UserListAdapter(

    val context: Context,
    var userList: ArrayList<ArticleResult>,
    private val itemClickListener: (ArticleResult, position: Int) -> Unit) : RecyclerView.Adapter<UserListAdapter.UserViewHolder>()
    {

    var position: Int = 0

    /* Initialization */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        return UserViewHolder(
            context, LayoutInflater.from(context).inflate(R.layout.layout_user_row, parent, false)
        )
    }

    override fun getItemCount() = userList.size

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.bindView(userList[position], position, itemClickListener)
        this.position = position

    }

    class UserViewHolder(val context: Context, itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bindView(user: ArticleResult, selectedPosition: Int, itemClickListener: (ArticleResult, pos: Int) -> Unit) {


            val tv_userName = itemView.findViewById<TextView>(R.id.tv_userName)
            val tv_userType = itemView.findViewById<TextView>(R.id.tv_userType)
            val iv_avatar = itemView.findViewById<ImageView>(R.id.iv_avatar)

            try {
                tv_userName.text = user.articleTitle
                tv_userType.text = user.articleSection

                Glide.with(itemView).load(user.articleImage)
                    .apply(RequestOptions().centerCrop())
                    .into(iv_avatar)

                itemView.setOnClickListener { itemClickListener(user, selectedPosition) }

            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    public fun addItem(user: ArticleResult) {
        userList.add(user)
        notifyItemInserted(position)
        notifyItemRangeChanged(position, userList.size)
    }

    public fun removeItem(position: Int) {
        userList.removeAt(position)
        notifyItemRemoved(position)
        notifyItemRangeChanged(position, userList.size)
    }

}