package edu.escuelaing.arep.ASE.app.persistencia;

import java.util.Collection;

public interface CRUD<T,I,D>{

    public void guardar(I identificador, D info);

    public void eliminar(I identificador);

    public T buscar(I identificador);

    public Collection<T> buscarTodos();


}
