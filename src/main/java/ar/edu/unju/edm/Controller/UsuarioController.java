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

import ar.edu.unju.edm.model.Usaurio;
import ar.edu.unju.edm.until.ListadoUsuario;

@Controller
public class UsuarioController {

    // Constanres con mayusculas
    private static final org.apache.juli.logging.Log MARCOS = LogFactory.getLog(UsuarioController.class);
    // private static final Log MARCOS=LogFactory.getLog(UsuarioController.class);
    // private static final Log MARCOS = (Log) LogFactory.getLog(UsuarioController.class);

    @Autowired
    Usaurio nuevoUsuario;

    @GetMapping("/otroUsuario")
    public ModelAndView addUser(){

        ModelAndView modelView = new ModelAndView("cargarUsuario");
        // modelView.addObject("nuevoUsuario");

        modelView.addObject("usuario", nuevoUsuario);

        
        modelView.addObject("band", false);
        return modelView;

    }

    @Autowired
    ListadoUsuario listadoUsuario;

    @PostMapping("/guardarUsuario")
    public String saveUser(@Valid @ModelAttribute ("usuario") Usaurio userToSave, BindingResult resultado, Model model){

        // MARCOS.info("ingresando al metodo guardar Usuario: "+userToSave.getApellido());
        if(resultado.hasErrors()){
            MARCOS.fatal("Error de validadcion");
            // model.addAllAttributes("usuario", userToSave);
            model.addAttribute("usuario", userToSave);
            return "cargarUsuario";
        }
        listadoUsuario.getListado().add(userToSave);
        // MARCOS.error("tamaño del listado: "+listadoUsuario.getListado().size());
        return "redirect:/otroUsuario";
    }

    @GetMapping("/mostrarUsurio")
    public ModelAndView showTableUsers(){
        
        ModelAndView modelView = new ModelAndView("mostrarUsuarios");

        modelView.addObject("listUser", listadoUsuario.getListado());
        
        return modelView;
    }

    @GetMapping("/editUser/{dni}")
    public ModelAndView editarUsuario(@PathVariable (name = "dni") Long dni){

        Usaurio usuarioEncontrado = new Usaurio();
        // nuevoUsuario usuarioEncontrado;

        for(int i=0;i<listadoUsuario.getListado().size();i++){
            if(listadoUsuario.getListado().get(i).getDni().equals(dni)){
                usuarioEncontrado = listadoUsuario.getListado().get(i);
            }
        }
        
        ModelAndView encontrado = new ModelAndView("cargarUsuario");

        // encontrado.addObject("listUser", usuarioEncontrado);
        encontrado.addObject("usuario", usuarioEncontrado);
        encontrado.addObject("band", true);
        
        return encontrado;
    }

    @PostMapping("/modificarUsuario")
    public String modificarUser(@Valid @ModelAttribute ("usuario") Usaurio userToEdit, BindingResult resultado, Model model){

        // MARCOS.info("ingresando al metodo guardar Usuario: "+userToEdit.getApellido());
        if(resultado.hasErrors()){
            // MARCOS.fatal("Error de validadcion");
            model.addAttribute("usuario", userToEdit);
            return "cargarUsuario";
        }

        for(int i=0;i<listadoUsuario.getListado().size();i++){
            if(listadoUsuario.getListado().get(i).getDni().equals(userToEdit.getDni())){
                listadoUsuario.getListado().set(1, userToEdit);
            }
        }
        // listadoUsuario.getListado().add(userToEdit);
        // MARCOS.error("tamaño del listado: "+listadoUsuario.getListado().size());
        return "redirect:/mostrarUsuarios";
    }

    @GetMapping("/deleteUser/{dni}")
    public ModelAndView eliminarUsuario(@Valid @ModelAttribute ("usuario") Usaurio usuarioParaSacar, BindingResult resultado, Model model){
        
        Usaurio usuarioEncontrado = new Usaurio();

        ModelAndView modelView = new ModelAndView("mostrarUsuarios");

        modelView.addObject("listUser", listadoUsuario.getListado());
        
        return modelView;
    }

    


}
