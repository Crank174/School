package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextInputDialog;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class Controller implements Initializable {


    @FXML
    Button buttonLogin;

    @FXML
    ImageView startImage;

    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        Class<?> clazz = Controller.class;
        InputStream input1 = clazz.getResourceAsStream("image1.jpg");
        Image image = new Image(input1);
        startImage.setImage(image);
    }

    @FXML
    public void signUp() throws IOException {
        String login="";
        String pass="";

        TextInputDialog dialog = new TextInputDialog("");

        dialog.setTitle("Вход в систему");
        dialog.setHeaderText("");

        dialog.setContentText("Введите логин:");

        Optional<String> result = dialog.showAndWait();

        if (result.isPresent()) {

            login = result.get();
            login=login.trim();

        }

        TextInputDialog dialog2 = new TextInputDialog("");

        dialog2.setTitle("Вход в систему");
        dialog2.setHeaderText("");
        dialog2.setContentText("Введите пароль:");

        Optional<String> result2 = dialog2.showAndWait();
        if (result2.isPresent()) {

            pass = result2.get();
            pass=pass.trim();

        }
        if(login.equals("") | pass.equals(""))
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);

            alert.setTitle("Ошибка");

            alert.setHeaderText("Сообщение об ошибке");

            alert.setContentText("Проверьте введенные логин и пароль");

            alert.showAndWait();
        }
        else
        {
            User user = new User(login, pass);

            User user1=user.checkUser(user);
            if(user1.login.equals("not") & user1.pass.equals("found"))
            {
                Alert alert1 = new Alert(Alert.AlertType.ERROR);

                alert1.setTitle("Ошибка");

                alert1.setHeaderText("Сообщение об ошибке");

                alert1.setContentText("Проверьте введенные логин и пароль");

                alert1.showAndWait();
            }
            else {
                String name=user1.name;
                String lastname=user1.lastname;
                if (user1.type.equals("admin"))
                {
                    Stage stage = (Stage) buttonLogin.getScene().getWindow();
                    // do what you have to do
                    stage.close();
                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("adminScene.fxml"));
                    Parent root1 = (Parent) fxmlLoader.load();
                    stage = new Stage();
                    stage.initModality(Modality.APPLICATION_MODAL);
                    stage.setTitle("Админ панель");
                    stage.setScene(new Scene(root1));
                    AdminSceneController AdminController = fxmlLoader.getController(); //получаем контроллер для второй формы
                    AdminController.setUser(user1);
                    stage.show();
                }
                else if (user1.type.equals("teacher"))
                {
                    Stage stage = (Stage) buttonLogin.getScene().getWindow();
                    // do what you have to do
                    stage.close();
                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("TeacherScene.fxml"));
                    Parent root1 = (Parent) fxmlLoader.load();
                    stage = new Stage();
                    stage.initModality(Modality.APPLICATION_MODAL);
                    stage.setTitle("Панель учителя");
                    stage.setScene(new Scene(root1));
                    TeacherSceneController TeacherController = fxmlLoader.getController(); //получаем контроллер для второй формы
                    TeacherController.setUser(name,lastname);
                    stage.show();

                }
                else
                {
                    Stage stage = (Stage) buttonLogin.getScene().getWindow();
                    // do what you have to do
                    stage.close();
                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("childScene.fxml"));
                    Parent root1 = (Parent) fxmlLoader.load();
                    stage = new Stage();
                    stage.initModality(Modality.APPLICATION_MODAL);
                    stage.setTitle("Панель ученика");
                    stage.setScene(new Scene(root1));
                    ChildSceneController ChildController = fxmlLoader.getController(); //получаем контроллер для второй формы
                    ChildController.setUser(name,lastname);
                    stage.show();
                }


            }
        }





    }

}
