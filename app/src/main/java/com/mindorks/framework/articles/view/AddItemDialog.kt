package com.mindorks.framework.articles.view

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.mindorks.framework.toasterexample.R
import com.mindorks.framework.toasterlibrary.ArticleResult


class AddItemDialog(context: Context) : Dialog(context){

    lateinit var et_name: EditText
    lateinit var et_userType: EditText
    lateinit var btn_add: Button
    var iAddItemCallback: IAddItemCallback? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_item_dialog)
        /* To display full Screen of Dialog */
        window!!.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        init()
    }

    fun init(){
        et_name = findViewById(R.id.et_name)
        et_userType = findViewById(R.id.et_userType)
        btn_add = findViewById(R.id.btn_add)

        btn_add.setOnClickListener(View.OnClickListener {
            dismiss()
            if(iAddItemCallback != null){
                iAddItemCallback!!.addItem(ArticleResult(et_name.text.toString(),et_userType.text.toString(),"https://avatars2.githubusercontent.com/u/32?v=4"))
                Toast.makeText(context, R.string.user_added, Toast.LENGTH_LONG).show()
            }
        })
    }

    interface IAddItemCallback{
        fun addItem(user: ArticleResult)
    }

    fun onAddItem(iAddItemCallback: IAddItemCallback){
        this.iAddItemCallback = iAddItemCallback
    }
}
