package io.bloco.template.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import io.bloco.template.data.repository.ContactRepository
import io.bloco.template.utils.Resource
import kotlinx.coroutines.Dispatchers

class ContactViewModel(private val contactRepository: ContactRepository):ViewModel() {

    fun getContacts(pageNumber:Int) = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = contactRepository.getContacts(pageNumber)))
        } catch (exception: Exception) {
            emit(Resource.error(data = null, message = exception.message ?: "Error Occurred!"))
        }
    }
}