package com.example.demo.controllers;

import com.example.demo.model.entity.ItemType;
import com.example.demo.model.entity.Pet;
import com.example.demo.services.IItemTypeService;
import com.example.demo.services.IPetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(value="/item_type")
public class ItemTypeController extends AbstractController<ItemType, IItemTypeService>{
    @Autowired
    protected ItemTypeController(IItemTypeService service) {
        super(service);
    }

    @GetMapping("/list")
    public String readingAll(Model model) {
        model.addAttribute("types", service.getAll());
        System.out.println(123);
        return "admin/admin-category";
    }

    @GetMapping("/new")
    public String createItemType(Model model) {
        // create student object to hold student form data
        ItemType itemType = new ItemType();
        model.addAttribute("type", itemType);
        return "admin/admin-category-add";

    }

    @PostMapping("/create")
    public String saveItemType(@ModelAttribute("type") ItemType itemType) {
        service.create(itemType);
        return "redirect:/item_type/list";
    }

    @GetMapping("/edit/{id}")
    public String editItemType(@PathVariable Long id, Model model) {
        model.addAttribute("type", service.findById(id));
        return "admin/admin-category-edit";
    }

    @PostMapping("/{id}")
    public String updateItemType(@PathVariable Long id,
                                 @ModelAttribute("type") ItemType itemType,
                                 Model model) {

        // get pet from database by id
        ItemType existingItemType = service.findById(id);
        existingItemType.setId(id);
        existingItemType.setItemTypeName(itemType.getItemTypeName());

        // save updated pet object
        service.update(id, existingItemType);
        return "redirect:/item_type/list";
    }

    // handler method to handle delete student request

    @GetMapping("del/{id}")
    public String deleteItemType(@PathVariable Long id) {
        service.delete(id);
        return "redirect:/item_type/list";
    }
}
