package com.dionep.kotiksmultiplatform.shared.mvi

data class SideEffect<out Msg, out News>(
    val msg: Msg? = null,
    val news: News? = null
) {

    companion object {
        fun <Msg, News> msg(msg: Msg) =
            SideEffect<Msg, News>(msg = msg, news = null)

        fun <Msg, News> news(news: News) =
            SideEffect<Msg, News>(msg = null, news = news)
    }
}