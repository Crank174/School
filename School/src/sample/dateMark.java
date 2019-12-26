package sample;

public class dateMark {
    String date;
    String mark;
    dateMark(String date,String mark)
    {
        this.date=date;
        this.mark=mark;

    }

    public void setDate(String date)
    {
        this.date=date;
    }

    public void setMark(String mark)
    {
        this.mark=mark;
    }

    public String getDate()
    {
        return this.date;
    }

    public String getMark()
    {
        return this.mark;
    }
}
