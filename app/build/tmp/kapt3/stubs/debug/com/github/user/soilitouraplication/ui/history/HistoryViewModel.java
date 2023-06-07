package com.github.user.soilitouraplication.ui.history;

import java.lang.System;

@kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u0006\u0010\u000b\u001a\u00020\fR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001d\u0010\u0005\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u00070\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n\u00a8\u0006\r"}, d2 = {"Lcom/github/user/soilitouraplication/ui/history/HistoryViewModel;", "Landroidx/lifecycle/ViewModel;", "historyApi", "Lcom/github/user/soilitouraplication/api/HistoryApi;", "(Lcom/github/user/soilitouraplication/api/HistoryApi;)V", "historyList", "Landroidx/lifecycle/MutableLiveData;", "", "Lcom/github/user/soilitouraplication/api/History;", "getHistoryList", "()Landroidx/lifecycle/MutableLiveData;", "fetchHistory", "", "app_debug"})
public final class HistoryViewModel extends androidx.lifecycle.ViewModel {
    private final com.github.user.soilitouraplication.api.HistoryApi historyApi = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.MutableLiveData<java.util.List<com.github.user.soilitouraplication.api.History>> historyList = null;
    
    public HistoryViewModel(@org.jetbrains.annotations.NotNull()
    com.github.user.soilitouraplication.api.HistoryApi historyApi) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.MutableLiveData<java.util.List<com.github.user.soilitouraplication.api.History>> getHistoryList() {
        return null;
    }
    
    public final void fetchHistory() {
    }
}