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

	private static final Log GRUPO05 = LogFactory.getLog(CursoController.class);
	
	@Autowired
	Curso nuevoCurso;

	@Autowired
	ICursoService cursoService;
	
	@Autowired
	ListadoCursos listadoCursos;
	
	// cargar cursos
	@GetMapping({"/otroCurso"})	
	public ModelAndView addCourse() {
		
		GRUPO05.info("Ingresando al metodo agregar Curso");
		ModelAndView modelView = new ModelAndView("cargarCurso");
		modelView.addObject("unCurso", nuevoCurso);
		modelView.addObject("band", false);
		// modelView.addObject("idCurso", nuevoCurso.getId());
		return modelView;
	}
	
	@PostMapping("/guardarCurso")
	public String saveCourse(@Valid @ModelAttribute ("unCurso") Curso coursetosave, BindingResult result, ModelMap model) {

		if(result.hasErrors()) {
			GRUPO05.fatal("ERROR DE VALIDACION");
			model.addAttribute("unCurso", coursetosave);
			return "cargarCurso";
		}

		try {
			// model.addAttribute();
			cursoService.guardarCurso(coursetosave);
		} catch (Exception e) {
			model.addAttribute("formCourseErrorMessage", e.getMessage());
			model.addAttribute("unCurso", coursetosave);
			// model.addAttribute("idCurso", coursetosave.getId());
			GRUPO05.error("Saliendo del metodo: saveCourse");
			return "cargarCurso";
		}

		model.addAttribute("formCourseErrorMessage", "Curso guardado corecramente");
		model.addAttribute("unCurso", coursetosave);

		return "cargarCurso";
	}
		
	@GetMapping("/listarCursos")	
	public ModelAndView showCourse() {
		ModelAndView modelView = new ModelAndView("mostrarCursos");

		modelView.addObject("listacursos", cursoService.listarCursos());
		GRUPO05.info("Ingresando al metodo showCourse "+cursoService.listarCursos().get(0).getNombre());

		return modelView;
	}
	
	Integer rescatarId;
		
	@GetMapping("/editarCurso/{id}")
	public ModelAndView getFormEditCourse(Model model, @PathVariable(name = "id") Integer id) throws Exception{
		Curso cursoEncontrado = new Curso();
		try {
			cursoEncontrado = cursoService.buscarCurso(id);
		} catch (Exception e) {
			model.addAttribute("formCourseErrorMessage", e.getMessage());
		}
		ModelAndView modelView = new ModelAndView("cargarCurso");
		modelView.addObject("unCurso", cursoEncontrado);
		rescatarId=cursoEncontrado.getId();
		GRUPO05.error("Saliendo del metodo: getFormEditCourse "+cursoEncontrado.getId());
			
		modelView.addObject("band", true);

		return modelView;
	}

	@PostMapping("/editarCurso")
	public ModelAndView postEditCourse(@ModelAttribute("curso") Curso cursoModificado){
		GRUPO05.info("El id del curso ha modiicar es: "+rescatarId);
		cursoModificado.setId(rescatarId);
		cursoService.modficarCurso(cursoModificado);
		ModelAndView modelView = new ModelAndView("mostrarCursos");
		modelView.addObject("listacursos", cursoService.listarCursos());
		modelView.addObject("formCourseErrorMessage", "Curso guardado Correctamente");
		GRUPO05.info("Curso modificado guardado correctamente");

		return modelView;
	}

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