//
//  AddFactController.swift
//  iosApp
//
//  Created by Дамир on 05.02.2021.
//  Copyright © 2021 orgName. All rights reserved.
//

import Foundation
import SwiftUI

struct AddFactController: View {
    
    @EnvironmentObject var viewRouter: ViewRouter
    
    var body: some View {
        AddFactView().environmentObject(viewRouter)
    }
    
}
