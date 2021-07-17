package io.bloco.template.data.remote.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Meta(
    @Expose
    @SerializedName("success")
    val success: Boolean,
    @Expose
    @SerializedName("message")
    val message: String,
    @Expose
    @SerializedName("pageNumber")
    val pageNumber: Int,
    @Expose
    @SerializedName("pageSize")
    val pageSize: Int
)
