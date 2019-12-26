package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class propuskController {

    @FXML
    TableView tableMarks;

    @FXML
    private final ObservableList<dateMark> marksData= FXCollections.observableArrayList();

    @FXML
    TableColumn<dateMark,String> dateColumn;

    @FXML
    TableColumn<dateMark,String> markColumn;

    public void start(String name,String lastname,String lesson)
    {
        ArrayList<String> list = new ArrayList<>();
        Scanner in=null;
        try {
            in = new Scanner(new File("C:\\Users\\home\\Desktop\\Venia\\src\\sample\\"+lesson+".txt"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        while (in.hasNextLine()) {
            list.add(in.nextLine());
        }
        list.stream()
                .forEach(p->
                {
                    String[] s=p.split(";");
                    if(s[0].equals(name) & s[1].equals(lastname))
                    {
                        Arrays.stream(s)
                            .forEach(n->
                            {
                                if(!n.equals(name) & !n.equals(lastname))
                                {
                                    marksData.add(new dateMark(n,"Пропуск"));
                                    dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
                                    markColumn.setCellValueFactory(new PropertyValueFactory<>("mark"));
                                    tableMarks.setItems(marksData);
                                }
                            });
                    }
                });

    }
}
