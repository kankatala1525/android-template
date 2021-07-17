package io.bloco.template.data.repository

import io.bloco.template.data.remote.ApiHelper

class ContactRepository (private val apiHelper: ApiHelper) {

    suspend fun getContacts(pageNumber:Int) = apiHelper.getContacts(pageNumber)
}