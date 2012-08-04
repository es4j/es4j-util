package org.es4j.util;

import java.util.UUID;

public class UUIDExt {
    
    public static final UUID EMPTY = UUID.fromString("00000000-0000-0000-0000-000000000000");

/*
    public static UUID fromBytes(ByteString byteString) {
        byte[] bytesOriginal = byteString.toByteArray();
        byte[] bytes = new byte[16];

        // Reverse the first 4 bytes
        bytes[0] = bytesOriginal[3];
        bytes[1] = bytesOriginal[2];
        bytes[2] = bytesOriginal[1];
        bytes[3] = bytesOriginal[0];
        // Reverse 6th and 7th
        bytes[4] = bytesOriginal[5];
        bytes[5] = bytesOriginal[4];
        // Reverse 8th and 9th
        bytes[6] = bytesOriginal[7];
        bytes[7] = bytesOriginal[6];
        // Copy the rest straight up        
        for (int i = 8; i < 16; i++) {
            bytes[i] = bytesOriginal[i];
        }

        return fromByteArray(bytes);
    }

    public static ByteString toBytes(UUID uuid) {
        byte[] bytesOriginal = toByteArray(uuid);
        byte[] bytes = new byte[16];

        // Reverse the first 4 bytes
        bytes[0] = bytesOriginal[3];
        bytes[1] = bytesOriginal[2];
        bytes[2] = bytesOriginal[1];
        bytes[3] = bytesOriginal[0];
        // Reverse 6th and 7th
        bytes[4] = bytesOriginal[5];
        bytes[5] = bytesOriginal[4];
        // Reverse 8th and 9th
        bytes[6] = bytesOriginal[7];
        bytes[7] = bytesOriginal[6];
        // Copy the rest straight up        
        for (int i = 8; i < 16; i++) {
            bytes[i] = bytesOriginal[i];
        }
        return ByteString.copyFrom(bytes);
    }
*/
    public static byte[] toByteArray(UUID uuid) {
        long msb = uuid.getMostSignificantBits();
        long lsb = uuid.getLeastSignificantBits();
        byte[] buffer = new byte[16];

        for (int i = 0; i < 8; i++) {
            buffer[i] = (byte) (msb >>> 8 * (7 - i));
        }
        for (int i = 8; i < 16; i++) {
            buffer[i] = (byte) (lsb >>> 8 * (7 - i));
        }
        return buffer;
    }

    public static UUID fromByteArray(byte[] byteArray) {

        long msb = 0;
        long lsb = 0;
        for (int i = 0; i < 8; i++) {
            msb = (msb << 8) | (byteArray[i] & 0xff);
        }
        for (int i = 8; i < 16; i++) {
            lsb = (lsb << 8) | (byteArray[i] & 0xff);
        }
        UUID result = new UUID(msb, lsb);

        return result;
    }
}
