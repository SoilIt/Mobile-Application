package com.github.user.soilitouraplication.ui.detection;

import java.lang.System;

@kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u0000`\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0002\b\u000e\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\u0010\u0014\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u0000 /2\u00020\u00012\u00020\u0002:\u0001/B\u000f\b\u0016\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u00a2\u0006\u0002\u0010\u0005B+\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\b\b\u0002\u0010\b\u001a\u00020\t\u0012\u000e\b\u0002\u0010\n\u001a\b\u0012\u0004\u0012\u00020\f0\u000b\u00a2\u0006\u0002\u0010\rJ\b\u0010\u001a\u001a\u00020\u001bH\u0016J\b\u0010\u001c\u001a\u00020\tH\u0016J\u001b\u0010\u001d\u001a\u00020\u001e2\f\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020!0 H\u0002\u00a2\u0006\u0002\u0010\"J\u001e\u0010#\u001a\b\u0012\u0004\u0012\u00020\f0\u000b2\u0006\u0010$\u001a\u00020%2\u0006\u0010&\u001a\u00020\fH\u0002J\u0018\u0010\'\u001a\u00020(2\u0006\u0010$\u001a\u00020%2\u0006\u0010)\u001a\u00020\fH\u0002J\u0010\u0010*\u001a\u00020\u001e2\u0006\u0010+\u001a\u00020,H\u0016J\u0018\u0010-\u001a\u00020\u001b2\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010.\u001a\u00020\tH\u0016R\u001a\u0010\b\u001a\u00020\tX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011R\u001c\u0010\u0006\u001a\u0004\u0018\u00010\u0007X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\u0013\"\u0004\b\u0014\u0010\u0015R \u0010\n\u001a\b\u0012\u0004\u0012\u00020\f0\u000bX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0016\u0010\u0017\"\u0004\b\u0018\u0010\u0019\u00a8\u00060"}, d2 = {"Lcom/github/user/soilitouraplication/ui/detection/Classifier;", "Lcom/github/user/soilitouraplication/ui/detection/SoilClassifier;", "Landroid/os/Parcelable;", "parcel", "Landroid/os/Parcel;", "(Landroid/os/Parcel;)V", "interpreter", "Lorg/tensorflow/lite/Interpreter;", "inputSize", "", "labelList", "", "", "(Lorg/tensorflow/lite/Interpreter;ILjava/util/List;)V", "getInputSize", "()I", "setInputSize", "(I)V", "getInterpreter", "()Lorg/tensorflow/lite/Interpreter;", "setInterpreter", "(Lorg/tensorflow/lite/Interpreter;)V", "getLabelList", "()Ljava/util/List;", "setLabelList", "(Ljava/util/List;)V", "close", "", "describeContents", "getSortedResult", "Lcom/github/user/soilitouraplication/ui/detection/SoilClassifier$Recognition;", "labelProbArray", "", "", "([[F)Lcom/github/user/soilitouraplication/ui/detection/SoilClassifier$Recognition;", "loadLabelList", "assetManager", "Landroid/content/res/AssetManager;", "labelPath", "loadModelFile", "Ljava/nio/MappedByteBuffer;", "modelPath", "recognizeImage", "bitmap", "Landroid/graphics/Bitmap;", "writeToParcel", "flags", "Companion", "app_debug"})
public final class Classifier implements com.github.user.soilitouraplication.ui.detection.SoilClassifier, android.os.Parcelable {
    @org.jetbrains.annotations.Nullable()
    private org.tensorflow.lite.Interpreter interpreter;
    private int inputSize;
    @org.jetbrains.annotations.NotNull()
    private java.util.List<java.lang.String> labelList;
    @org.jetbrains.annotations.NotNull()
    public static final com.github.user.soilitouraplication.ui.detection.Classifier.Companion Companion = null;
    private static final float THRESHOLD = 0.1F;
    @org.jetbrains.annotations.NotNull()
    @kotlin.jvm.JvmField()
    public static final android.os.Parcelable.Creator<com.github.user.soilitouraplication.ui.detection.Classifier> CREATOR = null;
    
    public Classifier() {
        super();
    }
    
    public Classifier(@org.jetbrains.annotations.Nullable()
    org.tensorflow.lite.Interpreter interpreter, int inputSize, @org.jetbrains.annotations.NotNull()
    java.util.List<java.lang.String> labelList) {
        super();
    }
    
    @org.jetbrains.annotations.Nullable()
    public final org.tensorflow.lite.Interpreter getInterpreter() {
        return null;
    }
    
    public final void setInterpreter(@org.jetbrains.annotations.Nullable()
    org.tensorflow.lite.Interpreter p0) {
    }
    
    public final int getInputSize() {
        return 0;
    }
    
    public final void setInputSize(int p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<java.lang.String> getLabelList() {
        return null;
    }
    
    public final void setLabelList(@org.jetbrains.annotations.NotNull()
    java.util.List<java.lang.String> p0) {
    }
    
    public Classifier(@org.jetbrains.annotations.NotNull()
    android.os.Parcel parcel) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public com.github.user.soilitouraplication.ui.detection.SoilClassifier.Recognition recognizeImage(@org.jetbrains.annotations.NotNull()
    android.graphics.Bitmap bitmap) {
        return null;
    }
    
    @java.lang.Override()
    public void close() {
    }
    
    @kotlin.jvm.Throws(exceptionClasses = {java.io.IOException.class})
    private final java.nio.MappedByteBuffer loadModelFile(android.content.res.AssetManager assetManager, java.lang.String modelPath) throws java.io.IOException {
        return null;
    }
    
    @kotlin.jvm.Throws(exceptionClasses = {java.io.IOException.class})
    private final java.util.List<java.lang.String> loadLabelList(android.content.res.AssetManager assetManager, java.lang.String labelPath) {
        return null;
    }
    
    private final com.github.user.soilitouraplication.ui.detection.SoilClassifier.Recognition getSortedResult(float[][] labelProbArray) {
        return null;
    }
    
    @java.lang.Override()
    public void writeToParcel(@org.jetbrains.annotations.NotNull()
    android.os.Parcel parcel, int flags) {
    }
    
    @java.lang.Override()
    public int describeContents() {
        return 0;
    }
    
    @kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J&\u0010\b\u001a\u00020\u00052\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\f2\u0006\u0010\u000e\u001a\u00020\u000fR\u0016\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u00048\u0006X\u0087\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0010"}, d2 = {"Lcom/github/user/soilitouraplication/ui/detection/Classifier$Companion;", "", "()V", "CREATOR", "Landroid/os/Parcelable$Creator;", "Lcom/github/user/soilitouraplication/ui/detection/Classifier;", "THRESHOLD", "", "create", "assetManager", "Landroid/content/res/AssetManager;", "modelPath", "", "labelPath", "inputSize", "", "app_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        @kotlin.jvm.Throws(exceptionClasses = {java.io.IOException.class})
        public final com.github.user.soilitouraplication.ui.detection.Classifier create(@org.jetbrains.annotations.NotNull()
        android.content.res.AssetManager assetManager, @org.jetbrains.annotations.NotNull()
        java.lang.String modelPath, @org.jetbrains.annotations.NotNull()
        java.lang.String labelPath, int inputSize) throws java.io.IOException {
            return null;
        }
    }
}