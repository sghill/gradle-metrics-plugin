package nebula.plugin.metrics;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.classic.spi.ThrowableProxy;
import ch.qos.logback.core.AppenderBase;
import com.google.common.base.Optional;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * A Logback {@link ch.qos.logback.core.Appender} that throws {@link java.lang.AssertionError} when events at error or
 * higher are appended.
 *
 * @author Danny Thomas
 */
public class AssertAppender extends AppenderBase<ILoggingEvent> {
    @Override
    protected void append(ILoggingEvent eventObject) {
        checkNotNull(eventObject);
        if (eventObject.getLevel().isGreaterOrEqual(Level.ERROR)) { // TODO make this configurable so all events with throwable cause an assertion error
            Optional<ThrowableProxy> proxy = Optional.fromNullable((ThrowableProxy) eventObject.getThrowableProxy());
            // There's a chance we're dealing with code that catches Throwable, so output to standard error so we definitely won't miss this
            System.err.println("Caught error or higher level event, message: " + eventObject.getFormattedMessage());
            if (proxy.isPresent()) {
                ThrowableProxy throwableProxy = proxy.get();
                Throwable throwable = throwableProxy.getThrowable();
                throwable.printStackTrace();
                throw new AssertionError(eventObject.getFormattedMessage(), throwable);
            } else {
                throw new AssertionError(eventObject.getFormattedMessage());
            }
        }
    }
}
