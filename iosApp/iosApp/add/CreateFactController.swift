//
//  AddFactController.swift
//  iosApp
//
//  Created by Дамир on 05.02.2021.
//  Copyright © 2021 orgName. All rights reserved.
//

import Foundation
import SwiftUI
import shared

@available(iOS 14.0, *)
struct CreateFactController: View {
    
    @State private var componentHolder: AddFactsComponentHolder = AddFactsComponentHolder()
    @State private var stateProxy: CreateFactStateProxy = CreateFactStateProxy()
    @State private var showError: Bool = false
    @EnvironmentObject var viewRouter: ViewRouter
    
    var body: some View {
        CreateFactView(
            componentHolder: componentHolder,
            stateProxy: stateProxy
        )
            .environmentObject(viewRouter)
            .onAppear(perform: onAppear)
            .onDisappear(perform: onDisappear)
            .alert(isPresented: self.$showError, content: {
                Alert(title: Text("Error occured"))
            })
    }
    
    private func onAppear() {
        componentHolder.component.bindListeners { (featureState: CreateFactFeatureComponent.State?) in
            if (featureState != nil) {
                self.stateProxy.updateState(newState: featureState!)
            }
        } newsListener: { (news: CreateFactFeatureComponent.News?) in
            if (news is CreateFactFeatureComponent.NewsFailure) {
                showError.toggle()
            }
            switch news {
                case CreateFactFeatureComponent.NewsCreateSuccess(): viewRouter.navigateTo(screen: .facts)
                default: do {}
            }
        }

    }
    
    private func onDisappear() {
        self.componentHolder.component.disposeListeners()
    }
    
}

class AddFactsComponentHolder {
    let component = CreateFactFeatureComponent()
    
    deinit {
        component.disposeFeature()
    }
}
