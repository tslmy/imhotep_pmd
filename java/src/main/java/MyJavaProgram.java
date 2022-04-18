import java.time.Clock;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MyJavaProgram {
    private static final String DATE_TIME_FORMAT = "dd-MM-yyyy HH:mm:ss";
    private static final Logger LOGGER = LoggerFactory.getLogger(MyJavaProgram.class);

    public static void main(String[] args) {
        String username = System.getProperty("user.name");
        System.out.printf("Hello, %s!\n", username);

        // Use the actual clock in production. This clock will be mocked in unit tests.
        Clock clock = Clock.systemDefaultZone();
        String dateTimeFormatted = MyJavaProgram.getDateTimeFormatted(clock);
        LOGGER.info("Started.", dateTimeFormatted);
        System.out.println(String.join("%n", MyJavaProgram.getTriangle()));
    }

    /**
     * Return a formatted local date and time string.
     *
     * @return The current date and time formatted as <code>dateTimeFormat</code>.
     */
    static String getDateTimeFormatted(Clock clock) {
        LocalDateTime localDateTime = java.time.LocalDateTime.now(clock);
        DateTimeFormatter formatter = java.time.format.DateTimeFormatter.ofPattern(MyJavaProgram.DATE_TIME_FORMAT);
        return formatter.format(localDateTime);
    }

    /**
     * This is how you provide default values for parameters of a method in Java.
     *
     * @return A triangle.
     */
    private static String[] getTriangle() {
        return MyJavaProgram.getTriangle(4);
    }


    /**
     * Generates a triangle of the size <code>size</code>.
     *
     * @param size Size of the triangle.
     * @return An array of strings, each representing a horizontal line. The lines should form a triangle.
     */
    private static String[] getTriangle(int size) {
        String[] lines = new String[size];
        for (int i = 0; i < size; i++) lines[i] = ".".repeat(i + 1);
        return lines;
    }
}
