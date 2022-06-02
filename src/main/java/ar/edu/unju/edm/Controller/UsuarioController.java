package ar.edu.unju.edm.Controller;

import javax.validation.Valid;

import org.apache.commons.logging.Log;
// import org.apache.commons.logging.Log;
// import org.apache.juli.log;
import org.apache.juli.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unju.edm.model.Usuario;
import ar.edu.unju.edm.services.IUsuarioService;
import ar.edu.unju.edm.util.ListadoUsuario;

@Controller
public class UsuarioController {

    // Constanres con mayusculas
    private static final org.apache.juli.logging.Log MARCOS = LogFactory.getLog(UsuarioController.class);
    // private static final Log MARCOS=LogFactory.getLog(UsuarioController.class);
    // private static final Log MARCOS = (Log) LogFactory.getLog(UsuarioController.class);

    @Autowired
    Usuario nuevoUsuario;

    @Autowired
    IUsuarioService serviceUsuario;

    @GetMapping("/otroUsuario")
    public ModelAndView addUser(){

        ModelAndView modelView = new ModelAndView("cargarUsuario");
        // modelView.addObject("nuevoUsuario");

        modelView.addObject("usuario", nuevoUsuario);

        
        modelView.addObject("band", false);
        return modelView;

    }

    

    @PostMapping("/guardarUsuario")
    public String saveUser(@Valid @ModelAttribute ("usuario") Usuario userToSave, BindingResult resultado, Model model){

        // MARCOS.info("ingresando al metodo guardar Usuario: "+userToSave.getApellido());
        if(resultado.hasErrors()){
            MARCOS.fatal("Error de validadcion");
            model.addAttribute("usuario", userToSave);
            return "cargarUsuario";
        }

        try {
            serviceUsuario.guardarUsuario(userToSave);
        } catch (Exception e) {
            MARCOS.error("no se pudo guardar usuario");
        }

        return "redirect:/otroUsuario";
    }

    @GetMapping("/mostrarUsuario")
    public ModelAndView showTableUsers(){
        
        ModelAndView modelView = new ModelAndView("mostrarUsuarios");

        modelView.addObject("listUser", serviceUsuario.mostrarUsuarios());
        
        return modelView;
    }

    @GetMapping("/editUser/{dni}")
    public ModelAndView editarUsuario(@PathVariable (name = "dni") Long dni){

        Usuario usuarioEncontrado = new Usuario();
        // nuevoUsuario usuarioEncontrado;

        // for(int i=0;i<listadoUsuario.getListado().size();i++){
        //     if(listadoUsuario.getListado().get(i).getDni().equals(dni)){
        //         usuarioEncontrado = listadoUsuario.getListado().get(i);
        //     }
        // }
        
        ModelAndView encontrado = new ModelAndView("cargarUsuario");

        // encontrado.addObject("listUser", usuarioEncontrado);
        encontrado.addObject("usuario", usuarioEncontrado);
        encontrado.addObject("band", true);
        
        return encontrado;
    }

    @PostMapping("/modificarUsuario")
    public String modificarUser(@Valid @ModelAttribute ("usuario") Usuario userToEdit, BindingResult resultado, Model model){

        // MARCOS.info("ingresando al metodo guardar Usuario: "+userToEdit.getApellido());
        if(resultado.hasErrors()){
            // MARCOS.fatal("Error de validadcion");
            model.addAttribute("usuario", userToEdit);
            return "cargarUsuario";
        }

        
        // listadoUsuario.getListado().add(userToEdit);
        // MARCOS.error("tamaño del listado: "+listadoUsuario.getListado().size());
        return "redirect:/mostrarUsuarios";
    }

    @GetMapping("/deleteUser/{dni}")
    public String eliminarUsuario(@Valid @ModelAttribute Long dni){

        try {
            
            serviceUsuario.eliminarUsuario(dni);
        } catch (Exception e) {
            MARCOS.error("no se pdo eliminar el usuario");
        }
        
        return "redirect:/mostrarUsuario";
    }

    


}
