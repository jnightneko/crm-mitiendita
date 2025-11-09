package org.wmd.wapp;

import android.app.Activity;
import android.content.Intent;
import android.view.KeyEvent;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class WAppClient extends WebViewClient {

    private final Activity activity;
    public WAppClient(Activity activity) {
        this.activity = activity;
    }

    @Override
    public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
        if (Page.wappCheck(request)) {
            return false;
        }

        Intent intent = new Intent(Intent.ACTION_VIEW, request.getUrl());
        activity.startActivity(intent);
        return true;
    }
}
