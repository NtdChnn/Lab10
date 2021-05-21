/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab10;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author notda
 */
public class LoanCalculatorController implements Initializable {

    @FXML
    private Button saveBtn,loadBtn,calculateBtn,clearBtn;
    @FXML
    private TextField annualInterestRateField, numberOfYearField,loanAmountField,monthlyPaymentField,TotalPaymentField;
     double annualInterestRate,loanAmount,monthlyPayment,TotalPayment;
     int numberOfYear;
     
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        annualInterestRateField.setText("");
        numberOfYearField.setText("");
        loanAmountField.setText("");
        monthlyPaymentField.setText("");
        TotalPaymentField.setText("");
    }    

    @FXML
    private void saveBtnAction(ActionEvent event) throws FileNotFoundException, IOException {
    File file = new File("loan.dat");
    FileOutputStream fos = new FileOutputStream(file);
    DataOutputStream output = new DataOutputStream(fos);
    
    output.writeDouble(Double.parseDouble(annualInterestRateField.getText()));
    output.writeInt(Integer.parseInt(numberOfYearField.getText()));
    output.writeDouble(Double.parseDouble(loanAmountField.getText()));
    output.close();
    }

    @FXML
    private void loadBtnAction(ActionEvent event) throws FileNotFoundException, IOException {
    DataInputStream input = new DataInputStream(new FileInputStream(new File("loan.dat")));
    while(input.available() != 0)
    {
        annualInterestRateField.setText(String.valueOf(input.readDouble()));
        numberOfYearField.setText(String.valueOf(input.readInt()));
        loanAmountField.setText(String.valueOf(input.readDouble()));
    }
    input.close();
            monthlyPaymentField.setText("");
            TotalPaymentField.setText("");
    }

    @FXML
    private void calculateBtnAction(ActionEvent event) {
        annualInterestRate = Double.parseDouble(annualInterestRateField.getText());
        numberOfYear = Integer.parseInt(numberOfYearField.getText());
        loanAmount = Double.parseDouble(loanAmountField.getText());
        
        Loan loan = new Loan(annualInterestRate,numberOfYear,loanAmount);
        monthlyPayment = loan.getMonthlyPayment();
        TotalPayment = loan.getTotalPayment();
        
        monthlyPaymentField.setText(String.valueOf(monthlyPayment));
        TotalPaymentField.setText(String.valueOf(TotalPayment));
    }

    @FXML
    private void clearBtnAction(ActionEvent event) {
        annualInterestRateField.setText("");
        numberOfYearField.setText("");
        loanAmountField.setText("");
        monthlyPaymentField.setText("");
        TotalPaymentField.setText("");
    }
    
}
