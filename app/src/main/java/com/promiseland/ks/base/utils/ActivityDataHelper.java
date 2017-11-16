package com.promiseland.ks.base.utils;

import com.promiseland.ks.base.model.ActivityData;

import java.util.List;

public class ActivityDataHelper {
    private static int accountForLanguage = 0;

    public static ActivityData getActivityData(String dataString, List<String> segments) {
        ActivityData toReturn = new ActivityData();
        if (FieldHelper.INSTANCE.isEmpty(dataString) || segments == null || segments.size() <= 0) {
            return toReturn;
        }
        String dataTypeSegment = segments.get(0);
        if (!isLanguageSegment(dataTypeSegment) || segments.size() <= 1) {
            accountForLanguage = 0;
        } else {
            dataTypeSegment = segments.get(1);
            accountForLanguage = 1;
        }

        if (isCommentSegment(dataTypeSegment)) {
            return setCommentsDeeplinkType(toReturn, segments);
        }
        return toReturn;
    }

    private static boolean isCommentSegment(String segment) {
        return FieldHelper.INSTANCE.equals(segment, "comments");
    }

    private static boolean isLanguageSegment(String dataTypeSegment) {
        return FieldHelper.INSTANCE.equals(dataTypeSegment, "en") || FieldHelper.INSTANCE.equals(dataTypeSegment, "de") || FieldHelper.INSTANCE.equals(dataTypeSegment, "zh");
    }

    private static ActivityData setCommentsDeeplinkType(ActivityData activityData, List<String> segments) {
        if (segments.size() == accountForLanguage + 2) {
            activityData.mDataType = 12;
            activityData.mDataSubType = 1;
            activityData.mStringData = (String) segments.get(accountForLanguage + 1);
        } else {
            activityData.mDataType = 1;
        }
        return activityData;
    }

    public static void trackDeeplink(ActivityData activityData) {
        // TODO
    }

    public static boolean isInvalid(ActivityData activityData) {
        return activityData == null || activityData.isInvalid();
    }

}
