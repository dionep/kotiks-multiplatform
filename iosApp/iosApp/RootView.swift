//
//  RootView.swift
//  iosApp
//
//  Created by Дамир on 05.02.2021.
//  Copyright © 2021 orgName. All rights reserved.
//

import SwiftUI

@available(iOS 14.0, *)
struct RootView: View {
    
    @EnvironmentObject var viewRouter: ViewRouter
    
    var body: some View {
        switch viewRouter.currentPage {
            case .facts:
                FactsController().environmentObject(viewRouter)
                    .transition(.scale)
            case .addFact:
                CreateFactController().environmentObject(viewRouter)
                    .transition(.scale)
        }
    }
    
}

@available(iOS 14.0, *)
struct RootView_Previews: PreviewProvider {
    static var previews: some View {
        RootView().environmentObject(ViewRouter())
    }
}
