package sample;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import javax.xml.catalog.Catalog;

public class EditController{
    @FXML
    private TextField userLoginField;

    @FXML
    private TextField userPassField;

    @FXML
    private TextField userNameField;

    @FXML
    private TextField userLastNameField;


    private Stage dialogStage;

    private User user;

    private String userType;

    private boolean okClicked =false;

    public void setUserType(String type)
    {
        userType=type;
    }

    public void setDialogStage(Stage dialogStage)
    {
        this.dialogStage=dialogStage;
    }

    public void setUser(User user)
    {
        this.user=user;
        userLoginField.setText(user.getLogin());
        userPassField.setText(user.getPass());
        userNameField.setText(user.getName());
        userLastNameField.setText(user.getLastname());
    }

    public boolean isOkClicked()
    {
        return okClicked;
    }

    private boolean isInputValid()
    {
        String errorMessage="";
        if(userLoginField.getText()== null || userLoginField.getText().length()==0)
        {
            errorMessage +="Нет доступного логина\n";
        }

        if(userPassField.getText()== null || userPassField.getText().length()==0)
        {
            errorMessage +="Нет доступного пароля\n";
        }

        if(userNameField.getText()== null || userNameField.getText().length()==0)
        {
            errorMessage +="Нет доступного имени\n";
        }

        if(userLastNameField.getText()== null || userLastNameField.getText().length()==0)
        {
            errorMessage +="Нет доступной фамилии\n";
        }
        if(errorMessage.length()==0)
        {
            return true;
        }
        else
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initOwner(dialogStage);
            alert.setTitle("Некорректные поля");
            alert.setHeaderText("Внесите корректную информацию");
            alert.setContentText(errorMessage);
            alert.showAndWait();

            return false;
        }


    }

    @FXML
    private void handleOk()
    {
        if(isInputValid())
        {

            user.setLogin(userLoginField.getText());
            user.setPass(userPassField.getText());
            user.setName(userNameField.getText());
            user.setLastname(userLastNameField.getText());
            user.setType(userType);
            okClicked=true;
            dialogStage.close();
        }
    }

    @FXML
    private void handleCancel()
    {
        dialogStage.close();
    }



}
