package com.example.demo.controllers;

import com.example.demo.model.entity.Item;
import com.example.demo.model.entity.ItemType;
import com.example.demo.services.IItemService;
import com.example.demo.services.IItemTypeService;
import com.example.demo.services.IPetService;
import com.example.demo.services.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(value = "/item")
public class ItemController extends AbstractController<Item, IItemService> {
    private IPetService iPetService;
    private IItemTypeService iItemTypeService;
    private IUserService iUserService;
    private IItemService iItemService;

    @Autowired
    protected ItemController(IItemService service,
                             IPetService iPetService,
                             IItemTypeService iItemTypeService,
                             IUserService iUserService,
                             IItemService iItemService) {
        super(service);
        this.iPetService = iPetService;
        this.iItemTypeService = iItemTypeService;
        this.iUserService = iUserService;
        this.iItemService = iItemService;
    }


    @GetMapping(value = "/name")
    public String getItemsByName(Authentication authentication,
                                 @RequestParam(name = "itemName") String itemName,
                                 Model model) {
        model.addAttribute("userRole",iUserService.getUserRole(authentication));
        model.addAttribute("userId", iUserService.getUserId(authentication));
        model.addAttribute("pets", iPetService.getAll());
        model.addAttribute("types", iItemTypeService.getAll());
        //System.out.println(iItemService.getItemContainingItemName(itemName).size());
        System.out.println(itemName);
        System.out.println("11111111111111111");
        System.out.println(itemName);
        //System.out.println(iItemService.getItemContainingItemName("Объект").size());

        //System.out.println("222222222222222222");
        //iItemService.getItemContainingItemName(itemName).stream().forEach(x -> System.out.println(x.toString()));
        //model.addAttribute("items", iItemService.getItemByName(itemName));
        model.addAttribute("items", iItemService.getItemContainingItemName(itemName));
        return "searchProduct";
    }

    @GetMapping(value = "/type/{item_type_id}")
    public List<Item> getItemsByItemType(@PathVariable(name = "item_type_id") Long itemName) {
        return service.getItemByItemType(itemName);
    }

    /**@GetMapping(value = "/sort/{sort_id}")
    public List<Item> getItemsSort(@PathVariable(name = "sort_id") int itemName) {
        return service.getAllSorting(itemName);
    }*/

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
        existingItem.setCost(item.getCost());
        existingItem.setDescription(item.getDescription());
        existingItem.setImagePath(item.getImagePath());
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
