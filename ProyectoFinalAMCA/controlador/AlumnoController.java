package aplicacion.controlador;
 
import java.util.List;
 
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping; 
 
import aplicacion.modelo.Alumno; 
import aplicacion.persistencia.AlumnoDAO;

@RequestMapping("/alumnos")
@Controller
public class AlumnoController {

	 
	 	
	 
	private AlumnoDAO crudAlumno = new AlumnoDAO( ); 
	
	@GetMapping(value={"","/"})
	String alumnos(Model model) {

		List<Alumno> lista = crudAlumno.listarAlumnosJPA();
		model.addAttribute("alumnos",lista );
		model.addAttribute("nuevoAlumno",new Alumno() );
		model.addAttribute("alumnoaEditar", new Alumno());    
		model.addAttribute("nombreNuevo", "");    
		return "alumnos";
	}

	@GetMapping(value="/add/{nombre}")
	public String insertarAlumno(Model model, @PathVariable String nombre ) {
		 
		System.out.println("Insertando alumno nuevo: "+nombre);		
		
		return "redirect:/alumnos";
	}
	
	
	@PostMapping("/add")
	public String addAlumno(@ModelAttribute("nuevoAlumno") Alumno alumno, BindingResult bindingResult) {
		
		crudAlumno.insertarAlumnoJPA(alumno);
		System.out.println("Insertando alumno nuevo: "+alumno.getNombre());			
				
		return "redirect:/alumnos";	
	}
	
	@PostMapping("/edit/{id}")
	public String editarAlumno(@PathVariable Integer id, @ModelAttribute("alumnoaEditar") Alumno alumno,BindingResult bindingResult) {
		
		Alumno alumnoAEditar = crudAlumno.buscarPorIdJPA(id);
		alumnoAEditar.setNombre(alumno.getNombre());
		crudAlumno.insertarAlumnoJPA(alumnoAEditar);
		
		return "redirect:/alumnos";
		 
	}
	
	  
	
	@GetMapping({"/{id}"})
	public String obtenerAlumno(Model model, @PathVariable Integer id) {
	 
		model.addAttribute("alumno", crudAlumno.buscarPorIdJPA(id));
		
		return "alumno";
	}
	
	@GetMapping({"/buscar/{nombre}"})
	public String obtenerAlumno(@PathVariable String nombre) {
		return "alumno";
	}
	
	@GetMapping({"/delete/{id}"})
	public String borrarAlumno(@PathVariable Integer id) {
		
		crudAlumno.eliminarAlumnoJPA(crudAlumno.buscarPorIdJPA(id));
		
		return "redirect:/alumnos";
		 
	}
	

	
	
	
}
