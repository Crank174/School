package sample;

import javafx.collections.ObservableList;
import javafx.fxml.Initializable;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Scanner;

public class User {
    String login;
    String pass;
    String type;
    String name;
    String lastname;
    String marks;

    User(String login, String pass, String type, String name, String lastname)
    {
        this.login=login;
        this.pass=pass;
        this.type=type;
        this.name=name;
        this.lastname=lastname;
    }

    User(String login, String pass, String name, String lastname)
    {
        this.login=login;
        this.pass=pass;
        this.name=name;
        this.lastname=lastname;
    }

    User(String name, String lastname,String marks)
    {
        this.name=name;
        this.lastname=lastname;
        this.marks=marks;
    }

    User(String login, String pass)
    {
        this.login=login;
        this.pass=pass;
    }


    User checkUser(User user) throws FileNotFoundException {
        String s;
        User result;
        Scanner in = new Scanner(new File("C:\\Users\\home\\Desktop\\Venia\\src\\sample\\users.txt"));
        while (in.hasNextLine())
        {
            s=in.nextLine();
            String[] LogPass = s.split(";");
            if (LogPass[0].equals(user.login) & LogPass[1].equals(user.pass))
            {
                result=new User(login, pass,LogPass[2],LogPass[3],LogPass[4]);
                return result;


            }
        }
        return new User("not", "found");

    }

    public static void refreshList(ObservableList<User> listChild,ObservableList<User> listTeacher) throws IOException {
        FileWriter fstream1 = new FileWriter("C:\\Users\\home\\Desktop\\Venia\\src\\sample\\users.txt");// конструктор с одним параметром - для перезаписи
        BufferedWriter out1 = new BufferedWriter(fstream1); //  создаём буферезированный поток
        out1.write(""); // очищаем, перезаписав поверх пустую строку
        out1.close(); // закрываем
        for(User item: listChild)
        {
            Path p = Paths.get("C:\\Users\\home\\Desktop\\Venia\\src\\sample\\users.txt");

            String s1 = item.login + ";" +item.pass+";"+item.type+";"+item.name+";"+item.lastname+ System.lineSeparator();
            try {
                Files.write(p, s1.getBytes(), StandardOpenOption.APPEND);
            } catch (IOException e) {
                System.err.println(e);
            }
        }
        for(User item: listTeacher)
        {
            Path p = Paths.get("C:\\Users\\home\\Desktop\\Venia\\src\\sample\\users.txt");

            String s1 = item.login + ";" +item.pass+";"+item.type+";"+item.name+";"+item.lastname+ System.lineSeparator();
            try {
                Files.write(p, s1.getBytes(), StandardOpenOption.APPEND);
            } catch (IOException e) {
                System.err.println(e);
            }
        }
        Path p1 = Paths.get("C:\\Users\\home\\Desktop\\Venia\\src\\sample\\users.txt");
        String userString="admin;root;admin;Админ;Суперюзорович";
        Files.write(p1, userString.getBytes(), StandardOpenOption.APPEND);
    }

    public static void refreshListChild(ObservableList<User> listChild,String lesson) throws IOException {
        FileWriter fstream1 = new FileWriter("C:\\Users\\home\\Desktop\\Venia\\src\\sample\\"+lesson+".txt");// конструктор с одним параметром - для перезаписи
        BufferedWriter out1 = new BufferedWriter(fstream1); //  создаём буферезированный поток
        out1.write(""); // очищаем, перезаписав поверх пустую строку
        out1.close(); // закрываем
        for (User item : listChild) {
            Path p = Paths.get("C:\\Users\\home\\Desktop\\Venia\\src\\sample\\"+lesson+".txt");

            String s1 = item.name + ";" + item.lastname + ";" + item.marks  + System.lineSeparator();
            try {
                Files.write(p, s1.getBytes(), StandardOpenOption.APPEND);
            } catch (IOException e) {
                System.err.println(e);
            }
        }
    }
    public static void refreshPropusk(User user,String lesson) throws IOException {
        Scanner in = new Scanner(new File("C:\\Users\\home\\Desktop\\Venia\\src\\sample\\"+lesson+".txt"));
        String s;
        ArrayList<String> list = new ArrayList<>();
        ArrayList<String> newlist = new ArrayList<>();
        while(in.hasNextLine())
        {
            s=in.nextLine();
            list.add(s);
        }
        list.stream()
                .forEach(p->
                {
                    if(p.split(";")[0].equals(user.getName())& p.split(";")[1].equals(user.getLastname()))
                    {
                        p=user.getName()+";"+user.getLastname()+";"+user.getMarks();
                    }
                    newlist.add(p);
                });
        FileWriter fstream1 = new FileWriter("C:\\Users\\home\\Desktop\\Venia\\src\\sample\\"+lesson+".txt");// конструктор с одним параметром - для перезаписи
        BufferedWriter out1 = new BufferedWriter(fstream1); //  создаём буферезированный поток
        out1.write(""); // очищаем, перезаписав поверх пустую строку
        out1.close(); // закрываем
        for (String item : newlist) {
            Path p = Paths.get("C:\\Users\\home\\Desktop\\Venia\\src\\sample\\"+lesson+".txt");

            String s1 = item + System.lineSeparator();
            try {
                Files.write(p, s1.getBytes(), StandardOpenOption.APPEND);
            } catch (IOException e) {
                System.err.println(e);
            }
        }


    }
    public static void refreshListChildDelete(ObservableList<User> listChild,String lesson) throws IOException {


        Scanner in = new Scanner(new File("C:\\Users\\home\\Desktop\\Venia\\src\\sample\\"+lesson+".txt"));
        String s;
        ArrayList<String> list = new ArrayList<>();
        ArrayList<String> newlist = new ArrayList<>();
        while(in.hasNextLine())
        {
            s=in.nextLine();
            list.add(s);
        }
        FileWriter fstream1 = new FileWriter("C:\\Users\\home\\Desktop\\Venia\\src\\sample\\"+lesson+".txt");// конструктор с одним параметром - для перезаписи
        BufferedWriter out1 = new BufferedWriter(fstream1); //  создаём буферезированный поток
        out1.write(""); // очищаем, перезаписав поверх пустую строку
        out1.close(); // закрываем

        list.stream()
                .forEach(p->
                {
                    if(listChild.stream().anyMatch(n-> (n.getName().equals(p.split(";")[0]) & n.getLastname().equals(p.split(";")[1]))))
                    {
                        newlist.add(p);
                    }
                });

        for (String item : newlist) {
            Path p = Paths.get("C:\\Users\\home\\Desktop\\Venia\\src\\sample\\"+lesson+".txt");

            String s1 = item + System.lineSeparator();
            try {
                Files.write(p, s1.getBytes(), StandardOpenOption.APPEND);
            } catch (IOException e) {
                System.err.println(e);
            }
        }
    }

    public static void addNewChild(User user,String lesson) throws IOException {
            Path p = Paths.get("C:\\Users\\home\\Desktop\\Venia\\src\\sample\\"+lesson+".txt");

            String s1 = user.name + ";" + user.lastname + System.lineSeparator();
            try {
                Files.write(p, s1.getBytes(), StandardOpenOption.APPEND);
            } catch (IOException e) {
                System.err.println(e);
            }

    }

    public String getLogin()
    {
        return login;
    }

    public String getPass()
    {
        return pass;
    }

    public String getName()
    {
        return name;
    }

    public String getLastname()
    {
        return lastname;
    }

    public String getType()
    {
        return type;
    }

    public void setLogin(String login)
    {
        this.login=login;
    }

    public void setPass(String pass)
    {
        this.pass=pass;
    }

    public void setName(String name)
    {
        this.name=name;
    }

    public void setLastname(String lastname)
    {
        this.lastname=lastname;
    }

    public void setType(String type)
    {
        this.type=type;
    }

    public void setMarks(String marks){this.marks=marks;}

    public String getMarks(){return this.marks;}



}
