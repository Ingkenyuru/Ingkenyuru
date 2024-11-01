import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BMICalculator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Double> bmiList = new ArrayList<>();
        List<String> kategoriList = new ArrayList<>();
        boolean continueProgram = true;

        while (continueProgram) {
            System.out.println("\n=== Menu Kalkulator BMI ===");
            System.out.println("1. Hitung BMI");
            System.out.println("2. Tampilkan Rangkuman Hasil BMI");
            System.out.println("3. Keluar");
            System.out.print("Pilih opsi (1-3): ");

            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    // Input berat badan
                    double beratBadan = getValidInput(scanner, "Masukkan berat badan (kg): ");

                    // Input tinggi badan
                    double tinggiBadan = getValidInput(scanner, "Masukkan tinggi badan (m): ");

                    // Menghitung BMI
                    double bmi = calculateBMI(beratBadan, tinggiBadan);
                    bmiList.add(bmi);
                    kategoriList.add(kategoriBMI(bmi));

                    // Menampilkan hasil BMI
                    System.out.printf("BMI Anda adalah: %.2f%n", bmi);
                    System.out.println("Kategori berat badan: " + kategoriBMI(bmi));
                    break;

                case 2:
                    // Menampilkan semua hasil BMI dalam tabel
                    tampilkanRangkumanBMI(bmiList, kategoriList);
                    break;

                case 3:
                    continueProgram = false;
                    System.out.println("Terima kasih telah menggunakan Kalkulator BMI!");
                    break;

                default:
                    System.out.println("Pilihan tidak valid. Silakan coba lagi.");
            }
        }

        scanner.close();
    }

    public static double getValidInput(Scanner scanner, String prompt) {
        double input;
        while (true) {
            System.out.print(prompt);
            if (scanner.hasNextDouble()) {
                input = scanner.nextDouble();
                if (input > 0) {
                    break; // Input valid
                } else {
                    System.out.println("Input harus lebih besar dari 0. Silakan coba lagi.");
                }
            } else {
                System.out.println("Input tidak valid. Silakan masukkan angka.");
                scanner.next(); // Clear invalid input
            }
        }
        return input;
    }

    public static double calculateBMI(double weight, double height) {
        return weight / (height * height);
    }

    public static String kategoriBMI(double bmi) {
        if (bmi < 18.5) {
            return "Kekurangan berat badan";
        } else if (bmi < 24.9) {
            return "Berat badan normal";
        } else if (bmi < 29.9) {
            return "Kelebihan berat badan";
        } else {
            return "Kegemukan (Obesitas)";
        }
    }

    public static void tampilkanRangkumanBMI(List<Double> bmiList, List<String> kategoriList) {
        if (bmiList.isEmpty()) {
            System.out.println("Belum ada data BMI yang dihitung.");
            return;
        }

        System.out.printf("%-10s %-10s %-30s%n", "No", "BMI", "Kategori");
        System.out.println("-----------------------------------------------");
        for (int i = 0; i < bmiList.size(); i++) {
            System.out.printf("%-10d %-10.2f %-30s%n", i + 1, bmiList.get(i), kategoriList.get(i));
        }
    }
}
