package com.amazon.interview.px;

import org.apache.commons.codec.digest.MurmurHash3;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class HashServiceTest {

    @Test
    public void generateHash() {
        String url = "https://github.io/";
        long hash = MurmurHash3.hash32x86(url.getBytes());
        assertEquals(94327744l, hash);
    }
}
