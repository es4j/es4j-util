package org.es4j.util.exceptions;

// http://www.gamlor.info/wordpress/2010/02/throwing-checked-excpetions-like-unchecked-exceptions-in-java/
/*
You can use it anywhere!

private int methodWithoutCheckedException() {
    try{
        return legacyMethodWithCheckedExceptions();
    } catch (OperationFailedException e){
        throw UncheckedThrow.throwUnchecked(e);
    }
}

It’s not only limited to rethrows, you can throw a checked exception anywhere.
However I think that’s a very bad idea!

private String demoJustThrow(){
    throw UncheckedThrow.throwUnchecked(new IOException("See, I'm not declared"));
}
*/


/**
 * @author roman.stoffel@gamlor.info
 * @since 03.02.2010
 */
public final class UncheckedThrow {
    private UncheckedThrow(){}

    // Now this returns an exception, so that you can satisfy the compiler by throwing it.
    // But in reality we throw the given exception!
    public static RuntimeException throwUnchecked(final Exception ex){
        // Now we use the 'generic' method. Normally the type T is inferred
        // from the parameters. However you can specify the type also explicit!
        // Now we du just that! We use the RuntimeException as type!
        // That means the throwsUnchecked throws an unchecked exception!
        // Since the types are erased, no type-information is there to prevent this!
        UncheckedThrow.<RuntimeException>throwsUnchecked(ex);

        // This is also the satisfy the compiler. It's actually unreachable code!
        throw new AssertionError("This code should be unreachable. Something went terrible wrong here!");
    }

    /**
     * Remember, Generics are erased in Java. So this basically throws an Exception. The real
     * Type of T is lost during the compilation
     */
    public static <T extends Exception> void throwsUnchecked(Exception toThrow) throws T{
        // Since the type is erased, this cast actually does nothing!!!
        // we can throw any exception
        throw (T) toThrow;
    }
}

