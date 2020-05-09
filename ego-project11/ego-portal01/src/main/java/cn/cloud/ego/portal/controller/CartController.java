package cn.cloud.ego.portal.controller;

import cn.cloud.ego.base.vo.Cart;
import cn.cloud.ego.portal.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    /**
     * 添加商品到购物车
     * @param id
     * @param num
     * @param request
     * @param response
     * @return
     */
    @GetMapping("/add/{num}/{id}.html")
    public String add(@PathVariable Long id, @PathVariable Integer num, HttpServletRequest request, HttpServletResponse response){
        List<Cart> cartList = cartService.addToCart(id, num, request, response);
        request.setAttribute("cartList",cartList);
        return "cart";
    }

    /**
     * 展示购物车
     * @param request
     * @return
     */
    @GetMapping("/cart.html")
    public String show(HttpServletRequest request){
        List<Cart> cartList = cartService.showCart(request);
        request.setAttribute("cartList",cartList);
        return "cart";
    }

    /**
     * 更新购物车
     * @param id
     * @param num
     * @param request
     * @param response
     * @return
     */
    @PostMapping("/update/num/{id}/{num}.html")
    public String update(@PathVariable Long id, @PathVariable Integer num, HttpServletRequest request, HttpServletResponse response){
        List<Cart> cartList = cartService.updateCart(id, num, request, response);
        request.setAttribute("cartList",cartList);
        return "cart";
    }

    /**
     * 删除购物车中的商品
     * @param id
     * @param request
     * @param response
     * @return
     */
    @GetMapping("/delete/{id}.html")
    public String deleteCart(@PathVariable Long id, HttpServletRequest request, HttpServletResponse response){
        List<Cart> cartList = cartService.deleteCart(id,request,response);
        request.setAttribute("cartList",cartList);
        return "cart";
    }

}
