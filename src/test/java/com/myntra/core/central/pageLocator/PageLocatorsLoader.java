package com.myntra.core.central.pageLocator;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.myntra.core.enums.ChannelUtils;
import com.myntra.core.enums.IdentificationPlatform;
import com.myntra.core.utils.JsonReader;
import com.myntra.utils.logger.ILogger;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;

public class PageLocatorsLoader implements ILogger {
    private Map<String, Map<IdentificationPlatform, Identifier>> map = new HashMap<>();
    private Properties convertedPropertiesForChannel = null;

    public PageLocatorsLoader(String path) throws Exception {
        JsonReader reader = new JsonReader(path);
        JsonObject root = reader.getRoot();
        LOG.debug(String.format("Loading pageLocator file - %s", path));
        parseLocatorsFromFile(root);
    }

    private void parseLocatorsFromFile(JsonObject root) {
        for (Entry<String, JsonElement> idJson : root.entrySet()) {
            String elementName = idJson.getKey()
                                       .trim()
                                       .toLowerCase();
            map.put(elementName, new HashMap<>());
            Iterator<Entry<String, JsonElement>> elementValuesForChannelsIterator = idJson.getValue()
                                                                                          .getAsJsonObject()
                                                                                          .entrySet()
                                                                                          .iterator();
            LOG.debug(String.format("Loading locators for element - %s :: %s", elementName, idJson.getValue()
                                                                                                  .getAsJsonObject()
                                                                                                  .toString()));
            parseEachLocator(elementName, elementValuesForChannelsIterator);
        }
    }

    private void parseEachLocator(String elementName, Iterator<Entry<String, JsonElement>> elementValuesForChannelsIterator) {
        IdentificationPlatform platform;
        Identifier identifier;
        while (elementValuesForChannelsIterator.hasNext()) {
            Entry<String, JsonElement> id = elementValuesForChannelsIterator.next();
            platform = IdentificationPlatform.valueOf(id.getKey()
                                                        .trim()
                                                        .toUpperCase());
            String[] parts = id.getValue()
                               .getAsString()
                               .split("=", 2);
            LOG.debug(String.format("Creating identifier per channel - %s", id.getValue()
                                                                              .getAsString()));
            identifier = new Identifier(parts[0].trim(), parts[1].trim());
            map.get(elementName)
               .put(platform, identifier);
        }
    }

    public synchronized Identifier getIdentifier(ChannelUtils channelUtils, String name) {
        return map.get(name.toLowerCase())
                  .get(ChannelUtils.asIdentificationPlatform(channelUtils));
    }

    public Properties convertToProperties(ChannelUtils channelName) {
        if (null == convertedPropertiesForChannel) {
            convertedPropertiesForChannel = new Properties();
            IdentificationPlatform identificationPlatform = ChannelUtils.mappingChannelForLoadingLocators(channelName);
            for (String elementName : map.keySet()) {
                HashMap elementValues = (HashMap<IdentificationPlatform, Identifier>) map.get(elementName);
                Identifier elementValueForChannel = (Identifier) elementValues.get(identificationPlatform);
                convertedPropertiesForChannel.setProperty(elementName + "." + elementValueForChannel.getIdType()
                                                                                                    .name()
                                                                                                    .toLowerCase(),
                        elementValueForChannel.getValue());
            }
        }
        return convertedPropertiesForChannel;
    }
}
