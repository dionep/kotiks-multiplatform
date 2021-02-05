//
//  AddFileView.swift
//  iosApp
//
//  Created by Дамир on 05.02.2021.
//  Copyright © 2021 orgName. All rights reserved.
//

import Foundation
import SwiftUI
import shared

struct AddFactView: View {
    
    @EnvironmentObject var viewRouter: ViewRouter
    
    var body: some View {
        NavigationView {
            content
                .navigationBarItems(
                    leading: Button("< Back") {
                        viewRouter.navigateTo(screen: .facts)
                    },
                    trailing: Button("Save") {
                        
                    }
                )
        }
        
    }
    
    private var content: some View {
        EmptyView()
    }
    
}
