package com.myntra.core.enums;

import com.myntra.ui.Channel;
import com.myntra.utils.config.ConfigProperties;

import java.util.*;

import static com.myntra.ui.BaseUIHelper.CONFIG_MANAGER;

public enum ChannelUtils {
    NATIVE_IOS,
    IOS_WEB,
    NATIVE_ANDROID,
    ANDROID_WEB,
    DESKTOP;

    private static Set<ChannelUtils> mobileNativeChannelUtils = new HashSet<>(Arrays.asList(ChannelUtils.NATIVE_IOS, ChannelUtils.NATIVE_ANDROID));
    private static final Map<ChannelUtils, IdentificationPlatform> platformIdMap = new HashMap<>();

    static {
        platformIdMap.put(ChannelUtils.DESKTOP, IdentificationPlatform.DESKTOP);
        platformIdMap.put(ChannelUtils.ANDROID_WEB, IdentificationPlatform.MOBILE_WEB);
        platformIdMap.put(ChannelUtils.NATIVE_ANDROID, IdentificationPlatform.NATIVE_ANDROID);
        platformIdMap.put(ChannelUtils.IOS_WEB, IdentificationPlatform.MOBILE_WEB);
        platformIdMap.put(ChannelUtils.NATIVE_IOS, IdentificationPlatform.NATIVE_IOS);
    }

    public static boolean isMobileNativePlatform(ChannelUtils channelUtils) {
        return mobileNativeChannelUtils.contains(channelUtils);
    }

    public static IdentificationPlatform asIdentificationPlatform(ChannelUtils channelUtils) {
        return platformIdMap.get(channelUtils);
    }

    public static ChannelUtils mappingChannelForLoadingData(Channel channelName) {
        ChannelUtils mappedChannelUtils;
        switch (channelName) {
            case DESKTOP_WEB:
                mappedChannelUtils = ChannelUtils.DESKTOP;
                break;

            case NATIVE_ANDROID:
                mappedChannelUtils = ChannelUtils.NATIVE_ANDROID;
                break;

            case NATIVE_IOS:
                mappedChannelUtils = ChannelUtils.NATIVE_IOS;
                break;

            case MOBILE_WEB:
                mappedChannelUtils = CONFIG_MANAGER.getString(ConfigProperties.UI_PHONE_OS.getKey())
                                                   .toLowerCase()
                                                   .contains("android") ? ChannelUtils.ANDROID_WEB : ChannelUtils.IOS_WEB;
                break;

            default:
                throw new IllegalArgumentException("Invalid Channel provided - " + channelName.getName());
        }
        return mappedChannelUtils;
    }

    public static IdentificationPlatform mappingChannelForLoadingLocators(ChannelUtils channelName) {
        IdentificationPlatform mappedIdentificationPlatform;
        switch (channelName) {
            case NATIVE_IOS:
                mappedIdentificationPlatform = IdentificationPlatform.NATIVE_IOS;
                break;

            case IOS_WEB:
                mappedIdentificationPlatform = IdentificationPlatform.MOBILE_WEB;
                break;

            case NATIVE_ANDROID:
                mappedIdentificationPlatform = IdentificationPlatform.NATIVE_ANDROID;
                break;

            case ANDROID_WEB:
                mappedIdentificationPlatform = IdentificationPlatform.MOBILE_WEB;
                break;

            case DESKTOP:
                mappedIdentificationPlatform = IdentificationPlatform.DESKTOP;
                break;

            default:
                throw new IllegalArgumentException("Invalid ChannelUtils provided - " + channelName.name());
        }
        return mappedIdentificationPlatform;
    }
}
