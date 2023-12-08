package com.albireo3754.consumingrest;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Quote {
    String type;
    Value value2;

    public Quote() {
    }

    public Value getValue2() {
        return value2;
    }

    public void setValue2(Value value2) {
        this.value2 = value2;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
