package com.example.quotesapp

interface QuptesResponseListener {
    fun didFecth(response: List<QuotesModel>, message: String)
    fun didError(message: String)
}
