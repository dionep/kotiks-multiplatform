//
//  CatsSwiftVIew.swift
//  iosApp
//
//  Created by Дамир on 27.01.2021.
//  Copyright © 2021 orgName. All rights reserved.
//

import Foundation
import SwiftUI
import shared

struct CatsSwiftView: View {
    
    var componentHolder: ComponentHolder
    @ObservedObject var stateProxy: FactsStateProxy
    
    var body: some View {
        NavigationView {
            content
            .navigationBarItems(
                leading: Button("Title") {},
                trailing: Button("Refresh") {
                    self.componentHolder.component.accept(msg: FactsFeatureComponent.MsgLoad())
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
                            .listRowInsets(EdgeInsets())
                    }
                }
            }
        }
    }
    
}

struct CatsSwiftView_Previews: PreviewProvider {
    static var previews: some View {
        CatsSwiftView(
            componentHolder: ComponentHolder(),
            stateProxy: FactsStateProxy()
        )
    }
}
