package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Scanner;

public class ChildSceneController {


    @FXML
    Label rusSrLabel;

    @FXML
    Label mathSrLabel;

    @FXML
    Label physSrLabel;


    @FXML
    Label infSrLabel;

    @FXML
    Label nameLabel;

    @FXML
    Button showMarksRus;

    @FXML
    Button showMarksMath;

    @FXML
    Button showMarksPhys;

    @FXML
    Button showMarksInf;

    String name1;
    String lastname1;
    public void setUser(String name, String lastname)
    {
        name1=name;
        lastname1=lastname;
        nameLabel.setText(name1+" "+lastname1);
        setSr("rus");
        setSr("math");
        setSr("phys");
        setSr("inf");



    }

    public void setSr(String lesson)
    {
        int sum = 0;
        User user= new User(name1,lastname1,name1,lastname1);
        String s = searchUser(user, lesson);
        if(s!="")
        {
            String[] sArr = s.split(";");
            int count = sArr.length;
            for (int i = 0; i < sArr.length; i++) {
                sum = sum + Integer.parseInt(sArr[i].split(",")[1]);
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
    public void openTableMarksRus() throws IOException {
        User user= new User(name1,lastname1,name1,lastname1);
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

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("marksScene.fxml"));
        Scene secondScene = new Scene(fxmlLoader.load());
        Stage newWindow = new Stage();
        newWindow.setTitle("Таблица успеваемости");
        newWindow.setScene(secondScene);
        markSceneController controller = fxmlLoader.getController();
        controller.setLessonUser(name1,lastname1,"math");
        newWindow.show();
    }

    @FXML
    public void openTableMarksPhys() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("marksScene.fxml"));
        Scene secondScene = new Scene(fxmlLoader.load());
        Stage newWindow = new Stage();
        newWindow.setTitle("Таблица успеваемости");
        newWindow.setScene(secondScene);
        markSceneController controller = fxmlLoader.getController();
        controller.setLessonUser(name1,lastname1,"phys");
        newWindow.show();
    }

    @FXML
    public void openTableMarksInf() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("marksScene.fxml"));
        Scene secondScene = new Scene(fxmlLoader.load());
        Stage newWindow = new Stage();
        newWindow.setTitle("Таблица успеваемости");
        newWindow.setScene(secondScene);
        markSceneController controller = fxmlLoader.getController();
        controller.setLessonUser(name1,lastname1,"inf");
        newWindow.show();
    }

    @FXML
    public void openPropuskRus() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("propuskScene.fxml"));
        Scene secondScene = new Scene(fxmlLoader.load());
        Stage newWindow = new Stage();
        newWindow.setTitle("Таблица посещаемости");
        newWindow.setScene(secondScene);
        propuskController controller = fxmlLoader.getController();
        controller.start(name1,lastname1,"PropuskRus");
        newWindow.show();
    }

    @FXML
    public void openPropuskMath() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("propuskScene.fxml"));
        Scene secondScene = new Scene(fxmlLoader.load());
        Stage newWindow = new Stage();
        newWindow.setTitle("Таблица посещаемости");
        newWindow.setScene(secondScene);
        propuskController controller = fxmlLoader.getController();
        controller.start(name1,lastname1,"PropuskMath");
        newWindow.show();
    }

    @FXML
    public void openPropuskPhys() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("propuskScene.fxml"));
        Scene secondScene = new Scene(fxmlLoader.load());
        Stage newWindow = new Stage();
        newWindow.setTitle("Таблица посещаемости");
        newWindow.setScene(secondScene);
        propuskController controller = fxmlLoader.getController();
        controller.start(name1,lastname1,"PropuskPhys");
        newWindow.show();
    }

    @FXML
    public void openPropuskInf() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("propuskScene.fxml"));
        Scene secondScene = new Scene(fxmlLoader.load());
        Stage newWindow = new Stage();
        newWindow.setTitle("Таблица посещаемости");
        newWindow.setScene(secondScene);
        propuskController controller = fxmlLoader.getController();
        controller.start(name1,lastname1,"PropuskInf");
        newWindow.show();
    }







}
