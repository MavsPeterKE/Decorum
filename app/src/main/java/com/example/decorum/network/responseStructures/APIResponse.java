package com.example.decorum.network.responseStructures;

import com.google.gson.annotations.SerializedName;

public class APIResponse<T> extends BaseModel {
    @SerializedName("data")
    public T data = (T) new Object();

    @SerializedName("message")
    public Object strMessage = "";

}