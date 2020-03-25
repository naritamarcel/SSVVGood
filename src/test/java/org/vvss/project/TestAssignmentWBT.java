package org.vvss.project;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.vvss.project.Domain.Student;
import org.vvss.project.Domain.Teme;
import org.vvss.project.Repository.StudentRepo;
import org.vvss.project.Repository.TemeRepo;
import org.vvss.project.Service.ServiceStudent;
import org.vvss.project.Service.ServiceTeme;
import org.vvss.project.Validator.StudentValidator;
import org.vvss.project.Validator.TemeValidator;
import org.vvss.project.Validator.ValidationException;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.junit.Assert.*;

public class TestAssignmentWBT {

    ServiceTeme serviceTeme;
    TemeRepo repo;

    @Before
    public void init(){
        repo = new TemeRepo(new TemeValidator(), "teme.xml");
        serviceTeme = new ServiceTeme(repo);
    }

    @Test
    public void testAddAssignment()
    {
        Teme assignment = new Teme(6, "done", 6, 8);
        assertNull(serviceTeme.find(6));
        serviceTeme.add(assignment);
        assertNotNull(serviceTeme.find(6));
        assertEquals(repo.findOne(6), serviceTeme.find(6));
    }

    @Test
    public void testAddAssignmentInvalid()
  {
        Teme assignment = new Teme(7, "done", 17, 16);
        try {
            serviceTeme.add(assignment);
            assert (false);
        } catch (ValidationException ex){
            assertEquals("\nDeadline invalid",ex.getMessage());
        }
        assertNull(serviceTeme.find(7));
        assertNull(repo.findOne(7));
    }

    @After
    public void clearTests() {
        Iterator<Teme> assignmentIterator = serviceTeme.all().iterator();
        List<Teme> assignmentList = new ArrayList<>();
        assignmentIterator.forEachRemaining(assignmentList::add);
        assignmentList.forEach(assignment -> serviceTeme.del(assignment.getID()));
    }
}
