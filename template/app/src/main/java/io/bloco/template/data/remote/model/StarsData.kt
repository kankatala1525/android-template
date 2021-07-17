package io.bloco.template.data.remote.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class StarsData(
    @Expose
    @SerializedName("meta")
    val meta: Meta,
    @Expose
    @SerializedName("content")
    val content: Contacts
)
