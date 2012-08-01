package org.es4j.util.logging;


/// <summary>
/// Indicates the ability to log diagnostic information.
/// </summary>
/// <remarks>
/// Object instances which implement this interface must be designed to be multi-thread safe.
/// </remarks>
public interface ILog {

    /// <summary>
    /// Logs the most detailed level of diagnostic information.
    /// </summary>
    /// <param name="message">The diagnostic message to be logged.</param>
    /// <param name="values">All parameter to be formatted into the message, if any.</param>
    void verbose(String message, Object... values);

    /// <summary>
    /// Logs the debug-level diagnostic information.
    /// </summary>
    /// <param name="message">The diagnostic message to be logged.</param>
    /// <param name="values">All parameter to be formatted into the message, if any.</param>
    void debug(String message, Object... values);

    /// <summary>
    /// Logs important runtime diagnostic information.
    /// </summary>
    /// <param name="message">The diagnostic message to be logged.</param>
    /// <param name="values">All parameter to be formatted into the message, if any.</param>
    void info(String message, Object... values);

    /// <summary>
    /// Logs diagnostic issues to which attention should be paid.
    /// </summary>
    /// <param name="message">The diagnostic message to be logged.</param>
    /// <param name="values">All parameter to be formatted into the message, if any.</param>
    void warn(String message, Object... values);

    /// <summary>
    /// Logs application and infrastructure-level errors.
    /// </summary>
    /// <param name="message">The diagnostic message to be logged.</param>
    /// <param name="values">All parameter to be formatted into the message, if any.</param>
    void error(String message, Object... values);

    /// <summary>
    /// Logs fatal errors which result in process termination.
    /// </summary>
    /// <param name="message">The diagnostic message to be logged.</param>
    /// <param name="values">All parameter to be formatted into the message, if any.</param>
    void fatal(String message, Object... values);
}