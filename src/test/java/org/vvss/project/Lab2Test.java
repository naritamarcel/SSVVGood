package org.vvss.project;

import org.junit.Before;
import org.junit.Test;
import org.vvss.project.Domain.Student;
import org.vvss.project.Repository.StudentRepo;
import org.vvss.project.Service.ServiceStudent;
import org.vvss.project.Validator.StudentValidator;
import org.vvss.project.Validator.ValidationException;

import static org.junit.Assert.*;

/**
 * Unit test for simple App.
 */
public class Lab2Test
{
    ServiceStudent serviceStudent;

    @Before
    public void init(){
        StudentRepo rep = new StudentRepo(new StudentValidator(), "studenti.xml");
        serviceStudent = new ServiceStudent(rep);
    }

    @Test
    public void shouldAnswerWithTrue()
    {
        assertTrue( true );
    }

    @Test
    public void testAddStudent()
    {
        Student stud = new Student("15", "Merceee", 935, "nrt@scs.ubbcluj.com", "Teacher");

        assertNull(serviceStudent.find("15"));
        serviceStudent.add(stud);
        assertNotNull(serviceStudent.find("15"));
    }

    @Test
    public void testAddStudentInvalid()
    {
        Student stud = new Student(" ", "Merceee", 935, "nrt@scs.ubbcluj.com", "Teacher");
        try {
            serviceStudent.add(stud);
            assert (false);
        } catch (ValidationException ex){
            assertEquals("\nID invalid",ex.getMessage());
        }
        assertNull(serviceStudent.find(" "));
    }
}
