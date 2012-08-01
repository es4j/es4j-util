package org.es4j.util.logging;

public abstract class LoggerDelegate {
    //Func<Type, ILog> logger;
    public abstract <T> ILog buildLogger(Class<T> typeToLog);
}
