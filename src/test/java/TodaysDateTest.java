import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Calendar;
import java.util.GregorianCalendar;

class TodaysDateTest {

    //para capturar la salida de System.out.println() y poder verificarla en los tests.

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    /**
     * Este método se ejecuta ANTES de cada test.
     * Aquí redirigimos la salida estándar (System.out) a nuestro 'outContent'
     * para poder "espiar" lo que se imprime en consola.
     */
    @BeforeEach
    void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }

    /**
     * Este método se ejecuta DESPUÉS de cada test.
     * Aquí restauramos la salida estándar a su valor original,
     * para que la consola funcione normalmente después de los tests.
     */
    @AfterEach
    void restoreStreams() {
        System.setOut(originalOut);
    }

    @Test
    void testFechaNormal() {
        GregorianCalendar testCal = new GregorianCalendar(2025, Calendar.MAY, 28, 15, 30, 0);
        TodaysDate todaysDate = new TodaysDate(testCal);

        todaysDate.printDateAndTime();

        assertEquals("15:30:0", todaysDate.getTime(), "La hora obtenida no es la esperada para un día normal.");
        assertEquals(28, todaysDate.getDay(), "El día no coincide para un día normal.");
        assertEquals(5, todaysDate.getMonth(), "El mes no coincide para un día normal.");
        assertEquals(2025, todaysDate.getYear(), "El año no coincide para un día normal.");

        assertTrue(outContent.toString().contains("Time: 15:30:0"), "La salida impresa de la hora es incorrecta.");
        assertTrue(outContent.toString().contains("Date: 5 28 2025"), "La salida impresa de la fecha es incorrecta.");
    }

    @Test
    void testFinDeAnio() {
        GregorianCalendar testCal = new GregorianCalendar(2024, Calendar.DECEMBER, 31, 23, 59, 59);
        TodaysDate todaysDate = new TodaysDate(testCal);

        todaysDate.printDateAndTime();

        assertEquals("23:59:59", todaysDate.getTime(), "La hora al final del año no es correcta.");
        assertEquals(31, todaysDate.getDay(), "El día al final del año no es correcto.");
        assertEquals(12, todaysDate.getMonth(), "El mes al final del año no es correcto.");
        assertEquals(2024, todaysDate.getYear(), "El año al final del año no es correcto.");
    }

    @Test
    void testInicioDeAnio() {
        GregorianCalendar testCal = new GregorianCalendar(2025, Calendar.JANUARY, 1, 0, 0, 0);
        TodaysDate todaysDate = new TodaysDate(testCal);

        todaysDate.printDateAndTime();

        assertEquals("0:0:0", todaysDate.getTime(), "La hora al inicio del año no es correcta.");
        assertEquals(1, todaysDate.getDay(), "El día al inicio del año no es correcto.");
        assertEquals(1, todaysDate.getMonth(), "El mes al inicio del año no es correcto.");
        assertEquals(2025, todaysDate.getYear(), "El año al inicio del año no es correcto.");
    }

    @Test
    void testPrimerDiaDelMes() {
        GregorianCalendar testCal = new GregorianCalendar(2025, Calendar.JULY, 1, 10, 0, 0);
        TodaysDate todaysDate = new TodaysDate(testCal);

        todaysDate.printDateAndTime();

        assertEquals(1, todaysDate.getDay(), "El día debería ser 1 para el primer día del mes.");
        assertEquals(7, todaysDate.getMonth(), "El mes debería ser 7 para julio.");
    }

    @Test
    void testFinDeMes31Dias() {
        GregorianCalendar testCal = new GregorianCalendar(2025, Calendar.MARCH, 31, 18, 0, 0);
        TodaysDate todaysDate = new TodaysDate(testCal);

        todaysDate.printDateAndTime();

        assertEquals(31, todaysDate.getDay(), "El día no es correcto para un mes de 31 días.");
        assertEquals(3, todaysDate.getMonth(), "El mes no es correcto para un mes de 31 días (marzo).");
    }

    @Test
    void testFinDeMes30Dias() {
        GregorianCalendar testCal = new GregorianCalendar(2025, Calendar.APRIL, 30, 18, 0, 0);
        TodaysDate todaysDate = new TodaysDate(testCal);

        todaysDate.printDateAndTime();

        assertEquals(30, todaysDate.getDay(), "El día no es correcto para un mes de 30 días.");
        assertEquals(4, todaysDate.getMonth(), "El mes no es correcto para un mes de 30 días (abril).");
    }

    @Test
    void testFebreroAnioNoBisiesto() {
        GregorianCalendar testCal = new GregorianCalendar(2025, Calendar.FEBRUARY, 28, 12, 0, 0);
        TodaysDate todaysDate = new TodaysDate(testCal);

        todaysDate.printDateAndTime();

        assertEquals(28, todaysDate.getDay(), "El día no es correcto para Febrero en año NO bisiesto.");
        assertEquals(2, todaysDate.getMonth(), "El mes no es correcto para Febrero.");
        assertEquals(2025, todaysDate.getYear(), "El año no es correcto para Febrero en año NO bisiesto.");
    }

    @Test
    void testFebreroAnioBisiesto() {
        GregorianCalendar testCal = new GregorianCalendar(2024, Calendar.FEBRUARY, 29, 12, 0, 0);
        TodaysDate todaysDate = new TodaysDate(testCal);

        todaysDate.printDateAndTime();

        assertEquals(29, todaysDate.getDay(), "El día no es correcto para Febrero en año bisiesto.");
        assertEquals(2, todaysDate.getMonth(), "El mes no es correcto para Febrero.");
        assertEquals(2024, todaysDate.getYear(), "El año no es correcto para Febrero en año bisiesto.");
    }

    @Test
    void testFormatoHoraSinCerosIniciales() {
        GregorianCalendar testCal = new GregorianCalendar(2025, Calendar.JANUARY, 1, 5, 5, 5);
        TodaysDate todaysDate = new TodaysDate(testCal);

        todaysDate.printDateAndTime();

        assertEquals("5:5:5", todaysDate.getTime(), "El formato de la hora no es el esperado (sin ceros iniciales).");
        assertTrue(outContent.toString().contains("Time: 5:5:5"), "La salida de la hora no coincide con el formato esperado.");
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