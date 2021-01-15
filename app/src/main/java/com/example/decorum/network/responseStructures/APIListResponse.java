package com.example.decorum.network.responseStructures;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class APIListResponse<T> extends BaseModel {
    @SerializedName("data")
    public List<T> data = new ArrayList<T>();

    @SerializedName("message")
    public Object strMessage = "";

}