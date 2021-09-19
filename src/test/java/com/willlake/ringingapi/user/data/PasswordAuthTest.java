package com.willlake.ringingapi.user.data;

import org.junit.Test;

public class PasswordAuthTest {
    PasswordAuth passwordAuth = new PasswordAuth();

    @Test
    public void canHashPasswordCorrectly() {
        String hash = passwordAuth.hash("password");
        System.out.println(hash);
    }
}
