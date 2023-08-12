package com.example.beechang.foodyfoodyfoody.base

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.beechang.foodyfoodyfoody.model.ApiException
 import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch

open class BaseViewModel : ViewModel() {

    protected fun <T> Flow<T>.handleErrors(action: suspend (errorMassage: String) -> Unit): Flow<T> =
        catch { e ->
            Log.e("e" , e.message.toString())
            var errorMassage = "기타 오류 발생 " + e.message
            when (e) {
                is ApiException.TokenExpired -> errorMassage = "인증문제 발생 "
                is ApiException.NotFound -> errorMassage = "잘못된 경로 요청 "
                is ApiException.ServerError -> errorMassage = "연결 서버의 문제발생"
                is ApiException.OthersError -> errorMassage = "네트워크 오류발생"
            }
            action(errorMassage)
        }

}