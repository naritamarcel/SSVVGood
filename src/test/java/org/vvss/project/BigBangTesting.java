package org.vvss.project;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.vvss.project.Domain.Nota;
import org.vvss.project.Domain.Student;
import org.vvss.project.Domain.Teme;
import org.vvss.project.Repository.NoteRepo;
import org.vvss.project.Repository.StudentRepo;
import org.vvss.project.Repository.TemeRepo;
import org.vvss.project.Service.ServiceNote;
import org.vvss.project.Service.ServiceStudent;
import org.vvss.project.Service.ServiceTeme;
import org.vvss.project.Validator.NotaValidator;
import org.vvss.project.Validator.StudentValidator;
import org.vvss.project.Validator.TemeValidator;

import java.util.*;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

public class BigBangTesting {
    ServiceStudent serviceStudent;
    ServiceTeme serviceTeme;
    ServiceNote serviceNote;
    NoteRepo noteR;

    @Before
    public void init() {
        StudentRepo rep = new StudentRepo(new StudentValidator(), "student_test.xml");
        serviceStudent = new ServiceStudent(rep);
        TemeRepo temeRepo = new TemeRepo(new TemeValidator(), "assignment_test.xml");
        serviceTeme = new ServiceTeme(temeRepo);
        noteR = new NoteRepo(new NotaValidator());
        serviceNote = new ServiceNote(noteR);
    }

    @Test
    public void testStudent() {
        String id = "15";
        String nume = "Nume";
        int gr = 931;
        String em = "a@scs.ubbcluj.com";
        String prof = "Teacher";
        Student stud = new Student(id, nume, gr, em, prof);
        assertNull(serviceStudent.find(id));
        serviceStudent.add(stud);
        assertNotNull(serviceStudent.find(id));
    }

    @Test
    public void testAssignment() {
        Integer id = 1;
        String description = "Do something";
        Integer deadlineWeek = 4;
        Integer deliverWeek = 3;
        Teme t = new Teme(id, description, deliverWeek, deadlineWeek);
        assertNull(serviceTeme.find(id));
        serviceTeme.add(t);
        assertNotNull(serviceTeme.find(id));
    }

    @Test
    public void testNote() {
        Map.Entry<String, Integer> id = new AbstractMap.SimpleEntry<>("id", 10);
        Student s = new Student("15", "Nume", 921, "a@scs.ubbcluj.com", "Teacher");
        Teme t = new Teme(1, "Do something", 4, 3);
        float valoare = 9.5f;
        int week = 5;
        Nota n = new Nota(id, s, t, valoare, week);
        assertNull(noteR.findOne(id));
        serviceNote.add(n, "src/test/java/Catalog.xml");
        assertNotNull(noteR.findOne(id));
    }

    @Test
    public void testAll() {
        testStudent();
        testAssignment();
        testNote();
    }

    @After
    public void clearTests() {
        Iterator<Student> studentIterator = serviceStudent.all().iterator();
        List<Student> studentList = new ArrayList<>();
        studentIterator.forEachRemaining(studentList::add);
        studentList.forEach(student -> serviceStudent.del(student.getID()));
        Iterator<Teme> iterator = serviceTeme.all().iterator();
        List<Teme> assignList = new ArrayList<>();
        iterator.forEachRemaining(assignList::add);
        assignList.forEach(assign -> serviceTeme.del(assign.getID()));
    }
}
