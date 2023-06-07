package com.github.user.soilitouraplication.ui.history;

import java.lang.System;

@kotlin.Suppress(names = {"DEPRECATION"})
@kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u0000n\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 +2\u00020\u0001:\u0001+B\u0005\u00a2\u0006\u0002\u0010\u0002J\b\u0010!\u001a\u00020\"H\u0002J$\u0010#\u001a\u00020$2\u0006\u0010%\u001a\u00020&2\b\u0010\'\u001a\u0004\u0018\u00010(2\b\u0010)\u001a\u0004\u0018\u00010*H\u0017R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.\u00a2\u0006\u0002\n\u0000R\u001a\u0010\u0005\u001a\u00020\u0006X\u0086.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\u000e\u0010\u000b\u001a\u00020\fX\u0082.\u00a2\u0006\u0002\n\u0000R\u001a\u0010\r\u001a\u00020\u000eX\u0080.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0012R\u0017\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00150\u0014\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017R\u000e\u0010\u0018\u001a\u00020\u0019X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u001a\u001a\u00020\u001bX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u001c\u001a\u00020\u001bX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u001d\u001a\u00020\u001eX\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u001f\u001a\u00020 X\u0082.\u00a2\u0006\u0002\n\u0000\u00a8\u0006,"}, d2 = {"Lcom/github/user/soilitouraplication/ui/history/HistoryFragment;", "Landroidx/fragment/app/Fragment;", "()V", "binding", "Lcom/github/user/soilitouraplication/databinding/FragmentHistoryBinding;", "historyAdapter", "Lcom/github/user/soilitouraplication/ui/history/HistoryAdapter;", "getHistoryAdapter", "()Lcom/github/user/soilitouraplication/ui/history/HistoryAdapter;", "setHistoryAdapter", "(Lcom/github/user/soilitouraplication/ui/history/HistoryAdapter;)V", "historyApi", "Lcom/github/user/soilitouraplication/api/HistoryApi;", "historyDao", "Lcom/github/user/soilitouraplication/database/HistoryDao;", "getHistoryDao$app_debug", "()Lcom/github/user/soilitouraplication/database/HistoryDao;", "setHistoryDao$app_debug", "(Lcom/github/user/soilitouraplication/database/HistoryDao;)V", "historyList", "", "Lcom/github/user/soilitouraplication/api/History;", "getHistoryList", "()Ljava/util/List;", "historyViewModel", "Lcom/github/user/soilitouraplication/ui/history/HistoryViewModel;", "isNullOrEmpty", "", "isRefreshing", "itemTouchHelper", "Landroidx/recyclerview/widget/ItemTouchHelper;", "swipeRefreshLayout", "Landroidx/swiperefreshlayout/widget/SwipeRefreshLayout;", "fetchHistory", "", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "savedInstanceState", "Landroid/os/Bundle;", "Companion", "app_debug"})
public final class HistoryFragment extends androidx.fragment.app.Fragment {
    private com.github.user.soilitouraplication.ui.history.HistoryViewModel historyViewModel;
    private com.github.user.soilitouraplication.api.HistoryApi historyApi;
    private com.github.user.soilitouraplication.databinding.FragmentHistoryBinding binding;
    public com.github.user.soilitouraplication.ui.history.HistoryAdapter historyAdapter;
    private androidx.recyclerview.widget.ItemTouchHelper itemTouchHelper;
    private boolean isNullOrEmpty = true;
    @org.jetbrains.annotations.NotNull()
    private final java.util.List<com.github.user.soilitouraplication.api.History> historyList = null;
    public com.github.user.soilitouraplication.database.HistoryDao historyDao;
    private androidx.swiperefreshlayout.widget.SwipeRefreshLayout swipeRefreshLayout;
    private boolean isRefreshing = false;
    @org.jetbrains.annotations.NotNull()
    public static final com.github.user.soilitouraplication.ui.history.HistoryFragment.Companion Companion = null;
    
    public HistoryFragment() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.github.user.soilitouraplication.ui.history.HistoryAdapter getHistoryAdapter() {
        return null;
    }
    
    public final void setHistoryAdapter(@org.jetbrains.annotations.NotNull()
    com.github.user.soilitouraplication.ui.history.HistoryAdapter p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<com.github.user.soilitouraplication.api.History> getHistoryList() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.github.user.soilitouraplication.database.HistoryDao getHistoryDao$app_debug() {
        return null;
    }
    
    public final void setHistoryDao$app_debug(@org.jetbrains.annotations.NotNull()
    com.github.user.soilitouraplication.database.HistoryDao p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    @android.annotation.SuppressLint(value = {"NotifyDataSetChanged"})
    @java.lang.Override()
    public android.view.View onCreateView(@org.jetbrains.annotations.NotNull()
    android.view.LayoutInflater inflater, @org.jetbrains.annotations.Nullable()
    android.view.ViewGroup container, @org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
        return null;
    }
    
    private final void fetchHistory() {
    }
    
    @kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002\u00a8\u0006\u0003"}, d2 = {"Lcom/github/user/soilitouraplication/ui/history/HistoryFragment$Companion;", "", "()V", "app_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
}