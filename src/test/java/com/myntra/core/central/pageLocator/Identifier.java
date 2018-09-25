package com.myntra.core.central.pageLocator;

import com.myntra.core.enums.IdentifyBy;

public class Identifier {

    private IdentifyBy by = null;
    private String value = null;

    public Identifier(String by, String value) {
        setIdType(by);
        setValue(value);
    }

    public IdentifyBy getIdType() {
        return by;
    }

    private void setIdType(String by) {
        this.by = IdentifyBy.valueOf(by.toUpperCase());
    }

    public String getValue() {
        return value;
    }

    private void setValue(String value) {
        this.value = value;
    }

}
