package com.example.picobotella7.webservice

class ApiUtils {
    companion object{
        fun getApiService(): ApiService{
            return RetrofitClient.getRetrofit().create(ApiService::class.java)
        }
    }
}