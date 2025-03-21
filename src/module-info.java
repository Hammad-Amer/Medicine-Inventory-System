module MedStorageSystem {
	requires javafx.controls;
	requires javafx.fxml;
	requires java.sql;
	requires javafx.graphics;
	
	opens ui to javafx.fxml; 
	opens application to javafx.graphics, javafx.fxml;
}
