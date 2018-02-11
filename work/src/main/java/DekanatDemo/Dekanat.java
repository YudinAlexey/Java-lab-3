package DekanatDemo;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;
import java.lang.*;

public class Dekanat {

    private ArrayList<Student> AllStudents=new ArrayList<Student>();
    private ArrayList<Group> Groups=new ArrayList<Group>();

    public ArrayList<Group> getGroups() {
        return Groups;
    }
    public ArrayList<Student> getAllStudents() {
        return AllStudents;
    }

    public void makeStudents(){
        try {
            Scanner studentSc = new Scanner(getClass().getResourceAsStream("/Students.txt"));
            int i=0;

            while (studentSc.hasNext()){
                Student tempStud = new Student(i++,studentSc.nextLine());
                Group randomGroup=Groups.get(ThreadLocalRandom.current().nextInt(0,Groups.size()));
                tempStud.setGroup(randomGroup);
                randomGroup.addStudent(tempStud);
                AllStudents.add(tempStud);
            }
        } catch (Exception e){
            e.printStackTrace();
        }

    }
    public void makeGroup(){
        try {
            Scanner groupSc = new Scanner(getClass().getResourceAsStream("/Group.txt"));
            while (groupSc.hasNext()){
                Groups.add(new Group(groupSc.next()));
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }
    public void addRandomMarks(){
        for (Student a:AllStudents){
            a.addMark(ThreadLocalRandom.current().nextInt(2,6));
        }
    }
    public int clearStudent(){
        int clearNum=0;
        for (int i =0; i<AllStudents.size();i++) {
            Student stud = AllStudents.get(i);
            if(stud.averageMarks()<3&&stud.averageMarks()>0){
                outStudent(stud);
                clearNum++;
            }
        }
        return clearNum;
    }
    private void outStudent(Student out){
        out.getGroup().removeStudent(out);
        AllStudents.remove(out);
        printOut("студент "+out.getFio()+" отчислен(-a)");
    }

    public void crossGroups(int id, Group A, Group B){
        Student cross = A.findId(id);
        cross.setGroup(B);
        B.addStudent(cross);
        A.removeStudent(cross);
        printOut("студент "+cross.getFio()+" переведен из "+A.getTitle()+" в группу "+B.getTitle());
    }
    public void saveData(){
        try{
            Writer file = new FileWriter("StudentList.txt");
            for(Student stud: AllStudents) {
                String out=stud.getID()+". "+stud.getFio()+"| группа: "+stud.getGroup().getTitle()+" средний балл: "+stud.averageMarks()+"\n";
                file.write(out);
            }
            file.close();
        } catch(Exception e){
            e.printStackTrace();
        }
    }
    public void chooseHeads(){
        for (Group a:Groups){
            a.chooseHead();
            printOut("студент "+a.getHead().getFio()+" избран старостой группы "+a.getTitle());
        }
    }
    private void printOut(String out){
        try{
            Writer file = new FileWriter("log.txt",true);
            file.write(out+"\n");
            file.close();
        } catch(Exception e){
            e.printStackTrace();
        }
    }
    public Student findId(int id){
        Student foundById = null;
        for (Student stud: AllStudents) {
            if(id==stud.getID()){
                foundById=stud;
            }
        }
        return foundById;
    }

    public ArrayList<Student> findFio(String FIO){
        ArrayList<Student> foundByFio=new ArrayList<Student>();
        for (Student stud: AllStudents) {
            if(FIO.equals(stud.getFio())){
                foundByFio.add(stud);
            }
        }
        return foundByFio;
    }
}
