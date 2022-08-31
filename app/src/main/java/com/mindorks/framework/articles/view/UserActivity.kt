package com.mindorks.framework.articles.view

import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.telecom.Call
import android.view.*
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.mindorks.framework.articles.utilities.Utilities
import com.mindorks.framework.toasterexample.R
import com.mindorks.framework.toasterlibrary.ApiClient
import com.mindorks.framework.toasterlibrary.ArticleResult
import com.mindorks.framework.toasterlibrary.ArticlesResponse
import com.mindorks.framework.toasterlibrary.Constants.REQUEST_CODE
import com.mindorks.framework.toasterlibrary.Constants.RESULT_CODE
import com.mindorks.framework.toasterlibrary.CustomRecyclerView
import java.lang.Exception


class UserActivity : BaseActivity(), View.OnClickListener{
    lateinit var fab_addItem: FloatingActionButton
    lateinit var recyclerView: RecyclerView
    lateinit var userAdapter: UserListAdapter
    lateinit var progerssProgressDialog: ProgressDialog
    var userList = ArrayList<ArticleResult>()
    var position: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user)
        initialise()
    }

    private fun initialise() {
        super.init()
        toolbar?.setTitle("Articles")

        /* To hide navigationIcon */
        supportActionBar?.setDisplayHomeAsUpEnabled(false)

        fab_addItem = findViewById(R.id.fab_addItem)
        recyclerView = findViewById(R.id.recyclerView)
        fab_addItem.setOnClickListener(this)
        recyclerView.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        progerssProgressDialog = ProgressDialog(this)
        progerssProgressDialog.setTitle("Loading")
        progerssProgressDialog.setMessage("Please Wait...")
        progerssProgressDialog.setCancelable(false)
        progerssProgressDialog.show()

        try {

            fetchData()
            userAdapter = UserListAdapter(this, userList, {user,position -> onItemClicked(user,position)})
            recyclerView.adapter = userAdapter;

        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    /* To hide menu */
    override fun onPrepareOptionsMenu(menu: Menu?): Boolean {
        var deleteItem = menu!!.findItem(R.id.menu_delete)
        deleteItem.setVisible(false)
        return true
    }

    private fun fetchData() {


        CustomRecyclerView().fetchArticleDataFromApi(
           { articleList ->
               userList.addAll(articleList)
               recyclerView.adapter!!.notifyDataSetChanged()
               progerssProgressDialog.dismiss()
           },
           {
               progerssProgressDialog.dismiss()
               Utilities.showAlert(this@UserActivity, getString(R.string.check_internet_connection))
           }

       )

    }

    private fun onItemClicked(user: ArticleResult, position: Int){

        this.position = position
        var intent = Intent(this, UserDetailsActivity::class.java)
        intent.putExtra("username",user.articleTitle)
        intent.putExtra("userType",user.articleSection)
        intent.putExtra("image",user.articleImage)
        startActivityForResult(intent, REQUEST_CODE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(resultCode.equals(RESULT_CODE)){
            try{
                userAdapter.removeItem(position)
                Toast.makeText(this, R.string.user_deleted, Toast.LENGTH_LONG).show()

            }catch (e: Exception){
                e.printStackTrace()
            }
        }
    }

    override fun onClick(view: View?) {
        if (view != null) {
            when (view.id) {
                R.id.fab_addItem -> {
                    var dialog = AddItemDialog(this)
                    dialog.show()
                    dialog.window!!.setLayout(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT
                    )

                    dialog.onAddItem(object : AddItemDialog.IAddItemCallback {
                        override fun addItem(user: ArticleResult) {

                            userAdapter.addItem(user)

                        }
                    })
                }
            }
        }
    }
}

