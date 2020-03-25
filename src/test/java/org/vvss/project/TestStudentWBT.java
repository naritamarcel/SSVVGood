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

import static org.junit.Assert.*;

public class TestStudentWBT {

    ServiceStudent serviceStudent;
    StudentRepo repo;

    @Before
    public void init(){
        repo = new StudentRepo(new StudentValidator(), "assignmenti.xml");
        serviceStudent = new ServiceStudent(repo);
    }

    @Test
    public void testAddStudent()
    {
        String id = "17";
        Student stud = new Student(id, "Merceee", 935, "nrt@scs.ubbcluj.com", "Teacher");
        assertNull(serviceStudent.find(id));
        serviceStudent.add(stud);
        assertNotNull(serviceStudent.find(id));
        assertEquals(repo.findOne(id), serviceStudent.find(id));
    }

    @Test
    public void testAddStudentInvalid()
    {
        String id = "";
        Student stud = new Student(id, "Merceee", 935, "nrt@scs.ubbcluj.com", "Teacher");
        try {
            serviceStudent.add(stud);
            assert (false);
        } catch (ValidationException ex){
            assertEquals("\nID invalid",ex.getMessage());
        }
        assertNull(serviceStudent.find(id));
        assertNull(repo.findOne(id));
    }

    @After
    public void clearTests() {
        Iterator<Student> assignmentIterator = serviceStudent.all().iterator();
        List<Student> assignmentList = new ArrayList<>();
        assignmentIterator.forEachRemaining(assignmentList::add);
        assignmentList.forEach(assignment -> serviceStudent.del(assignment.getID()));
    }
}
