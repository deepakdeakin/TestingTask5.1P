package sit707_week5;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class WeatherControllerTest {

    private static WeatherController wController;
    private static double[] hourlyTemperatures;

    @BeforeClass
    public static void setUpClass() {
        wController = WeatherController.getInstance();
        int totalHours = wController.getTotalHours();
        hourlyTemperatures = new double[totalHours];
        for (int i = 0; i < totalHours; i++) {
            hourlyTemperatures[i] = wController.getTemperatureForHour(i + 1);
        }
    }

    @AfterClass
    public static void tearDownClass() {
        wController.close();
    }

    @Test
    public void testStudentIdentity() {
        String studentId = "220007878";
        assertEquals("Student ID is incorrect", "220007878", studentId);
    }

    @Test
    public void testStudentName() {
        String studentName = "Deepak Kumar Khatri";
        assertEquals("Student name is incorrect", "Deepak Kumar Khatri", studentName);
    }

    @Test
    public void testTemperatureMin() {
        double minTemperature = findMinTemperature();
        assertEquals("Minimum temperature does not match", minTemperature, wController.getTemperatureMinFromCache(), 0);
    }

    @Test
    public void testTemperatureMax() {
        double maxTemperature = findMaxTemperature();
        assertEquals("Maximum temperature does not match", maxTemperature, wController.getTemperatureMaxFromCache(), 0);
    }

    @Test
    public void testTemperatureAverage() {
        double averageTemperature = calculateAverageTemperature();
        assertEquals("Average temperature does not match", averageTemperature, wController.getTemperatureAverageFromCache(), 0);
    }

    private double findMinTemperature() {
        double minTemperature = Double.MAX_VALUE;
        for (double temperature : hourlyTemperatures) {
            if (temperature < minTemperature) {
                minTemperature = temperature;
            }
        }
        return minTemperature;
    }

    private double findMaxTemperature() {
        double maxTemperature = Double.MIN_VALUE;
        for (double temperature : hourlyTemperatures) {
            if (temperature > maxTemperature) {
                maxTemperature = temperature;
            }
        }
        return maxTemperature;
    }

    private double calculateAverageTemperature() {
        double sumTemperature = 0;
        for (double temperature : hourlyTemperatures) {
            sumTemperature += temperature;
        }
        return sumTemperature / hourlyTemperatures.length;
    }

    @Test
    public void testTemperaturePersist() {
        /*
         * Remove below comments ONLY for 5.3C task.
         */
//        System.out.println("+++ testTemperaturePersist +++");
//        
//        // Initialise controller
//        WeatherController wController = WeatherController.getInstance();
//        
//        String persistTime = wController.persistTemperature(10, 19.5);
//        String now = new SimpleDateFormat("H:m:s").format(new Date());
//        System.out.println("Persist time: " + persistTime + ", now: " + now);
//        
//        Assert.assertTrue(persistTime.equals(now));
//        
//        wController.close();
    }
}
