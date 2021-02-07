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

@available(iOS 14.0, *)
struct CreateFactView: View {
    
    var componentHolder: AddFactsComponentHolder
    @ObservedObject var stateProxy: CreateFactStateProxy
    @EnvironmentObject var viewRouter: ViewRouter
    
    @State private var factText: String = ""
    
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
            let state = stateProxy.state
            if (state == nil || state!.isLoading) {
                ProgressView()
            } else {
                Text("Creating your new fact")
                Divider()
                TextEditor(text: $factText)
                    .textFieldStyle(RoundedBorderTextFieldStyle())
                    .padding()
                    .overlay(RoundedRectangle(cornerRadius: 10).stroke(Color.blue, lineWidth: 1))
                    .padding()
            }
        }
    }
    
}
