package com.myntra.core.utils;

import com.myntra.core.enums.ConfigType;
import com.myntra.utils.logger.ILogger;
import net.sf.cglib.proxy.Callback;
import net.sf.cglib.proxy.Enhancer;

import static com.myntra.ui.BaseUIHelper.CONFIG_MANAGER;

public class DynamicEnhancer extends Enhancer implements ILogger {
    public static Object create(Class type, Callback callback) {
        boolean isDebug = Boolean.valueOf(CONFIG_MANAGER.getString(ConfigType.useDynamicLogger.name()));

        Object newInstance = null;

        if (isDebug) {
            LOG.info(String.format("useDynamicLogger = %s. Creating instance of class - %s - using DynamicLogger", String.valueOf(isDebug),
                    type.getName()));
            newInstance = Enhancer.create(type, callback);
        } else {
            LOG.info(String.format("useDynamicLogger = %s. Creating instance of class - %s - directly", String.valueOf(isDebug), type.getName()));
            try {
                newInstance = type.newInstance();
            } catch (InstantiationException | IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return newInstance;
    }
}
