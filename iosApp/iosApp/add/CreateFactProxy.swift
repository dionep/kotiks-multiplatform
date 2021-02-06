//
//  AddFactProxy.swift
//  iosApp
//
//  Created by Дамир on 06.02.2021.
//  Copyright © 2021 orgName. All rights reserved.
//

import Foundation
import shared

class CreateFactProxy: ObservableObject {
    
    @Published var state: CreateFactFeatureComponent.State?
    
    public func updateState(newState: CreateFactFeatureComponent.State) {
        self.state = newState
    }
    
}
