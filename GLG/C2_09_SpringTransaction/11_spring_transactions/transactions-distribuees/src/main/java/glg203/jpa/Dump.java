package glg203.jpa;

import java.sql.*;

/**
 * Dump des deux bases de données derby utilisées pour montrer leur contenu.
 * <p>Application java simple, non Spring boot.
 */
public class Dump {

    public static void dumpDB(String dbName, int dbPort) throws SQLException {
        String user = dbName;
        String password = dbName;
        String url = "jdbc:derby://localhost:"+ dbPort + "/"+ dbName;
        System.out.println("**********************************************");
        System.out.println("* Contenu de la base "+ dbName);        
        try (
            Connection connection = DriverManager.getConnection(url, user, password);
            Statement st = connection.createStatement();
            ResultSet res = st.executeQuery("select * from Compte");
        ) {
            while (res.next()) {
                String numero = res.getString("numero");
                String client = res.getString("client");
                double solde = res.getDouble("solde");
                System.out.println("* Client "+ numero + " "+ client+ " solde:" + solde);
            }
        }
    }

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        // Chargement du driver
        Class.forName("org.apache.derby.jdbc.ClientDriver");
        System.out.println();
        dumpDB("db1", 50033);
        dumpDB("db2", 50034);
        System.out.println("**********************************************");
        System.out.println();
    }
}