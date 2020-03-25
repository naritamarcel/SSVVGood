package org.vvss.project.Service;

import org.vvss.project.Domain.Nota;
import org.vvss.project.Repository.NoteRepo;

public class ServiceNote {
    private NoteRepo rep;

    public ServiceNote(NoteRepo rep) {
        this.rep = rep;
    }

    /**
     * Adauga Nota
     * Returneaza Nota adaugata
     */
    public Nota add(Nota s, String fd) {
        return rep.save(s, fd);
    }

    public Iterable<Nota> all() {
        return rep.findAll();
    }
}

