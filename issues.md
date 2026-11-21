# ISSUES with App


- Thumbnail files that are too big will cause the app to crash (add filter/verification)

```
2026-11-21 17:49:44.611 10860-10860 AndroidRuntime          org.setu.baristafinder               E  FATAL EXCEPTION: main
                                                                                                    Process: org.setu.baristafinder, PID: 10860
                                                                                                    java.lang.RuntimeException: Canvas: trying to draw too large(199756800bytes) bitmap.
                                                                                                    	at android.graphics.RecordingCanvas.throwIfCannotDraw(RecordingCanvas.java:267)
                                                                                                    	at android.graphics.BaseRecordingCanvas.drawBitmap(BaseRecordingCanvas.java:98)
                                                                                                    	at android.graphics.drawable.BitmapDrawable.draw(BitmapDrawable.java:561)
                                                                                                    	at android.widget.ImageView.onDraw(ImageView.java:1446)
                                                                                                    	at android.view.View.draw(View.java:25177)
                                                                                                    	at android.view.View.updateDisplayListIfDirty(View.java:24036)
                                                                                                    	at android.view.View.draw(View.java:24907)
                                                                                                    	at android.view.ViewGroup.drawChild(ViewGroup.java:4780)
                                                                                                    	at android.view.ViewGroup.dispatchDraw(ViewGroup.java:4538)
                                                                                                    	at android.view.View.updateDisplayListIfDirty(View.java:24022)
                                                                                                    	at android.view.View.draw(View.java:24907)
                                                                                                    	at android.view.ViewGroup.drawChild(ViewGroup.java:4780)
                                                                                                    	at android.view.ViewGroup.dispatchDraw(ViewGroup.java:4538)
                                                                                                    	at android.view.View.updateDisplayListIfDirty(View.java:24022)
                                                                                                    	at android.view.View.draw(View.java:24907)
                                                                                                    	at android.view.ViewGroup.drawChild(ViewGroup.java:4780)
                                                                                                    	at android.view.ViewGroup.dispatchDraw(ViewGroup.java:4538)
                                                                                                    	at android.view.View.updateDisplayListIfDirty(View.java:24022)
                                                                                                    	at android.view.View.draw(View.java:24907)
                                                                                                    	at android.view.ViewGroup.drawChild(ViewGroup.java:4780)
                                                                                                    	at android.view.ViewGroup.dispatchDraw(ViewGroup.java:4538)
                                                                                                    	at android.view.View.updateDisplayListIfDirty(View.java:24022)
                                                                                                    	at android.view.View.draw(View.java:24907)
                                                                                                    	at android.view.ViewGroup.drawChild(ViewGroup.java:4780)
                                                                                                    	at android.view.ViewGroup.dispatchDraw(ViewGroup.java:4538)
                                                                                                    	at android.view.View.updateDisplayListIfDirty(View.java:24022)
                                                                                                    	at android.view.View.draw(View.java:24907)
                                                                                                    	at android.view.ViewGroup.drawChild(ViewGroup.java:4780)
                                                                                                    	at android.view.ViewGroup.dispatchDraw(ViewGroup.java:4538)
                                                                                                    	at android.view.View.updateDisplayListIfDirty(View.java:24022)
                                                                                                    	at android.view.View.draw(View.java:24907)
                                                                                                    	at android.view.ViewGroup.drawChild(ViewGroup.java:4780)
                                                                                                    	at android.view.ViewGroup.dispatchDraw(ViewGroup.java:4538)
                                                                                                    	at com.android.internal.policy.DecorView.dispatchDraw(DecorView.java:1179)
                                                                                                    	at android.view.View.draw(View.java:25180)
                                                                                                    	at com.android.internal.policy.DecorView.draw(DecorView.java:1161)
                                                                                                    	at android.view.View.updateDisplayListIfDirty(View.java:24036)
                                                                                                    	at android.view.ThreadedRenderer.updateViewTreeDisplayList(ThreadedRenderer.java:768)
                                                                                                    	at android.view.ThreadedRenderer.updateRootDisplayList(ThreadedRenderer.java:774)
                                                                                                    	at android.view.ThreadedRenderer.draw(ThreadedRenderer.java:872)
                                                                                                    	at android.view.ViewRootImpl.draw(ViewRootImpl.java:6051)
                                                                                                    	at android.view.ViewRootImpl.performDraw(ViewRootImpl.java:5706)
                                                                                                    	at android.view.ViewRootImpl.performTraversals(ViewRootImpl.java:4795)
                                                                                                    	at android.view.ViewRootImpl.doTraversal(ViewRootImpl.java:3288)
                                                                                                    	at android.view.ViewRootImpl$TraversalRunnable.run(ViewRootImpl.java:11344)
                                                                                                    	at android.view.Choreographer$CallbackRecord.run(Choreographer.java:1689)
                                                                                                    	at android.view.Choreographer$CallbackRecord.run(Choreographer.java:1698)
                                                                                                    	at android.view.Choreographer.doCallbacks(Choreographer.java:1153)
                                                                                                    	at android.view.Choreographer.doFrame(Choreographer.java:1079)
                                                                                                    	at android.view.Choreographer$FrameDisplayEventReceiver.run(Choreographer.java:1646)
                                                                                                    	at android.os.Handler.handleCallback(Handler.java:958)
                                                                                                    	at android.os.Handler.dispatchMessage(Handler.java:99)
                                                                                                    	at android.os.Looper.loopOnce(Looper.java:230)
                                                                                                    	at android.os.Looper.loop(Looper.java:319)
                                                                                                    	at android.app.ActivityThread.main(ActivityThread.java:8919)
                                                                                                    	at java.lang.reflect.Method.invoke(Native Method)
                                                                                                    	at com.android.internal.os.RuntimeInit$MethodAndArgsCaller.run(RuntimeInit.java:578)
                                                                                                    	at com.android.internal.os.ZygoteInit.main(ZygoteInit.java:1103)
2026-11-21 17:49:44.636 10860-10860 Process                 org.setu.baristafinder               I  Sending signal. PID: 10860 SIG: 9
---------------------------- PROCESS ENDED (10860) for package org.setu.baristafinder ----------------------------
```

- Adding bad coordinates will close the create menu before showing the message. It should stay open, or remember the edit fields' values.
- Map screen crashes app