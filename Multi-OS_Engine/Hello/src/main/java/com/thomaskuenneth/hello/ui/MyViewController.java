package com.thomaskuenneth.hello.ui;

import apple.uikit.UIButton;
import apple.uikit.UILabel;
import apple.uikit.UIViewController;
import org.moe.natj.general.Pointer;
import org.moe.natj.general.ann.Owned;
import org.moe.natj.general.ann.RegisterOnStartup;
import org.moe.natj.objc.ObjCRuntime;
import org.moe.natj.objc.ann.IBOutlet;
import org.moe.natj.objc.ann.ObjCClassName;
import org.moe.natj.objc.ann.Property;
import org.moe.natj.objc.ann.Selector;
import java.util.Locale;

@org.moe.natj.general.ann.Runtime(ObjCRuntime.class)
@ObjCClassName("MyViewController")
@RegisterOnStartup
public class MyViewController extends UIViewController {
    public UILabel label = null;
    private int count;

    @Override
    public void viewDidLoad() {
        label = getLabel();
        label.setText("Noch nicht geklickt");
        count = 0;
    }

    @Selector("label")
    @Property
    @IBOutlet
    public native UILabel getLabel();

    @Owned
    @Selector("alloc")
    public static native MyViewController alloc();

    @Selector("init")
    public native MyViewController init();

    protected MyViewController(Pointer peer) {
        super(peer);
    }

    @Selector("button:")
    public void buttonClicked(UIButton sender) {
        label.setText(String.format(Locale.US,
                "%d mal geklickt",
                ++count));
    }
}
