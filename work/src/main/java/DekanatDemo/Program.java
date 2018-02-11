package DekanatDemo;
public class Program {
    public static void main(String[] args) {
        Dekanat D = new Dekanat();
        D.makeGroup();
        D.makeStudents();
        D.chooseHeads();
        D.addRandomMarks();
        D.addRandomMarks();
        D.addRandomMarks();
        D.addRandomMarks();
        System.out.println("общее число студентов " +D.getAllStudents().size());
        D.crossGroups(D.getGroups().get(0).getHead().getID(),D.getGroups().get(0),D.getGroups().get(1));
        D.clearStudent();
        System.out.println("общее число студентов после отчисления " +D.getAllStudents().size());
        D.saveData();
    }
}

