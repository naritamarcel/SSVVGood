package org.vvss.project.Validator;

public interface Validator<E> {
    public String validate(E el);
}
