package com.example.demo.controllers;

import com.example.demo.model.entity.Pet;
import com.example.demo.services.IPetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping(value="/pet")
public class PetController extends AbstractController<Pet, IPetService>{
    @Autowired
    protected PetController(IPetService service) {
        super(service);
    }


    @GetMapping("/list")
    public String readingAll(Model model) {
        model.addAttribute("pets", service.getAll());
        System.out.println(123);
        return "admin/admin-pet";
    }

    @GetMapping("/new")
    public String createPet(Model model) {
        // create student object to hold student form data
        Pet pet = new Pet();
        model.addAttribute("pet", pet);
        return "admin/admin-pet-add";

    }

    @PostMapping("/create")
    public String savePet(@ModelAttribute("pet") Pet pet) {
        service.create(pet);
        return "redirect:/pet/list";
    }

    @GetMapping("/edit/{id}")
    public String editPet(@PathVariable Long id, Model model) {
        model.addAttribute("pet", service.findById(id));
        return "admin/admin-pet-edit";
    }

    @PostMapping("/{id}")
    public String updatePet(@PathVariable Long id,
                            @ModelAttribute("pet") Pet pet,
                            Model model) {

        // get pet from database by id
        Pet existingPet = service.findById(id);
        existingPet.setId(id);
        existingPet.setPetName(pet.getPetName());

        // save updated pet object
        service.update(id, existingPet);
        return "redirect:/pet/list";
    }

    // handler method to handle delete student request

    @GetMapping("del/{id}")
    public String deletePet(@PathVariable Long id) {
        service.delete(id);
        return "redirect:/pet/list";
    }
}
