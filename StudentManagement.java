import java.sql.*;
import java.util.Scanner;
public class StudentManagement{
static Scanner sc=new Scanner(System.in);
public static void main(String[] args){
while(true){
System.out.println("---Student Management System---");
System.out.println("1.Add Student");
System.out.println("2.View Students");
System.out.println("3.Update Student");
System.out.println("4.Delete Student");
System.out.println("5.Exit");
System.out.print("Choose option:");
int choice=sc.nextInt();
switch(choice){
case 1:addStudent();break;
case 2:viewStudents();break;
case 3:updateStudent();break;
case 4:deleteStudent();break;
case 5:System.exit(0);
default:System.out.println("Invalid option");
}
}
}
static void addStudent(){
try(Connection con=DBConnection.getConnection()){
System.out.print("Enter ID:");
int id=sc.nextInt();
System.out.print("Enter Name:");
String name=sc.next();
System.out.print("Enter Age:");
int age=sc.nextInt();
System.out.print("Enter Course:");
String course=sc.next();
PreparedStatement ps=con.prepareStatement("INSERT INTO students VALUES(?,?,?,?)");
ps.setInt(1,id);
ps.setString(2,name);
ps.setInt(3,age);
ps.setString(4,course);
ps.executeUpdate();
System.out.println("Student Added");
}catch(Exception e){
System.out.println("Error");
}
}
static void viewStudents(){
try(Connection con=DBConnection.getConnection()){
Statement st=con.createStatement();
ResultSet rs=st.executeQuery("SELECT * FROM students");
while(rs.next()){
System.out.println(rs.getInt(1)+" "+rs.getString(2)+" "+rs.getInt(3)+" "+rs.getString(4));
}
}catch(Exception e){
System.out.println("Error");
}
}
static void updateStudent(){
try(Connection con=DBConnection.getConnection()){
System.out.print("Enter ID:");
int id=sc.nextInt();
System.out.print("New Course:");
String course=sc.next();
PreparedStatement ps=con.prepareStatement("UPDATE students SET course=? WHERE id=?");
ps.setString(1,course);
ps.setInt(2,id);
ps.executeUpdate();
System.out.println("Updated");
}catch(Exception e){
System.out.println("Error");
}
}
static void deleteStudent(){
try(Connection con=DBConnection.getConnection()){
System.out.print("Enter ID:");
int id=sc.nextInt();
PreparedStatement ps=con.prepareStatement("DELETE FROM students WHERE id=?");
ps.setInt(1,id);
ps.executeUpdate();
System.out.println("Deleted");
}catch(Exception e){
System.out.println("Error");
}
}
}
