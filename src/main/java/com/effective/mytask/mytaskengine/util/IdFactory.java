package com.effective.mytask.mytaskengine.util;

import java.util.UUID;

public class IdFactory {

    public static String generateId() {
        return UUID.randomUUID().toString().replace("-", "");
    }
}
