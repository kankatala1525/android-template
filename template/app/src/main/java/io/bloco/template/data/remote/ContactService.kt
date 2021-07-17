package io.bloco.template.data.remote

import io.bloco.template.data.remote.model.ContactList
import io.bloco.template.data.remote.model.StarsData
import retrofit2.Response
import retrofit2.http.*

interface ContactService {
    @GET("v1/contacts")
    suspend fun getAllContacts(@Query("pageNumber") pageNumber: Int) : Response<ContactList>

    @POST("v1/stars/")
    @FormUrlEncoded
    suspend fun starAdd(@Field("id") id: Int) : Response<StarsData>
    

}