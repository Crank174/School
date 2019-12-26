package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Scanner;

public class AdminSceneController implements Initializable {

    @FXML
    private final ObservableList<User> userDataChild= FXCollections.observableArrayList();

    @FXML
    private final ObservableList<User> userDataTeacher= FXCollections.observableArrayList();

    @FXML
    TableView<User> childTable;

    @FXML TableView<User> teacherTable;

    @FXML
    TableColumn<User, String> childLoginColumn;

    @FXML
    TableColumn<User, String> childPassColumn;

    @FXML
    TableColumn<User, String> childNameColumn;

    @FXML
    TableColumn<User, String> childLastNameColumn;

    @FXML
    TableColumn<User, String> teacherLoginColumn;

    @FXML
    TableColumn<User, String> teacherPassColumn;

    @FXML
    TableColumn<User, String> teacherNameColumn;

    @FXML
    TableColumn<User, String> teacherLastNameColumn;

    @FXML
    Button addChildButton;

    @FXML Button deleteChildButton;

    @FXML
    Button addTeacherButton;

    @FXML Button deleteTeacherButton;

    User currentUser;
    public void setUser(User user)
    {
        currentUser=user;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        Scanner in = null;
        try {
            in = new Scanner(new File("C:\\Users\\home\\Desktop\\Venia\\src\\sample\\users.txt"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        String s;
        String login;
        String pass;
        String name;
        String lastname;
        while (in.hasNextLine())
        {

            s=in.nextLine();
            String[] userArr = s.split(";");
            if(userArr[2].equals("child"))
            {
                login=userArr[0];
                pass=userArr[1];
                name=userArr[3];
                lastname=userArr[4];
                userDataChild.add(new User(login,pass,"child",name,lastname));

                childLoginColumn.setCellValueFactory(new PropertyValueFactory<>("login"));
                childPassColumn.setCellValueFactory(new PropertyValueFactory<>("pass"));
                childNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
                childLastNameColumn.setCellValueFactory(new PropertyValueFactory<>("lastname"));
                childTable.setItems(userDataChild);
            }
            else if(userArr[2].equals("teacher"))
            {
                login=userArr[0];
                pass=userArr[1];
                name=userArr[3];
                lastname=userArr[4];
                userDataTeacher.add(new User(login,pass,"teacher",name,lastname));

                teacherLoginColumn.setCellValueFactory(new PropertyValueFactory<>("login"));
                teacherPassColumn.setCellValueFactory(new PropertyValueFactory<>("pass"));
                teacherNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
                teacherLastNameColumn.setCellValueFactory(new PropertyValueFactory<>("lastname"));
                teacherTable.setItems(userDataTeacher);
            }


        }
    }

    @FXML
    private void handleNewChild() throws IOException
    {
        User tempUser=new User("Логин","Пароль","Имя","Фамилия");

        boolean okClicked=this.showProductEditDialog(tempUser,"child");
        if(okClicked)
        {
            userDataChild.add(tempUser);
            childLoginColumn.setCellValueFactory(new PropertyValueFactory<>("login"));
            childPassColumn.setCellValueFactory(new PropertyValueFactory<>("pass"));
            childNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
            childLastNameColumn.setCellValueFactory(new PropertyValueFactory<>("lastname"));
            childTable.setItems(userDataChild);
            User.refreshList(childTable.getItems(),teacherTable.getItems());
            User.addNewChild(tempUser, "rus");
            User.addNewChild(tempUser, "math");
            User.addNewChild(tempUser, "phys");
            User.addNewChild(tempUser, "inf");
            User.addNewChild(tempUser, "PropuskRus");
            User.addNewChild(tempUser, "PropuskMath");
            User.addNewChild(tempUser, "PropuskPhys");
            User.addNewChild(tempUser, "PropuskInf");
        }
    }

    @FXML
    private void handleNewTeacher() throws IOException
    {
        User tempUser=new User("Логин","Пароль","teacher","Имя","Фамилия");

        boolean okClicked=this.showProductEditDialog(tempUser,"teacher");
        if(okClicked)
        {
            userDataTeacher.add(tempUser);
            teacherLoginColumn.setCellValueFactory(new PropertyValueFactory<>("login"));
            teacherPassColumn.setCellValueFactory(new PropertyValueFactory<>("pass"));
            teacherNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
            teacherLastNameColumn.setCellValueFactory(new PropertyValueFactory<>("lastname"));
            teacherTable.setItems(userDataTeacher);
            User.refreshList(childTable.getItems(),teacherTable.getItems());
        }
    }

    public boolean showProductEditDialog(User user,String type)
    {
        try
        {
            FXMLLoader loader= new FXMLLoader();
            loader.setLocation(EditController.class.getResource("EditScene.fxml"));
            AnchorPane page =(AnchorPane) loader.load();

            Stage dialogStage=new Stage();
            dialogStage.setTitle("Новый пользователь");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(null);
            Scene scene=new Scene(page);
            dialogStage.setScene(scene);

            EditController controller=loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setUser(user);
            controller.setUserType(type);

            dialogStage.showAndWait();
            return controller.isOkClicked();
        } catch (IOException e)
        {
            e.printStackTrace();
            return false;
        }
    }

    @FXML
    private void handleDeleteChild() throws IOException {
        int selectedIndex=childTable.getSelectionModel().getSelectedIndex();
        if(selectedIndex>=0)
        {
            childTable.getItems().remove(selectedIndex);
            User.refreshList(childTable.getItems(),teacherTable.getItems());
            User.refreshListChildDelete(childTable.getItems(), "rus");
            User.refreshListChildDelete(childTable.getItems(), "math");
            User.refreshListChildDelete(childTable.getItems(), "phys");
            User.refreshListChildDelete(childTable.getItems(), "inf");
            User.refreshListChildDelete(childTable.getItems(), "PropuskRus");
            User.refreshListChildDelete(childTable.getItems(), "PropuskMath");
            User.refreshListChildDelete(childTable.getItems(), "PropuskPhys");
            User.refreshListChildDelete(childTable.getItems(), "PropuskInf");



        }
        else
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);

            alert.setTitle("Ошибка");

            alert.setHeaderText("Сообщение об ошибке");

            alert.setContentText("Ни один пользователь не выбран!");

            alert.showAndWait();
        }
    }

    @FXML
    private void handleDeleteTeacher() throws IOException {
        int selectedIndex=teacherTable.getSelectionModel().getSelectedIndex();
        if(selectedIndex>=0)
        {
            teacherTable.getItems().remove(selectedIndex);
            User.refreshList(childTable.getItems(),teacherTable.getItems());

        }
        else
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);

            alert.setTitle("Ошибка");

            alert.setHeaderText("Сообщение об ошибке");

            alert.setContentText("Ни один пользователь не выбран!");

            alert.showAndWait();
        }
    }


}
