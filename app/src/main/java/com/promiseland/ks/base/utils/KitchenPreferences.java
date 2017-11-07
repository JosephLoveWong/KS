package com.promiseland.ks.base.utils;


import java.util.Locale;

public interface KitchenPreferences {
    boolean getDebugModeEnabled();

    long getFirstStartDate();

    boolean getHasSeenBubbleDialog();

    boolean getHasSeenFeedbackRequest();

    boolean getHasSeenIntroScreen();

    boolean getHasSeenProfilePictureTooltip();

    boolean getHasSeenSwipeUpGesture();

    boolean getHasSeenWhatsNewScreen();

    String getInstallationId();

    String getJwtAccessToken();

    String getJwtUserToken();

    int getLastUsedVersionCodeAndUpdate();

    Locale getPreferredLocale();

    int getPushSettings();

    boolean getShowTrackingEvents();

    String getTestGroup();

    int getVideoPlaybackSetting();

    boolean isCurrentLocale(Locale locale);

    boolean isPushNotificationsActive();

    boolean isTrackingEnabled();

    boolean isUnitMetric();

    boolean needsFirstTimeFeed();

    void removeJwtUserToken();

    void setDebugModeEnabled(boolean z);

    void setFirstStartDate(long j);

    void setHasSeenBubbleDialog(boolean z);

    void setHasSeenFeedbackRequest(boolean z);

    void setHasSeenIntroScreen(boolean z);

    void setHasSeenProfilePictureTooltip(boolean z);

    void setHasSeenSwipeUpGesture(boolean z);

    void setHasSeenWhatsNewScreen(boolean z);

    void setJwtAccessToken(String str);

    void setJwtUserToken(String str);

    void setPreferredLanguage(Locale locale);

    void setPreferredMeasureUnit(boolean z);

    void setPushSetting(int i, boolean z);

    void setShowTrackingEvents(boolean z);

    void setTrackingEnabled(boolean z);

    void setVideoPlayBackSetting(int i);

    void toggleTestGroup();
}
