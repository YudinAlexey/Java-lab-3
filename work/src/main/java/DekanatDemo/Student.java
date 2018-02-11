package DekanatDemo;
import java.util.ArrayList;

import static java.lang.System.exit;

public class Student {
    private int ID;
    private String Fio;
    private int Num;
    private ArrayList<Integer> Marks=new ArrayList<Integer>();
    private Group group;
    Student(int id, String name){
        this.ID=id;
        this.Fio=name;
    }
    public void setGroup(Group group) {
        this.group = group;
    }
    public int getID() {
        return ID;
    }

    public String getFio() {
        return Fio;
    }

    public int getNum() {
        return Num;
    }

    public ArrayList<Integer> getMarks() {
        return Marks;
    }

    public Group getGroup() {
        return group;
    }
    void addMark(int newMark) {
        Marks.add(newMark);
        calcNum();
    }

    private void calcNum(){
        Num=Marks.size();
    }
    public double averageMarks(){
        calcNum();
        double SumMarks=0;
        for (int a:Marks){
            SumMarks+=a;
        }
        if (Num>0) {
            return SumMarks/Num;
        } else {
            //System.out.println("Нет оценок у студента");
            //exit (-1);
            return -1;
        }
    }
}
