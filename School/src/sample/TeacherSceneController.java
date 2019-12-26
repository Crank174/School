package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.Scanner;

public class TeacherSceneController implements Initializable {

    @FXML
    TableView<User> rusTable;

    @FXML
    TableView<User> mathTable;

    @FXML
    TableView<User> physTable;

    @FXML
    TableView<User> infTable;

    @FXML
    TableColumn<User,String> rusName;

    @FXML
    TableColumn<User,String> mathName;

    @FXML
    TableColumn<User,String> physName;

    @FXML
    TableColumn<User,String> infName;

    @FXML
    TableColumn<User,String> rusLastName;

    @FXML
    TableColumn<User,String> mathLastName;

    @FXML
    TableColumn<User,String> physLastName;

    @FXML
    TableColumn<User,String> infLastName;

    @FXML
    Label rusSrLabel;


    @FXML
    Label mathSrLabel;


    @FXML
    Label physSrLabel;

    @FXML
    Label nameLabel;


    @FXML
    Label infSrLabel;


    @FXML Button addMarkRus;

    @FXML Button addMarkMath;

    @FXML Button addMarkPhys;

    @FXML Button addMarkInf;

    @FXML Button buttonMarks;

    @FXML
    private final ObservableList<User> rusData= FXCollections.observableArrayList();

    @FXML
    private final ObservableList<User> mathData= FXCollections.observableArrayList();

    @FXML
    private final ObservableList<User> physData= FXCollections.observableArrayList();
    @FXML
    private final ObservableList<User> infData= FXCollections.observableArrayList();

    static String name1;
    static String lastname1;
    public void setUser(String name, String lastname)
    {
        name1=name;
        lastname1=lastname;
        nameLabel.setText(name1+" "+lastname1);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        setTableLessons("rus");
        setTableLessons("math");
        setTableLessons("phys");
        setTableLessons("inf");
    }


    public String[] dialogMark()
    {
        String mark;
        String date;
        String[] dateMark = new String[2];

        TextInputDialog dialog1 = new TextInputDialog("");
        dialog1.setTitle("");
        dialog1.setHeaderText("");
        dialog1.setContentText("Введите оценку:");

        Optional<String> result1 = dialog1.showAndWait();
        if (result1.isPresent()) {
            mark = result1.get();
            if(!(mark.equals("5") || mark.equals("4") || mark.equals("3") || mark.equals("3")))
            {
                mark="";
                Alert alert = new Alert(Alert.AlertType.ERROR);

                alert.setTitle("Ошибка");

                alert.setHeaderText("Сообщение об ошибке");

                alert.setContentText("Оценка должна быть от 2 до 5!");

                alert.showAndWait();
            }


        }
        else mark="";
        TextInputDialog dialog2 = new TextInputDialog("");
        dialog2.setTitle("");
        dialog2.setHeaderText("");
        dialog2.setContentText("Введите дату");

        Optional<String> result2 = dialog2.showAndWait();
        if (result2.isPresent()) {
            date=result2.get();
            dateMark[0]=date;


        }
        else
        {
            date="";
            dateMark[0]=date;
        }

        dateMark[0]=date;
        dateMark[1]=mark;

        return dateMark;
    }


    public String dialogPropusk()
    {
        String date="";

        TextInputDialog dialog1 = new TextInputDialog("");
        dialog1.setTitle("");
        dialog1.setHeaderText("");
        dialog1.setContentText("Введите дату");

        Optional<String> result1 = dialog1.showAndWait();
        if (result1.isPresent()) {
            date = result1.get();
            if(date.equals(""))
            {
                Alert alert = new Alert(Alert.AlertType.ERROR);

                alert.setTitle("Ошибка");

                alert.setHeaderText("Сообщение об ошибке");

                alert.setContentText("Пустое поле");

                alert.showAndWait();
            }


        }


        return date;
    }




    public void alert()
    {
        Alert alert = new Alert(Alert.AlertType.ERROR);

        alert.setTitle("Ошибка");

        alert.setHeaderText("Сообщение об ошибке");

        alert.setContentText("Ни один обьект не выбран!");

        alert.showAndWait();
    }

    public void refreshTableRus(User user) throws IOException {
        rusData.add(user);
        rusName.setCellValueFactory(new PropertyValueFactory<>("name"));
        rusLastName.setCellValueFactory(new PropertyValueFactory<>("lastname"));
        rusTable.setItems(rusData);

        user.refreshListChild(rusTable.getItems(),"rus");
    }

    public void refreshTableMath(User user) throws IOException {
        mathData.add(user);
        mathName.setCellValueFactory(new PropertyValueFactory<>("name"));
        mathLastName.setCellValueFactory(new PropertyValueFactory<>("lastname"));
        mathTable.setItems(mathData);

        user.refreshListChild(mathTable.getItems(),"math");
    }

    public void refreshTablePhys(User user) throws IOException {
        physData.add(user);
        physName.setCellValueFactory(new PropertyValueFactory<>("name"));
        physLastName.setCellValueFactory(new PropertyValueFactory<>("lastname"));
        physTable.setItems(physData);

        user.refreshListChild(physTable.getItems(),"phys");
    }

    public void refreshTableInf(User user) throws IOException {
        infData.add(user);
        infName.setCellValueFactory(new PropertyValueFactory<>("name"));
        infLastName.setCellValueFactory(new PropertyValueFactory<>("lastname"));
        infTable.setItems(infData);

        user.refreshListChild(infTable.getItems(),"inf");
    }


    @FXML
    public void addMarkRus() throws IOException {
        String[] markDate = new String[2];
        markDate[0]="";
        markDate=dialogMark();
        User user;
        String s;
        int selectedIndex=rusTable.getSelectionModel().getSelectedIndex();
        if(selectedIndex>=0 & markDate[0]!="")
        {
            user=rusTable.getSelectionModel().getSelectedItem();
            s=searchUser(user, "rus");
            rusTable.getItems().remove(selectedIndex);
            s=s+markDate[0]+","+markDate[1];
            user.setMarks(s);
            refreshTableRus(user);


        }
        else
        {
            alert();
        }
    }

    @FXML
    public void openPropuskRus() throws IOException {
        User user=rusTable.getSelectionModel().getSelectedItem();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("propuskScene.fxml"));
        Scene secondScene = new Scene(fxmlLoader.load());
        Stage newWindow = new Stage();
        newWindow.setTitle("Таблица посещаемости");
        newWindow.setScene(secondScene);
        propuskController controller = fxmlLoader.getController();
        controller.start(user.name,user.lastname,"PropuskRus");
        newWindow.show();
    }

    @FXML
    public void openPropuskMath() throws IOException {
        User user=rusTable.getSelectionModel().getSelectedItem();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("propuskScene.fxml"));
        Scene secondScene = new Scene(fxmlLoader.load());
        Stage newWindow = new Stage();
        newWindow.setTitle("Таблица посещаемости");
        newWindow.setScene(secondScene);
        propuskController controller = fxmlLoader.getController();
        controller.start(user.name,user.lastname,"PropuskMath");
        newWindow.show();
    }

    @FXML
    public void openPropuskPhys() throws IOException {
        User user=rusTable.getSelectionModel().getSelectedItem();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("propuskScene.fxml"));
        Scene secondScene = new Scene(fxmlLoader.load());
        Stage newWindow = new Stage();
        newWindow.setTitle("Таблица посещаемости");
        newWindow.setScene(secondScene);
        propuskController controller = fxmlLoader.getController();
        controller.start(user.name,user.lastname,"PropuskPhys");
        newWindow.show();
    }

    @FXML
    public void openPropuskInf() throws IOException {
        User user=rusTable.getSelectionModel().getSelectedItem();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("propuskScene.fxml"));
        Scene secondScene = new Scene(fxmlLoader.load());
        Stage newWindow = new Stage();
        newWindow.setTitle("Таблица посещаемости");
        newWindow.setScene(secondScene);
        propuskController controller = fxmlLoader.getController();
        controller.start(user.name,user.lastname,"PropuskInf");
        newWindow.show();
    }


    @FXML
    public void addPropuskRus() throws IOException {
        String date;
        date=dialogPropusk();
        User user;
        String s;
        int selectedIndex=rusTable.getSelectionModel().getSelectedIndex();
        if(selectedIndex>=0 & date!="")
        {
            user=rusTable.getSelectionModel().getSelectedItem();
            s=searchUser(user, "PropuskRus");
            s=s+date;
            user.setMarks(s);
            User.refreshPropusk(user,"PropuskRus");


        }
        else
        {
            alert();
        }
    }

    @FXML
    public void addPropuskMath() throws IOException {
        String date;
        date=dialogPropusk();
        User user;
        String s;
        int selectedIndex=rusTable.getSelectionModel().getSelectedIndex();
        if(selectedIndex>=0 & date!="")
        {
            user=rusTable.getSelectionModel().getSelectedItem();
            s=searchUser(user, "PropuskMath");
            s=s+date;
            user.setMarks(s);
            User.refreshPropusk(user,"PropuskMath");


        }
        else
        {
            alert();
        }
    }

    @FXML
    public void addPropuskPhys() throws IOException {
        String date;
        date=dialogPropusk();
        User user;
        String s;
        int selectedIndex=rusTable.getSelectionModel().getSelectedIndex();
        if(selectedIndex>=0 & date!="")
        {
            user=rusTable.getSelectionModel().getSelectedItem();
            s=searchUser(user, "PropuskPhys");
            s=s+date;
            user.setMarks(s);
            User.refreshPropusk(user,"PropuskPhys");


        }
        else
        {
            alert();
        }
    }
    @FXML
    public void addPropuskInf() throws IOException {
        String date;
        date=dialogPropusk();
        User user;
        String s;
        int selectedIndex=rusTable.getSelectionModel().getSelectedIndex();
        if(selectedIndex>=0 & date!="")
        {
            user=rusTable.getSelectionModel().getSelectedItem();
            s=searchUser(user, "PropuskInf");
            s=s+date;
            user.setMarks(s);
            User.refreshPropusk(user,"PropuskInf");


        }
        else
        {
            alert();
        }
    }

    public String searchUser(User user,String lesson)
    {
        String[] userArr;
        Scanner in=null;
        String s;
        String result="";
        String[] s2;
        try {
            in = new Scanner(new File("C:\\Users\\home\\Desktop\\Venia\\src\\sample\\"+lesson+".txt"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        while (in.hasNextLine()) {
            s=in.nextLine();
            userArr=s.split(";");
            if(userArr[0].equals(user.name) & userArr[1].equals(user.lastname))
            {
                for(int i=2;i<userArr.length;i++)result=result+userArr[i]+";";
            }
        }
        return result;
    }

    @FXML
    public void addMarkMath() throws IOException {
        String[] markDate = new String[2];
        markDate[0]="";
        markDate=dialogMark();
        User user;
        String s;
        int selectedIndex=mathTable.getSelectionModel().getSelectedIndex();
        if(selectedIndex>=0 & markDate[0]!="")
        {
            user=mathTable.getSelectionModel().getSelectedItem();
            s=searchUser(user, "math");
            mathTable.getItems().remove(selectedIndex);
            s=s+markDate[0]+","+markDate[1];
            user.setMarks(s);
            refreshTableMath(user);


        }
        else
        {
            alert();
        }
    }

    @FXML
    public void addMarkPhys() throws IOException {
        String[] markDate = new String[2];
        markDate[0]="";
        markDate=dialogMark();
        User user;
        String s;
        int selectedIndex=physTable.getSelectionModel().getSelectedIndex();
        if(selectedIndex>=0 & markDate[0]!="")
        {
            user=physTable.getSelectionModel().getSelectedItem();
            s=searchUser(user, "phys");
            physTable.getItems().remove(selectedIndex);
            s=s+markDate[0]+","+markDate[1];
            user.setMarks(s);
            refreshTablePhys(user);


        }
        else
        {
            alert();
        }
    }

    @FXML
    public void addMarkInf() throws IOException {
        String[] markDate = new String[2];
        markDate[0]="";
        markDate=dialogMark();
        User user;
        String s;
        int selectedIndex=infTable.getSelectionModel().getSelectedIndex();
        if(selectedIndex>=0 & markDate[0]!="")
        {
            user=infTable.getSelectionModel().getSelectedItem();
            s=searchUser(user, "inf");
            infTable.getItems().remove(selectedIndex);
            s=s+markDate[0]+","+markDate[1];
            user.setMarks(s);
            refreshTableInf(user);


        }
        else
        {
            alert();
        }
    }






    public void setTableLessons(String lesson)
    {
        String s;
        String name;
        String markstr;
        String lastname;
        Scanner in = null;
        String[] userArr;
        switch (lesson) {
            case ("rus"):
                try {
                    in = new Scanner(new File("C:\\Users\\home\\Desktop\\Venia\\src\\sample\\"+lesson+".txt"));
                    } catch (FileNotFoundException e) {
                    e.printStackTrace();
                    }
                while (in.hasNextLine()) {
                    s = in.nextLine();
                    userArr=s.split(";");
                    name = userArr[0];
                    lastname = userArr[1];
                    User user=new User(name,lastname);
                    markstr=searchUser(user, "rus");
                    rusData.add(new User(name, lastname,markstr));
                    rusName.setCellValueFactory(new PropertyValueFactory<>("name"));
                    rusLastName.setCellValueFactory(new PropertyValueFactory<>("lastname"));
                    rusTable.setItems(rusData);
                }
                showChildDetails(null,lesson);
                rusTable.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> showChildDetails(newValue,lesson));
                break;

                case("math"):
            try {
                in = new Scanner(new File("C:\\Users\\home\\Desktop\\Venia\\src\\sample\\"+lesson+".txt"));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            while (in.hasNextLine()) {
                s = in.nextLine();
                userArr = s.split(";");
                name = userArr[0];
                lastname = userArr[1];
                User user=new User(name,lastname);
                markstr=searchUser(user, "math");
                mathData.add(new User(name, lastname,markstr));
                mathName.setCellValueFactory(new PropertyValueFactory<>("name"));
                mathLastName.setCellValueFactory(new PropertyValueFactory<>("lastname"));
                mathTable.setItems(mathData);
            }
            showChildDetails(null,lesson);
            mathTable.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> showChildDetails(newValue,lesson));
            break;

            case("phys"):
                try {
                    in = new Scanner(new File("C:\\Users\\home\\Desktop\\Venia\\src\\sample\\"+lesson+".txt"));
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                while (in.hasNextLine()) {
                    s = in.nextLine();
                    userArr = s.split(";");
                    name = userArr[0];
                    lastname = userArr[1];
                    User user=new User(name,lastname);
                    markstr=searchUser(user, "phys");
                    physData.add(new User(name, lastname,markstr));
                    physName.setCellValueFactory(new PropertyValueFactory<>("name"));
                    physLastName.setCellValueFactory(new PropertyValueFactory<>("lastname"));
                    physTable.setItems(physData);
                }
                showChildDetails(null,lesson);
                physTable.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> showChildDetails(newValue,lesson));

                break;

            case("inf"):

                try {
                    in = new Scanner(new File("C:\\Users\\home\\Desktop\\Venia\\src\\sample\\"+lesson+".txt"));
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                while (in.hasNextLine()) {
                    s = in.nextLine();
                    userArr = s.split(";");
                    name = userArr[0];
                    lastname = userArr[1];
                    User user=new User(name,lastname);
                    markstr=searchUser(user, "inf");
                    infData.add(new User(name, lastname,markstr));
                    infName.setCellValueFactory(new PropertyValueFactory<>("name"));
                    infLastName.setCellValueFactory(new PropertyValueFactory<>("lastname"));
                    infTable.setItems(infData);
                }
                showChildDetails(null,lesson);
                infTable.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> showChildDetails(newValue,lesson));

                break;





        }
    }

    public void showChildDetails(User user,String lesson)
    {
        if(user!=null) {
            int sum = 0;

            String s = searchUser(user, lesson);
            if(s!="")
            {
                String[] sArr = s.split(";");
                int count = sArr.length;
                for (int i = 0; i < sArr.length; i++) {
                    if(sArr[i].split(",")[1]!="none")sum = sum + Integer.parseInt(sArr[i].split(",")[1]);

                }
                double srBall = (double) sum / count;

                switch (lesson) {
                    case ("rus"):
                        rusSrLabel.setText(Double.toString(srBall));
                        break;

                    case ("math"):
                        mathSrLabel.setText(Double.toString(srBall));
                        break;

                    case ("phys"):
                        physSrLabel.setText(Double.toString(srBall));
                        break;
                    case ("inf"):
                        infSrLabel.setText(Double.toString(srBall));
                        break;
                }
            }
            else
            {
                switch (lesson) {
                    case ("rus"):
                        rusSrLabel.setText("0");
                        break;

                    case ("math"):
                        mathSrLabel.setText("0");
                        break;

                    case ("phys"):
                        physSrLabel.setText("0");
                        break;
                    case ("inf"):
                        infSrLabel.setText("0");
                        break;
                }
            }


        }
        else
        {
            switch (lesson) {
                case ("rus"):
                    rusSrLabel.setText("0");
                    break;

                case ("math"):
                    mathSrLabel.setText("0");
                    break;

                case ("phys"):
                    physSrLabel.setText("0");
                    break;
                case ("inf"):
                    infSrLabel.setText("0");
                    break;
            }
        }




    }

    @FXML
    public void openTableMarksRus() throws IOException {
        User user=rusTable.getSelectionModel().getSelectedItem();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("marksScene.fxml"));
        Scene secondScene = new Scene(fxmlLoader.load());
        Stage newWindow = new Stage();
        newWindow.setTitle("Таблица успеваемости");
        newWindow.setScene(secondScene);
        markSceneController controller = fxmlLoader.getController();
        controller.setLessonUser(user.name,user.lastname,"rus");
        newWindow.show();
    }

    @FXML
    public void openTableMarksMath() throws IOException {
        User user=mathTable.getSelectionModel().getSelectedItem();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("marksScene.fxml"));
        Scene secondScene = new Scene(fxmlLoader.load());
        Stage newWindow = new Stage();
        newWindow.setTitle("Таблица успеваемости");
        newWindow.setScene(secondScene);
        markSceneController controller = fxmlLoader.getController();
        controller.setLessonUser(user.name,user.lastname,"math");
        newWindow.show();
    }

    @FXML
    public void openTableMarksPhys() throws IOException {
        User user=physTable.getSelectionModel().getSelectedItem();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("marksScene.fxml"));
        Scene secondScene = new Scene(fxmlLoader.load());
        Stage newWindow = new Stage();
        newWindow.setTitle("Таблица успеваемости");
        newWindow.setScene(secondScene);
        markSceneController controller = fxmlLoader.getController();
        controller.setLessonUser(user.name,user.lastname,"phys");
        newWindow.show();
    }

    @FXML
    public void openTableMarksInf() throws IOException {
        User user=infTable.getSelectionModel().getSelectedItem();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("marksScene.fxml"));
        Scene secondScene = new Scene(fxmlLoader.load());
        Stage newWindow = new Stage();
        newWindow.setTitle("Таблица успеваемости");
        newWindow.setScene(secondScene);
        markSceneController controller = fxmlLoader.getController();
        controller.setLessonUser(user.name,user.lastname,"inf");
        newWindow.show();
    }


}
