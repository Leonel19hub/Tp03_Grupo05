package ar.edu.unju.edm.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unju.edm.model.Usaurio;
import ar.edu.unju.edm.until.ListadoUsuario;

@Controller
public class UsuarioController {

    @Autowired
    Usaurio nuevoUsuario;

    @GetMapping("/otroUsuario")
    public ModelAndView addUser(){

        ModelAndView modelView = new ModelAndView("cargarUsuario");
        // modelView.addObject("nuevoUsuario");

        modelView.addObject("usuario", nuevoUsuario);

        return modelView;
    }

    @Autowired
    ListadoUsuario listadoUsuario;

    @PostMapping("/guardarUsuario")
    public String saveUser(@ModelAttribute ("usuario") Usaurio userToSave){

        listadoUsuario.getListado().add(userToSave);
        // System.out.println("tamaño del listado: "+listadoUsuario.getListado().size());
        return "redirect:/otroUsuario";
    }

    @GetMapping("/mostrarUsurio")
    public ModelAndView showTableUsers(){
        
        ModelAndView modelView = new ModelAndView("mostrarUsuarios");

        modelView.addObject("listUser", listadoUsuario.getListado());
        
        return modelView;
    }

}
