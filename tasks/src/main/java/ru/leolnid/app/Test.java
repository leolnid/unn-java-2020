package ru.leolnid.app;
/*
 * Copyright (c) 2000-2020 TeamDev Ltd. All rights reserved.
 * TeamDev PROPRIETARY and CONFIDENTIAL.
 * Use is subject to licence terms.
 */
import static com.teamdev.jxbrowser.engine.RenderingMode.*;

import com.teamdev.jxbrowser.browser.Browser;
import com.teamdev.jxbrowser.engine.Engine;
import com.teamdev.jxbrowser.engine.EngineOptions;
import com.teamdev.jxbrowser.view.swing.BrowserView;

import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
/**...*/
public final class Test {
    public static void main(String[] args) {
        System.setProperty("jxbrowser.license.key", "1BNDHFSC1FX9KAPRRNJRVFUWIHZQT8ISV75JWCDCLJZMX37C1FI8UK4M3L9G6Q5BVVUJVB");

        EngineOptions options = EngineOptions.newBuilder(HARDWARE_ACCELERATED).build();
        Engine engine = Engine.newInstance(options);

        Browser browser = engine.newBrowser();

        SwingUtilities.invokeLater(() -> {
            // Create the Swing BrowserView component
            BrowserView view = BrowserView.newInstance(browser);

            JFrame frame = new JFrame();
            frame.add(view, BorderLayout.CENTER);
            frame.setSize(350, 600);
            frame.setVisible(true);

            browser.navigation().loadUrl("D:\\Development\\unn-java-2020\\tasks\\src\\main\\java\\ru\\leolnid\\app\\gui\\index.html");
        });
    }
}