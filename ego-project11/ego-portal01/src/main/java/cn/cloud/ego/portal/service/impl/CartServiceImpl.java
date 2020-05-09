package cn.cloud.ego.portal.service.impl;

import cn.cloud.ego.base.pojo.Item;
import cn.cloud.ego.base.utils.JsonUtils;
import cn.cloud.ego.base.vo.Cart;
import cn.cloud.ego.portal.service.CartService;
import cn.cloud.ego.portal.service.ItemService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

@Service
public class CartServiceImpl implements CartService {

    @Value("${ego.cart.key}")
    private String cartKey;

    @Autowired
    private ItemService itemService;

    /**
     * 添加商品到购物车
     * @param id
     * @param num
     * @param request
     * @param response
     * @return
     */
    @Override
    public List<Cart> addToCart(Long id, Integer num, HttpServletRequest request, HttpServletResponse response) {
        // 1.创建List<Cart>对象
        List<Cart> carts = this.getCart(request);
        // 2.获取商品信息
        // 判断购物车中是否存在该商品
        boolean flag = false; // 假设不存在
        Item item = itemService.findById(id);
        if(item != null){
            for (Cart cart : carts) {
                // 存在则将商品数量加1即可
                if(cart.getId().equals(item.getId()+"")){
                    cart.setNum(cart.getNum()+1);
                    flag = true; // 确定存在
                    break;
                }
            }
        }
        // 3.封装对象
        if(!flag && item!=null){
            // 不存在则创建新的对象
            Cart cart = new Cart();
            // 封装数据
            cart.setId(id+"");
            cart.setNum(num);
            cart.setTitle(item.getTitle());
            cart.setImage(item.getImage());
            cart.setPrice(item.getPrice());
            // 添加到集合中
            carts.add(cart);
        }
        // 4.写入到Cookie中
        // 编码转换
        Base64.Encoder encoder = Base64.getEncoder();
        // 创建Cookie
        Cookie cookie = new Cookie(this.cartKey,encoder.encodeToString(JsonUtils.objectToJson(carts).getBytes()));
        // 设置访问权限
        cookie.setPath("/");
        // 添加Cookie
        response.addCookie(cookie);
        // 5.返回结果
        return carts;
    }

    /**
     * 展示购物车
     * @param request
     * @return
     */
    @Override
    public List<Cart> showCart(HttpServletRequest request) {
        return this.getCart(request);
    }

    /**
     * 更新购物车中的商品
     * @param id
     * @param num
     * @param request
     * @param response
     * @return
     */
    @Override
    public List<Cart> updateCart(Long id, Integer num, HttpServletRequest request, HttpServletResponse response) {
        // 1.创建List<Cart>对象
        List<Cart> carts = this.getCart(request);
        // 2.获取商品信息
        // 判断购物车中是否存在该商品
        Item item = itemService.findById(id);
        if(item != null && num!=null){
            for (Cart cart : carts) {
                // 存在则将商品数量加1即可
                if(cart.getId().equals(item.getId()+"")){
                    cart.setNum(num);
                    break;
                }
            }
            // 3.写入到Cookie中
            // 编码转换
            Base64.Encoder encoder = Base64.getEncoder();
            // 创建Cookie
            Cookie cookie = new Cookie(this.cartKey,encoder.encodeToString(JsonUtils.objectToJson(carts).getBytes()));
            // 设置访问权限
            cookie.setPath("/");
            // 添加Cookie
            response.addCookie(cookie);
        }
        // 4.返回结果
        return carts;
    }

    /**
     * 删除购物车中的商品
     * @param id
     * @param request
     * @param response
     * @return
     */
    @Override
    public List<Cart> deleteCart(Long id, HttpServletRequest request, HttpServletResponse response) {
        // 1.创建List<Cart>对象
        List<Cart> carts = this.getCart(request);
        // 2.获取商品信息
        // 判断购物车中是否存在该商品
        Item item = itemService.findById(id);
        if(item != null){
            for (Cart cart : carts) {
                // 删除商品
                if(cart.getId().equals(item.getId()+"")){
                    carts.remove(cart);
                    break;
                }
            }
            // 3.写入到Cookie中
            // 编码转换
            Base64.Encoder encoder = Base64.getEncoder();
            // 创建Cookie
            Cookie cookie = new Cookie(this.cartKey,encoder.encodeToString(JsonUtils.objectToJson(carts).getBytes()));
            // 设置访问权限
            cookie.setPath("/");
            // 添加Cookie
            response.addCookie(cookie);
        }
        // 4.返回结果
        return carts;
    }

    /**
     * 获取购物车的信息
     * @param request
     * @return
     */
    private List<Cart> getCart(HttpServletRequest request){
        // 1.创建List<Cart>对象
        List<Cart> carts = null;
        // 2.获取数据
        // 判断Cookie中是否存在用户的购物车信息
        boolean flag = false; // 假设不存在
        Cookie[] cookies = request.getCookies();
        if(cookies!=null && cookies.length>0){
            // 循环遍历Cookie
            for (Cookie cookie : cookies) {
                // 判断存在
                if(cookie.getName().equals(this.cartKey)){
                    String value = cookie.getValue();
                    // 编码转换
                    if(StringUtils.isNotBlank(value)){
                        Base64.Decoder decoder = Base64.getDecoder();
                        String decode = new String(decoder.decode(value));
                        // 3.封装对象
                        carts = JsonUtils.jsonToList(decode,Cart.class);
                        flag = true; // 确认存在
                        break;
                    }
                }
            }
        }
        // 4.返回结果
        if(!flag){
            carts = new ArrayList<>();
        }
        return carts;
    }

}
