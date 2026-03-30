package com.example.baitapt_tuan5_sql.controller;

import com.example.baitapt_tuan5_sql.models.CartItem;
import com.example.baitapt_tuan5_sql.models.Order;
import com.example.baitapt_tuan5_sql.models.OrderDetail;
import com.example.baitapt_tuan5_sql.models.product;
import com.example.baitapt_tuan5_sql.repository.OrderDetailRepository;
import com.example.baitapt_tuan5_sql.repository.OrderRepository;
import com.example.baitapt_tuan5_sql.service.ProductService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/cart")
public class CartController {

    @Autowired
    private ProductService productService;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @SuppressWarnings("unchecked")
    private List<CartItem> getCart(HttpSession session) {
        List<CartItem> cart = (List<CartItem>) session.getAttribute("cart");
        if (cart == null) {
            cart = new ArrayList<>();
            session.setAttribute("cart", cart);
        }
        return cart;
    }

    @PostMapping("/add")
    public ResponseEntity<String> addToCart(@RequestParam Long productId, @RequestParam(defaultValue = "1") int quantity, HttpSession session) {
        List<CartItem> cart = getCart(session);
        product p = productService.findById(productId).orElse(null);
        if (p == null) return ResponseEntity.notFound().build();

        for (CartItem item : cart) {
            if (item.getProduct().getId().equals(productId)) {
                item.setQuantity(item.getQuantity() + quantity);
                return ResponseEntity.ok("Sản phẩm đã được thêm vào giỏ hàng.");
            }
        }

        cart.add(new CartItem(p, quantity));
        return ResponseEntity.ok("Sản phẩm đã được thêm vào giỏ hàng.");
    }

    @GetMapping
    public List<CartItem> getCartItems(HttpSession session) {
        return getCart(session);
    }

    @PostMapping("/checkout")
    public ResponseEntity<Order> checkout(HttpSession session) {
        List<CartItem> cart = getCart(session);
        if (cart.isEmpty()) return ResponseEntity.badRequest().build();

        double total = cart.stream().mapToDouble(CartItem::getTotalPrice).sum();
        Order order = new Order(total);
        order = orderRepository.save(order);

        for (CartItem item : cart) {
            OrderDetail detail = new OrderDetail(order, item.getProduct(), item.getQuantity(), item.getProduct().getPrice());
            orderDetailRepository.save(detail);
        }

        session.removeAttribute("cart");
        return ResponseEntity.ok(order);
    }
    
    @DeleteMapping("/remove/{productId}")
    public ResponseEntity<Void> removeFromCart(@PathVariable Long productId, HttpSession session) {
        List<CartItem> cart = getCart(session);
        cart.removeIf(item -> item.getProduct().getId().equals(productId));
        return ResponseEntity.noContent().build();
    }
}
