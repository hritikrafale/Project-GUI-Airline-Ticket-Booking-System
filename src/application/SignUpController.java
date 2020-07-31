package application;

import java.time.*;

import java.sql.*;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;

public class SignUpController {

	private String fname;
	private String lname;
	private String username;
	private String password;
    private java.sql.Date dob;
	private String gender;
//	private Date date_of_birth;
	
	
	@FXML
	private TextField first_name;
	
	@FXML
	private TextField last_name;
	
	@FXML
	private DatePicker date;
	
	@FXML
	private TextField user;
	
	@FXML
	private PasswordField pass;
	
	@FXML
	private RadioButton male_rb;
	
	@FXML
	private RadioButton female_rb;
	
	@FXML
	public void submitController(ActionEvent event) throws Exception
	{	 	
		//ZoneId defaultZoneId = ZoneId.systemDefault();
		
		fname=first_name.getText();
		lname=last_name.getText();
		username=user.getText();
		password=pass.getText();
		dob = java.sql.Date.valueOf(date.getValue());
		
		if(male_rb.isSelected())
		{
			gender=male_rb.getText();
		}
		else
		{
			gender=female_rb.getText();
		}
		
		try
		{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
			Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl2","scott","root");
			PreparedStatement stmt=con.prepareStatement("insert into users values(?,?,?,?,?,?)");  
			stmt.setString(1,fname);
			stmt.setString(2,lname);
			stmt.setString(3,gender);
			stmt.setString(4,username);
			stmt.setString(5,password);
			stmt.setDate(6,dob);
			stmt.executeUpdate();
			con.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}
	
}
