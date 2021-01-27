package com.dionep.kotiksmultiplatform.datasource

import com.badoo.reaktive.scheduler.ioScheduler
import com.badoo.reaktive.single.Single
import com.badoo.reaktive.single.singleFromFunction
import com.badoo.reaktive.single.subscribeOn
import java.io.BufferedReader
import java.net.HttpURLConnection
import java.net.URL

internal class CatsDataSourceImpl : CatsDataSource {

    override fun load(): Single<String> =
        singleFromFunction {
            val url = URL(makeCatsFactsUrl())
            val connection = url.openConnection() as HttpURLConnection

            connection
                .inputStream
                .bufferedReader()
                .use(BufferedReader::readText)
        }
            .subscribeOn(ioScheduler)

}
