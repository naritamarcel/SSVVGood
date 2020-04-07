package org.vvss.project;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.vvss.project.Domain.Teme;
import org.vvss.project.Repository.TemeRepo;
import org.vvss.project.Service.ServiceTeme;
import org.vvss.project.Validator.TemeValidator;
import org.vvss.project.Validator.ValidationException;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

public class TestAssignment {

    ///Homework lab3WBT

    ServiceTeme serviceTeme;

    @Before
    public void init() {
        TemeRepo rep = new TemeRepo(new TemeValidator(), "assignment_test.xml");
        serviceTeme = new ServiceTeme(rep);
    }

    @Test
    public void testAssignmentValid() {
        Integer deadlineWeek = 4;
        Integer deliverWeek = 3;
        Teme t = new Teme(1, "description", deliverWeek, deadlineWeek);
        assertNull(serviceTeme.find(1));
        serviceTeme.add(t);
        assertNotNull(serviceTeme.find(1));
    }

    @Test(expected = ValidationException.class)
    public void testAssignmentIdNull() {
        Integer deadlineWeek = 4;
        Integer deliverWeek = 3;
        Teme t = new Teme(null, "description", deliverWeek, deadlineWeek);
        assertNull(serviceTeme.find(null));
        serviceTeme.add(t);
    }

    @Test(expected = ValidationException.class)
    public void testAssignmentIdSmallerThan1() {
        Integer deadlineWeek = 4;
        Integer deliverWeek = 3;
        Teme t = new Teme(-2, "description", deliverWeek, deadlineWeek);
        assertNull(serviceTeme.find(-1));
        serviceTeme.add(t);
    }

    @Test(expected = ValidationException.class)
    public void testDeadlineWeekBiggerThan14() {
        Integer deadlineWeek = 15;
        Integer deliverWeek = 3;
        Teme t = new Teme(2, "description", deliverWeek, deadlineWeek);
        assertNull(serviceTeme.find(2));
        serviceTeme.add(t);
    }

    @Test(expected = ValidationException.class)
    public void testDeadlineWeekSmallerThan1() {
        Integer deadlineWeek = -1;
        Integer deliverWeek = 3;
        Teme t = new Teme(2, "description", deliverWeek, deadlineWeek);
        assertNull(serviceTeme.find(2));
        serviceTeme.add(t);
    }

    @Test(expected = ValidationException.class)
    public void testDeadlineWeekSmallerThanDeliverWeek() {
        Integer deadlineWeek = 4;
        Integer deliverWeek = 13;
        Teme t = new Teme(3, "description", deliverWeek, deadlineWeek);
        assertNull(serviceTeme.find(3));
        serviceTeme.add(t);
    }

    @Test(expected = ValidationException.class)
    public void testDeliverWeekBiggerThan14() {
        Integer deadlineWeek = 4;
        Integer deliverWeek = 15;
        Teme t = new Teme(3, "description", deliverWeek, deadlineWeek);
        assertNull(serviceTeme.find(3));
        serviceTeme.add(t);
    }

    @Test(expected = ValidationException.class)
    public void testDeliverWeekSmallerThan1() {
        Integer deadlineWeek = 4;
        Integer deliverWeek = -1;
        Teme t = new Teme(3, "description", deliverWeek, deadlineWeek);
        assertNull(serviceTeme.find(3));
        serviceTeme.add(t);
    }

    @Test(expected = ValidationException.class)
    public void testDescriptionNull() {
        Integer deadlineWeek = 4;
        Teme t = new Teme(3, null, 1, deadlineWeek);
        assertNull(serviceTeme.find(3));
        serviceTeme.add(t);
    }

    @Test(expected = ValidationException.class)
    public void testEmptyDescription() {
        Integer deadlineWeek = 4;
        Teme t = new Teme(3, "", 1, deadlineWeek);
        assertNull(serviceTeme.find(3));
        serviceTeme.add(t);
    }

    @Test(expected = ValidationException.class)
    public void testIdDeadlineInvalid() {
        Integer id = null;
        Integer deadlineWeek = -4;
        Teme t = new Teme(id, "description", 1, deadlineWeek);
        assertNull(serviceTeme.find(id));
        serviceTeme.add(t);
    }

    @Test(expected = ValidationException.class)
    public void testIdDeliverInvalid() {
        Integer id = null;
        Integer deadlineWeek = 4;
        Integer deliverWeek = -1;
        Teme t = new Teme(id, "description", deliverWeek, deadlineWeek);
        assertNull(serviceTeme.find(id));
        serviceTeme.add(t);
    }

    @Test(expected = ValidationException.class)
    public void testDeadlineDeliverInvalid() {
        Integer deadlineWeek = -4;
        Integer deliverWeek = -1;
        Teme t = new Teme(1, "description", deliverWeek, deadlineWeek);
        assertNull(serviceTeme.find(1));
        serviceTeme.add(t);
    }

    @Test(expected = ValidationException.class)
    public void testAllInvalid() {
        Integer id = null;
        Teme t = new Teme(id, "", 7, 4);
        assertNull(serviceTeme.find(id));
        serviceTeme.add(t);
    }

    @After
    public void clearTests() {
        Iterator<Teme> iterator = serviceTeme.all().iterator();
        List<Teme> assignList = new ArrayList<>();
        iterator.forEachRemaining(assignList::add);
        assignList.forEach(assign -> serviceTeme.del(assign.getID()));
    }
}
