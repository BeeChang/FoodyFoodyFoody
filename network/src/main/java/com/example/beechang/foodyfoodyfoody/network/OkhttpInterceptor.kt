package com.example.beechang.foodyfoodyfoody.network

import okhttp3.Interceptor
import okhttp3.Response

class OkhttpInterceptor : Interceptor{

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val response = chain.proceed(request)

//        if (response.code == 401) {
//            runBlocking {
//                val refreshToken = tokenRepository.getRefreshToken()
//                refreshToken?.let {
//                    val accessToken = tokenRepository.getToken()
//                    accessToken?.let {
//                        val authResponse = apiRepository.refreshToken(accessToken, refreshToken)
//                        tokenRepository.saveTokens(authResponse.accessToken, authResponse.refreshToken)
//
//                        val newRequest = request.newBuilder()
//                            .header("Authorization", "Bearer ${authResponse.accessToken}")
//                            .build()
//
//                        return@runBlocking chain.proceed(newRequest)
//                    }
//                }
//            }
//        }

        return response
    }

}