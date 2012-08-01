package org.es4j.util.logging;


// Provides the ability to get a new instance of the configured logger.
public class LogFactory {

    // Initializes static members of the LogFactory class.
    /*static */ LogFactory() {
        ILog logger = new NullLogger();
        //BuildLogger = type => logger;
    }

    // Gets or sets the log builder of the configured logger.  
    // This should be invoked to return a new logging instance.
    private static NullLogger     nullLogger   = new NullLogger();
    private static LoggerDelegate buildLogger_ = new LoggerDelegate() {

        @Override
        public <T> ILog buildLogger(Class<T> typeToLog) {
            return nullLogger;
        }
    };

    public static <T> ILog buildLogger(Class<T> clazz) {
        return buildLogger_.buildLogger(clazz);
    }
    
    public static void setBuildLogger(LoggerDelegate delegate) {
        buildLogger_ = delegate;
    }

    private static class NullLogger implements ILog {

        @Override
        public void verbose(String message, Object... values) {
        }

        @Override
        public void debug(String message, Object... values) {
        }

        @Override
        public void info(String message, Object... values) {
        }

        @Override
        public void warn(String message, Object... values) {
        }

        @Override
        public void error(String message, Object... values) {
        }

        @Override
        public void fatal(String message, Object... values) {
        }
    }
}