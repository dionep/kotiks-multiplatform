//
//  Cats.swift
//  iosApp
//
//  Created by Дамир on 29.01.2021.
//  Copyright © 2021 orgName. All rights reserved.
//

import SwiftUI
import shared

struct Cats: View {
    
    @State private var componentHolder: ComponentHolder = ComponentHolder()
    @State private var stateProxy: FactsStateProxy = FactsStateProxy()
    
    var body: some View {
        CatsSwiftView(
            componentHolder: componentHolder,
            stateProxy: stateProxy
        )
            .onAppear(perform: onAppear)
            .onDisappear(perform: onDisappear)
    }
    
    private func onAppear() {
        componentHolder.component.bindListeners { (featureState: FactsFeatureComponent.State?) in
            if (featureState != nil) {
                self.stateProxy.updateState(newState: featureState!)
            }
        } newsListener: { (news: FactsFeatureComponent.News?) in
            
        }

    }
    
    private func onDisappear() {
        self.componentHolder.component.disposeListeners()
    }

}

class ComponentHolder {
    let component = FactsFeatureComponent()
    
    deinit {
        component.disposeFeature()
    }
}

struct ContentView_Previews: PreviewProvider {
    static var previews: some View {
        Cats()
    }
}
