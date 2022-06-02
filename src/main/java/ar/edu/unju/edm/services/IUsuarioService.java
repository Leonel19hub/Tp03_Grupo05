package ar.edu.unju.edm.services;

import org.springframework.stereotype.Service;

import ar.edu.unju.edm.model.Usuario;
import ar.edu.unju.edm.util.ListadoUsuario;

@Service
public interface IUsuarioService {

    public void guardarUsuario(Usuario usuario);
    public ListadoUsuario mostrarUsuarios();
    public void eliminarUsuario(Long dni);
    public void modificarrUsuario(Usuario usuario);
    public Usuario buscarUsuario(Long dni);

}
