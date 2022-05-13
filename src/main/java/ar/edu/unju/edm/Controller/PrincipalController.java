package ar.edu.unju.edm.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PrincipalController {

	@GetMapping({"/menu", "/principal", "/grupo5", "/home"})
    public String getMenu(){
        return "principal";
    }
}
