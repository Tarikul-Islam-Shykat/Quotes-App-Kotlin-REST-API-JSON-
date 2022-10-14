package com.example.quotesapp

import android.content.Context
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

class RequestManager (mContext: Context){

    var retrofit = Retrofit.Builder()
        .baseUrl("https://type.fit/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun GetAllQuotes(listener: QuptesResponseListener){

        val call = retrofit.create(CallQuotes::class.java).callQuotes()

        call.enqueue(object : Callback<List<QuotesModel>>{

            override fun onResponse(call: Call<List<QuotesModel>>, response: Response<List<QuotesModel>>) {
                if(!response.isSuccessful){ // if the response we get from the api is not succesfful
                    listener.didError(response.message()) // response.message get the message code, if the data fetched successfully or not
                    return
                }
                response.body()?.let { listener.didFecth(it, response.message()) }
            }

            override fun onFailure(call: Call<List<QuotesModel>>, t: Throwable) {
                t.message?.let { listener.didError(it) }
            }
        })
    }

    private  interface  CallQuotes{ // api call and adding them into a  list.
        @GET(value = "api/quotes")
        fun callQuotes(): Call<List<QuotesModel>>
    }
}

/*
* As we want to show those item, we have to get the item in the list.
*
* */