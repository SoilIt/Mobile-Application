package com.github.user.soilitouraplication.ui.home;

import java.lang.System;

@kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\b\u0002\u0018\u00002\f\u0012\b\u0012\u00060\u0002R\u00020\u00000\u0001:\u0001\u0014B\u0005\u00a2\u0006\u0002\u0010\u0003J\b\u0010\u0007\u001a\u00020\bH\u0016J\u001c\u0010\t\u001a\u00020\n2\n\u0010\u000b\u001a\u00060\u0002R\u00020\u00002\u0006\u0010\f\u001a\u00020\bH\u0016J\u001c\u0010\r\u001a\u00060\u0002R\u00020\u00002\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\bH\u0016J\u0016\u0010\u0011\u001a\u00020\n2\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00060\u0013H\u0007R\u0014\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0015"}, d2 = {"Lcom/github/user/soilitouraplication/ui/home/CampaignAdapter;", "Landroidx/recyclerview/widget/RecyclerView$Adapter;", "Lcom/github/user/soilitouraplication/ui/home/CampaignAdapter$ViewHolder;", "()V", "list", "Ljava/util/ArrayList;", "Lcom/github/user/soilitouraplication/api/Campaign;", "getItemCount", "", "onBindViewHolder", "", "holder", "position", "onCreateViewHolder", "parent", "Landroid/view/ViewGroup;", "viewType", "setList", "campaigns", "", "ViewHolder", "app_debug"})
public final class CampaignAdapter extends androidx.recyclerview.widget.RecyclerView.Adapter<com.github.user.soilitouraplication.ui.home.CampaignAdapter.ViewHolder> {
    private final java.util.ArrayList<com.github.user.soilitouraplication.api.Campaign> list = null;
    
    public CampaignAdapter() {
        super();
    }
    
    @android.annotation.SuppressLint(value = {"NotifyDataSetChanged"})
    public final void setList(@org.jetbrains.annotations.NotNull()
    java.util.List<com.github.user.soilitouraplication.api.Campaign> campaigns) {
    }
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public com.github.user.soilitouraplication.ui.home.CampaignAdapter.ViewHolder onCreateViewHolder(@org.jetbrains.annotations.NotNull()
    android.view.ViewGroup parent, int viewType) {
        return null;
    }
    
    @java.lang.Override()
    public void onBindViewHolder(@org.jetbrains.annotations.NotNull()
    com.github.user.soilitouraplication.ui.home.CampaignAdapter.ViewHolder holder, int position) {
    }
    
    @java.lang.Override()
    public int getItemCount() {
        return 0;
    }
    
    @kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0086\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u000e\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bJ\u0018\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\nH\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u000e"}, d2 = {"Lcom/github/user/soilitouraplication/ui/home/CampaignAdapter$ViewHolder;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "binding", "Lcom/github/user/soilitouraplication/databinding/CampaignItemBinding;", "(Lcom/github/user/soilitouraplication/ui/home/CampaignAdapter;Lcom/github/user/soilitouraplication/databinding/CampaignItemBinding;)V", "bind", "", "campaign", "Lcom/github/user/soilitouraplication/api/Campaign;", "dpToPx", "", "context", "Landroid/content/Context;", "dp", "app_debug"})
    public final class ViewHolder extends androidx.recyclerview.widget.RecyclerView.ViewHolder {
        private final com.github.user.soilitouraplication.databinding.CampaignItemBinding binding = null;
        
        public ViewHolder(@org.jetbrains.annotations.NotNull()
        com.github.user.soilitouraplication.databinding.CampaignItemBinding binding) {
            super(null);
        }
        
        public final void bind(@org.jetbrains.annotations.NotNull()
        com.github.user.soilitouraplication.api.Campaign campaign) {
        }
        
        private final int dpToPx(android.content.Context context, int dp) {
            return 0;
        }
    }
}