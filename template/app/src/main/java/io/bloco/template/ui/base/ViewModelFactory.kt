package io.bloco.template.ui.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import io.bloco.template.data.repository.ContactRepository
import io.bloco.template.data.remote.ApiHelper
import io.bloco.template.ui.viewmodel.ContactViewModel

class ViewModelFactory (private val apiHelper: ApiHelper) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ContactViewModel::class.java)) {
            return ContactViewModel(ContactRepository(apiHelper)) as T
        }
        throw IllegalArgumentException("Unknown class name")
    }

}