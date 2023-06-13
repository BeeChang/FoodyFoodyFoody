package com.example.beechang.foodyfoodyfoody.base

import androidx.lifecycle.ViewModel
import com.example.beechang.foodyfoodyfoody.model.ResultError
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch

open class BaseViewModel : ViewModel() {

    protected fun <T> Flow<T>.handleErrors(action: suspend (errorMassage: String) -> Unit): Flow<T> =
        catch { e ->
            var errorMassage = "기타 오류 발생"
            when (e) {
                is ResultError.TokenExpired -> errorMassage = "인증문제 발생"
                is ResultError.NotFound -> errorMassage = "잘못된 경로 요청"
                is ResultError.ServerError -> errorMassage = "연결 서버의 문제발생"
                is ResultError.OthersError -> errorMassage = "네트워크 오류발생"
            }
            action(errorMassage)
        }

}