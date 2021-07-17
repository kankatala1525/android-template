package io.bloco.template.ui.main.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import io.bloco.template.R
import io.bloco.template.data.remote.RetrofitBuilder
import io.bloco.template.data.remote.model.Contacts
import kotlinx.android.synthetic.main.contact_row.view.*

class ContactsAdapter(private val contactList: ArrayList<Contacts>) : RecyclerView.Adapter<ContactsAdapter.DataViewHolder>() {

    class DataViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(contact: Contacts) {
            itemView.apply {
                textViewName.text = contact.name
                textViewEmail.text = contact.email
                Glide.with(imageViewAvatar.context)
                    .load(RetrofitBuilder.IMAGE_BASE_URL+contact.thumbnail)
                    .into(imageViewAvatar)
                if(contact.isStarred==1){
                    imageViewFav.setImageResource(R.drawable.icfavorite)
                }else{
                    imageViewFav.setImageResource(R.drawable.ic_unfavorite)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder =  DataViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.contact_row, parent, false)
    )
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
        notifyItemRangeInserted(oldCount,contactList.size)
//        notifyDataSetChanged()
    }
}