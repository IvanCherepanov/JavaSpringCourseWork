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
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Comparator;

@Controller
@RequestMapping(value = "/user")
public class UserController extends AbstractController<User, IUserService> {
    private IPetService iPetService;
    private IUserService iUserService;
    private IItemTypeService iItemTypeService;
    private IItemService iItemService;

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

    @GetMapping("/products")
    public String products(Authentication authentication,
                           @RequestParam(name = "pId", required = false) Long IpetId,
                           @RequestParam(name = "pTId", required = false) Long IproductTypeId,
                           Model model) {
        model.addAttribute("userRole", getUserRole(authentication));
        System.out.println(getUserRole(authentication));
        Long petId = (IpetId == null) ? 0 : IpetId;
        Long productTypeId = (IproductTypeId == null) ? 0 : IproductTypeId;

        model.addAttribute("pets", iPetService.getAll());
        iPetService.getAll().stream().forEach(x -> System.out.println(x.getPetName()));
        model.addAttribute("pId", petId);
        model.addAttribute("pTId", productTypeId);


        model.addAttribute("types", iItemTypeService.getAll());

        if (petId == 0 && productTypeId == 0) {
            model.addAttribute("items", iItemService.getAll());
            System.out.println("1");
        }
        else if (petId == 0 && productTypeId != 0) {
            model.addAttribute("items", iItemService.getItemByItemType(productTypeId));
            System.out.println("2");
        }
        else if (petId != 0 && productTypeId == 0) {
            model.addAttribute("items", iItemService.getItemByPetId(petId));
            System.out.println("3");
        } else {
            model.addAttribute("items", iItemService.getItemByPetIdAndTypeId(petId, productTypeId));
            System.out.println("4");
        }
        model.addAttribute("sortItemsByName", Comparator.comparing(Item::getItemName));
        model.addAttribute("sortItemsByCost", Comparator.comparing(Item::getCost));
        return "products";
    }
}
