package Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Backend.Medicine;

public class DBhandler {

    private static final String DB_URL = "jdbc:mysql://localhost:3306/MEDSTORAGE";
    private static final String DB_USERNAME = "root";
    private static final String DB_PASSWORD = "12345";

    private static DBhandler instance;
    private Connection connection;

    private DBhandler() {
        try {
            connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
        } catch (SQLException e) {
            throw new RuntimeException("Failed to connect to database: " + e.getMessage());
        }
    }

    public static DBhandler getInstance() {
        if (instance == null) {
            synchronized (DBhandler.class) {
                if (instance == null) {
                    instance = new DBhandler();
                }
            }
        }
        return instance;
    }

    public boolean validateUser(String username, String password) throws SQLException {
        String query = "SELECT * FROM User1 WHERE username1 = ? AND password1 = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, username);
            statement.setString(2, password);
            try (ResultSet resultSet = statement.executeQuery()) {
                return resultSet.next(); // If result set has at least one row, user exists
            }
        }
    }
    
    // Fetch all medicine names for ComboBox
    public List<String> getAllMedicines() {
        List<String> medicines = new ArrayList<>();
        String query = "SELECT name1 FROM Meds";

        try (PreparedStatement stmt = connection.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                medicines.add(rs.getString("name1"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return medicines;
    }

    // Fetch medicine details based on name
    public Medicine getMedicineDetails(String medicineName) {
        String query = "SELECT * FROM Meds WHERE name1 = ?";
        Medicine medicine = null;

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
             
            stmt.setString(1, medicineName);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                medicine = new Medicine(
                    rs.getString("name1"),
                    rs.getString("description1"),
                    rs.getInt("price"),
                    rs.getInt("stock"),
                    rs.getDate("ManDate").toString(),
                    rs.getDate("ExpDate").toString()
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return medicine;
    }
    
    public boolean addMedicine(String name, String desc, int price, int stock, String manDate, String expDate) {
        String query = "INSERT INTO Meds (name1, description1, price, stock, ManDate, ExpDate) VALUES (?, ?, ?, ?, ?, ?)";
        
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, name);
            stmt.setString(2, desc);
            stmt.setInt(3, price);
            stmt.setInt(4, stock);
            stmt.setString(5, manDate);
            stmt.setString(6, expDate);

            int rowsInserted = stmt.executeUpdate();
            return rowsInserted > 0;  // Returns true if insertion is successful
        } catch (SQLException e) {
            System.out.println("Error inserting medicine: " + e.getMessage());
            return false;
        }
    }
    
 // Fetch expired medicines
    public List<String> getExpiredMedicines() {
        List<String> expiredMeds = new ArrayList<>();
        String query = "SELECT name1 FROM Meds WHERE ExpDate < CURDATE()";


        try (PreparedStatement stmt = connection.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                expiredMeds.add(rs.getString("name1"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return expiredMeds;
    }

    // Delete medicine from database
    public void removeMedicine(String medicineName) {
    	String query = "DELETE FROM Meds WHERE name1 = ?";

        
        try (PreparedStatement stmt = connection.prepareStatement(query)) {

            stmt.setString(1, medicineName);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public boolean updateMedicineStock(String medicineName, int newStock) {
        String query = "UPDATE Meds SET stock = ? WHERE name1 = ?";

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, newStock);
            stmt.setString(2, medicineName);
            
            int rowsUpdated = stmt.executeUpdate();
            return rowsUpdated > 0;  // Returns true if update is successful
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public int getTotalSales(String medicineName) {
        String query = "SELECT SUM(s.bought) AS totalSales " +
                       "FROM Sales s " +
                       "JOIN Meds m ON s.MID = m.MID " +
                       "WHERE m.name1 = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, medicineName);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("totalSales");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0; 
    }

    public List<String> getTopMedicinesBySales() {
        List<String> topMeds = new ArrayList<>();
        String query = 
            "SELECT m.name1 " +
            "FROM Sales s " +
            "JOIN Meds m ON s.MID = m.MID " +
            "GROUP BY m.MID " +
            "ORDER BY SUM(s.bought) DESC " +
            "LIMIT 10";

        try (PreparedStatement stmt = connection.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                topMeds.add(rs.getString("name1"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return topMeds;
    }
    
    public boolean addLog(String logMessage) {
        String query = "INSERT INTO Logs1 (log) VALUES (?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, logMessage);
            int rowsInserted = stmt.executeUpdate();
            return rowsInserted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<String> getLogsDescending() {
        List<String> logs = new ArrayList<>();
        String query = "SELECT log FROM Logs1 ORDER BY LID DESC"; 

        try (PreparedStatement stmt = connection.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
              
                logs.add(rs.getString("log"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return logs;
    }
    
    


}
