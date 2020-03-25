package org.vvss.project;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.vvss.project.Domain.Student;
import org.vvss.project.Repository.StudentRepo;
import org.vvss.project.Service.ServiceStudent;
import org.vvss.project.Validator.StudentValidator;
import org.vvss.project.Validator.ValidationException;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

public class TestStudent {

    ServiceStudent serviceStudent;

    @Before
    public void init(){
        StudentRepo rep = new StudentRepo(new StudentValidator(), "student_test.xml");
        serviceStudent = new ServiceStudent(rep);
    }

    @Test
    public void testAddStudentValid() {
        Student stud = new Student("67", "Merceee", 935, "nrt@scs.ubbcluj.com", "Teacher");

        assertNull(serviceStudent.find("67"));
        serviceStudent.add(stud);
        assertNotNull(serviceStudent.find("67"));
    }

    @Test(expected = ValidationException.class)
    public void testAddStudentIdNull() {

        Student stud = new Student(null, "Merceee", 935, "nrt@scs.ubbcluj.com", "Teacher");

        serviceStudent.add(stud);
    }

    @Test(expected = ValidationException.class)
    public void testAddStudentIdEmpty() {
        String id = "";
        Student stud = new Student(id, "Merceee", 935, "nrt@scs.ubbcluj.com", "Teacher");

        assertNull(serviceStudent.find(id));
        serviceStudent.add(stud);
    }

    @Test(expected = ValidationException.class)
    public void testAddStudentIdNegative() {

        Student stud = new Student("-2", "Merceee", 935, "nrt@scs.ubbcluj.com", "Teacher");

        assertNull(serviceStudent.find("-2"));
        serviceStudent.add(stud);
    }

    @Test(expected = ValidationException.class)
    public void testAddStudentIdNaN() {
        String id = "not";
        Student stud = new Student(id, "Merceee", 935, "nrt@scs.ubbcluj.com", "Teacher");

        assertNull(serviceStudent.find(id));
        serviceStudent.add(stud);
    }

    @Test(expected = ValidationException.class)
    public void testAddStudentNameNotValid() {
        Student stud = new Student("35", ":;;:", 935, "nrt@scs.ubbcluj.com", "Teacher");

        assertNull(serviceStudent.find("35"));
        serviceStudent.add(stud);
    }

    @Test(expected = NullPointerException.class)
    public void testAddStudentNameNull() {
        Student stud = new Student("35", null, 935, "nrt@scs.ubbcluj.com", "Teacher");

        assertNull(serviceStudent.find("35"));
        serviceStudent.add(stud);
    }

    @Test(expected = ValidationException.class)
    public void testAddStudentGroupNegativeNumber() {
        Student stud = new Student("35", "Merceee", -2, "nrt@scs.ubbcluj.com", "Teacher");

        assertNull(serviceStudent.find("35"));
        serviceStudent.add(stud);
    }

    @Test(expected = ValidationException.class)
    public void testAddStudentGroupInvalid() {
        Student stud = new Student("35", "Merceee", 111, "nrt@scs.ubbcluj.com", "Teacher");

        assertNull(serviceStudent.find("35"));
        serviceStudent.add(stud);
    }

    @Test(expected = ValidationException.class)
    public void addStudentEmailInvalid() {
        Student stud = new Student("35", "Merceee", 935, "ii", "Teacher");

        assertNull(serviceStudent.find("35"));
        serviceStudent.add(stud);
    }

    @Test(expected = NullPointerException.class)
    public void addStudentEmailNull() {
        Student stud = new Student("35", "Merceee", 935, null, "Teacher");
        assertNull(serviceStudent.find("35"));
        serviceStudent.add(stud);
    }

    @Test(expected = ValidationException.class)
    public void testAddStudentEmailEmpty() {

        Student stud = new Student("67", "Merceee", 935, "", "Teacher");
        assertNull(serviceStudent.find("67"));
        serviceStudent.add(stud);
    }

    @Test(expected = ValidationException.class)
    public void addStudentProfessorEmpty() {

        Student stud = new Student("67", "Merceee", 935, "nrt@scs.ubbcluj.com", "");
        assertNull(serviceStudent.find("67"));
        serviceStudent.add(stud);
    }

    @Test(expected = NullPointerException.class)
    public void addStudentProfessorNull() {

        Student stud = new Student("67", "Merceee", 935, "nrt@scs.ubbcluj.com", null);

        assertNull(serviceStudent.find("67"));
        serviceStudent.add(stud);
    }

    @After
    public void clearTests() {
        Iterator<Student> studentIterator = serviceStudent.all().iterator();
        List<Student> studentList = new ArrayList<>();
        studentIterator.forEachRemaining(studentList::add);
        studentList.forEach(student -> serviceStudent.del(student.getID()));
    }
}
