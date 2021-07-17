package io.bloco.template.ui.main

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import io.bloco.template.R
import io.bloco.template.data.remote.ApiHelper
import io.bloco.template.data.remote.RetrofitBuilder
import io.bloco.template.data.remote.model.Contacts
import io.bloco.template.ui.base.ViewModelFactory
import io.bloco.template.ui.main.adapter.ContactsAdapter
import io.bloco.template.ui.viewmodel.ContactViewModel
import io.bloco.template.utils.Resource
import kotlinx.android.synthetic.main.activity_contacts.*

class ContactsActivity : AppCompatActivity() {

    lateinit var contactViewModel: ContactViewModel
    lateinit var contactsAdapter: ContactsAdapter

    private var currentPage = 1
    private var totalAvailablePages = 1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contacts)

        setupViewModel()
        setupUI()
        getContacts()
    }




    private fun setupViewModel() {

        contactViewModel= ViewModelProvider(
            this,
            ViewModelFactory(ApiHelper(RetrofitBuilder.apiService))
        ).get(ContactViewModel::class.java)
    }
    private fun setupUI() {

        contactsAdapter = ContactsAdapter(arrayListOf())
        recyclerView.addItemDecoration(
            DividerItemDecoration(
                recyclerView.context,
                (recyclerView.layoutManager as LinearLayoutManager).orientation
            )
        )
        recyclerView.adapter= contactsAdapter

        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener(){

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if(recyclerView.canScrollVertically(1)){
                    if(currentPage<=totalAvailablePages){
                        currentPage+=1
                        getContacts()
                    }
                }
            }
        })

    }
    private fun getContacts() {
        Log.d("pageNumner",""+currentPage)

        contactViewModel.getContacts(currentPage).observe(this,  {
            it?.let { resource ->
                when (resource.status) {
                    Resource.Status.SUCCESS -> {
                        recyclerView.visibility = View.VISIBLE
                        progressBar.visibility = View.GONE
                        resource.data?.let { contactsList ->
                            totalAvailablePages=contactsList.body()!!.meta.pageSize
                            retrieveList(contactsList.body()!!.content)
                        }
                    }
                    Resource.Status.ERROR -> {
                        recyclerView.visibility = View.VISIBLE
                        progressBar.visibility = View.GONE
                        Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
                    }
                    Resource.Status.LOADING -> {
                        progressBar.visibility = View.VISIBLE
                        recyclerView.visibility = View.VISIBLE
                    }
                }
            }
        })
    }
    private fun retrieveList(contactList: List<Contacts>) {
        contactsAdapter.apply {
            addContacts(contactList)
        }
    }
}