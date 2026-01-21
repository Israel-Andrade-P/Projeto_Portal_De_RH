package util;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class DateUtils {
    public static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    public static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("HH:mm");
    public static final LocalTime HORA_MINIMA_ENTRADA = LocalTime.of(6, 0);
    public static final LocalTime HORA_MAXIMA_SAIDA = LocalTime.of(22,0);
}
