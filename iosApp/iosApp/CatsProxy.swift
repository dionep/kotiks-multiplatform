//
//  CatsProxy.swift
//  iosApp
//
//  Created by Дамир on 27.01.2021.
//  Copyright © 2021 orgName. All rights reserved.
//

import Foundation
import shared

class CatsProxy: AbstractMviView<CatsViewModel, CatsViewEvent>, CatsView, ObservableObject {

    @Published var model: CatsViewModel?
    
    override func render(model: Any) {
        self.model = model as! CatsViewModel
    }
    
}
