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
    
    @ObservedObject var proxy: CatsModelProxy
    var componentHolder: ComponentHolder
    
    var body: some View {
        NavigationView {
            content
            .navigationBarItems(
                leading: Button("Title") {},
                trailing: Button("Refresh") {
                    self.componentHolder.accept(event: CatsViewUiEvents.Load())
                }
            )
        }
    }

    
    private var content: some View {
        let model: CatsViewUiState! = self.proxy.model
        return Group {
            if (model == nil) {
                EmptyView()
            } else if (model.isLoading) {
                Text("Loading.....")
            }
            else {
                List {
                    ForEach(model.facts, id: \.self) { item in
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
        CatsSwiftView(proxy: CatsModelProxy(), componentHolder: ComponentHolder())
    }
}
