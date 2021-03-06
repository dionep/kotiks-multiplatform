import UIKit
import SwiftUI
import shared

@available(iOS 14.0, *)
class SceneDelegate: UIResponder, UIWindowSceneDelegate {

    var window: UIWindow?

    func scene(_ scene: UIScene, willConnectTo session: UISceneSession, options connectionOptions: UIScene.ConnectionOptions) {
        KoinKt.doInitKoin()
        if let windowScene = scene as? UIWindowScene {
            let window = UIWindow(windowScene: windowScene)
            window.rootViewController = UIHostingController(rootView: RootView().environmentObject(ViewRouter()))
            self.window = window
            window.makeKeyAndVisible()
        }
    }


}

