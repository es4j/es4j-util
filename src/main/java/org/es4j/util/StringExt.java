package org.es4j.util;

/**
 *
 * @author Esfand
 */
public class StringExt {
    public static String Empty;

    public static boolean isNullOrEmpty(String key) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    public static String formatWith(String string, Object... computeHashKey) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    public static String Empty() {
        return "";
    }
    
    public static String trimStart(String string, String chars) {
        return string.replaceAll("^[" + chars + "]+", "");
    }
}
