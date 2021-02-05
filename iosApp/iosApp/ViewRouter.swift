//
//  ViewRouter.swift
//  iosApp
//
//  Created by Дамир on 05.02.2021.
//  Copyright © 2021 orgName. All rights reserved.
//

import SwiftUI

class ViewRouter: ObservableObject {
    
    @Published var currentPage: Screen = .facts
    
    func navigateTo(screen: Screen) {
        print(screen)
        currentPage = screen
    }
    
}
