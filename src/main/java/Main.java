import com.example.likeherotozero.entity.Co2EmissionsEntity;
import jakarta.persistence.*;
import org.apache.commons.csv.CSVFormat;
import org.mindrot.jbcrypt.BCrypt;

import java.io.FileReader;
import java.io.Reader;
import java.sql.*;
import java.util.Properties;

import org.apache.commons.csv.CSVRecord;

import java.util.List;

public class Main {
    public static void main(String[] args) {
//        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("default");
//        EntityManager entityManager = entityManagerFactory.createEntityManager();
//        EntityTransaction transaction = entityManager.getTransaction();
//
//        try {
//            transaction.begin();
//
//            Query q = entityManager.createQuery("select e from Co2EmissionsEntity e where e.amountValue != 0");
//            q.setMaxResults(10);
//            List<Co2EmissionsEntity> resultList = q.getResultList();
//            for (Co2EmissionsEntity entity : resultList) {
//                System.out.println(entity.toString());
//            }
//
//            transaction.commit();
//        } finally {
//            if (transaction.isActive()) {
//                transaction.rollback();
//            }
//            entityManager.close();
//            entityManagerFactory.close();
//        }
        String[] passwords = {"passwort123", "sicherpasswort456", "adminpass789"};
        for (String password : passwords) {
            String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());
            System.out.println(hashedPassword);
        }


        String url = "jdbc:mysql://localhost:3306/like_hero_to_zero";
        Properties properties = new Properties();
        properties.put("user", "root");
        properties.put("password", "Dunci2019");

        String csvFile = "/Users/lisafranz/Desktop/CO2_emissions_transposed_data (1) (2) 2.csv";
        String query = "INSERT INTO co2_emissions (country_code, date, amount_value, user_id) VALUES (?, ?, ?, NULL)";
        String fetchCountryCodeQuery = "SELECT country_code FROM country WHERE country_name = ?";

        try (
                Connection conn = DriverManager.getConnection(url, properties);
                Reader in = new FileReader(csvFile);
                PreparedStatement stmt = conn.prepareStatement(query);
                PreparedStatement fetchCountryCodeStmt = conn.prepareStatement(fetchCountryCodeQuery);
        ) {
            Iterable<CSVRecord> records = CSVFormat.DEFAULT
                    .withFirstRecordAsHeader()
                    .parse(in);
            int lineNumber = 1;

            for (CSVRecord record : records) {
                String country_name = record.get("country_name");
                //String country_code = record.get("country_code");
                String dateStr = record.get("date");
                Integer date = null;
                try {
                    date = Integer.parseInt(record.get("date"));
                } catch (NumberFormatException e) {
                    System.err.println("Fehler beim Parsen von date in Zeile " + lineNumber + ": " + dateStr );
                    lineNumber++;
                    continue;
                }

                //double amount_value = Double.parseDouble(record.get("amount_value"));
                String amountValueStr = record.get("amount_value");
                Double amount_value = null;

                fetchCountryCodeStmt.setString(1, country_name);
                ResultSet rs = fetchCountryCodeStmt.executeQuery();
                String country_code = null;
                if(rs.next()) {
                    country_code = rs.getString("country_code");
                }

                if(amountValueStr != null && !amountValueStr.isEmpty())
                {
                    try {
                        amount_value = Double.parseDouble(amountValueStr);
                    } catch (NumberFormatException e) {
                        System.err.println("Fehler beim Parsen von amount_value" + amountValueStr);
                    }
                }

                stmt.setString(1, country_code);
                stmt.setInt(2, date);
                if(amount_value != null) {
                stmt.setDouble(3, amount_value);
                } else {
                    stmt.setNull(3, Types.DOUBLE);
                }
                //stmt.setNull(4, Types.INTEGER);
                stmt.addBatch();
                lineNumber++;
            }

            stmt.executeBatch();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
