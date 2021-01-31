package com.dionep.kotiksmultiplatform

import com.dionep.kotiksmultiplatform.CatsView.Event
import com.dionep.kotiksmultiplatform.CatsView.Model
import com.dionep.kotiksmultiplatform.datasource.CatsDataSource
import com.dionep.kotiksmultiplatform.datasource.CatsDataSourceFactory
import com.dionep.kotiksmultiplatform.integration.CatsStoreParser
import com.dionep.kotiksmultiplatform.integration.toIntent
import com.dionep.kotiksmultiplatform.integration.toModel
import com.dionep.kotiksmultiplatform.shared.mvi.Component
import com.dionep.kotiksmultiplatform.shared.mvi.MviView
import com.dionep.kotiksmultiplatform.store.CatsFeatureImpl
import com.dionep.kotiksmultiplatform.store.CatsFeatureImpl.Intent
import com.dionep.kotiksmultiplatform.store.CatsFeatureImpl.State

class CatsComponent internal constructor(dataSource: CatsDataSource) : Component<Model, Event, State, Intent>(
    stateMapper = { it.toModel() },
    eventMapper = { it.toIntent() }
) {

    constructor() : this(CatsDataSourceFactory())

    override var view: MviView<Model, Event>? = null

    override val feature: CatsFeatureImpl = CatsFeatureImpl(
        dataSource = dataSource,
        parser = CatsStoreParser
    )


}