package com.megazone.springbootbackend.common;

import java.util.UUID;

public class GenerateUUID {
    public static String generateUUID() {
        return UUID.randomUUID().toString();
    }
}
