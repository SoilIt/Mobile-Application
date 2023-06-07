package com.github.user.soilitouraplication.ui.detection;

import java.lang.System;

@kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u0000f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0011\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0015\n\u0002\b\u0003\u0018\u0000 #2\u00020\u0001:\u0001#B\u0005\u00a2\u0006\u0002\u0010\u0002J\b\u0010\u000e\u001a\u00020\u000fH\u0002J\u0010\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0013H\u0002J\b\u0010\u0014\u001a\u00020\u0015H\u0002J\u0012\u0010\u0016\u001a\u00020\u00152\b\u0010\u0017\u001a\u0004\u0018\u00010\u0018H\u0014J\b\u0010\u0019\u001a\u00020\u0015H\u0014J+\u0010\u001a\u001a\u00020\u00152\u0006\u0010\u001b\u001a\u00020\u001c2\f\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\u001f0\u001e2\u0006\u0010 \u001a\u00020!H\u0016\u00a2\u0006\u0002\u0010\"R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0004X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082.\u00a2\u0006\u0002\n\u0000\u00a8\u0006$"}, d2 = {"Lcom/github/user/soilitouraplication/ui/detection/ClassifierActivity;", "Landroidx/appcompat/app/AppCompatActivity;", "()V", "btnDetectObject", "Landroid/widget/ImageView;", "cameraExecutor", "Ljava/util/concurrent/ExecutorService;", "classifier", "Lcom/github/user/soilitouraplication/ui/detection/Classifier;", "imageViewResult", "previewView", "Landroidx/camera/view/PreviewView;", "textViewResult", "Landroid/widget/TextView;", "allPermissionsGranted", "", "imageProxyToBitmap", "Landroid/graphics/Bitmap;", "image", "Landroidx/camera/core/ImageProxy;", "makeButtonVisible", "", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onDestroy", "onRequestPermissionsResult", "requestCode", "", "permissions", "", "", "grantResults", "", "(I[Ljava/lang/String;[I)V", "Companion", "app_debug"})
public final class ClassifierActivity extends androidx.appcompat.app.AppCompatActivity {
    private com.github.user.soilitouraplication.ui.detection.Classifier classifier;
    private java.util.concurrent.ExecutorService cameraExecutor;
    private android.widget.TextView textViewResult;
    private android.widget.ImageView btnDetectObject;
    private android.widget.ImageView imageViewResult;
    private androidx.camera.view.PreviewView previewView;
    @org.jetbrains.annotations.NotNull()
    public static final com.github.user.soilitouraplication.ui.detection.ClassifierActivity.Companion Companion = null;
    private static final java.lang.String MODEL_PATH = "mobilenet_quant_v1_224.tflite";
    private static final java.lang.String LABEL_PATH = "labels.txt";
    private static final int INPUT_SIZE = 224;
    public static final int CAMERA_X_RESULT = 200;
    private static final java.lang.String[] REQUIRED_PERMISSIONS = {"android.permission.CAMERA"};
    private static final int REQUEST_CODE_PERMISSIONS = 10;
    
    public ClassifierActivity() {
        super();
    }
    
    @java.lang.Override()
    protected void onCreate(@org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    @java.lang.Override()
    protected void onDestroy() {
    }
    
    private final void makeButtonVisible() {
    }
    
    private final android.graphics.Bitmap imageProxyToBitmap(androidx.camera.core.ImageProxy image) {
        return null;
    }
    
    private final boolean allPermissionsGranted() {
        return false;
    }
    
    @java.lang.Override()
    public void onRequestPermissionsResult(int requestCode, @org.jetbrains.annotations.NotNull()
    java.lang.String[] permissions, @org.jetbrains.annotations.NotNull()
    int[] grantResults) {
    }
    
    @kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0007X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u0016\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00070\u000bX\u0082\u0004\u00a2\u0006\u0004\n\u0002\u0010\f\u00a8\u0006\r"}, d2 = {"Lcom/github/user/soilitouraplication/ui/detection/ClassifierActivity$Companion;", "", "()V", "CAMERA_X_RESULT", "", "INPUT_SIZE", "LABEL_PATH", "", "MODEL_PATH", "REQUEST_CODE_PERMISSIONS", "REQUIRED_PERMISSIONS", "", "[Ljava/lang/String;", "app_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
}