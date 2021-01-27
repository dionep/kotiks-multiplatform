package com.badoo.kmpmvi.shared.kittens.datasource

internal actual fun CatsDataSourceFactory(): KittenDataSource = KittenDataSourceImpl()
