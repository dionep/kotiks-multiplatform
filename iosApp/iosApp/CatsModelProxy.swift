//
//  CatsProxy.swift
//  iosApp
//
//  Created by Дамир on 27.01.2021.
//  Copyright © 2021 orgName. All rights reserved.
//

import Foundation
import shared

class CatsModelProxy: MviView, ObservableObject {
    
    func render(model: Any) {
        self.model = model as? CatsViewModel
    }
    
    @Published var model: CatsViewModel?
    
    
}
