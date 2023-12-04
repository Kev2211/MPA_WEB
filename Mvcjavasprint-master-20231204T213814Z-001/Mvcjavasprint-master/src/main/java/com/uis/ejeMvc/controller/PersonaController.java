package com.uis.ejeMvc.controller;

import com.uis.ejeMvc.model.Ciudad;
import com.uis.ejeMvc.model.Persona;
import com.uis.ejeMvc.model.Tipodocumento;
import com.uis.ejeMvc.repository.CiudadRepository;
import com.uis.ejeMvc.repository.PersonaRepositoryInter;
import com.uis.ejeMvc.repository.TipodocumentoRepository;
import com.uis.ejeMvc.service.PersonaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/persona")
public class PersonaController {
    @Autowired
    private PersonaRepositoryInter personaRepositoryInter;

    @Autowired
    private TipodocumentoRepository tipodocumentoRepository;

    @Autowired
    private CiudadRepository ciudadRepository;

    @GetMapping("/personas")
    public String ListaPersonas(Model model){
        List<Persona> listaPersona = personaRepositoryInter.findAll();
        model.addAttribute("listaPersona", listaPersona);
        return "personas";
    }

    @GetMapping("/personas/nuevo")
    public String mostrarFormularioDeNuevaPersona(Model model){
        List<Tipodocumento> listaTipodocumento = tipodocumentoRepository.findAll();
        List<Ciudad> listaCiudad = ciudadRepository.findAll();

        model.addAttribute("persona", new Persona());
        model.addAttribute("listaTipodocumento", listaTipodocumento);
        model.addAttribute("listaCiudad", listaCiudad);
        return "persona_formulario";
    }

    @PostMapping("/personas/guardar")
    public String guardarPersona(Persona persona){
        personaRepositoryInter.save(persona);
        return "redirect:/persona/personas";
    }

    @GetMapping("/inicio")
    public String regreso() {
        return "index";
    }

    @GetMapping("/personas/eliminar/{id}")
    public String deletePersona(@PathVariable ("id") Long id, Model model){
        personaRepositoryInter.deleteById(id);
        return "redirect:/persona/personas";
    }

    @GetMapping("/personas/editar/{id}")
    public String EditarPersona(@PathVariable("id") Long id, Model modelo){
        Persona persona = personaRepositoryInter.findById(id).get();
        modelo.addAttribute("persona", persona);

        List<Tipodocumento> listaTipodocumento = tipodocumentoRepository.findAll();
        List<Ciudad> listaCiudad = ciudadRepository.findAll();
        modelo.addAttribute("listaTipodocumento", listaTipodocumento);
        modelo.addAttribute("listaCiudad", listaCiudad);

        return "persona_formulario";
    }

}

/*
   @PostMapping
    public ResponseEntity<Persona> savePersona(@RequestBody Persona persona){
        return ResponseEntity.status(HttpStatus.CREATED).body(
                personaService.savePersona(persona)
        );
    }

    @GetMapping
    public ResponseEntity<Page<Persona>> getAllPersona(
           @RequestParam (required = false, defaultValue = "0") Integer page,
           @RequestParam (required = false, defaultValue = "10") Integer size,
           @RequestParam (required = false, defaultValue = "false") Boolean eneablePagination
    ){
        return ResponseEntity.ok(personaService.getAllPersona(page, size, eneablePagination));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity deletePersona(@PathVariable("id") Long id){
        personaService.deletePersona(id);
        return ResponseEntity.ok(!personaService.existById(id));
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Optional<Persona>> findPersonasById(@PathVariable("id") Long id){
        return ResponseEntity.status(HttpStatus.OK).body(personaService.findById(id));
    }

    @PostMapping(consumes = "application/json", produces = "application/json")
    public ResponseEntity<Persona> editPersona(@RequestBody Persona persona){
        return ResponseEntity.status(HttpStatus.CREATED).body(
                personaService.savePersona(persona)
        );
    }
 */
