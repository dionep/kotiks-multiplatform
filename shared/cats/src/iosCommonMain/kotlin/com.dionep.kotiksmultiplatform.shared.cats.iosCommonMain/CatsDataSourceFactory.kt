package com.dionep.kotiksmultiplatform.shared.cats.iosCommonMain

import com.dionep.kotiksmultiplatform.shared.cats.commonMain.datasource.CatsDataSource

internal actual fun CatsDataSourceFactory(): CatsDataSource = CatsDataSourceImpl()