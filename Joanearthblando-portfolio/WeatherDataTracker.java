public class WeatherDataTracker {
    public static void main(String[] args) {
        
        double[][] temperatures = {
            {25.1, 24.2, 23.4, 30.2, 26.2, 21.4, 22.3},
            {23.1, 24.5, 21.3, 25.5, 23.4, 26.7, 29.9},
            {21.1, 21.4, 23.4, 25.5, 24.1, 23.2, 18.2}
        };

        String[] cities = {"City A:", "City B:", "City C:"};
        
        double highestAverage = Double.MIN_VALUE;

        
        for (int i = 0; i < 3; i++) {
            System.out.println("\tMonday\t       Tuesday\t      Wednesday\t       Thursday\t        Friday\t       Saturday\t        Sunday");
            System.out.print(cities[i] + "\t");
            double City = Double.MIN_VALUE;
            double sum = 0;
            for (int j = 0; j < 7; j++) {
                System.out.print(temperatures[i][j] + "°C" + "\t" + "\t");
                sum += temperatures[i][j];
                City = Math.max(City, temperatures[i][j]);
            }
            double average = sum / 7;
            System.out.println();

            highestAverage = Math.max(highestAverage, average);
            System.out.println("\tHighest Temperature: " + String.format("%.1f°C", City));
            System.out.printf("\tWeekly Average Temperature:" + " " + "%.1f°C\n", average);
        }      
    }
}
