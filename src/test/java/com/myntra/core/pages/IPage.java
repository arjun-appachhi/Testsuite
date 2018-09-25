package com.myntra.core.pages;

import com.myntra.core.central.pageLocator.Identifier;
import com.myntra.core.enums.ChannelUtils;

interface IPage {
    String pageName();

    ChannelUtils getChannelUtils();

    Identifier getIdentifier(String name);
}
