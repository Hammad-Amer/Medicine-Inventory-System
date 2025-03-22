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
import javafx.scene.control.TextArea;
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
	
	 private static String user;
	 
	    public static String getUser() {
	        return user;
	    }

	
	    public static void setUser(String newUser) {
	        user = newUser;
	    }
	
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
            	
            	setUser(username);
                dbHandler.addLog(user + "   Logged into the application ");
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
		
        loadMedicineNames();
        if (SearchMeds_Combobox != null) {
            SearchMeds_Combobox.setOnAction(this::fetchMedicineDetails);
        }
        
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
    private void loadDelExpMedsScreen(ActionEvent event) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/ui/DelExpMeds.fxml"));
		Parent root = loader.load();
		
		Scene scene = new Scene(root);
		Stage stage = new Stage();
		stage.setScene(scene);
		stage.setTitle("MEDICINE STORAGE SYSTEM");
		stage.show();
		
        
        ((Stage)((Node)event.getSource()).getScene().getWindow()).close();
    }
    
    @FXML
    private void loadStockMedsScreen(ActionEvent event) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/ui/UpdateMedStock.fxml"));
		Parent root = loader.load();
		
		Scene scene = new Scene(root);
		Stage stage = new Stage();
		stage.setScene(scene);
		stage.setTitle("MEDICINE STORAGE SYSTEM");
		stage.show();
		
        
        ((Stage)((Node)event.getSource()).getScene().getWindow()).close();
    }
    
    @FXML
    private void loadGetMedsSalesScreen(ActionEvent event) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/ui/GetMedsSales.fxml"));
		Parent root = loader.load();
		
		Scene scene = new Scene(root);
		Stage stage = new Stage();
		stage.setScene(scene);
		stage.setTitle("MEDICINE STORAGE SYSTEM");
		stage.show();
		
        
        ((Stage)((Node)event.getSource()).getScene().getWindow()).close();
    }
    
    @FXML
    private void loadTopMedsSalesScreen(ActionEvent event) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/ui/TopMedsSales.fxml"));
		Parent root = loader.load();
		
		Scene scene = new Scene(root);
		Stage stage = new Stage();
		stage.setScene(scene);
		stage.setTitle("MEDICINE STORAGE SYSTEM");
		stage.show();
		
        
        ((Stage)((Node)event.getSource()).getScene().getWindow()).close();
    }
    
    @FXML
    private void loaduserLogsScreen(ActionEvent event) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/ui/Logs.fxml"));
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
        if (DelMeds_Combobox != null) { 
            loadExpiredMedicines();  
            DelMeds_Combobox.setOnAction(event -> handleComboBoxSelection()); 
        }
        if (StcokMeds_Combobox != null) {
            initializeStockUI();
        }
        
        if (GetMedsSales_Combobox != null) {
        	initializeSalesUI();
        }
        
        if (TopMedsSales_Combobox != null) {
            initializeTopMedsUI();
        }
        
        if(LogsMeds_maintextarea != null) {
        	 fillLogsTextArea();
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
        	
    
            dbHandler.addLog(user + "     Added medicine: " + name);
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
    private ComboBox<String> DelMeds_Combobox;

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
    
    private void loadExpiredMedicines() {
    	DelMeds_Combobox.getItems().setAll(dbHandler.getExpiredMedicines());
    }

    @FXML
    private void handleRemoveButton() {
        String selectedMedicine = DelMeds_Combobox.getValue();
        
        if (selectedMedicine != null) {
            dbHandler.removeMedicine(selectedMedicine);
            showAlert("Success", "Medicine Removed Successfully!");
        
            dbHandler.addLog(user + "	Deleted medicine  "+selectedMedicine);
            loadExpiredMedicines(); 
            clearMedicineDetails();
        } else {
            showAlert("Error", "Please select a medicine to remove.");
        }
    }
    
    @FXML
    private void handleComboBoxSelection() {
        String selectedMedicine = DelMeds_Combobox.getValue();
        
        if (selectedMedicine != null) {
            Medicine med = dbHandler.getMedicineDetails(selectedMedicine);
            if (med != null) {
                DelMeds_Name.setText(med.getName());
                DelMeds_Desc.setText(med.getDescription());
                DelMeds_Price.setText(String.valueOf(med.getPrice()));
                DelMeds_Stock.setText(String.valueOf(med.getStock()));
                DelMeds_ManDate.setText(med.getManDate());
                DelMeds_ExpDate.setText(med.getExpDate());
            }
        }
    }
    
 
    private void clearMedicineDetails() {
        DelMeds_Name.setText("");
        DelMeds_Desc.setText("");
        DelMeds_Price.setText("");
        DelMeds_Stock.setText("");
        DelMeds_ManDate.setText("");
        DelMeds_ExpDate.setText("");
    }

    /////////////////////////////////////////////////////////////////////////
    
    @FXML
    private Button SearchMeds_Addbutton;

    @FXML
    private Button StockMeds_BackButton;

    @FXML
    private ComboBox<String> StcokMeds_Combobox;

    @FXML
    private TextField StcokMeds_addstock;

    @FXML
    private Text StcokMeds_currentstock;

    @FXML
    private Text StcokMeds_desciption;

    @FXML
    private Text StcokMeds_name;

    @FXML
    private Text StcokMeds_price;
    
    private void initializeStockUI() {
 
        List<String> medicines = dbHandler.getAllMedicines();
        StcokMeds_Combobox.getItems().setAll(medicines);

        StcokMeds_Combobox.setOnAction(event -> handleStockComboBoxSelection());
    }
    
    private void handleStockComboBoxSelection() {
        String selectedMedicine = StcokMeds_Combobox.getValue();
        if (selectedMedicine != null) {
            Medicine med = dbHandler.getMedicineDetails(selectedMedicine);
            if (med != null) {
             
                StcokMeds_name.setText(med.getName());
                StcokMeds_desciption.setText(med.getDescription());
                StcokMeds_price.setText(String.valueOf(med.getPrice()) + " Rs.");
                StcokMeds_currentstock.setText(String.valueOf(med.getStock()));
  
                StcokMeds_addstock.clear();
            }
        }
    }
    
    @FXML
    private void handleAddStock(ActionEvent event) {
    
        String selectedMedicine = StcokMeds_Combobox.getValue();
        if (selectedMedicine == null) {
            showAlert("Error", "Please select a medicine first.");
            return;
        }

  
        String addStockText = StcokMeds_addstock.getText().trim();
        if (addStockText.isEmpty()) {
            showAlert("Error", "Please enter a valid number to add to stock.");
            return;
        }

        int addStock;
        try {
            addStock = Integer.parseInt(addStockText);
            if (addStock < 0) {
                showAlert("Error", "Cannot add negative stock.");
                return;
            }
        } catch (NumberFormatException e) {
            showAlert("Error", "Stock must be a valid integer.");
            return;
        }

     
        String currentStockText = StcokMeds_currentstock.getText();
        int currentStock = 0;
        try {
            currentStock = Integer.parseInt(currentStockText);
        } catch (NumberFormatException e) {
            showAlert("Error", "Current stock is invalid. Please reselect the medicine.");
            return;
        }

      
        int newStock = currentStock + addStock;

  
        boolean success = dbHandler.updateMedicineStock(selectedMedicine, newStock);
        if (success) {
            showAlert("Success", "Stock updated successfully.");
            
     
            dbHandler.addLog(user + "	 Updated medicine stock ");
            Medicine updatedMed = dbHandler.getMedicineDetails(selectedMedicine);
            if (updatedMed != null) {
                StcokMeds_currentstock.setText(String.valueOf(updatedMed.getStock()));
            }
         
            StcokMeds_addstock.clear();
        } else {
            showAlert("Error", "Failed to update stock. Please try again.");
        }
    }

    
    /////////////////////////////////////////////////////////////////////////////////////////////////
    
    @FXML
    private Button GetMedsSales_BackButton;

    @FXML
    private ComboBox<String> GetMedsSales_Combobox;

    @FXML
    private Text GetMedsSales_TotalSales;

    @FXML
    private Text GetMedsSales_desc;

    @FXML
    private Text GetMedsSales_names;

    @FXML
    private Text GetMedsSales_price;

    
    private void initializeSalesUI() {
        if (GetMedsSales_Combobox != null) {
            List<String> medicines = dbHandler.getAllMedicines();
            GetMedsSales_Combobox.getItems().setAll(medicines);
            GetMedsSales_Combobox.setOnAction(event -> handleSalesComboBoxSelection());
        }
    }

    private void handleSalesComboBoxSelection() {
        String selectedMedicine = GetMedsSales_Combobox.getValue();
        if (selectedMedicine != null) {
            Medicine med = dbHandler.getMedicineDetails(selectedMedicine);
            int totalSales = dbHandler.getTotalSales(selectedMedicine);
            if (med != null) {
                GetMedsSales_names.setText(med.getName());
                GetMedsSales_desc.setText(med.getDescription());
                GetMedsSales_price.setText(String.valueOf(med.getPrice()) + " Rs.");
                GetMedsSales_TotalSales.setText(String.valueOf(totalSales));
            }
        }
    }
    
    
    //////////////////////////////////////////////////////////////////
    
    @FXML
    private Button TopMedsSales_BackButton;

    @FXML
    private ComboBox<String> TopMedsSales_Combobox;

    @FXML
    private Text TopMedsSales_TotalSales;

    @FXML
    private Text TopMedsSales_desc;

    @FXML
    private Text TopMedsSales_names;

    @FXML
    private Text TopMedsSales_price;

    private void initializeTopMedsUI() {
       
        List<String> topMeds = dbHandler.getTopMedicinesBySales();
        
        
        TopMedsSales_Combobox.getItems().setAll(topMeds);

      
        TopMedsSales_Combobox.setOnAction(event -> handleTopMedsComboBoxSelection());
    }

    private void handleTopMedsComboBoxSelection() {
        String selectedMedicine = TopMedsSales_Combobox.getValue();
        if (selectedMedicine != null) {
          
            Medicine med = dbHandler.getMedicineDetails(selectedMedicine);
        
            int totalSales = dbHandler.getTotalSales(selectedMedicine);

            if (med != null) {
              
                TopMedsSales_names.setText(med.getName());
                TopMedsSales_desc.setText(med.getDescription());
                TopMedsSales_price.setText(med.getPrice() + " Rs.");
                TopMedsSales_TotalSales.setText(String.valueOf(totalSales));
            }
        }
    }

    /////////////////////////////////////////////////////////
    
    @FXML
    private Button LogsMeds_BackButton;

    @FXML
    private TextArea LogsMeds_maintextarea;


  
    private void fillLogsTextArea() {
        List<String> logsList = dbHandler.getLogsDescending();
        StringBuilder sb = new StringBuilder();

        for (String log : logsList) {
            sb.append(log).append("\n");
        }

        LogsMeds_maintextarea.setText(sb.toString());
    }


}
