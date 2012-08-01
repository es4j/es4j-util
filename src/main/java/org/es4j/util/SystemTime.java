package org.es4j.util;

import java.util.concurrent.Callable;

/// <summary>
/// Provides the ability to override the current moment in time to facilitate testing.
/// Original idea by Ayende Rahien:
/// http://ayende.com/Blog/archive/2008/07/07/Dealing-with-time-in-tests.aspx
/// </summary>
public class SystemTime {

    /// <summary>
    /// The callback to be used to resolve the current moment in time.
    /// </summary>
    private static Callable<DateTime>/*Func<DateTime>*/ resolver;

    /// <summary>
    /// Gets the current moment in time.
    /// </summary>
    private static DateTime utcNow; // {get { return Resolver == null ? DateTime.UtcNow : Resolver(); } };


    public static  DateTime utcNow() {
        return (resolver == null) ? new DateTime() : resolve();
    }

    private static DateTime resolve() {
        try {
            return resolver.call();
        } catch (Exception ex) {
            //Logger.getLogger(SystemTime.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException("SystemTime resolver");
        }
    }

    public static <T extends Callable<DateTime>> void setResolver(T resolver) {
        SystemTime.resolver = resolver;
    }
}
