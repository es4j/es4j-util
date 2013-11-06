package org.es4j.util;

import java.util.UUID;


public class Empty {
    
    public static final UUID UUID() {
        return UUID.fromString("00000000-0000-0000-0000-000000000000");
    } 
    
    public static final String String() {
        return "";
    }
    
    public static final int Integer() {
        return Integer.MAX_VALUE;
    }
    
    public static final long Long() {
        return Long.MAX_VALUE;
    }
    
    public static final byte[] ByteArray() {
        return new byte[0];
    }
    
}
