//
//  Facts.swift
//  iosApp
//
//  Created by Дамир on 05.02.2021.
//  Copyright © 2021 orgName. All rights reserved.
//

import SwiftUI
import shared

struct FactsController: View {
    
    @State private var componentHolder: FactsComponentHolder = FactsComponentHolder()
    @State private var stateProxy: FactsStateProxy = FactsStateProxy()
    @State var showError: Bool = false
    @EnvironmentObject var viewRouter: ViewRouter
    
    var body: some View {
        
        FactsView(
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
        componentHolder.component.bindListeners { (featureState: FactsFeatureComponent.State?) in
            if (featureState != nil) {
                self.stateProxy.updateState(newState: featureState!)
            }
        } newsListener: { (news: FactsFeatureComponent.News?) in
            if (news is FactsFeatureComponent.NewsFailure) {
                showError.toggle()
            }
        }

    }
    
    private func onDisappear() {
        self.componentHolder.component.disposeListeners()
    }

}

class FactsComponentHolder {
    let component = FactsFeatureComponent()
    
    deinit {
        component.disposeFeature()
    }
}

struct ContentView_Previews: PreviewProvider {
    static var previews: some View {
        FactsController().environmentObject(ViewRouter())
    }
}

