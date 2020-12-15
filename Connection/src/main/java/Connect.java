import java.sql.*;

public class Connect {
        private Connection connection = null;
        private Statement stmt = null;
        private static Connect instance;
        public static Connect getInstance(){
            if(instance==null){
                instance=new Connect();
            }
            return instance;
        }
        private Connect(){
            try {
                Class.forName("org.sqlite.JDBC");
                connection = DriverManager.getConnection("jdbc:sqlite:/Users/aminhasanzadehmoghadam/Desktop/SE Project/main.sqlite");
                System.out.println("Database has been opened successfully");
                connection.setAutoCommit(false);
            } catch (Exception e) {
                System.err.println(e.getClass().getName() + ": " + e.getMessage());
                System.exit(0);
            }
        }
        public void QueryRecord(String sql){
            try {
                stmt = connection.createStatement();
                stmt.executeUpdate(sql);
                stmt.close();
                connection.commit();
                connection.close();
                System.out.println("Data has been record successfully");
            }
            catch (Exception e){
                System.err.println(e.getClass().getName() + ": " + e.getMessage());
                System.exit(0);
            }
        }
}