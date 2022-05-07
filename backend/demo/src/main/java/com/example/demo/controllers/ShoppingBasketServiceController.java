package com.example.demo.controllers;

import com.example.demo.model.entity.ShoppingBasket;
import com.example.demo.model.entity.User;
import com.example.demo.services.IItemService;
import com.example.demo.services.IPetService;
import com.example.demo.services.IShoppingBasketService;
import com.example.demo.services.IUserService;
import com.example.demo.services.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping(value = "/shopping_basket")
public class ShoppingBasketServiceController extends AbstractController<ShoppingBasket, IShoppingBasketService> {
    private IItemService iItemService;
    private IUserService iUserService;
    private IPetService iPetService;
    private IShoppingBasketService iShoppingBasketService;

    @Autowired
    protected ShoppingBasketServiceController(IShoppingBasketService service,
                                              IItemService iItemService,
                                              IUserService iUserService,
                                              IPetService iPetService,
                                              IShoppingBasketService iShoppingBasketService) {
        super(service);
        this.iItemService = iItemService;
        this.iUserService = iUserService;
        this.iPetService = iPetService;
        this.iShoppingBasketService = iShoppingBasketService;
    }

    private int getTotalPrice(List<ShoppingBasket> purchases) {
        int result = 0;
        for (ShoppingBasket purchase : purchases) {
            result += (iItemService.findById(purchase.getItemId())).getCost() * purchase.getAmount();
        }
        return result;
    }

    @PostMapping("/addPurchase")
    public String addPurchase(Authentication authentication,
                                    final RedirectAttributes redirectAttributes,
                                    @RequestParam Long id,
                                    @RequestParam int count,
                                    Model model) {
        String userRole = ((User) (((UserServiceImpl) iUserService).loadUserByUsername(authentication.getName()))).getRole();
        if (userRole == "GUEST") {
            model.addAttribute("Status", "user_guest");
            redirectAttributes.addFlashAttribute("Status", "user_guest");
        }
        else {
            Long userId = ((User) (((UserServiceImpl) iUserService).loadUserByUsername(authentication.getName()))).getId();
            ShoppingBasket shoppingBasket =  iShoppingBasketService.getShoppingBasketByUserIdAndItemId(userId, id);
            if (shoppingBasket == null) {
                ShoppingBasket newPurchase = new ShoppingBasket();
                newPurchase.setUserId(userId);
                newPurchase.setItemId(id);
                newPurchase.setAmount(count);
                iShoppingBasketService.create(newPurchase);
                model.addAttribute("Status", "OK");
                redirectAttributes.addAttribute("Status", "OK");
            }
            else {
                shoppingBasket.setAmount(shoppingBasket.getAmount()+count);
                iShoppingBasketService.create(shoppingBasket);
                model.addAttribute("Status", "OK");
                redirectAttributes.addAttribute("Status", "OK");
            }
        }
        return "redirect:/shopping_basket/purchases";
    }

    @GetMapping("/purchases")
    public String purchases(Authentication authentication, Model model) {
        String userRole = ((User) (((UserServiceImpl) iUserService).loadUserByUsername(authentication.getName()))).getRole();
        Long userId = ((User) (((UserServiceImpl) iUserService).loadUserByUsername(authentication.getName()))).getId();
        model.addAttribute("totalPrice", getTotalPrice(iShoppingBasketService.getItemByUserId(userId)));
        model.addAttribute("userRole", userRole);
        model.addAttribute("userName", authentication.getName());
        model.addAttribute("pets", iPetService.getAll());
        model.addAttribute("purchases", iShoppingBasketService.getItemByUserId(userId));
        model.addAttribute("productsService", iItemService);
        return "basket";
    }

}
