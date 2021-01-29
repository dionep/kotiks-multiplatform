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
    @State private var holder: ComponentHolder?
    @State private var proxy = CatsProxy()
    
    var body: some View {
        CatsSwiftView(proxy: proxy)
            .onAppear(perform: onAppear)
            .onDisappear(perform: onDisappear)
    }

    private func onAppear() {
        if (self.holder == nil) {
            self.holder = ComponentHolder()
        }
        self.holder?.component.onViewCreated(view: self.proxy)
        self.holder?.component.onStart()
    }
    
    private func onDisappear() {
        self.holder?.component.onViewDestroyed()
        self.holder?.component.onStop()
    }
}

private class ComponentHolder {
    let component = CatsComponent()
    
    deinit {
        component.onDestroy()
    }
}

struct ContentView_Previews: PreviewProvider {
    static var previews: some View {
        Cats()
    }
}
