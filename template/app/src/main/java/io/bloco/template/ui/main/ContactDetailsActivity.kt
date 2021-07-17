package io.bloco.template.ui.main

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import io.bloco.template.R
import io.bloco.template.data.remote.ApiHelper
import io.bloco.template.data.remote.RetrofitBuilder
import io.bloco.template.data.remote.model.Contacts
import io.bloco.template.databinding.ContactDetailsBinding
import io.bloco.template.ui.base.ViewModelFactory
import io.bloco.template.ui.viewmodel.ContactViewModel
import io.bloco.template.ui.viewmodel.StarsViewModel
import io.bloco.template.utils.Resource

class ContactDetailsActivity : AppCompatActivity() {
    lateinit var contactDetailsBinding: ContactDetailsBinding
    var contact: Contacts? = null
    lateinit var starsViewModel: StarsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        contactDetailsBinding = ContactDetailsBinding.inflate(layoutInflater)
        setContentView(contactDetailsBinding.root)

        contact = intent.getSerializableExtra("details") as Contacts?

        Log.d("details", "dahjdasd")

        contactDetailsBinding.textViewName.text = contact?.name
        contactDetailsBinding.textViewNumber.text = contact?.phone
        contactDetailsBinding.textViewEmail.text = contact?.email
        Glide.with(this)
            .load(RetrofitBuilder.IMAGE_BASE_URL + contact?.thumbnail)
            .transform(CircleCrop())
            .into(contactDetailsBinding.imageViewAvatar)
        if (contact?.isStarred == 1) {
            contactDetailsBinding.imageViewFav.setImageResource(R.drawable.icfavorite)
        } else {
            contactDetailsBinding.imageViewFav.setImageResource(R.drawable.ic_unfavorite)
        }
        setupViewModel()
        contactDetailsBinding.imageViewFav.setOnClickListener {
            updateDate(contact?.id!!)
        }

    }

    private fun setupViewModel() {
        starsViewModel = ViewModelProvider(
            this,
            ViewModelFactory(ApiHelper(RetrofitBuilder.apiService))
        ).get(StarsViewModel::class.java)
    }
    private fun updateDate(id:Int){
        starsViewModel.startsAdd(id).observe(this, Observer {

            it?.let { resource ->
                when (resource.status) {
                    Resource.Status.SUCCESS -> {
                        when(resource.data?.code()){
                            404->
                            {
                                Toast.makeText(this, resource.data.message(), Toast.LENGTH_LONG).show()

                            }
                            200->{
                                resource.data?.let { starsData ->

                                    updateUI(starsData.body()!!.content)
                                }
                            }
                        }

                    }
                    Resource.Status.ERROR -> {

                        Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
                    }
                    Resource.Status.LOADING -> {

                    }
                }
            }
        })
    }

    private fun updateUI(content: Contacts) {
        if (contact?.updateContactInfo?.isStarred == 1) {
            contactDetailsBinding.imageViewFav.setImageResource(R.drawable.icfavorite)
        } else {
            contactDetailsBinding.imageViewFav.setImageResource(R.drawable.ic_unfavorite)
        }



    }
}