package cn.cloud.ego.portal.service;

import cn.cloud.ego.base.vo.Cart;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public interface CartService {

    /**
     * 添加商品到购物车
     * @param id
     * @param num
     * @param request
     * @param response
     * @return
     */
    List<Cart> addToCart(Long id, Integer num, HttpServletRequest request, HttpServletResponse response);

    /**
     * 展示购物车
     * @param request
     * @return
     */
    List<Cart> showCart(HttpServletRequest request);

    /**
     * 更新购物车中的商品
     * @param id
     * @param num
     * @param request
     * @param response
     * @return
     */
    List<Cart> updateCart(Long id, Integer num, HttpServletRequest request, HttpServletResponse response);

    /**
     * 删除购物车中的商品
     * @param id
     * @param request
     * @param response
     * @return
     */
    List<Cart> deleteCart(Long id,HttpServletRequest request, HttpServletResponse response);

}
