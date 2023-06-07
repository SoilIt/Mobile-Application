package com.github.user.soilitouraplication.ui.home;

import java.lang.System;

@kotlin.Suppress(names = {"DEPRECATION"})
@kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u0000 \u00182\u00020\u0001:\u0001\u0018B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\r\u0010\r\u001a\u00020\u000eH\u0000\u00a2\u0006\u0002\b\u000fJ\u0012\u0010\u0010\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u00070\u0011J\b\u0010\u0012\u001a\u00020\nH\u0002J\u0010\u0010\u0013\u001a\u00020\u000e2\b\u0010\u0014\u001a\u0004\u0018\u00010\u0015J\u000e\u0010\u0016\u001a\u00020\u000e2\u0006\u0010\u0017\u001a\u00020\u0015R\u001a\u0010\u0005\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u00070\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010\t\u001a\b\u0012\u0004\u0012\u00020\n0\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\t\u0010\u000bR\u0016\u0010\f\u001a\n\u0012\u0004\u0012\u00020\b\u0018\u00010\u0007X\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0019"}, d2 = {"Lcom/github/user/soilitouraplication/ui/home/HomeViewModel;", "Landroidx/lifecycle/AndroidViewModel;", "application", "Landroid/app/Application;", "(Landroid/app/Application;)V", "campaigns", "Landroidx/lifecycle/MutableLiveData;", "", "Lcom/github/user/soilitouraplication/api/Campaign;", "isLoading", "", "()Landroidx/lifecycle/MutableLiveData;", "savedState", "fetchCampaigns", "", "fetchCampaigns$app_debug", "getCampaigns", "Landroidx/lifecycle/LiveData;", "isNetworkAvailable", "restoreState", "state", "Landroid/os/Bundle;", "saveState", "outState", "Companion", "app_debug"})
public final class HomeViewModel extends androidx.lifecycle.AndroidViewModel {
    private final androidx.lifecycle.MutableLiveData<java.util.List<com.github.user.soilitouraplication.api.Campaign>> campaigns = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.MutableLiveData<java.lang.Boolean> isLoading = null;
    private java.util.List<com.github.user.soilitouraplication.api.Campaign> savedState;
    @org.jetbrains.annotations.NotNull()
    public static final com.github.user.soilitouraplication.ui.home.HomeViewModel.Companion Companion = null;
    private static final java.lang.String KEY_SAVED_STATE = "saved_state";
    
    public HomeViewModel(@org.jetbrains.annotations.NotNull()
    android.app.Application application) {
        super(null);
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.MutableLiveData<java.lang.Boolean> isLoading() {
        return null;
    }
    
    public final void fetchCampaigns$app_debug() {
    }
    
    private final boolean isNetworkAvailable() {
        return false;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<java.util.List<com.github.user.soilitouraplication.api.Campaign>> getCampaigns() {
        return null;
    }
    
    public final void saveState(@org.jetbrains.annotations.NotNull()
    android.os.Bundle outState) {
    }
    
    public final void restoreState(@org.jetbrains.annotations.Nullable()
    android.os.Bundle state) {
    }
    
    @kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0005"}, d2 = {"Lcom/github/user/soilitouraplication/ui/home/HomeViewModel$Companion;", "", "()V", "KEY_SAVED_STATE", "", "app_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
}