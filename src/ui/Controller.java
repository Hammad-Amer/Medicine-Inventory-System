package ui;

import Database.DBhandler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.List;

import Backend.Medicine;

public class Controller {
	 
	private DBhandler dbHandler = DBhandler.getInstance();
    @FXML
    private Button login_button;

    @FXML
    private TextField login_password;

    @FXML
    private TextField login_username;

    @FXML
    private void handleLogin(ActionEvent event) throws IOException {
        String username = login_username.getText().trim();
        String password = login_password.getText().trim();

        if (username.isEmpty() || password.isEmpty()) {
            showAlert("Error", "Please enter both username and password!");
            return;
        }

        try {
            if (dbHandler.validateUser(username, password)) {
                loadMainScreen(event);
            } else {
                showAlert("Login Failed", "Wrong login credentials!");
            }
        } catch (SQLException e) {
            showAlert("Database Error", "Error connecting to the database: " + e.getMessage());
        }
    }

    private void loadMainScreen(ActionEvent event) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/ui/MainScreen.fxml"));
		Parent root = loader.load();

		Scene scene = new Scene(root);
		Stage stage = new Stage();
		stage.setScene(scene);
		stage.setTitle("MEDICINE STORAGE SYSTEM");
		stage.show();

        
        ((Stage)((Node)event.getSource()).getScene().getWindow()).close();
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
    
    
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////
    
    @FXML
    private Button Main_Activitylogs;

    @FXML
    private Button Main_AddNewMeds;

    @FXML
    private Button Main_DeleteExpMeds;

    @FXML
    private Button Main_GetMedsSales;

    @FXML
    private Button Main_SearchMeds;

    @FXML
    private Button Main_TopSellMeds;

    @FXML
    private Button Main_UpdateStock;
    
    @FXML
    private void loadSearchMedsScreen(ActionEvent event) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/ui/SearchMeds.fxml"));
		Parent root = loader.load();
		
		Scene scene = new Scene(root);
		Stage stage = new Stage();
		stage.setScene(scene);
		stage.setTitle("MEDICINE STORAGE SYSTEM");
		stage.show();
		
        
        ((Stage)((Node)event.getSource()).getScene().getWindow()).close();
    }
    
    @FXML
    private void loadAddhMedsScreen(ActionEvent event) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/ui/AddMeds.fxml"));
		Parent root = loader.load();
		
		Scene scene = new Scene(root);
		Stage stage = new Stage();
		stage.setScene(scene);
		stage.setTitle("MEDICINE STORAGE SYSTEM");
		stage.show();
		
        
        ((Stage)((Node)event.getSource()).getScene().getWindow()).close();
    }
    
    @FXML
    private void BackToMainScreen(ActionEvent event) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/ui/MainScreen.fxml"));
		Parent root = loader.load();
		
		Scene scene = new Scene(root);
		Stage stage = new Stage();
		stage.setScene(scene);
		stage.setTitle("MEDICINE STORAGE SYSTEM");
		stage.show();
		
        
        ((Stage)((Node)event.getSource()).getScene().getWindow()).close();
    }
    
    
    
    
    /////////////////////////////////////////////////////////////////
    
    

    @FXML
    private Button SearchMeds_BackButton;

    @FXML
    private ComboBox<String> SearchMeds_Combobox;

    @FXML
    private Text SearchMeds_Desc;

    @FXML
    private Text SearchMeds_ExpDate;

    @FXML
    private Text SearchMeds_ManDate;

    @FXML
    private Text SearchMeds_Name;

    @FXML
    private Text SearchMeds_Price;

    @FXML
    private Text SearchMeds_Stock;
    

    /** MEDICINE SEARCH FUNCTIONALITY **/
    @FXML
    public void initialize() {
        loadMedicineNames();
        if (SearchMeds_Combobox != null) {
            SearchMeds_Combobox.setOnAction(this::fetchMedicineDetails);
        }
    }

    private void loadMedicineNames() {
        List<String> medicines = dbHandler.getAllMedicines();
        if (SearchMeds_Combobox != null) {
            SearchMeds_Combobox.getItems().addAll(medicines);
        }
    }

    private void fetchMedicineDetails(ActionEvent event) {
        String selectedMedicine = SearchMeds_Combobox.getValue();
        if (selectedMedicine != null) {
            Medicine medicine = dbHandler.getMedicineDetails(selectedMedicine);
            if (medicine != null) {
                SearchMeds_Name.setText(medicine.getName());
                SearchMeds_Desc.setText(medicine.getDescription());
                SearchMeds_Price.setText(String.valueOf(medicine.getPrice()) + "Rs.");
                SearchMeds_Stock.setText(String.valueOf(medicine.getStock()));
                SearchMeds_ManDate.setText(medicine.getManDate());
                SearchMeds_ExpDate.setText(medicine.getExpDate());
            }
        }
    }
    
    ///////////////////////////////////////////////////
    

    @FXML
    private Button AddMeds_AddButton;

    @FXML
    private Button AddMeds_back;

    @FXML
    private TextField AddMeds_desc;

    @FXML
    private TextField AddMeds_expdate_date;

    @FXML
    private TextField AddMeds_expdate_month;

    @FXML
    private TextField AddMeds_expdate_year;

    @FXML
    private TextField AddMeds_mandate_date;

    @FXML
    private TextField AddMeds_mandate_month;

    @FXML
    private TextField AddMeds_mandate_year;

    @FXML
    private TextField AddMeds_name;

    @FXML
    private TextField AddMeds_price;

    @FXML
    private TextField AddMeds_stock;
    
    @FXML
    private void handleAddMedicine(ActionEvent event) {
        String name = AddMeds_name.getText().trim();
        String desc = AddMeds_desc.getText().trim();
        String priceText = AddMeds_price.getText().trim();
        String stockText = AddMeds_stock.getText().trim();

        if (name.isEmpty() || desc.isEmpty() || priceText.isEmpty() || stockText.isEmpty()) {
            showAlert("Error", "Please fill in all required fields.");
            return;
        }

        int price, stock;
        try {
            price = Integer.parseInt(priceText);
            stock = Integer.parseInt(stockText);
        } catch (NumberFormatException e) {
            showAlert("Error", "Price and stock must be valid numbers.");
            return;
        }

        String manDate = formatDate(AddMeds_mandate_date, AddMeds_mandate_month, AddMeds_mandate_year);
        String expDate = formatDate(AddMeds_expdate_date, AddMeds_expdate_month, AddMeds_expdate_year);

        if (manDate == null || expDate == null) {
            showAlert("Error", "Invalid manufacturing or expiry date.");
            return;
        }

        boolean success = dbHandler.addMedicine(name, desc, price, stock, manDate, expDate);
        if (success) {
            showAlert("Success", "Medicine added successfully.");
            clearFieldsMed();
        } else {
            showAlert("Error", "Failed to add medicine. Please try again.");
        }
    }

    private String formatDate(TextField dayField, TextField monthField, TextField yearField) {
        String day = dayField.getText().trim();
        String month = monthField.getText().trim();
        String year = yearField.getText().trim();

        if (day.isEmpty() || month.isEmpty() || year.isEmpty()) {
            return null;
        }

        try {
            int d = Integer.parseInt(day);
            int m = Integer.parseInt(month);
            int y = Integer.parseInt(year);

            if (m < 1 || m > 12) 
            {
                showAlert("Error", "Invalid month: " + m + ". Please enter a value between 1 and 12.");
                return null;
            }
            if (d < 1 || d > 31) 
            { 
                showAlert("Error", "Invalid day: " + d + ". Please enter a value between 1 and 31.");
                return null;
            }

         
            LocalDate date = LocalDate.of(y, m, d);
            return date.toString(); // Format YYYY-MM-DD

        } 
        catch (NumberFormatException e) 
        {
            showAlert("Error", "Date must contain only numbers.");
            return null;
        } 
        catch (DateTimeException e) 
        {
            showAlert("Error", "Invalid date: " + day + "/" + month + "/" + year);
            return null;
        }
    }


    private void clearFieldsMed() {
        AddMeds_name.clear();
        AddMeds_desc.clear();
        AddMeds_price.clear();
        AddMeds_stock.clear();
        AddMeds_mandate_date.clear();
        AddMeds_mandate_month.clear();
        AddMeds_mandate_year.clear();
        AddMeds_expdate_date.clear();
        AddMeds_expdate_month.clear();
        AddMeds_expdate_year.clear();
    }
    ///////////////////////////////////////////////////////////////////////////////////////
    
    @FXML
    private Button DelMeds_BackButton;

    @FXML
    private Button DelMeds_BackButton1;

    @FXML
    private ComboBox<?> DelMeds_Combobox;

    @FXML
    private Text DelMeds_Desc;

    @FXML
    private Text DelMeds_ExpDate;

    @FXML
    private Text DelMeds_ManDate;

    @FXML
    private Text DelMeds_Name;

    @FXML
    private Text DelMeds_Price;

    @FXML
    private Text DelMeds_Stock;
    
    
    
}
