import java.util.*;
import java.io.*;
public class StudentManagement
{
    static class Student
    {
        String name;
        int id;
        int age;
        String course;
    }
    static ArrayList<Student> students = new ArrayList<>();
    static int nextId = 1;
    static Scanner sc = new Scanner(System.in);
    public static void main(String args[])
    {
        loadFromFile();
        while(true)
        {
            System.out.println("Welcome to Student Management System!");
            System.out.println("1. Add Student");
            System.out.println("2. View Student");
            System.out.println("3. Search Student");
            System.out.println("4. Delete Student");
            System.out.println("5. Update Student");
            System.out.println("6. Exit");

            System.out.println("Enter your choice:");
            int choice = sc.nextInt();
            switch (choice) {
                case 1:
                    addStudent();
                    break;
                case 2:
                    viewStudents();
                    break;
                case 3:
                    searchStudent();
                    break;
                case 4:
                    deleteStudent();
                    break;
                case 5:
                    updateStudent();
                    break;
                case 6:
                    System.out.println("Exiting program");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice!");
            }
        }
    }
    static void addStudent()
    {
        sc.nextLine();

        System.out.println("Enter student name:");
        String name = sc.nextLine();

        System.out.println("Enter student age:");
        int age = sc.nextInt();

        sc.nextLine();

        System.out.println("Enter student course:");
        String course = sc.nextLine();

        Student s = new Student();
        s.name = name;
        s.id = nextId;
        nextId++;
        s.age = age;
        s.course = course;

        students.add(s);
        saveToFile();
        System.out.println("Student added successfully! ID: " + s.id);
    }
    static void viewStudents()
    {
        if(students.isEmpty())
        {
            System.out.println("No students data found!");
            return;
        }
        System.out.printf("%-10s %-15s %-10s %-15s\n", "ID", "Name", "Age", "Course");
        System.out.println("------------------------------------------------------");

        for(Student s : students)
        {
            System.out.printf("%-10d %-15s %-10d %-15s\n", s.id, s.name, s.age, s.course);
        }
    }
    static void searchStudent()
    {
        System.out.println("Enter student id to search:");
        int searchid = sc.nextInt();

        boolean found = false;
        
        for(Student s:students){
            if(s.id == searchid)
            {
                System.out.println("Student details!");
                System.out.println("ID:"+s.id);
                System.out.println("Name:"+s.name);
                System.out.println("age:"+s.age);
                System.out.println("course:"+s.course);
                System.out.println("------------------------------");

                found = true;
                break;
            }
        }
        if(!found)
        {
            System.out.println("Invalid id-Student not found!");
        }
    }
    static void deleteStudent()
    {
        System.out.println("Enter student id to delete:");
        int deleteid = sc.nextInt();

        boolean found = false;

        for(Student s : students)
        {
            if(s.id == deleteid)
            {
                students.remove(s);
                saveToFile();
                System.out.println("Student removed successfully!");

                found = true;
                break;
            }
        }
        if(!found)
        {
            System.out.println("Invalid id-Enter correct id!");
        }
    }
    static void updateStudent()
    {
        System.out.println("Enter student id to update:");
        int updateid = sc.nextInt();

        boolean found = false;

        for(Student s : students)
        {
            if(s.id == updateid)
            {
                sc.nextLine();

                System.out.println("Enter student name:");
                s.name = sc.nextLine();

                System.out.println("Enter student id:");
                s.id = sc.nextInt();

                System.out.println("Enter student age:");
                s.age = sc.nextInt();

                sc.nextLine();

                System.out.println("Enter student course:");
                s.course = sc.nextLine();

                saveToFile();
                System.out.println("Student details updated successfully!");

                found = true;
                break;
            }
        }
        if(!found)
        {
            System.out.println("Invalid Id!");
        }
    }
    static void saveToFile()
    {
        try
        {
            FileWriter fw = new FileWriter("students.txt");

            for(Student s : students)
            {
                fw.write(s.id + "," + s.name + "," + s.age + "," + s.course + "\n");
            }

            fw.close();
        }
        catch(Exception e)
        {
            System.out.println("Error saving file");
        }
    }
    static void loadFromFile()
    {
        try
        {
            File file = new File("students.txt");

            if(!file.exists())
            {
                return;
            }

            Scanner fileScanner = new Scanner(file);

            while(fileScanner.hasNextLine())
            {
                String line = fileScanner.nextLine();
                String parts[] = line.split(",");

                Student s = new Student();
                s.id = Integer.parseInt(parts[0]);
                s.name = parts[1];
                s.age = Integer.parseInt(parts[2]);
                s.course = parts[3];

                students.add(s);
                if(s.id >= nextId)
                {
                    nextId = s.id + 1;
                }
            }

            fileScanner.close();
        }
        catch(Exception e)
        {
            System.out.println("Error loading file");
        }
    }
    static boolean idExists(int id)
    {
        for(Student s : students)
        {
            if(s.id == id)
            {
                return true;
            }
        }
        return false;
    }
}