package com.example.demo.controllers;

import com.example.demo.model.dao.IUserRepository;
import com.example.demo.model.entity.Item;
import com.example.demo.model.entity.User;
import com.example.demo.services.IItemService;
import com.example.demo.services.IItemTypeService;
import com.example.demo.services.IPetService;
import com.example.demo.services.IUserService;
import com.example.demo.services.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Comparator;

@Controller
@RequestMapping(value = "/user")
public class UserController extends AbstractController<User, IUserService> {
    private IPetService iPetService;
    private IUserService iUserService;
    private IItemTypeService iItemTypeService;
    private IItemService iItemService;
    private BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

    @Autowired
    protected UserController(IUserService service, IPetService iPetService,
                             IUserService iUserService, IItemTypeService iItemTypeService,
                             IItemService iItemService) {
        super(service);
        this.iPetService = iPetService;
        this.iUserService = iUserService;
        this.iItemService = iItemService;
        this.iItemTypeService = iItemTypeService;
    }

    private String getUserRole(Authentication authentication) {
        if (authentication == null)
            return "GUEST";
        else
            return ((User) (((UserServiceImpl) iUserService).loadUserByUsername(authentication.getName()))).getRole();
    }

    private Long getUserId(Authentication authentication) {
        if (authentication == null)
            return -1l;
        else
            return ((User) (((UserServiceImpl) iUserService).
                    loadUserByUsername(authentication.getName()))).
                    getId();
    }

    @GetMapping("/products")
    public String products(Authentication authentication,
                           @RequestParam(name = "pId", required = false) Long IpetId,
                           @RequestParam(name = "pTId", required = false) Long IproductTypeId,
                           @RequestParam(name = "sId", required = false) Integer sortId,
                           Model model) {
        // TODO: 09.05.2022 добавить эти методы везде, где не админка
        model.addAttribute("userRole", getUserRole(authentication));
        model.addAttribute("userId", getUserId(authentication));
        System.out.println(getUserRole(authentication));
        Long petId = (IpetId == null) ? 0 : IpetId;
        Long productTypeId = (IproductTypeId == null) ? 0 : IproductTypeId;

        model.addAttribute("pets", iPetService.getAll());
        iPetService.getAll().stream().forEach(x -> System.out.println(x.getPetName()));
        model.addAttribute("pId", petId);
        model.addAttribute("pTId", productTypeId);


        model.addAttribute("types", iItemTypeService.getAll());

        if (petId == 0 && productTypeId == 0) {
            model.addAttribute("items", iItemService.getAllSorting(sortId, iItemService.getAll()));
            System.out.println("1");
        }
        else if (petId == 0 && productTypeId != 0) {
            model.addAttribute("items",iItemService.getAllSorting(sortId, iItemService.getItemByItemType(productTypeId)));
            System.out.println("2");
        }
        else if (petId != 0 && productTypeId == 0) {
            model.addAttribute("items", iItemService.getAllSorting(sortId,iItemService.getItemByPetId(petId)));
            System.out.println("3");
        } else {
            model.addAttribute("items", iItemService.getAllSorting(sortId,iItemService.getItemByPetIdAndTypeId(petId, productTypeId)));
            System.out.println("4");
        }
        model.addAttribute("sortItemsByName", Comparator.comparing(Item::getItemName));
        model.addAttribute("sortItemsByCost", Comparator.comparing(Item::getCost));
        return "products";
    }

    @GetMapping("/list")
    public String readingAll(Authentication authentication,
                             Model model) {
        model.addAttribute("users", service.getAll());
        model.addAttribute("userRole", getUserRole(authentication));
        model.addAttribute("userId", getUserId(authentication));
        System.out.println(456);
        return "admin/admin-user";
    }

    @GetMapping("/new")
    public String createUser(Model model) {
        // create student object to hold student form data
        User user = new User();
        model.addAttribute("users", service.getAll());
        model.addAttribute("user", user);
        return "admin/admin-user-add";

    }

    @PostMapping("/create")
    public String saveUser(@ModelAttribute("user") User user) {
        service.create(user.getUsername(), user.getPassword(), user.getEmail(), user.getRole());
        return "redirect:/user/list";
    }

    @GetMapping("/edit/{id}")
    public String editUser(@PathVariable Long id, Model model) {
        model.addAttribute("user", service.findById(id));
        return "admin/admin-user-edit";
    }

    @PostMapping("/{id}")
    public String updateUser(@PathVariable Long id,
                             @ModelAttribute("user") User user,
                             Model model) {

        // get pet from database by id
        User existingUser = ((User) (((UserServiceImpl) iUserService).loadUserByUsername(user.getUsername())));
        existingUser.setId(id);
        existingUser.setUsername(user.getUsername());
        existingUser.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        existingUser.setEmail(user.getEmail());
        existingUser.setRole(user.getRole());


        // save updated pet object
        service.update(id, existingUser);
        return "redirect:/user/list";
    }

    // handler method to handle delete student request

    @GetMapping("del/{id}")
    public String deleteUser(@PathVariable Long id) {
        service.delete(id);

        return "redirect:/user/list";
    }
}
