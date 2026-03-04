package dorn.core;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;
import java.time.LocalDate;

/**
 * A Gson {@link TypeAdapter} for serializing and deserializing {@link LocalDate}
 * objects as ISO-8601 date strings (e.g., {@code "2025-03-04"}).
 */
public class LocalDateAdapter extends TypeAdapter<LocalDate> {
    /**
     * Serializes a {@link LocalDate} to its ISO-8601 string representation.
     *
     * @param out   the JSON writer to write to
     * @param value the {@link LocalDate} to serialize
     * @throws IOException if an I/O error occurs while writing
     */
    @Override
    public void write(JsonWriter out, LocalDate value) throws IOException {
        out.value(value.toString()); // ISO-8601 string
    }

    /**
     * Deserializes a {@link LocalDate} from an ISO-8601 date string.
     *
     * @param in the JSON reader to read from
     * @return the parsed {@link LocalDate}
     * @throws IOException if an I/O error occurs while reading
     */
    @Override
    public LocalDate read(JsonReader in) throws IOException {
        return LocalDate.parse(in.nextString());
    }
}
