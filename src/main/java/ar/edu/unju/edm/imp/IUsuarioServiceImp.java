package ar.edu.unju.edm.imp;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.unju.edm.model.Usaurio;
import ar.edu.unju.edm.services.IUsuarioService;
import ar.edu.unju.edm.until.ListadoUsuario;

@Service
public class IUsuarioServiceImp implements IUsuarioService{

    @Autowired
    ListadoUsuario listadoUsuario;

    @Override
    public void guardarUsuario(Usaurio userToSave) {
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
        
        Usaurio auxiliar = new Usaurio();

        auxiliar =  buscarUsuario(dni);

        auxiliar.setEstado(false);
        
    }

    @Override
    public void modificarrUsuario(Usaurio usuario) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public Usaurio buscarUsuario(Long dni) {

        Usaurio auxiliar = new Usaurio();

        for(int i=0;i < listadoUsuario.getListado().size();i++){
            if (listadoUsuario.getListado().get(i).getDni().equals(dni)) {
                auxiliar =  listadoUsuario.getListado().get(i);
            }
        }
        
        return auxiliar;
    }
    
}
