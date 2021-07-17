package io.bloco.template.data.remote


class ApiHelper(private val contactService: ContactService) {

    suspend fun getContacts(pageNumber: Int) = contactService.getAllContacts(pageNumber)
}