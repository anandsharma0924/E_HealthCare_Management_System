import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Patient extends Database {
    Scanner scanner = new Scanner(System.in);

    void PatientLogin() throws SQLException, ClassNotFoundException {
        System.out.println("Enter 1 to Register");
        System.out.println("Enter 2 to login");
        int choice = scanner.nextInt();
        if (choice == 1) {
            registerPatient();
        } else if (choice == 2) {
            loginPatient();
        } else {
            throw new IllegalArgumentException("Invalid Input");
        }
        ContinuePatient();
    }

    void registerPatient() throws SQLException, ClassNotFoundException {
        try {
            System.out.println("Enter the Patient Id: ");
            int patientId = scanner.nextInt();
            scanner.nextLine();

            System.out.println("Enter the Patient Name: ");
            String patientName = scanner.nextLine();

            System.out.println("Enter the Patient age: ");
            String patientAge = scanner.nextLine();

            System.out.println("Enter the Patient number: ");
            String patientNumber = scanner.nextLine();

            System.out.println("Enter the Patient address: ");
            String patientAddress = scanner.nextLine();

            System.out.println("Enter the 4 Digit password: ");
            String password = scanner.nextLine();

            String query = "INSERT INTO Patienttable VALUES(?,?,?,?,?,?)";
            PreparedStatement preparedStatement = con.prepareStatement(query);
            preparedStatement.setInt(1, patientId);
            preparedStatement.setString(2, patientName);
            preparedStatement.setString(3, patientAge);
            preparedStatement.setString(4, patientNumber);
            preparedStatement.setString(5, patientAddress);
            preparedStatement.setString(6, password);
            int i = preparedStatement.executeUpdate();
            if (i > 0) {
                System.out.println("Register Patient successfully!");
            } else {
                System.out.println("Error in inserting data.");
            }
            loginPatient();
        } catch (Exception e) {
            e.printStackTrace();
        }
        ContinuePatient();
    }

    void loginPatient() throws SQLException, ClassNotFoundException {
        System.out.println("Login to your account");
        int patientId = 0;
        String patientPassword = null;

        System.out.println("Enter the Patient Id");
        int userId = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Enter your password");
        String userPassword = scanner.nextLine();

        // add database
        PreparedStatement ps = con.prepareStatement("SELECT * FROM Patienttable WHERE P_id = ?");
        ps.setInt(1, userId);
        ResultSet rs = ps.executeQuery();
        boolean choice = true;

        while (rs.next()) {
            patientId = rs.getInt("P_id");
            patientPassword = rs.getString("Password");

            if (patientId != 0 && patientPassword != null &&
                    userId == patientId && userPassword.equalsIgnoreCase(patientPassword)) {
                System.out.println(patientId);
                System.out.println(patientPassword);
                System.out.println("---------------------------------------------------------");
                System.out.println("Login Successfully");
                System.out.println("---------------------------------------------------------");

                getPatient();
                choice = false;
            }
        }

        if (choice) {
            System.out.println("Data does not match, login failed");
        }
        ContinuePatient();

    }

    void getPatient() throws SQLException, ClassNotFoundException {
        int select;
        System.out.println(" 1 View Profile ");
        System.out.println(" 2 Book Appointment");
        System.out.println(" 3 View Report ");
        System.out.println(" 4 View Appointment");
        System.out.println(" 5 Give Feedback to Doctor");
        System.out.println(" 6 Payment");
        System.out.println(" 7 Logout");
        select = scanner.nextInt();
        if (select == 1) {
            viewProfile();
        } else if (select == 2) {
            bookAppointment();
        } else if (select == 3) {
            // viewReport();
        } else if (select == 4) {
            viewAppointment();
        } else if (select == 5) {
            // giveFeedback();
        } else if (select == 6) {
            // payment();
        } else if (select == 7) {
            // logout();
        } else {
            System.out.println("Invalid Option. Please try again: ");
        }
    }

    void viewProfile() throws SQLException, ClassNotFoundException {
        System.out.println("Enter your Id");
        int userId = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Enter your password");
        String userPassword = scanner.nextLine();

        PreparedStatement ps = con.prepareStatement("SELECT * FROM Patienttable WHERE P_id = ?");
        ps.setInt(1, userId);
        ResultSet rs = ps.executeQuery();
        boolean choice = true;

        while (rs.next()) {
            int Id = rs.getInt("P_id");
            String name = rs.getString("p_name");
            String age = rs.getString("p_age");
            String number = rs.getString("p_number");
            String address = rs.getString("p_address");
            String pass = rs.getString("Password");

            if (userId != 0 && userPassword != null && userId == Id && userPassword.equalsIgnoreCase(pass)) {
                System.out.println("---------------------------------------------------------");
                System.out.println(" ID      : " + Id);
                System.out.println(" Name    : " + name);
                System.out.println(" Number  : " + number);
                System.out.println(" Age     : " + age);
                System.out.println(" Address : " + address);
                System.out.println("---------------------------------------------------------");
                choice = false;
            }
        }
        if (choice) {
            System.out.println("Data does not match, login failed");
        }
        ContinuePatient();
    }

    void bookAppointment() throws SQLException, ClassNotFoundException {
        System.out.println("Book your Appointment");
        PreparedStatement su = con.prepareStatement("Select * from drtable ");
        ResultSet rs = su.executeQuery();
        // boolean choice = true;

        while (rs.next()) {
            int Id = rs.getInt("DrId");
            String name = rs.getString("DrName");
            String specilization = rs.getString("specilization");
            String Dremail = rs.getString("Dremail");
            String m_number = rs.getString("m_number");
            // String Password = rs.getString("Password");

            System.out.println("----------------------------------------------------------------------------------");
            System.out.println("DrId " + "   DeName   " + "  specilization " + "   Dremail    " + "        m_number  ");
            System.out.println(
                    Id + "    " + name + "         " + specilization + "         " + Dremail + "      " + m_number);
        }

        System.out.println("Enter Doctor Id book your Appointment : ");
        int userin_Id = scanner.nextInt();
        ResultSet oi = su.executeQuery();

        while (oi.next()) {
            try {
                int youId = oi.getInt("DrId");
                if (userin_Id == youId) {
                    // System.out.println("Enter Doctor Id : ");
                    // int Drid = scanner.nextInt();

                    System.out.println("Enter your 2 dist Id : ");
                    int p_id = scanner.nextInt();
                    scanner.nextLine();
                    System.out.println("why ");
                    String why = scanner.nextLine();

                    System.out.println("Enter your Name : ");
                    String P_name = scanner.nextLine();

                    System.out.println("Enter your Age : ");
                    String P_age = scanner.nextLine();

                    System.out.println("Enter your gender M/F");
                    String P_gender = scanner.nextLine();

                    System.out.println("Enter Appointment Day");
                    String P_day = scanner.nextLine();

                    System.out.println("Enter Appointment Time ");
                    String P_time = scanner.nextLine();

                    String Query = "INSERT INTO Appointment VALUES(?,?,?,?,?,?,?,?)";
                    PreparedStatement join = con.prepareStatement(Query);
                    join.setInt(1, userin_Id);
                    join.setInt(2, p_id);
                    join.setString(3, why);
                    join.setString(4, P_name);
                    join.setString(5, P_age);
                    join.setString(6, P_gender);
                    join.setString(7, P_day);
                    join.setString(8, P_time);
                    int i = join.executeUpdate();
                    if (i > 0) {
                        System.out.println(" Appointment Book successfully!");
                        ContinuePatient();
                    } else {
                        System.out.println("Error in inserting data.");
                    }
                }
            } catch (Exception d) {
                d.printStackTrace();
            }
        }
        ContinuePatient();
    }

    void viewAppointment() throws SQLException, ClassNotFoundException {
        try {
            PreparedStatement q = con.prepareStatement("SELECT * FROM Appointment WHERE P_id = ?");
            System.out.println("Enter patient Id");
            int userId = scanner.nextInt();
            boolean choice = true;

            q.setInt(1, userId);
            ResultSet us = q.executeQuery();
            while (us.next())

            {
                int Drid = us.getInt("Drid");
                int Id = us.getInt("P_id");
                String why = us.getString("why");
                String name = us.getString("p_name");
                String age = us.getString("p_age");
                String gender = us.getString("p_gender");
                String day = us.getString("p_day");
                String time = us.getString("P_time");
                // String pass = rs.getString("Password");
                if (userId != 0 && userId == Id) {
                    System.out.println("---------------------------------------------------------");
                    System.out.println(" Drid   : " + Drid);
                    System.out.println(" ID     : " + Id);
                    System.out.println(" why    : " + why);
                    System.out.println(" Name   : " + name);
                    System.out.println(" Age    : " + age);
                    System.out.println(" gender : " + gender);
                    System.out.println(" day    : " + day);
                    System.out.println(" time   : " + time);
                    System.out.println("---------------------------------------------------------");
                    choice = false;
                }
            }
            if (choice) {
                System.out.println("Data does not match, login failed");
            }
            ContinuePatient();

        } catch (

        Exception e) {
            System.out.println(e);
        }
    }
}