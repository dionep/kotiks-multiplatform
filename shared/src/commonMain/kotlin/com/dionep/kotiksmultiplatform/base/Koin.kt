package com.dionep.kotiksmultiplatform.base

import com.dionep.kotiksmultiplatform.network.NetworkSource
import com.dionep.kotiksmultiplatform.network.NetworkSourceImpl
import com.dionep.kotiksmultiplatform.repository.FactsRepository
import org.koin.core.context.startKoin
import org.koin.dsl.module

fun initKoin() = startKoin {
    modules(
        multiplatformModule
    )
}

val multiplatformModule = module {
    single<NetworkSource> { NetworkSourceImpl() }
    single { FactsRepository(get()) }
}