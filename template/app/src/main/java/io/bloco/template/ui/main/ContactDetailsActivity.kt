package io.bloco.template.ui.main

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import io.bloco.template.R
import io.bloco.template.data.remote.RetrofitBuilder
import io.bloco.template.data.remote.model.Contacts
import io.bloco.template.databinding.ContactDetailsBinding

class ContactDetailsActivity : AppCompatActivity() {
    lateinit var contactDetailsBinding: ContactDetailsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        contactDetailsBinding= ContactDetailsBinding.inflate(layoutInflater)
        setContentView(contactDetailsBinding.root)

        val contact = intent.getSerializableExtra("details") as Contacts?

        Log.d("details","dahjdasd")

        contactDetailsBinding.textViewName.text=contact?.name
        contactDetailsBinding.textViewNumber.text=contact?.phone
        contactDetailsBinding.textViewEmail.text=contact?.email
        Glide.with(this)
            .load(RetrofitBuilder.IMAGE_BASE_URL + contact?.thumbnail)
            .transform(CircleCrop())
            .into(contactDetailsBinding.imageViewAvatar)
        if (contact?.isStarred == 1) {
            contactDetailsBinding.imageViewFav.setImageResource(R.drawable.icfavorite)
        } else {
            contactDetailsBinding.imageViewFav.setImageResource(R.drawable.ic_unfavorite)
        }



    }
}