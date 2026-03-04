package dorn.parser;

import java.time.LocalDate;

/**
 * Utility class for parsing task descriptions and dates from tokenized user input.
 * Supports extraction of text and {@link LocalDate} values delimited by
 * keywords {@code /by}, {@code /from}, and {@code /to}.
 */
public class Parser {

    /**
     * Extracts the task description from the tokenized input.
     * Reads tokens starting from index 1 until a delimiter keyword
     * ({@code /by}, {@code /from}, or {@code /to}) is encountered or the input ends.
     *
     * @param parts the tokenized user input, where {@code parts[0]} is the command
     * @return the trimmed description string, or an empty string if none is found
     */
    public static String parseDescription(String[] parts){
        //by the case return the required text the task wants
        StringBuilder sb = new StringBuilder();
        for(int i = 1; i < parts.length; i++){
            if(parts[i].equals("/by") || parts[i].equals("/from") || parts[i].equals("/to")){
                break;
            }
            sb.append(parts[i]).append(" ");
        }
        return sb.toString().trim();
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
    public static LocalDate startDate(String[] parts){
        int fromIndex = -1;
        int toIndex = -1;

        for(int i = 0; i < parts.length; i++){
            if(parts[i].equals("/from")){
                fromIndex = i;
            }
            else if(parts[i].equals("/to")){
                toIndex = i;
            }
        }

        if (fromIndex != -1 && toIndex != -1 && fromIndex < toIndex) {
            StringBuilder startDate = new StringBuilder();
            for (int i = fromIndex + 1; i < toIndex; i++) {
                startDate.append(parts[i]).append(" ");
            }

            return LocalDate.parse(startDate.toString().trim());
        } else {
            return null;
        }
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
    public static LocalDate endDate(String[] parts){

        int fromIndex = -1;

        for(int i = 0; i < parts.length; i++){
            if(parts[i].equals("/by") || parts[i].equals("/to")){
                fromIndex = i;
                break;
            }
        }

        if(fromIndex != -1){
            StringBuilder endDate = new StringBuilder();
            for(int i = fromIndex + 1; i < parts.length; i++){
                endDate.append(parts[i]).append(" ");
            }
            return LocalDate.parse(endDate.toString().trim());
        }else{
            return null;
        }
    }
}
