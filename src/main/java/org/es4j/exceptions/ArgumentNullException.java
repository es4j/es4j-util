package org.es4j.exceptions;

import java.text.MessageFormat;

/**
 * Although it is common practice to throw a {@link NullPointerException} in
 * such cases, it is more and more considered bad practice these days. A
 * NullPointerException should only be thrown by the JVM when a null reference
 * is accidentally accessed. This exception is introduced to fill the gap and to
 * be more explicit in the case of a illegal null reference argument,
 */
public class ArgumentNullException extends IllegalArgumentException {

    public ArgumentNullException(String argument) {
        super(MessageFormat.format("The argument {0} was null.", argument));
    }

    public ArgumentNullException(String argument, String msg) {
        super(MessageFormat.format("The argument {0} was null. \n{1}", argument, msg));
    }

    public ArgumentNullException(String argument, String msg, Throwable throwable) {
        super(MessageFormat.format("The argument {0} was null. \n{1}", argument, msg), throwable);
    }

    public ArgumentNullException(String argument, Throwable throwable) {
        super(MessageFormat.format("The argument {0} was null.", argument), throwable);
    }
}
