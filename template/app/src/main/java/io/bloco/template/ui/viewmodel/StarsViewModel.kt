package io.bloco.template.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import io.bloco.template.data.repository.ContactRepository
import io.bloco.template.utils.Resource
import kotlinx.coroutines.Dispatchers

class StarsViewModel (private val contactRepository: ContactRepository): ViewModel() {

    fun startsAdd(pageNumber:Int) = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = contactRepository.getStarsAdd(pageNumber)))
        } catch (exception: Exception) {
            emit(Resource.error(data = null, message = exception.message ?: "Error Occurred!"))
        }
    }
}