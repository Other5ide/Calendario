import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Calendar;
import java.util.GregorianCalendar;

class TodaysDateTest {


    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @BeforeEach
    void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }

    @AfterEach
    void restoreStreams() {
        System.setOut(originalOut);
    }

    @Test
    void testComportamientoDefaultConFechaActual() {
        TodaysDate todaysDate = new TodaysDate();

        todaysDate.printDateAndTime();

        GregorianCalendar currentCal = new GregorianCalendar();

        assertEquals(currentCal.get(Calendar.DATE), todaysDate.getDay(), "El día actual no coincide en el comportamiento por defecto.");
        assertEquals(currentCal.get(Calendar.MONTH) + 1, todaysDate.getMonth(), "El mes actual no coincide en el comportamiento por defecto.");
        assertEquals(currentCal.get(Calendar.YEAR), todaysDate.getYear(), "El año actual no coincide en el comportamiento por defecto.");

        assertNotNull(todaysDate.getTime(), "La cadena de tiempo no debería ser nula en el comportamiento por defecto.");
        assertTrue(todaysDate.getTime().contains(":"), "La cadena de tiempo debería contener el formato de hora.");
        assertTrue(outContent.toString().contains("Time:"), "La salida no contiene la hora en el comportamiento por defecto.");
        assertTrue(outContent.toString().contains("Date:"), "La salida no contiene la fecha en el comportamiento por defecto.");
    }
}