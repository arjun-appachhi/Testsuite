package com.myntra.core.central;

import com.myntra.core.central.pageLocator.PageLocatorsLoader;
import com.myntra.core.utils.JavaUtils;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

class ObjectRepository {
    private Map<String, PageLocatorsLoader> rep = new HashMap<>();

    ObjectRepository(String repPath) throws Exception {
        for (File f : JavaUtils.listFiles(repPath, ".json")) {
            rep.put(f.getName()
                     .split("\\.")[0].toLowerCase(), new PageLocatorsLoader(f.getAbsolutePath()));
        }
    }

    PageLocatorsLoader getPageDef(String pageName) {
        return rep.get(pageName.toLowerCase());
    }

}
