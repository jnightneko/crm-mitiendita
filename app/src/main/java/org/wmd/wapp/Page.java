package org.wmd.wapp;

import android.webkit.WebResourceRequest;

import org.wmd.sys.Configuration;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

public class Page {

    private static final List<String> URLS;
    public static final String ROOT_URL;
    public static String ROOT_HOST;

    static {
        URLS = new ArrayList<>();
        ROOT_URL = Configuration.WEB_APP.get();

        try {
            URI uri = new URI(ROOT_URL);
            ROOT_HOST = uri.getHost();
        } catch (Exception ignored) {}
    }

    public static boolean wappCheck(WebResourceRequest request) {
        if (ROOT_HOST.equals(request.getUrl().getHost())) {
            return true;
        }
        for (final String url : URLS) {
            if (url.equals(request.getUrl().getHost())) {
                return true;
            }
        }
        return false;
    }
}
