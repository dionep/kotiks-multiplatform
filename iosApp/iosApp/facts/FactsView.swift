//
//  FactsSwiftView.swift
//  iosApp
//
//  Created by Дамир on 05.02.2021.
//  Copyright © 2021 orgName. All rights reserved.
//

import Foundation
import SwiftUI
import shared

struct FactsView: View {
    
    var componentHolder: FactsComponentHolder
    @ObservedObject var stateProxy: FactsStateProxy
    @EnvironmentObject var viewRouter: ViewRouter
    
    var body: some View {
        NavigationView {
            content
                .navigationBarItems(
                    leading: Button("Refresh") {
                        self.componentHolder.component.accept(msg: FactsFeatureComponent.MsgLoad())
                    },
                    trailing: Button("Add") {
                        viewRouter.navigateTo(screen: .addFact)
                    }
                )
        }
    }

    
    private var content: some View {
        let state = self.stateProxy.state
        return Group {
            if (state == nil) {
                EmptyView()
            }
            else if (state!.isLoading) {
                Text("Loading.....")
            }
            else {
                List {
                    ForEach(state?.facts ?? [], id: \.self) { item in
                        Text(item)
                    }
                }.listStyle(GroupedListStyle())
            }
        }
    }
    
}

struct FactsSwiftView_Previews: PreviewProvider {
    static var previews: some View {
        FactsView(
            componentHolder: FactsComponentHolder(),
            stateProxy: FactsStateProxy()
        ).environmentObject(ViewRouter())
    }
}
