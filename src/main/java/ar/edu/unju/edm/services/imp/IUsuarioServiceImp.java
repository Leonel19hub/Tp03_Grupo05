package ar.edu.unju.edm.services.imp;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.unju.edm.Controller.UsuarioController;
import ar.edu.unju.edm.model.Usuario;
import ar.edu.unju.edm.repository.UsuarioRepository;
import ar.edu.unju.edm.services.IUsuarioService;
import ar.edu.unju.edm.util.ListadoUsuario;

@Service
public class IUsuarioServiceImp implements IUsuarioService{

    private static final Log GRUPO05 = LogFactory.getLog(UsuarioController.class);
	
	@Autowired
	ListadoUsuario lista;

    @Autowired
    UsuarioRepository usuarioRepository;

    @Override
    public void guardarUsuario(Usuario usuario) {
        
        usuario.setEstado(true);
		// lista.getListadoUsuario().add(usuario);
        usuarioRepository.save(usuario);
        
    }

    @Override
    public void eliminarUsuario(Integer id) throws Exception {
        
        // for (int i = 0; i < lista.getListadoUsuario().size(); i++) {			
		// 	if (lista.getListadoUsuario().get(i).getDni().equals(id)) {				
		// 		lista.getListadoUsuario().get(i).setEstado(false);		
		// 	}            
        // }	
        Usuario auxiliar = new Usuario();
        auxiliar = buscarUsuario(id);
        auxiliar.setEstado(false);
        usuarioRepository.save(auxiliar);
        
    }

    @Override
    public void modificarUsuario(Usuario usuario) {
        
        // for (int i = 0; i < lista.getListadoUsuario().size(); i++) {			
		// 	if (lista.getListadoUsuario().get(i).getDni().equals(usuario.getDni())) {
		// 		GRUPO05.error("encontrando usuario "+usuario.getDni());
		// 		lista.getListadoUsuario().set(i, usuario);			
		// 	}            
        // }
        usuarioRepository.save(usuario);
        
    }

    @Override
    public List<Usuario> listarUsuarios() {
        
        List<Usuario> auxiliar = new ArrayList<>();
        auxiliar = (List<Usuario>) usuarioRepository.findAll();
        List<Usuario> auxiliar2 = new ArrayList<>();
		// GRUPO05.info("ingresando al metodo: listarUsuarios");
		for (int i = 0; i < auxiliar.size(); i++) {
			// GRUPO05.error("recorriendo: listadoUsuario"+auxiliar.get(i).getDni());
			
			if (auxiliar.get(i).getEstado()==true) {
				auxiliar2.add(auxiliar.get(i));		
			}            
        }
        // auxiliar = (List<Usuario>) usuarioRepository.findAll();
		return auxiliar2;
    }

    @Override
    public Usuario buscarUsuario(Integer id) throws Exception{
        
        Usuario usuarioEncontrado = new Usuario();
		// for (int i = 0; i < lista.getListadoUsuario().size(); i++) {
			
		// 	if (lista.getListadoUsuario().get(i).getDni().equals(id)) {

		// 		usuarioEncontrado = lista.getListadoUsuario().get(i);		
		// 	}            
        // }

        usuarioEncontrado = usuarioRepository.findById(id).orElseThrow(()-> new Exception("Usuario no encontrado"));
		return usuarioEncontrado;
    }
}