public class GradesTracker {

    public static void main(String[] args) {
        String[] students = {"Jose", "Juan", "Antonio", "Wally", "Emilio"};
        
        int[][] grades = {
            {85, 90, 78},  
            {88, 76, 85}, 
            {83, 81, 84},
            {85, 90, 83},
            {90, 88, 84} 
        };

        System.out.println("Student Name:\tAverage Grade:");
        System.out.println("------------------------------");
        for(int i = 0; i < students.length; i++) {
            int sum = 0;
            for(int j = 0; j < grades[i].length; j++) {
                sum += grades[i][j];
                
            }
            double average = (double) sum / grades[i].length;
            System.out.printf("%s\t\t%.0f%n", students[i], average);
        }
    }
}