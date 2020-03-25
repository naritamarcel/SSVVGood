package org.vvss.project;

import org.vvss.project.Repository.NoteRepo;
import org.vvss.project.Repository.StudentRepo;
import org.vvss.project.Repository.TemeRepo;
import org.vvss.project.Service.ServiceNote;
import org.vvss.project.Service.ServiceStudent;
import org.vvss.project.Service.ServiceTeme;
import org.vvss.project.UI.UI;
import org.vvss.project.Validator.NotaValidator;
import org.vvss.project.Validator.StudentValidator;
import org.vvss.project.Validator.TemeValidator;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        StudentRepo rep = new StudentRepo(new StudentValidator(), "studenti.xml");
        TemeRepo repo = new TemeRepo(new TemeValidator(), "teme.xml");
        NoteRepo r = new NoteRepo(new NotaValidator());
        ServiceStudent srv = new ServiceStudent(rep);
        ServiceTeme serv = new ServiceTeme(repo);
        ServiceNote sv = new ServiceNote(r);
        UI ui = new UI(srv, serv, sv);
        ui.show();
    }
}
