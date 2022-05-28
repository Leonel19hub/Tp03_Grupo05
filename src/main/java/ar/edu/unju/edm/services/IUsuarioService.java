package ar.edu.unju.edm.services;

import java.util.List;

import org.springframework.stereotype.Service;

import ar.edu.unju.edm.model.Usaurio;
import ar.edu.unju.edm.until.ListadoUsuario;

@Service
public interface IUsuarioService {

    public void guardarUsuario(Usaurio usuario);
    public ListadoUsuario mostrarUsuarios();
    public void eliminarUsuario(Long dni);
    public void modificarrUsuario(Usaurio usuario);
    public Usaurio buscarUsuario(Long dni);

}
