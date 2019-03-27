import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;


public class VolcanoAnalyzerTests {
    VolcanoAnalyzer va;

    @Before
    public void setUp() throws Exception {
        va = new VolcanoAnalyzer();
        va.loadVolcanoes(Optional.empty());
    }

    @Test
    public void volcanoExists() {
        //Setup
        Volcano classUnderTest = new Volcano();

        //Execute


        //Assert
        Assert.assertNotNull(classUnderTest);

        //Teardown
    }

    @Test
    public void loadVolcanoes() throws IOException, URISyntaxException {
        //Setup
        Integer expected = 804;

        //Execute
        Integer actual = va.numbVolcanoes();

        //Assert
        assertEquals("should have 804 volcanoes", expected, actual);

        //Teardown
    }

    @Test
    public void eruptedInEighties() {
        //Setup
        Integer expected = 40;

        //Execute
        Integer actual = va.eruptedInEighties().size();

        //Assert
        assertEquals("should have 40 volcanoes", expected, actual);

        //Teardown
    }

    @Test
    public void highVEI() {
        //Setup
        List<String> expected = Arrays.asList("Macauley Island", "Kikai", "Masaya", "Pago", "Taal", "Pinatubo", "Long Island", "Black Peak", "St. Helens", "Veniaminof", "Aniakchak", "Santorini", "Taupo", "Pago", "Pinatubo", "Raoul Island", "Okmok", "Apoyeque", "Ambrym", "Bona-Churchill", "Taupo", "Ksudach", "Ilopango", "Rabaul", "Pago", "Bona-Churchill", "Dakataua", "Ceboruco", "Changbaishan", "Quilotoa", "Kuwae", "Bardarbunga", "Huaynaputina", "Long Island", "Tambora", "Krakatau", "Santa Maria", "Novarupta", "Pinatubo");

        //Execute
        List<String> actual = va.highVEI();

        //Assert
        assertEquals("should have 39 volcanoes", expected.size(), actual.size());
        assertEquals("should have the correct volcanoes", expected, actual);

        //Teardown
    }

    @Test
    public void mostDeadly() {
        //Setup
        Volcano expected = new Volcano();
        expected.setYear(450);
        expected.setTsu("");
        expected.setEQ("");
        expected.setName("Ilopango");
        expected.setLocation("El Salvador");
        expected.setCountry("El Salvador");
        expected.setLatitude(13.672);
        expected.setLongitude(-89.053);
        expected.setElevation(450);
        expected.setType("Caldera");
        expected.setVEI(6);
        expected.setAgent("");
        expected.setDEATHS("30000");

        //Execute
        Volcano actual = va.mostDeadly();

        //Assert
        assertEquals("should have correct year", expected.getYear(), actual.getYear());
        assertEquals("should have correct Tsu", expected.getTsu(), actual.getTsu());
        assertEquals("should have correct EQ", expected.getEQ(), actual.getEQ());
        assertEquals("should have correct Name", expected.getName(), actual.getName());
        assertEquals("should have correct Location", expected.getLocation(), actual.getLocation());
        assertEquals("should have correct Country", expected.getCountry(), actual.getCountry());
        assertEquals("should have correct Latitude", expected.getLatitude(), actual.getLatitude());
        assertEquals("should have correct Longitude", expected.getLongitude(), actual.getLongitude());
        assertEquals("should have correct Elevation", expected.getElevation(), actual.getElevation());
        assertEquals("should have correct Type", expected.getType(), actual.getType());
        assertEquals("should have correct VEI", expected.getVEI(), actual.getVEI());
        assertEquals("should have correct Agent", expected.getAgent(), actual.getAgent());
        assertEquals("should have correct DEATHS", expected.getDEATHS(), actual.getDEATHS());
        //Teardown
    }

    @Test
    public void causedTsunami() {
        //Setup
        double expected = 17;

        //Execute
        double actual = va.causedTsunami();

        //Assert
        assertEquals("should be about 17.0 percent", expected, actual, .1);

        //Teardown
    }

    @Test
    public void mostCommonType() {
        //Setup
        String expected = "Stratovolcano";

        //Execute
        String actual = va.mostCommonType();

        //Assert
        assertEquals("should be Stratovolcano", expected, actual);

        //Teardown
    }

    @Test
    public void eruptionsByCountry() {
        //Setup
        int expected1 = 47;
        int expected2 = 189;
        int expected3 = 0;

        //Execute
        long actual1 = va.eruptionsByCountry("United States");
        long actual2 = va.eruptionsByCountry("Indonesia");
        long actual3 = va.eruptionsByCountry("Zimbabwe");

        //Assert
        assertEquals("should be 47 eruptions in United States", expected1, actual1);
        assertEquals("should be 189 eruptions in Indonesia", expected2, actual2);
        assertEquals("should be 0 eruptions in Zimbabwe", expected3, actual3);
        //Teardown
    }

    @Test
    public void averageElevation() {
        //Setup
        double expected = 1993.71;

        //Execute
        double actual = va.averageElevation();

        //Assert
        assertEquals("should be about 1993.71", expected, actual, .1);

        //Teardown
    }

    @Test
    public void volcanoTypes() {
        //Setup
        List<String> expected = Arrays.asList("Caldera", "Stratovolcano", "Complex volcano", "Shield volcano", "Pyroclastic shield", "Volcanic field", "Lava dome", "Subglacial volcano", "Crater rows", "Tuff cone", "Fissure vent", "Pyroclastic cone", "Submarine volcano", "Lava cone", "Pumice cone", "Mud volcano", "Cinder cone", "Compound volcano", "Maar");
        //Execute
        List<String> actual = va.volcanoTypes();

        //Assert
        assertEquals("should have 19 volcano types", expected.size(), actual.size());
        assertEquals("should have the correct volcano types",expected, actual);

        //Teardown
    }

    @Test
    public void percentNorth() {
        //Setup
        double expected = 64.55;

        //Execute
        double actual = va.percentNorth();

        //Assert
        assertEquals("should be about 64.55 percent", expected, actual, .1);

        //Teardown
    }

    @Test
    public void manyFilters() {
        //Setup
        String[] expected = { "Galunggung", "Hudson, Cerro" };
        //Execute
        String[] actual = va.manyFilters();

        //Assert
        assertEquals("should have 3 volcanoes", expected.length, actual.length);
        assertArrayEquals("should have the correct volcanoes",expected, actual);

        //Teardown
    }

    @Test
    public void elevatedVolcanoes() {
        //Setup
        String[] expected = { "Tungurahua", "Cotopaxi", "Bona-Churchill", "Bona-Churchill", "Ruiz", "Tungurahua", "Cotopaxi", "Cotopaxi", "Cotopaxi", "Cotopaxi", "Sabancaya", "Ararat", "Ruiz", "Cotopaxi", "Tungurahua", "Tungurahua", "Popocatepetl", "Sangay", "Ruiz", "Sabancaya", "Huila", "Popocatepetl", "Tungurahua", "Tungurahua", "Tungurahua", "Huila", "Tungurahua", "Huila", "Tungurahua", "Tungurahua", "Tungurahua", "Ubinas" };

        //Execute
        String[] actual = va.elevatedVolcanoes(4990);

        //Assert
        assertEquals("should have 32 volcanoes above 4990", expected.length, actual.length);
        assertArrayEquals("should have the correct volcanoes",expected, actual);

        //Teardown
    }

    @Test
    public void topAgentsOfDeath() {
        //Setup
        String[] expected = { "P", "M", "W", "A", "I" };

        //Execute
        String[] actual = va.topAgentsOfDeath();

        //Assert
        assertEquals("Agents of Death count", expected.length, actual.length);
        assertEquals("Agents of Death", expected, actual);

        //Teardown
    }
}
