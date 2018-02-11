package DekanatDemo;
import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class Group {
    private String Title;
    private ArrayList<Student> Students=new ArrayList<Student>();
    private int Num;
    private Student Head;
    Group(String Name){
        this.Title=Name;
    }
    public String getTitle() {
        return Title;
    }
    public void addStudent(Student student){
        Students.add(student);
    }
    public Student getHead() {
        return Head;
    }
    public int getNum() {
        Num=Students.size();
        return Num;
    }
    public void chooseHead(){
        getNum();
        int rand= ThreadLocalRandom.current().nextInt(0,Num);
        Head=Students.get(rand);
    }
    public double groupAverage(){
        int numSt=getNum();
        double average=0;
        double Mark;
        for (Student stud: Students){
            Mark=stud.averageMarks();
            if (Mark!=-1) {
                average += Mark;
            } else numSt-=1;
        }
        return average/numSt;
    }
    public Student findId(int id){
        Student foundById = null;
        for (Student stud: Students) {
            if(id==stud.getID()){
               foundById=stud;
            } 
        }
        return foundById;
    }

    public ArrayList<Student> findFio(String FIO){
        ArrayList<Student> foundByFio=new ArrayList<Student>();
        for (Student stud: Students) {
            if(FIO.equals(stud.getFio())){
                foundByFio.add(stud);
            }
        }
        return foundByFio;
    }

    void removeStudent(Student out){
        Students.remove(out);
        if(out.equals(Head)){
            chooseHead();
        }
    }

}
