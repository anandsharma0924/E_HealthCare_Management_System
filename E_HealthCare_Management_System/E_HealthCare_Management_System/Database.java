import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

public class Database {
    static Connection con;

    void Database() throws ClassNotFoundException, SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ health", "root", "!@#$%^&*()");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    void Continue() throws ClassNotFoundException, SQLException {
        Data object = new Data();
        String input;
        try (Scanner Sc = new Scanner(System.in)) {
            try {
                System.out.println("---------------------------------------------------------");
                System.out.println("Do you want continue 'yes' and 'no' ");
                System.out.println("---------------------------------------------------------");

                input = Sc.nextLine();
                if (input.equalsIgnoreCase("yes") || input.equalsIgnoreCase("YES")) {
                    object.Datas();
                } else if (input.equalsIgnoreCase("no") || input.equalsIgnoreCase("NO")) {
                    System.out.println("thankyou");
                }
            } catch (Exception E) {
                // E.printStackTrace();
                throw new Exception("Invalid Input");

            }
        }
    }

    void Continuemain() throws ClassNotFoundException, SQLException {
        Admin object1 = new Admin();
        String input;
        try (Scanner Sc = new Scanner(System.in)) {
            try {
                System.out.println("---------------------------------------------------------");
                System.out.println("Do you want continue 'yes' and 'no' ");
                System.out.println("---------------------------------------------------------");

                input = Sc.nextLine();
                if (input.equalsIgnoreCase("yes") || input.equalsIgnoreCase("YES")) {
                    object1.getAdminData();
                } else if (input.equalsIgnoreCase("no") || input.equalsIgnoreCase("NO")) {
                    System.out.println("thankyou");
                }
            } catch (Exception E) {
                throw new Exception("Invalid Input");

            }
        }
    }

    void ContinuePatient() throws ClassNotFoundException, SQLException {
        Patient object2 = new Patient();
        String input;
        try (Scanner Sc = new Scanner(System.in)) {
            try {
                System.out.println("---------------------------------------------------------");
                System.out.println("Do you want continue 'yes' and 'no' ");
                System.out.println("---------------------------------------------------------");

                input = Sc.nextLine();
                if (input.equalsIgnoreCase("yes") || input.equalsIgnoreCase("YES")) {
                    object2.PatientLogin();
                } else if (input.equalsIgnoreCase("no") || input.equalsIgnoreCase("NO")) {
                    System.out.println("thankyou");
                }
            } catch (Exception E) {
                throw new Exception("Invalid Input");

            }
        }
    }

}
