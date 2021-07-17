package io.bloco.template.data.remote.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Contacts(
    @Expose
    @SerializedName("id")
    val id: Int,
    @Expose
    @SerializedName("name")
    val name: String,
    @Expose
    @SerializedName("thumbnail")
    val thumbnail: String,
    @Expose
    @SerializedName("email")
    val email: String,
    @Expose
    @SerializedName("isStarred")
    val isStarred: Int
)
