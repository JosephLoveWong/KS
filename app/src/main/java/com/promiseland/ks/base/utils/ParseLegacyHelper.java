package com.promiseland.ks.base.utils;

import android.support.v7.widget.RecyclerView.ItemAnimator;

import com.ajnsnewmedia.kitchenstories.KitchenStoriesApp;

import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;

import timber.log.Timber;

public class ParseLegacyHelper {
    public static String readParseInstallationId() {
        String installationId = "";
        try {
            installationId = getParseFileAsText("installationId");
        } catch (Exception e) {
            Timber.i(e, "could not read parse installation id from file", new Object[0]);
        }
        return installationId;
    }

    public static String readParseSessionToken() {
        String sessionToken = "";
        try {
            JSONObject userJson = new JSONObject(getParseFileAsText("currentUser"));
            if (userJson.has("session_token")) {
                sessionToken = userJson.getString("session_token");
            }
        } catch (Exception e) {
            Timber.i(e, "could not read parse user file", new Object[0]);
        }
        return sessionToken;
    }

    public static void removeParseSession() {
        File idFile = new File(KitchenStoriesApp.getInstance().getDir("Parse", 0), "currentUser");
        if (idFile.exists()) {
            idFile.delete();
        }
    }

    private static String getParseFileAsText(String fileName) throws IOException {
        InputStream in = new FileInputStream(new File(KitchenStoriesApp.getInstance().getDir("Parse", 0), fileName));
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        byte[] buffer = new byte[ItemAnimator.FLAG_APPEARED_IN_PRE_LAYOUT];
        while (true) {
            int n = in.read(buffer);
            if (-1 == n) {
                return new String(out.toByteArray(), Charset.forName("UTF-8"));
            }
            out.write(buffer, 0, n);
        }
    }
}
