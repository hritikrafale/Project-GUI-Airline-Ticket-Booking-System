package application;

import javafx.event.ActionEvent;
import java.sql.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LoginController {
	
	private String user;
	private String pass;
	
	@FXML
	private TextField username;
	
	@FXML
	private PasswordField password;
	
	@FXML
	private Label loginlabel;
	
	@FXML
	public void loginChange(ActionEvent event) throws Exception
	{	
		user=username.getText();
		pass=password.getText();
		
		try
		{
			int rows_affected;
			Class.forName("oracle.jdbc.driver.OracleDriver");
			DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
			Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl2","scott","root");
			PreparedStatement stmt=con.prepareStatement("select * from users where username=? and password=?");
			stmt.setString(1,user);
			stmt.setString(2,pass);
			rows_affected=stmt.executeUpdate();
			if(rows_affected!=1)
			{
				loginlabel.setText("Wrong username or password!!!");
			}
			else
			{
				loginlabel.setText("Welcome!!!");
				Stage primaryStage = new Stage();
				Parent root=FXMLLoader.load(getClass().getResource("HomePage.fxml"));
				Scene scene = new Scene(root,450,400);
				scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
				primaryStage.setScene(scene);
				primaryStage.show();
				
			}
			con.close();
		}
		
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	@FXML
	public void signupChange(ActionEvent event) throws Exception
	{	
		Stage primaryStage = new Stage();
		Parent root=FXMLLoader.load(getClass().getResource("SignUp.fxml"));
		Scene scene = new Scene(root,350,500);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
}
