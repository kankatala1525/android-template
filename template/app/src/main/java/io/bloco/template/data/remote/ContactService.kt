package io.bloco.template.data.remote

import io.bloco.template.data.remote.model.ContactList
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ContactService {
    @GET("v1/contacts")
    suspend fun getAllContacts(@Query("pageNumber") pageNumber: Int) : Response<ContactList>

}