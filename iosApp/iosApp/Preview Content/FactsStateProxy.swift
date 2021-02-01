//
//  FactsStateProxy.swift
//  iosApp
//
//  Created by Дамир on 01.02.2021.
//  Copyright © 2021 orgName. All rights reserved.
//

import Foundation
import shared

class FactsStateProxy: ObservableObject {
    
    @Published var state: FactsFeatureComponent.State?
    
    public func updateState(newState: FactsFeatureComponent.State) {
        self.state = newState
    }
    
}
