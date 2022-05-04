package com.example.demo.controllers;

import com.example.demo.model.entity.Item;
import com.example.demo.model.entity.ItemType;
import com.example.demo.services.IItemService;
import com.example.demo.services.IItemTypeService;
import com.example.demo.services.IPetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(value = "/item")
public class ItemController extends AbstractController<Item, IItemService> {
    private IPetService iPetService;
    private IItemTypeService iItemTypeService;

    @Autowired
    protected ItemController(IItemService service, IPetService iPetService, IItemTypeService iItemTypeService) {
        super(service);
        this.iPetService = iPetService;
        this.iItemTypeService = iItemTypeService;
    }

    @GetMapping(value = "/name/{itemName}")
    public List<Item> getItemsByName(@PathVariable(name = "itemName") String itemName) {
        return service.getItemByName(itemName);
    }

    @GetMapping(value = "/type/{item_type_id}")
    public List<Item> getItemsByItemType(@PathVariable(name = "item_type_id") Long itemName) {
        return service.getItemByItemType(itemName);
    }

    @GetMapping(value = "/sort/{sort_id}")
    public List<Item> getItemsSort(@PathVariable(name = "sort_id") int itemName) {
        return service.getAllSorting(itemName);
    }

    @GetMapping("/list")
    public String readingAll(Model model) {
        model.addAttribute("items", service.getAll());
        System.out.println(456);
        return "admin/admin-item";
    }

    @GetMapping("/new")
    public String createItem(Model model) {
        // create student object to hold student form data
        Item item = new Item();
        model.addAttribute("pets", iPetService.getAll());
        model.addAttribute("types", iItemTypeService.getAll());
        model.addAttribute("item", item);
        return "admin/admin-item-add";

    }

    @PostMapping("/create")
    public String saveItem(@ModelAttribute("item") Item item) {
        service.create(item);
        return "redirect:/item/list";
    }

    @GetMapping("/edit/{id}")
    public String editItem(@PathVariable Long id, Model model) {
        model.addAttribute("item", service.findById(id));
        model.addAttribute("pets", iPetService.getAll());
        model.addAttribute("types", iItemTypeService.getAll());
        return "admin/admin-item-edit";
    }

    @PostMapping("/{id}")
    public String updateItem(@PathVariable Long id,
                             @ModelAttribute("item") Item item,
                             Model model) {

        // get pet from database by id
        Item existingItem = service.findById(id);
        existingItem.setId(id);
        existingItem.setItemName(item.getItemName());

        // save updated pet object
        service.update(id, existingItem);
        return "redirect:/item/list";
    }

    // handler method to handle delete student request

    @GetMapping("del/{id}")
    public String deleteItem(@PathVariable Long id) {
        service.delete(id);
        return "redirect:/item/list";
    }
}
