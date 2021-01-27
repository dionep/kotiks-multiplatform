package com.dionep.kotiksmultiplatform.datasource

import com.badoo.reaktive.disposable.Disposable
import com.badoo.reaktive.single.Single
import com.badoo.reaktive.single.single
import com.badoo.reaktive.utils.freeze
import platform.Foundation.*

internal class CatsDataSourceImpl : CatsDataSource {
    override fun load(): Single<String> =
        single { emitter ->
            val callback: (NSData?, NSURLResponse?, NSError?) -> Unit = {
                data: NSData?, _, error: NSError? ->
                if (data != null) {
                    emitter.onSuccess(NSString.create(data, NSUTF8StringEncoding).toString())
                } else emitter.onError(Throwable())
            }

            val task =
                NSURLSession.sharedSession.dataTaskWithURL(
                    NSURL(string = makeCatsFactsUrl()),
                    callback.freeze()
                )
            task.resume()
            emitter.setDisposable(Disposable(task::cancel))
        }

}