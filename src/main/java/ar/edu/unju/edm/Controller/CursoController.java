package ar.edu.unju.edm.Controller;

import javax.validation.Valid;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unju.edm.model.Curso;
import ar.edu.unju.edm.services.ICursoService;
import ar.edu.unju.edm.util.ListadoCursos;

@Controller
public class CursoController {

	private static final Log GRUPO05 = LogFactory.getLog(UsuarioController.class);
	
	@Autowired
	Curso nuevoCurso;

	@Autowired
	ICursoService cursoService;
	
	@Autowired
	ListadoCursos listadoCursos;
	
	// cargar cursos
	@GetMapping({"/otroCurso"})	
	public ModelAndView addCourse() {
		
		GRUPO05.info("Ingresando al metodo addUser");
		ModelAndView modelView = new ModelAndView("cargarCurso");
		modelView.addObject("unCurso", nuevoCurso);
		modelView.addObject("band", false);
		return modelView;
	}
		
	// guardar cursos
	@PostMapping("/guardarCurso")
	public String saveCourse(@Valid @ModelAttribute ("unCurso") Curso coursetosave, BindingResult result, ModelMap model) {
		
		// if(result.hasErrors()) {
		// 	GRUPO05.fatal("ERROR DE VALIDACION");
		// 	model.addAttribute("unCurso", coursetosave);
		// 	return "cargarCurso";
		// }
		// coursetosave.setId(listadoCursos.getListadoCurso().size()+1);
		// listadoCursos.getListadoCurso().add(coursetosave);
		// return "redirect:/otroCurso";

		if(result.hasErrors()) {
			GRUPO05.fatal("ERROR DE VALIDACION");
			model.addAttribute("unCurso", coursetosave);
			return "cargarCurso";
		}

		try {
			cursoService.guardarCurso(coursetosave);
		} catch (Exception e) {
			model.addAttribute("formCourseErrorMessage", e.getMessage());
			model.addAttribute("unCurso", coursetosave);
			GRUPO05.error("Saliendo del metodo: saveUser");
			return "cargarCurso";
		}

		model.addAttribute("formCourseErrorMessage", "Curso guardado corecramente");
		model.addAttribute("unCurso", coursetosave);

		return "cargarCurso";
	}
		
		// listar cursos
	@GetMapping("/listarCursos")	
	public ModelAndView showCourse() {
		// ModelAndView modelView = new ModelAndView("mostrarCursos");
		// modelView.addObject("listacursos", listadoCursos.getListadoCurso());
		// return modelView;
		ModelAndView modelView = new ModelAndView("mostrarCursos");

		modelView.addObject("listacursos", cursoService.listarCursos());
		GRUPO05.info("Ingresando al metodo showCourse "+cursoService.listarCursos().get(0).getNombre());

		return modelView;
	}
		
		// modificar cursos
		// @RequestMapping("/editecourse/{id}")
		// public ModelAndView modCourse(@PathVariable(name="id") int id) { 
		// 	Curso coursetomod = new Curso();
		// 	for(int i=0 ;i <  listadoCursos.getListadoCurso().size(); i++ ) { 
		// 		if(listadoCursos.getListadoCurso().get(i).getId() == id)
		// 			coursetomod = listadoCursos.getListadoCurso().get(i);
		// 	}
			
		// 	ModelAndView coursemod = new ModelAndView("cargarCurso");
		//     coursemod.addObject("unCurso", coursetomod);
		//     coursemod.addObject("band", "true");
		//     return coursemod;
		// }

		@GetMapping("/editarCurso/{id}")
		public ModelAndView getFormEditCourse(Model model, @PathVariable(name = "id") Integer id) throws Exception{
			Curso cursoEncontrado = new Curso();
			cursoEncontrado = cursoService.buscarCurso(id);
			ModelAndView modelView = new ModelAndView("cargarCurso");
			modelView.addObject("unCurso", cursoEncontrado);
			GRUPO05.error("Saliendo del metodo: getFormEditCourse "+cursoEncontrado.getId());
			
			modelView.addObject("band", true);

			return modelView;
		}

		//actualizar curso
		// @PostMapping("/modificarCurso")
		// public String savemodUser(@Valid @ModelAttribute ("unCurso") Curso cursoparamod, BindingResult result, Model model) {
			
		// 	if(result.hasErrors()) {
		// 		GRUPO05.fatal("Error de validacion");
		// 		model.addAttribute("unCurso", cursoparamod);
		// 		model.addAttribute("band", true);
		// 		return "cargarCurso";
		// 	}
		// 	for(int i=0 ;i <  listadoCursos.getListadoCurso().size(); i++ ) { 
		// 		if(listadoCursos.getListadoCurso().get(i).getId() == cursoparamod.getId())
		// 			listadoCursos.getListadoCurso().set(i, cursoparamod);
		// 	}
		
		// 	return "redirect:/listarCursos";
		// }

	@PostMapping("/editarCurso")
	public ModelAndView postEditCourse(@ModelAttribute("curso") Curso cursoModificado){
		ModelAndView modelView = new ModelAndView("mostrarCursos");
		cursoService.modficarCurso(cursoModificado);

		modelView.addObject("listacursos", cursoService.listarCursos());
		modelView.addObject("formCourseErrorMessage", "Curso guardado Correctamente");
		GRUPO05.info("Curso modificado guardado correctamente");

		return modelView;
	}
		
		
		// eliminar cursos
		// @RequestMapping("/deletecourse/{id}")
		// public String deleteCourse(@PathVariable(name="id") int id) {
		// 	for(int i=0 ;i <  listadoCursos.getListadoCurso().size(); i++ ) {
		// 		if(listadoCursos.getListadoCurso().get(i).getId() == id)
		// 		{
		// 			listadoCursos.getListadoCurso().remove(i);
		// 			System.out.println("Tamaño del listado: " + listadoCursos.getListadoCurso().size());
		// 		}
					
		// 	}
		    	
		//     return "redirect:/listarCursos";
		// }
	

	@GetMapping("/deletecourse/{id}")
	public String eliminar(@PathVariable Integer id, Model model){
		try {
			cursoService.eliminarCurso(id);
		} catch (Exception e) {
			GRUPO05.error("Encontrando Curso");
			model.addAttribute("formCourseErrorMessage", e.getMessage());
			return "redirect:/otroCurso";
		}
		return "redirect:/listarCursos";
	}
}
