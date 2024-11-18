// package in.ac.adit.pwj.miniproject.parking;

import java.util.Date;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;
import java.io.BufferedReader;

class Vehicle {
    String number, fuel, type, ownerName;
    long Time = System.currentTimeMillis();
    Date currentDate = new Date(); // Renamed to avoid conflict with java.util.Date

    void details() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter vehicle type (car/bike): ");
        type = sc.nextLine();

        if (type.equals("car") || type.equals("bike")) {
            System.out.print("Enter " + type + " Number: ");
            number = sc.nextLine();
            System.out.print("Enter " + type + " fuel type: ");
            fuel = sc.nextLine();
            System.out.print("Enter " + type + " Owner Name: ");
            ownerName = sc.nextLine();
            System.out.println("~~~ You can Park your Vehicle");
            System.out.println("------------------------------------------------");
        } else {
            System.out.println("Invalid vehicle.");
        }
    }
}

class Gateway {
    void enter(String temp_type) {
        if (temp_type.equals("car") || temp_type.equals("bike")) {
            System.out.println("Enter the Parking lot");
        } else {
            System.out.println("Sorry! Exit your Vehicle :(");
        }
    }

    void exit() {
        System.out.println("~~~ Visit us again :)");
    }
}

class Car extends Vehicle {
    void gadi() {
        details();
    }
}

class Admin {
    int login(String username, String password) {
        String user_name = "admin";
        String user_password = "test@123";
        if (user_name.equals(username) && user_password.equals(password)) {
            System.out.println("Login Successful");
            System.out.println("------------------------------------------------");
            return 1;
        } else {
            return 0;
        }
    }
}

public class ExpMyProject {
    public static void main(String[] args) {
        Car car = new Car();
        Gateway gt = new Gateway();
        Admin admin = new Admin();
        Scanner sc = new Scanner(System.in);
        String choice, temp_type, user, password, admin_choice;
        String File_name = "Data.csv";
        int size = 0, max_size = 3;

        try (FileWriter writer = new FileWriter(File_name)) {
            writer.append("Vehicle Owner")
                    .append(",")
                    .append("Vehicle Number")
                    .append(",")
                    .append("Vehicle Type")
                    .append(",")
                    .append("Vehicle Fuel Type")
                    .append(",")
                    .append("Date")
                    .append("\n");
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println(" |   This Mini Project is made by...   |\n |    Rushi Patel(12202120601038)      |\n |    Tanish Patel(12202120601048)     |");
        System.out.println("------------------------------------------------");
        System.out.println("Enter 1 for Admin");
        System.out.println("Enter 2 for User");

        do {
            System.out.print("Enter your Choice: ");
            admin_choice = sc.nextLine();
            if (admin_choice.equalsIgnoreCase("exit")) {
                System.out.println("Exiting...");
                break;
            }

            try {
                int int_admin_choice = Integer.parseInt(admin_choice);
                switch (int_admin_choice) {
                    case 1:
                        System.out.print("Enter username: ");
                        user = sc.nextLine();
                        System.out.print("Enter Password: ");
                        password = sc.nextLine();
                        int value = admin.login(user, password);
                        if (value == 1) {
                            // file management logic
                            String line;
                            String delimiter = ",";

                            try (BufferedReader br = new BufferedReader(new FileReader(File_name))) {
                                line = br.readLine();
                                System.out.println("CSV Header: " + line);

                                while ((line = br.readLine()) != null) {
                                    String[] data = line.split(delimiter);

                                    System.out.println("Owner Name: " + data[0] + ", Vehicle Number: " + data[1] + ", Vehicle Type: " + data[2] + ", Vehicle Fuel: " + data[3] + ", Date: " + data[4]);
                                }
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        } else {
                            System.out.println("Invalid Username or Password");
                            break;
                        }
                        break;

                    case 2:
                        do {
                            System.out.println("------------------------------------------------");
                            System.out.println("Press 1 to Enter");
                            System.out.println("Press 2 to Exit");
                            System.out.println("Press 3 to Check Vacancy");
                            System.out.print("Enter your Choice: ");
                            choice = sc.nextLine();

                            if (choice.equalsIgnoreCase("exit")) {
                                System.out.println("Exiting...");
                                break;
                            }

                            try {
                                int int_choice = Integer.parseInt(choice);

                                switch (int_choice) {
                                    case 1:
                                        if (size != max_size) {
                                            car.details();
                                            temp_type = car.type;
                                            if (temp_type.equals("car") || temp_type.equals("bike")) {
                                                gt.enter(temp_type);
                                                size += 1;
                                            }
                                            try (FileWriter writer = new FileWriter(File_name, true)) {
                                                writer.append(car.ownerName)
                                                        .append(",")
                                                        .append(car.number)
                                                        .append(",")
                                                        .append(car.type)
                                                        .append(",")
                                                        .append(car.fuel)
                                                        .append(",")
                                                        .append(car.currentDate.toString())
                                                        .append("\n");
                                            } catch (IOException e) {
                                                e.printStackTrace();
                                            }
                                        } else {
                                            System.out.println("Parking is full. Please wait or go to another nearby place.");
                                        }
                                        break;

                                    case 2:
                                        if (size != 0) {
                                            gt.exit();
                                            size -= 1;
                                        }
                                        else{
                                            System.out.println("------------------------------------------------");
                                            System.out.println("~~~ !!! No Vehichla inside !!! Invalid choice !!!");
                                        }
                                        break;

                                    case 3:
                                        System.out.println("Available Vacancy is " + (max_size - size));
                                        if ((max_size - size) == 0) {
                                            System.out.println("Sorry, Parking is Full");
                                        }
                                        break;

                                    default:
                                        System.out.println("!!! Invalid choice. Please select 1, 2, or 3. !!!");
                                        break;
                                }
                            } catch (NumberFormatException e) {
                                System.out.println("!!! Please enter a valid number !!!.");
                            }
                        } while (true);
                        break;

                    default:
                        System.out.println("!!! Invalid choice. Please select 1 or 2. !!!");
                        break;
                }
            } catch (NumberFormatException e) {
                System.out.println("!!! Please enter a valid number. !!!");
            }
        } while (true);

        sc.close();
    }
}
