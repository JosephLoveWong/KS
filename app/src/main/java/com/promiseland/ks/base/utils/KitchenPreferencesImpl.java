package com.promiseland.ks.base.utils;

import android.content.Context;
import android.content.SharedPreferences;

import org.greenrobot.eventbus.EventBus;

import java.util.Calendar;
import java.util.Locale;
import java.util.Random;
import java.util.UUID;

import timber.log.Timber;

public class KitchenPreferencesImpl implements KitchenPreferences {
    private Locale mCurrentLocale;
    private final EventBus mEventBus;
    private final SharedPreferences mPrefs;

    public KitchenPreferencesImpl(Context context, EventBus eventBus) {
        this.mEventBus = eventBus;
        this.mEventBus.register(this);
        this.mPrefs = context.getSharedPreferences("com.ajnsnewmedia.kitchenstories_preferences", 0);
        this.mPrefs.edit().remove("backend_image_buckets").remove("backend_image_sizes").apply();
    }

    public boolean isCurrentLocale(Locale locale) {
        String localeString = this.mPrefs.getString("locale", null);
        if (locale == null || localeString == null) {
            return locale == null && localeString == null;
        } else {
            return localeString.equals(locale.getLanguage());
        }
    }

    public boolean isUnitMetric() {
        String value = this.mPrefs.getString("measure_unit", null);
        if (value == null) {
            value = getDefaultUnits();
            setPreferredMeasureUnit(value.equals("metric"));
        }
        return value.equals("metric");
    }

    private String getDefaultUnits() {
        String country = java.util.Locale.getDefault().getCountry();
        if (country.equals("US") || country.equals("LR") || country.equals("MM")) {
            return "us";
        }
        return "metric";
    }

    public void setPreferredMeasureUnit(boolean isMetric) {
        this.mPrefs.edit().putString("measure_unit", isMetric ? "metric" : "us").apply();
    }

    public void setDbEmpty(boolean isEmpty) {
        this.mPrefs.edit().putBoolean("db_empty", isEmpty).apply();
    }

    public boolean isPushNotificationsActive() {
        return this.mPrefs.getBoolean("is_push_active", true);
    }

    public int getVideoPlaybackSetting() {
        return this.mPrefs.getInt("video_playback_setting", 0);
    }

    public void setVideoPlayBackSetting(int setting) {
        this.mPrefs.edit().putInt("video_playback_setting", setting).apply();
    }

    public String getJwtAccessToken() {
        return this.mPrefs.getString("jwt_access_tocken", "");
    }

    public void setJwtAccessToken(String jwtAccessToken) {
        this.mPrefs.edit().putString("jwt_access_tocken", jwtAccessToken).apply();
    }

    public String getJwtUserToken() {
        return this.mPrefs.getString("jwt_user_token", "");
    }

    public void setJwtUserToken(String userToken) {
        this.mPrefs.edit().putString("jwt_user_token", userToken).apply();
    }

    @Override
    public void setPreferredLanguage(Locale locale) {

    }

    public void removeJwtUserToken() {
        this.mPrefs.edit().remove("jwt_user_token").apply();
    }

    public int getLastUsedVersionCodeAndUpdate() {
        int version = this.mPrefs.getInt("last_used_version_code", 0);
        if (version < 242) {
            this.mPrefs.edit().putInt("last_used_version_code", 242).apply();
        }
        if (version == 0) {
            try {
                setFirstStartDate(Calendar.getInstance().getTimeInMillis());
            } catch (Throwable th) {
            }
        }
        Timber.d("last used version is %d", Integer.valueOf(version));
        return version;
    }

    @Override
    public Locale getPreferredLocale() {
        return null;
    }

    private void saveInstallationId(String installationId) {
        this.mPrefs.edit().putString("installation_id", installationId).apply();
    }

    public String getInstallationId() {
        CharSequence installationId = this.mPrefs.getString("installation_id", "");
        if (FieldHelper.isEmpty(installationId)) {
            return generateAndSaveInstallationId();
        }
        return installationId.toString();
    }

    private String generateAndSaveInstallationId() {
        String installationId = ParseLegacyHelper.readParseInstallationId();
        if (FieldHelper.isEmpty((CharSequence) installationId)) {
            installationId = UUID.randomUUID().toString();
        }
        saveInstallationId(installationId);
        return installationId;
    }

    public boolean isTrackingEnabled() {
        return this.mPrefs.getBoolean("user_allows_tracking", true);
    }

    public void setTrackingEnabled(boolean enabled) {
        this.mPrefs.edit().putBoolean("user_allows_tracking", enabled).apply();
    }

    public boolean getHasSeenIntroScreen() {
        return this.mPrefs.getBoolean("has_seen_intro_screen", false);
    }

    public void setHasSeenIntroScreen(boolean hasSeen) {
        this.mPrefs.edit().putBoolean("has_seen_intro_screen", hasSeen).apply();
    }

    public boolean getHasSeenSwipeUpGesture() {
        return this.mPrefs.getBoolean("has_seen_swipe_up_gesture", false);
    }

    public void setHasSeenSwipeUpGesture(boolean hasSeen) {
        this.mPrefs.edit().putBoolean("has_seen_swipe_up_gesture", hasSeen).apply();
    }

    public boolean getHasSeenFeedbackRequest() {
        return this.mPrefs.getBoolean("has_seen_feedback_request", false);
    }

    public void setHasSeenFeedbackRequest(boolean hasSeen) {
        this.mPrefs.edit().putBoolean("has_seen_feedback_request", hasSeen).apply();
    }

    public long getFirstStartDate() {
        return this.mPrefs.getLong("first_start_date", 0);
    }

    public void setFirstStartDate(long timeMillis) {
        this.mPrefs.edit().putLong("first_start_date", timeMillis).apply();
    }

    public boolean needsFirstTimeFeed() {
        return Calendar.getInstance().getTimeInMillis() - getFirstStartDate() < 172800000;
    }

    public void setDebugModeEnabled(boolean enabled) {
        this.mPrefs.edit().putBoolean("debug_mode_enabled", enabled).apply();
    }

    public boolean getDebugModeEnabled() {
        return this.mPrefs.getBoolean("debug_mode_enabled", false);
    }

    public void setHasSeenWhatsNewScreen(boolean set) {
        this.mPrefs.edit().putBoolean("has_seen_whats_new_screen", set).apply();
    }

    public boolean getHasSeenWhatsNewScreen() {
        return this.mPrefs.getBoolean("has_seen_whats_new_screen", true);
    }

    public String getTestGroup() {
        float randomVal = getRandomClientValue();
        if (randomVal < 0.0f) {
            randomVal = new Random().nextFloat();
            setRandomClientValue(randomVal);
        }
        return randomVal < 0.5f ? "a" : "b";
    }

    public void toggleTestGroup() {
        this.mPrefs.edit().putFloat("key_random", this.mPrefs.getFloat("key_random", -1.0f) < 0.5f ? 1.0f : 0.0f).apply();
    }

    private float getRandomClientValue() {
        return this.mPrefs.getFloat("key_random", -1.0f);
    }

    private void setRandomClientValue(float randomVal) {
        this.mPrefs.edit().putFloat("key_random", randomVal).apply();
    }

    public void setHasSeenBubbleDialog(boolean hasSeen) {
        this.mPrefs.edit().putBoolean("has_seen_bubble_dialog", hasSeen).apply();
    }

    public boolean getHasSeenBubbleDialog() {
        return this.mPrefs.getBoolean("has_seen_bubble_dialog", false);
    }

    public void setShowTrackingEvents(boolean show) {
        this.mPrefs.edit().putBoolean("show_tracking_events", show).apply();
    }

    public boolean getShowTrackingEvents() {
        return this.mPrefs.getBoolean("show_tracking_events", false);
    }

    public boolean getHasSeenProfilePictureTooltip() {
        return this.mPrefs.getBoolean("has_seen_profile_picture_tooltip", false);
    }

    public void setHasSeenProfilePictureTooltip(boolean hasSeen) {
        this.mPrefs.edit().putBoolean("has_seen_profile_picture_tooltip", hasSeen).apply();
    }

    public int getPushSettings() {
        return this.mPrefs.getInt("push_settings", 0);
    }

    public void setPushSetting(int pushSetting, boolean enable) {
        int setting;
        if (enable) {
            setting = getPushSettings() | pushSetting;
        } else {
            setting = getPushSettings() & (pushSetting ^ -1);
        }
        this.mPrefs.edit().putInt("push_settings", setting).apply();
    }
}
