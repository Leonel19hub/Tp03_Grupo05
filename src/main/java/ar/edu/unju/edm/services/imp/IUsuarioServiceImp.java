package ar.edu.unju.edm.services.imp;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.unju.edm.model.Usuario;
import ar.edu.unju.edm.services.IUsuarioService;
import ar.edu.unju.edm.util.ListadoUsuario;

@Service
public class IUsuarioServiceImp implements IUsuarioService{

    @Autowired
    ListadoUsuario listadoUsuario;

    @Override
    public void guardarUsuario(Usuario userToSave) {
        userToSave.setEstado(true);
        listadoUsuario.getListado().add(userToSave);
        
    }

    @Override
    public ListadoUsuario mostrarUsuarios() {

        // List <Usaurio> listadoAuxiliar = new ArrayList();
        ListadoUsuario auxiliar = new ListadoUsuario();

        for(int i=0;i < listadoUsuario.getListado().size();i++){
            if (listadoUsuario.getListado().get(i).getEstado() == true) {
                // auxiliar.add(listadoUsuario.getListado().get(i));
            }
        }

        return auxiliar;
    }

    @Override
    public void eliminarUsuario(Long dni) {
        
        Usuario auxiliar = new Usuario();

        auxiliar =  buscarUsuario(dni);

        auxiliar.setEstado(false);
        
    }

    @Override
    public void modificarrUsuario(Usuario userToEdit) {
        
        for(int i=0;i<listadoUsuario.getListado().size();i++){
            if(listadoUsuario.getListado().get(i).getDni().equals(userToEdit.getDni())){
                listadoUsuario.getListado().set(1, userToEdit);
            }
        }
        
    }

    @Override
    public Usuario buscarUsuario(Long dni) {

        Usuario auxiliar = new Usuario();

        for(int i=0;i < listadoUsuario.getListado().size();i++){
            if (listadoUsuario.getListado().get(i).getDni().equals(dni)) {
                auxiliar =  listadoUsuario.getListado().get(i);
            }
        }
        
        return auxiliar;
    }
    
}
