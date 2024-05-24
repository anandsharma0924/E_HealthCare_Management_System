import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Doctor extends Database {
    Scanner Sc = new Scanner(System.in);

    void getDoctorData() throws SQLException, ClassNotFoundException {
        try {
            int Id = 0;
            String pass = null;
            int base_ID = 0;
            String base_Pa = null;
            try {
                // user Add
                System.out.println("Welcome Docter please log in your A/c ");
                System.out.println("Enter your Id name : ");
                Id = Sc.nextInt();
                Sc.nextLine();
                System.out.println("Enter your password");
                pass = Sc.nextLine();
                // add database
                PreparedStatement ps = con.prepareStatement("Select * from drtable ");
                ResultSet sr = ps.executeQuery();
                boolean choic = true;
                while (sr.next()) {
                    base_ID = sr.getInt("DrId");
                    base_Pa = sr.getString("Password");
                    if (base_ID != 0 && base_Pa != null && Id == (base_ID) && pass.equalsIgnoreCase(base_Pa)) {
                        System.out.println("Login Successfully Doctor ");
                        System.out.println("---------------------------------------------------------");
                        getUsedData();
                        choic = false;
                    }
                }
                if (choic == true) {
                    System.out.println("Data does not match, login failed");
                }
            } catch (Exception W) {
                W.printStackTrace();
            }
        } catch (Exception W) {
            W.printStackTrace();
        }
    }

    void getUsedData() throws SQLException, ClassNotFoundException {
        System.out.println("1 view profile\n2 view Appointment\n3 Attend  Patient\n4 forget password");

        int userData = Sc.nextInt();
        if (userData == 1) {
            viewprofile();
        } else if (userData == 2) {
            viewAppointment();
        } else if (userData == 3) {
            attendPatient();
        } else {
            System.out.println(" invalid input try again ");
            Continue();
        }
    }

    void viewprofile() throws SQLException, ClassNotFoundException {
        System.out.println("Enter Doctor Id : ");
        int Id = Sc.nextInt();
        PreparedStatement us = con.prepareStatement("Select * from drtable WHERE DrId = ?");
        us.setInt(1, Id);
        ResultSet use = us.executeQuery();
        try {
            while (use.next()) {
                int ID = use.getInt(1);
                String name = use.getString(2);
                String specilization = use.getString(3);
                String email = use.getString(4);
                String number = use.getString(5);
                // String password = use.getString(6);
                if (Id != 0 && Id == ID) {
                    System.out.println(
                            "------------------------------------------------------------------------------------------------------------------------------------");
                    System.out.println("DrID : " + ID + " , Name : " + name + " , Specilization : " + specilization
                            + " , Email : " + email + " , Number : " + number);

                } else {
                    System.out.println("No Such User Found!");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        // Continue();
    }

    void viewAppointment() throws ClassNotFoundException, SQLException {
        try {
            System.out.println("Enter Doctor Id: ");
            int id = Sc.nextInt();
            PreparedStatement us = con.prepareStatement("Select * from Appointment WHERE Drid = ?");
            us.setInt(1, id);
            ResultSet ue = us.executeQuery();
            boolean found = false;
            while (ue.next()) {
                int Dr_id = ue.getInt("Drid");
                String appointmentID = ue.getString("p_id");
                String why = ue.getString("why");
                String name = ue.getString("p_name");
                String gender = ue.getString("P_gender");
                String day = ue.getString("P_day");
                String time = ue.getString("P_time");

                System.out
                        .println("-----------------------------------------------------------------------------------");
                System.out.println("Dr_id : " + Dr_id + ",Patient ID : " + appointmentID + ", why : " + why
                        + ", Patient name : " + name + ", Patient gender : " + gender + ", Patient day : " + day
                        + ", Patient time  : " + time);

                found = true;
            }
            if (!found) {
                System.out.println("No Appointments Found for Doctor with ID: " + id);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    void attendPatient() throws SQLException, ClassNotFoundException {
        System.out.println("Enter Doctor Id: ");
        int id = Sc.nextInt();

        // Prepare the SQL statement to select appointments for the given doctor ID
        PreparedStatement us = con.prepareStatement("SELECT * FROM Appointment WHERE Drid = ?");
        us.setInt(1, id);
        ResultSet ue = us.executeQuery();

        boolean found = false;
        String appointmentID = "";
        String name = "";
        int Dr_id = 0;
        String gender = "";
        String day = "";
        String time = "";
        String why = "";
        while (ue.next()) {
            Dr_id = ue.getInt("Drid");
            appointmentID = ue.getString("p_id");
            why = ue.getString("why");
            name = ue.getString("p_name");
            gender = ue.getString("P_gender");
            day = ue.getString("P_day");
            time = ue.getString("P_time");

            System.out.println("---------------------------------------------------------");
            System.out.println(
                    "Dr_id : " + Dr_id + ",Patient ID: " + appointmentID + ", why : " + why + ", Patient name : " + name
                            + ", Patient gender : " + gender + ", Patient day : " + day + ", Patient time : " + time);

            System.out.println("---------------------------------------------------------");

            found = true;
        }

        if (!found) {
            System.out.println("No Appointments Found for Doctor with ID: " + id);
            return;
        }

        System.out.println("Enter the attended Patient ID: ");
        Sc.nextLine(); // Consume the newline character
        String enteredId = Sc.nextLine();

        if (enteredId.equalsIgnoreCase(appointmentID)) {
            System.out.println("Patient found and attended.");

            System.out.println("Enter the patient's problem: ");
            String problem = Sc.nextLine();

            // Insert into the report table
            String reportQuery = "INSERT INTO report VALUES(?, ?, ?, ?, ?, ?,? ,?)";
            PreparedStatement reportStatement = con.prepareStatement(reportQuery);
            reportStatement.setInt(1, Dr_id);
            reportStatement.setString(2, appointmentID);
            reportStatement.setString(3, name);
            reportStatement.setString(4, why);
            reportStatement.setString(5, problem);
            reportStatement.setString(6, gender);
            reportStatement.setString(7, day);
            reportStatement.setString(8, time);

            int reportInsertResult = reportStatement.executeUpdate();

            if (reportInsertResult > 0) {
                System.out.println("Report for the patient inserted successfully!");

                System.out.println("Do you want to remove the patient? (yes/no)");
                String removeOption = Sc.nextLine();

                if (removeOption.equalsIgnoreCase("yes")) {
                    System.out.println("ram");
                    PreparedStatement removeStatement = con.prepareStatement("DELETE FROM Appointment WHERE p_id = ?");
                    removeStatement.setString(1, enteredId);
                    removeStatement.executeUpdate();
                    System.out.println("Patient removed successfully.");
                } else {
                    Continue();
                }

            } else {
                System.out.println("Error in inserting report data.");
            }

        } else {
            System.out.println("Patient ID does not match any appointment.");
        }
    }

}
