package com.gizmo.quotely.cli.validation;

import java.util.HashMap;
import java.util.Map;


public enum ValidationStatus {

    INVALID_STATUS(0),
    VALID_STATUS  (1),
    INCONCLUSIVE_STATUS(2);

    protected int value;
    private static Map map = new HashMap<String, Integer>();

    static {
        for (ValidationStatus result : ValidationStatus.values()) {
            map.put(result.value, result);
        }
    }

    private ValidationStatus(int value) {
        this.value = value;
    }

    public static ValidationStatus valueOf(int resultStatusType) {
        return (ValidationStatus) map.get(resultStatusType);
    }

    public int getValue() {
        return value;
    }
}

