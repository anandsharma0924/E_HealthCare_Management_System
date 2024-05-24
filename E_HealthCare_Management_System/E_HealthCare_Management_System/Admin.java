import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.Scanner;

public class Admin extends Database {
    Scanner Sc = new Scanner(System.in);

    void getAdminData() throws ClassNotFoundException, SQLException {
        try (Scanner Sc = new Scanner(System.in)) {
            int val;
            System.out.println();
            System.out.println("please Enter 1  view doctors list ");
            System.out.println("Please Enter 2  to add doctor");
            System.out.println("please Enter 3  Dotcor Remove ");
            System.out.println("please Enter 4  view Patient list ");
            System.out.println("please Enter 5  view feedback ");
            System.out.println("please Enter 6  view Reports  ");
            System.out.println("please Enter 7  logout ");

            val = Sc.nextInt();
            if (val == 1) {
                viewDoctorList();
            } else if (val == 2) {

                AddDoctor();
            } else if (val == 3) {
                DrRemove();
            } else if (val == 4) {
                PatientList();
            } else if (val == 5) {
                System.out.println("view Appointments");
                // Appointment.viewAppointments();
            } else if (val == 6) {
                Reports();
            } else if (val == 7) {
                logout();
            } else {
                System.out.println("Invalid Option !!! Please Enter Again ");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    void viewDoctorList() throws ClassNotFoundException, SQLException {
        String DrName;
        int DrId;
        String specialization;
        String Dremail;
        String m_number;
        try {
            PreparedStatement ps = con.prepareStatement("Select * from Drtable ");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                DrId = rs.getInt(1);
                DrName = rs.getString(2);
                specialization = rs.getString(3);
                Dremail = rs.getString(4);
                m_number = rs.getString(5);

                System.out.println("---------------------------------------------------------");
                System.out.println("DrId   =" + DrId);
                System.out.println("DrName = " + DrName);
                System.out.println("specialization = " + specialization);
                System.out.println("Dremail  = " + Dremail);
                System.out.println("m_number  = " + m_number);
            }
        } catch (Exception W) {
            W.printStackTrace();
        }
        System.out.println("Success...");
        Continuemain();

    }

    void AddDoctor() throws Exception, ClassNotFoundException, SQLException {

        try {
            System.out.println("Add a Doctor ");

            System.out.println("Enter a Doctor Id : ");
            int DrId = Sc.nextInt();
            // Sc.nextInt();

            System.out.println("Enter a Doctor name : ");
            Sc.nextLine();
            String dname = Sc.nextLine();
            // Sc.nextInt();

            System.out.println("Enter the specialization of doctor: ");
            String specilization = Sc.nextLine();
            // Sc.nextInt();

            System.out.println("Enter the Doctor Email: ");
            String Dremail = Sc.nextLine();
            // Sc.nextInt();

            System.out.println("Enter the Doctor mobile number : ");
            String m_number = Sc.nextLine();
            System.out.println("Enter the Doctor password : ");
            String Password = Sc.nextLine();
            // Sc.nextInt();

            String query1 = "INSERT INTO drtable VALUES(?,?,?,?,?,?)";
            PreparedStatement join = con.prepareStatement(query1);
            join.setInt(1, DrId);
            join.setString(2, dname);
            join.setString(3, specilization);
            join.setString(4, Dremail);
            join.setString(5, m_number);
            join.setString(6, Password);
            int i = join.executeUpdate();
            if (i > 0) {
                System.out.println(" Doctor Add  successfully!");
                Continuemain();
            } else {
                System.out.println("Error in inserting data.");
            }
        } catch (SQLIntegrityConstraintViolationException e) {
            System.out.println("This id already exists!");

            // System.out.println("Data added successfully!");
        }
        Continuemain();
    }

    void DrRemove() throws SQLException, ClassNotFoundException {
        try {
            System.out.println("please Enter the Doctor Id for Remove");
            int r_id = Sc.nextInt();

            PreparedStatement in = con.prepareStatement("delete from drtable where DrId=? ");
            in.setInt(1, r_id);
            if (in.executeUpdate() > 0) {
                System.out.println("Doctor Removed Successfully");
                Continuemain();
            } else {
                System.out.println("note remove");
            }
        } catch (Exception E) {
            System.out.println("Invalid Input");
        }
        Continuemain();
    }

    void PatientList() throws ClassNotFoundException, SQLException {
        System.out.println("\nPatient List\n");
        String query = "select * from Appointment";
        try {
            PreparedStatement so = con.prepareStatement(query);
            ResultSet sach = so.executeQuery();
            while (sach.next()) {
                int Drid = sach.getInt("Drid");
                int p_id = sach.getInt("p_id");
                String why = sach.getString("why");
                String P_name = sach.getString("P_name");
                String P_age = sach.getString("P_age");
                String P_gender = sach.getString("P_gender");
                String P_day = sach.getString("P_day");
                String P_time = sach.getString("P_time");

                System.out.println("---------------------------------------------------------");

                System.out.println(Drid + "   " + p_id + "  " + why + "   " + P_name + "   " + P_age + "  " + P_gender
                        + "  " + P_day + "  " + P_time);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        Continuemain();
    }

    void Reports() throws SQLException, ClassNotFoundException {
        PreparedStatement ps = con.prepareStatement("Select * from report ");
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            int DrId = rs.getInt(1);
            String P_id = rs.getString(2);
            String P_name = rs.getString(3);
            String why = rs.getString(4);
            String problem = rs.getString(5);
            String gender = rs.getString(6);
            String day = rs.getString(7);
            String time = rs.getString(8);

            System.out.println("-----------------------------------------");
            System.out.println("DrId : " + DrId);
            System.out.println("P_id : " + P_id);
            System.out.println("P_name : " + P_name);
            System.out.println("why come " + why);
            System.out.println("problem : " + problem);
            System.out.println("gender : " + gender);
            System.out.println("Appointments day : " + day);
            System.out.println("Appointments time : " + time);
        }
        Continue();
    }

    void logout() throws ClassNotFoundException, SQLException {
        System.out.println("Logging out...");
        Continue();
    }

}
