package com.dionep.kotiksmultiplatform

import com.dionep.kotiksmultiplatform.CatsView.*
import com.dionep.kotiksmultiplatform.datasource.CatsDataSource
import com.dionep.kotiksmultiplatform.datasource.CatsDataSourceFactory
import com.dionep.kotiksmultiplatform.integration.CatsStoreParser
import com.dionep.kotiksmultiplatform.shared.mvi.Component
import com.dionep.kotiksmultiplatform.shared.mvi.MviView
import com.dionep.kotiksmultiplatform.feature.CatsFeature
import com.dionep.kotiksmultiplatform.feature.CatsFeature.*

class CatsComponent internal constructor(dataSource: CatsDataSource) :
    Component<State, News, Msg, UiState, UiNews, UiEvents>(
        stateMapper = { featureState ->
            UiState(
                isLoading = featureState.isLoading,
                facts = featureState.facts
            )
        },
        newsMapper = { featureNews ->
            when (featureNews) {
                is News.Failure -> UiNews.Error
            }
        },
        uiEventsMapper = { uiEvents ->
            when (uiEvents) {
                UiEvents.Load -> Msg.Load
            }
        }
    ) {

    constructor() : this(CatsDataSourceFactory())

    override var view: MviView<UiState, UiNews, UiEvents>? = null

    override val feature: CatsFeature = CatsFeature(
        dataSource = dataSource,
        parser = CatsStoreParser
    )

}