package com.megazone.springbootbackend;

import com.megazone.springbootbackend.common.GenerateUUID;
import org.junit.jupiter.api.Test;


public class UUIDTest {

    @Test
    void uuidTest() {
        System.out.println("GenerateUUID.generateUUID() = " + GenerateUUID.generateUUID());
    }
}
