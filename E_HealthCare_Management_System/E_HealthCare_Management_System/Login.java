import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;


class Data extends Admin {
    Scanner Sc = new Scanner(System.in);
    Doctor oyy = new Doctor();
    Patient pt = new Patient();

    void Datas() throws SQLException, ClassNotFoundException {
        Database ob = new Database();
        ob.Database();
        try {
            System.out.println(
                    "             =>----------------------------welcome to E-HealthCare-Management-System side----------------------------------       <=");
            System.out.println(" 1. please Enter 1 login  Admin  portal  ");
            System.out.println(" 2. Please Enter 2 login  Doctors portal  ");
            System.out.println(" 3. Please Enter 3 login  patients portal   ");
            int uservalue = Sc.nextInt();
            System.out.println();
            // Your existing code here
            if (uservalue == 1) {
                getadminData();
            } else if (uservalue == 2) {
                oyy.getDoctorData();

            } else if (uservalue == 3) {
                System.out.println("Welcome to patients portal ");
                pt.PatientLogin();
            } else {
                throw new Exception("Invalid Input");
            }
        } catch (Exception e) {
            throw new Exception("Invalid Input");
            // Handle the exception gracefully or terminate the program
        }
        // Continue();
    }

    void getadminData() throws Exception, SQLException {
        System.out.println("Welcome to admin portal please log in A/C ");
        try {
            Database ob = new Database();
            ob.Database(); // Assuming this method establishes a database connection
            String Admin_idname;
            String Admin_password;
            String a = null;
            String p = null;
            // Admin Data get from Database
            try {
                PreparedStatement ps = con.prepareStatement("Select * from healthdetail ");
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    a = rs.getString("idname");
                    p = rs.getString("password");
                }
            } catch (Exception W) {
                W.printStackTrace();
            }
            System.out.println("Enter your Id name: ");
            Sc.nextLine();
            Admin_idname = Sc.nextLine();
            System.out.println("Enter your password");
            Admin_password = Sc.nextLine();
            if (a != null && p != null && Admin_idname.equalsIgnoreCase(a) && Admin_password.equalsIgnoreCase(p)) {
                System.out.println("Login Successfully Admin ");
                getAdminData();
            } else {
                System.out.println("Data does not match, login failed");
                Continue();
            }
        } catch (Exception E) {
            E.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

}

public class Login {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        Data obj = new Data();
        obj.Datas();
    }

}
