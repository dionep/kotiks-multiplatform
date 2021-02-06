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

struct CreateFactView: View {
    
    var componentHolder: AddFactsComponentHolder
    @ObservedObject var stateProxy: CreateFactProxy
    @EnvironmentObject var viewRouter: ViewRouter
    
    @State private var factText: String = "Fact"
    
    var body: some View {
        NavigationView {
            content
                .navigationBarItems(
                    leading: Button("< Back") {
                        viewRouter.navigateTo(screen: .facts)
                    },
                    trailing: Button("Save") {
                        componentHolder.component.accept(msg: CreateFactFeatureComponent.MsgCreate(text: factText))
                    }
                )
        }
        
    }
    
    private var content: some View {
        VStack {
            TextField("Enter your fact", text: $factText)
        }
    }
    
}
