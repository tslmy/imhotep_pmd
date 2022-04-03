import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.Clock;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MyJavaProgramTest {
    private static Clock mockClock() {
        // > LocalDateTime represents a date and a time-of-day. But lacking a timezone, this class cannot represent a
        // moment. A LocalDateTime value is inherently ambiguous.
        // https://stackoverflow.com/a/32443004/1147061
        LocalDateTime someDateAndTime = LocalDateTime.of(
                2021, 3, 7,
                12, 34, 56, 0);

        // https://docs.oracle.com/javase/8/docs/api/java/time/ZoneId.html
        ZoneId zoneId = ZoneId.systemDefault();

        Instant someInstant = someDateAndTime.toInstant(ZoneOffset.UTC);
        Clock fixedClock = Clock.fixed(someInstant, zoneId);
        return fixedClock;
    }

    @DisplayName("DateTime should be formatted correctly")
    @Test
    void getDateTimeFormatted() {
        String got = MyJavaProgram.getDateTimeFormatted(MyJavaProgramTest.mockClock());
        assertEquals("07-03-2021 04:34:56", got);
    }
}
