package dorn.parser;

import java.time.LocalDate;

/**
 * Utility class for parsing task descriptions and dates from tokenized user input.
 * Supports extraction of text and {@link LocalDate} values delimited by
 * keywords {@code /by}, {@code /from}, and {@code /to}.
 */
public class Parser {

    // -------------------------------------------------------------------------
    // Private helpers
    // -------------------------------------------------------------------------

    /**
     * Returns the index of the first occurrence of {@code token} in {@code parts},
     * or {@code -1} if not found.
     *
     * @param parts the tokenized user input to search through
     * @param token the delimiter token to look for (e.g. {@code "/by"})
     * @return the index of {@code token}, or {@code -1} if absent
     */
    private static int indexOf(String[] parts, String token) {
        for (int i = 0; i < parts.length; i++) {
            if (parts[i].equals(token)) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Returns {@code true} if {@code token} appears anywhere in {@code parts}.
     *
     * @param parts the tokenized user input to search through
     * @param token the delimiter token to check for
     * @return {@code true} if the token is present, {@code false} otherwise
     */
    public static boolean contains(String[] parts, String token) {
        return indexOf(parts, token) != -1;
    }

    /**
     * Joins the tokens in {@code parts} between indices {@code fromExclusive}
     * and {@code toExclusive} into a single trimmed string.
     *
     * @param parts         the tokenized user input
     * @param fromExclusive the index before the first token to include
     * @param toExclusive   the index after the last token to include
     * @return the joined, trimmed string, or an empty string if the range is empty
     */
    private static String tokensBetween(String[] parts, int fromExclusive, int toExclusive) {
        StringBuilder sb = new StringBuilder();
        for (int i = fromExclusive + 1; i < toExclusive; i++) {
            sb.append(parts[i]).append(" ");
        }
        return sb.toString().trim();
    }

    // -------------------------------------------------------------------------
    // Public parsing methods
    // -------------------------------------------------------------------------

    /**
     * Extracts the task description from the tokenized input.
     * Reads tokens starting from index 1 until a delimiter keyword
     * ({@code /by}, {@code /from}, or {@code /to}) is encountered or the input ends.
     *
     * @param parts the tokenized user input, where {@code parts[0]} is the command
     * @return the trimmed description string, or an empty string if none is found
     */
    public static String parseDescription(String[] parts) {
        // Find the first delimiter — description ends just before it
        int delimiterIndex = parts.length;
        for (int i = 1; i < parts.length; i++) {
            if (parts[i].equals("/by") || parts[i].equals("/from") || parts[i].equals("/to")) {
                delimiterIndex = i;
                break;
            }
        }
        return tokensBetween(parts, 0, delimiterIndex);
    }

    /**
     * Extracts the start date from the tokenized input for event tasks.
     * Reads tokens between the {@code /from} and {@code /to} keywords and
     * parses them as an ISO-8601 date.
     *
     * @param parts the tokenized user input containing {@code /from} and {@code /to}
     * @return the parsed {@link LocalDate} start date, or {@code null} if
     *         {@code /from} or {@code /to} are absent or in the wrong order
     */
    public static LocalDate startDate(String[] parts) {
        int fromIndex = indexOf(parts, "/from");
        int toIndex = indexOf(parts, "/to");

        if (fromIndex == -1 || toIndex == -1 || fromIndex >= toIndex) {
            return null;
        }

        String dateStr = tokensBetween(parts, fromIndex, toIndex);
        return LocalDate.parse(dateStr);
    }

    /**
     * Extracts the end date from the tokenized input for deadline or event tasks.
     * Reads tokens after the first occurrence of {@code /by} (for deadlines)
     * or {@code /to} (for events) and parses them as an ISO-8601 date.
     *
     * @param parts the tokenized user input containing {@code /by} or {@code /to}
     * @return the parsed {@link LocalDate} end date, or {@code null} if
     *         neither {@code /by} nor {@code /to} is found
     */
    public static LocalDate endDate(String[] parts) {
        int delimiterIndex = indexOf(parts, "/by");
        if (delimiterIndex == -1) {
            delimiterIndex = indexOf(parts, "/to");
        }

        if (delimiterIndex == -1) {
            return null;
        }

        String dateStr = tokensBetween(parts, delimiterIndex, parts.length);
        return LocalDate.parse(dateStr);
    }
}