package org.wmd.wapp;

import android.webkit.WebResourceRequest;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

public class Page {

    private static final List<String> URLS;
    public static String ROOT_URL;
    public static String ROOT_HOST;

    static {
        URLS = new ArrayList<>();
        ROOT_URL = System.getenv("WEB_APP");

        if (ROOT_URL == null || ROOT_URL.isEmpty()) {
            ROOT_URL = "https://virtual.mesoamericana.edu.gt/";
        }

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
