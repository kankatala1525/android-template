package io.bloco.template.ui.main.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import io.bloco.template.R
import io.bloco.template.data.remote.RetrofitBuilder
import io.bloco.template.data.remote.model.Contacts
import io.bloco.template.databinding.ContactRowBinding
import kotlinx.android.synthetic.main.contact_row.view.*

class ContactsAdapter(
    private val contactList: ArrayList<Contacts>,
    private val listener: ContactItemListener
) : RecyclerView.Adapter<ContactsAdapter.DataViewHolder>() {
    interface ContactItemListener {
        fun onClickedContact(contact: Contacts)
    }

    class DataViewHolder(
        private val binding: ContactRowBinding,
        private val listener: ContactItemListener
    ) : RecyclerView.ViewHolder(binding.root),
        View.OnClickListener {
        private lateinit var contact: Contacts

        init {
            binding.root.setOnClickListener(this)
        }

        fun bind(contact: Contacts) {
            this.contact = contact
            binding.textViewName.text = contact.name
            binding.textViewEmail.text = contact.email
            Glide.with(binding.root)
                .load(RetrofitBuilder.IMAGE_BASE_URL + contact.thumbnail)
                .transform(CircleCrop())
                .into(binding.imageViewAvatar)
            if (contact.isStarred == 1) {
                binding.imageViewFav.setImageResource(R.drawable.icfavorite)
            } else {
                binding.imageViewFav.setImageResource(R.drawable.ic_unfavorite)
            }


        }

        override fun onClick(v: View?) {
            listener.onClickedContact(contact)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder {
        val binding: ContactRowBinding =
            ContactRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DataViewHolder(binding, listener)
    }

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        holder.bind(contactList[position])
    }

    override fun getItemCount(): Int = contactList.size

    fun addContacts(contactList: List<Contacts>) {
        val oldCount: Int = this.contactList.size
        this.contactList.apply {
            //  clear()
            addAll(contactList)
        }
        notifyItemRangeInserted(oldCount, contactList.size)
//        notifyDataSetChanged()
    }
}