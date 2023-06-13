package com.example.beechang.foodyfoodyfoody.network

import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class Dispatcher(val foodyDispatchers : FoodyDispatchers)

enum class FoodyDispatchers {
    IO
}