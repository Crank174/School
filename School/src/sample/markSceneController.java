package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Scanner;


public class markSceneController {

    @FXML
    private final ObservableList<dateMark> marksData= FXCollections.observableArrayList();
    String name1;

    String lastname1;

    String lesson;

    public void setLessonUser(String name,String lastname,String lesson)
    {
        this.name1=name;
        this.lastname1=lastname;
        this.lesson=lesson;
        String s;
        String[] userArr;
        Scanner in=null;
        String[] s2;
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
            String date;
            String mark;
            if(name.equals(this.name1) & lastname.equals(this.lastname1))
            {
                for(int i=2;i<userArr.length;i++)
                {
                    s2=userArr[i].split(",");
                    date=s2[0];
                    mark=s2[1];
                    marksData.add(new dateMark(date,mark));
                    dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
                    markColumn.setCellValueFactory(new PropertyValueFactory<>("mark"));
                    tableMarks.setItems(marksData);

                }
            }
        }
    }

    @FXML
    TableView tableMarks;

    @FXML
    TableColumn<dateMark,String> dateColumn;

    @FXML
    TableColumn<dateMark,String> markColumn;
}
