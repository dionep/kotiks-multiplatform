//
//  CatsProxy.swift
//  iosApp
//
//  Created by Дамир on 27.01.2021.
//  Copyright © 2021 orgName. All rights reserved.
//

import Foundation
import shared

class CatsModelProxy: ObservableObject {
    
    func handleNews(news: Any) {}
    
    func render(state: Any) {
        self.model = state as? CatsViewUiState
    }
    
    @Published var model: CatsViewUiState?
    
    
}
