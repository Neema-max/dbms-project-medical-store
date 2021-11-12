package com.example.demo.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.example.demo.service.customer.customerCartServ;
import com.example.demo.service.customer.customerEditProfile;
import com.example.demo.service.customer.customerOrderService;
import com.example.demo.service.customer.customerWishlistServ;
import com.example.demo.service.authService;
import com.example.demo.service.admin.adminEditProfile;
import com.example.demo.service.admin.adminOrderService;
import com.example.demo.model.admin;
import com.example.demo.model.category;
import com.example.demo.model.items;
import com.example.demo.model.order;
import com.example.demo.model.orderToItems;
import com.example.demo.model.reviews;
import com.example.demo.model.reviewsShow;
import com.example.demo.model.customer;
import com.example.demo.model.itemShow.itemsShow;
import com.example.demo.model.itemShow.itemsShowWithReviews;
import com.example.demo.model.itemShow.orderItemShow;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class authController {
    @Autowired
    private customerWishlistServ cws;
    @Autowired
    private authService authServ; 
    @Autowired
    private customerCartServ ccs;
    @Autowired
    private customerEditProfile cep;
    @Autowired
    private customerOrderService cos;
    @Autowired
    private adminEditProfile aep;
    @Autowired
    private adminOrderService aos;
    private order or;
    private List<orderItemShow> orderItems;
    private itemsShowWithReviews is ;
    private List<itemsShow> searchResult;
    private List<itemsShow> categoryResult;
    @GetMapping("/error")
    public String error(){
        return "error";
    }
    @PostMapping("/search")
    public ResponseEntity<?> search(HttpServletRequest req,Model model){
        HashMap<String , String > data = new  HashMap<String , String >();
        String search = req.getParameter("search").trim();
        searchResult = authServ.getSearchResult(search);
        return ResponseEntity.ok(data);
    }
    @GetMapping("/")
    public String root(HttpSession session , Model model){
        model.addAttribute("searchResult", searchResult);
        model.addAttribute("items", authServ.getAllItems());
        if(is == null){
            itemsShowWithReviews si = new itemsShowWithReviews();
            int id = authServ.newId(4) - 1;
            items i = authServ.getItem(id);
            si.setItem(i);
            si.setItemImage(authServ.getItemImage(id));
            List<reviewsShow> reviewShow = authServ.getItemReviews(id); 
            si.setReviewShow(reviewShow);
            si.setCategory(authServ.getCatById(i.getCategoryId()));
            is = si;
        }
        model.addAttribute("catrgoryItemShow", categoryResult);
        model.addAttribute("itemshow", is );
        List<category> l = authServ.getAllCategories();
        model.addAttribute("categories", l);
        if(authServ.isLoggedIn(session)){
            if(authServ.isAdmin(session)){
                model.addAttribute("allOrders", aos.getAllOrders());
                model.addAttribute("employees",authServ.getAllEmployee() );
                model.addAttribute("customers", authServ.getAllCustomer());
                model.addAttribute("user", authServ.getAdmin(session));
                return "auth/admin/home";
            }
            if(authServ.isCustomer(session)){
                List<orderItemShow> cart = ccs.getCutomerCart(session);
                model.addAttribute("allOrders", cos.getAllOrders(Integer.parseInt(session.getAttribute("userId").toString())));
                model.addAttribute("subtotal" , ccs.getSubtotal());
                model.addAttribute("order", or );
                model.addAttribute("orderItems", orderItems);
                List<itemsShow> wishList = cws.getCustomerWishlist(session);
                //int subtotal = 0;
                //for(int i=0;i<cart.size();i++){
                //    subtotal += cart.get(i).getQuantity() * cart.get(i).getItem().getSellingPrice();
                //}
                //model.addAttribute("order",cos.getOrders());
                model.addAttribute("wishlist", wishList);
                model.addAttribute("cart", cart);
                model.addAttribute("user", authServ.getCustomer(session));
                return "auth/customer/home";
            }
            if(authServ.isEmployee(session)){
                
                model.addAttribute("allOrders", aos.getAllOrders());
                model.addAttribute("customers", authServ.getAllCustomer());
                model.addAttribute("user", authServ.getEmployee(session));
                return "auth/employee/home";
            }
        }
        return "home";
    } 
    @PostMapping("/itemShow")
    public ResponseEntity<?> itemShow(HttpServletRequest req){
        Hashtable<String,String> data = new Hashtable<String,String>();
        int id = Integer.parseInt(req.getParameter("id").toString());
        itemsShowWithReviews si = new itemsShowWithReviews();
        items i = authServ.getItem(id);
        si.setItem(i);
        si.setItemImage(authServ.getItemImage(id));
        si.setCategory(authServ.getCatById(i.getCategoryId()));
        List<reviewsShow> reviewShow = authServ.getItemReviews(id); 
        si.setReviewShow(reviewShow);
        is = si;
        return ResponseEntity.ok(data);
    }
    @PostMapping("addReview/{id}")
    public ResponseEntity<?> addReview(@PathVariable("id") int itemId,HttpServletRequest req,HttpSession session){
        Hashtable<String,String> data = new Hashtable<String,String>();

        float rating = Float.parseFloat(req.getParameter("rating"));
        String revdesc = req.getParameter("description").trim();
        int cid = Integer.parseInt(session.getAttribute("userId").toString());
        if(authServ.isCustomer(session)){
            reviews r = new reviews();
            r.setCustomerId(cid);
            r.setItemId(itemId);
            r.setRating(rating);
            r.setReviewDescription(revdesc);
            authServ.addReview(r);
        }
        return ResponseEntity.ok(data);
    }
    @PostMapping("/showItemsWithCateogry")
    public ResponseEntity<?> showItemsWithCateogry(HttpServletRequest req){
        Hashtable<String,String> data = new Hashtable<String,String>();

        int catId = Integer.parseInt(req.getParameter("id"));
        categoryResult = authServ.getCatItems(catId);
        return ResponseEntity.ok(data);
    }
    @PostMapping("/customerEditProfile")
    public ResponseEntity<?> editProfile(HttpSession session , HttpServletRequest req){
        Hashtable<String,String> data = new Hashtable<String,String>();

        int cid = Integer.parseInt(session.getAttribute("userId").toString());
        String name = req.getParameter("name");
        String username = req.getParameter("uname");
        String email = req.getParameter("email");
        String phone = req.getParameter("phone");
        String add = req.getParameter("address");
        String dob = req.getParameter("dob");
        String err="";
        customer present = authServ.getCustomer(session); 
        if( (!present.getUsername().equals(username)) && (authServ.checkUsernameExistance(username)!=0) ){
            err = "username already exist";
        }else if((authServ.checkEmailExistance(email) != 0)&&(!present.getEmail().equals(email))){
            err = "email already exist";
        }else{
            customer c = new customer();
            c.setName(name);
            c.setCustomerId(cid);
            c.setAddress(add);
            c.setEmail(email);
            c.setDob(dob);
            c.setPhoneNo(phone);
            c.setUsername(username);
            c.setAge(authServ.calcuateAge(dob));
            cep.editProfile(c);
            data.put("result","success");
            return ResponseEntity.ok(data);
        }
        data.put("result","error");
        data.put("error",err);
        return ResponseEntity.ok(data);
    }
    @PostMapping("/adminEditProfile")
    public ResponseEntity<?> adminEditProfile(HttpSession session , HttpServletRequest req){
        Hashtable<String,String> data = new Hashtable<String,String>();

        int cid = Integer.parseInt(session.getAttribute("userId").toString());
        String name = req.getParameter("name");
        String username = req.getParameter("uname");
        String email = req.getParameter("email");
        String phone = req.getParameter("phone");
        String add = req.getParameter("address");
        String dob = req.getParameter("dob");
        String err="";
        admin present = authServ.getAdmin(session);
        if( (!present.getUsername().equals(username)) && (authServ.checkUsernameExistance(username)!=0) ){
            err = "username already exist";
        }else if((authServ.checkEmailExistance(email) != 0)&&(!present.getEmail().equals(email))){
            err = "email already exist";
        }else{
            admin c = new admin();
            c.setName(name);
            c.setAdminId(cid);
            c.setAddress(add);
            c.setEmail(email);
            c.setDob(dob);
            c.setPhoneNo(phone);
            c.setUsername(username);
            c.setAge(authServ.calcuateAge(dob));
            aep.editProfile(c);
            data.put("result","success");
            return ResponseEntity.ok(data);
        }
        data.put("result","error");
        data.put("error",err);
        return ResponseEntity.ok(data);
    }
    @PostMapping("/adminchangePass")
    public ResponseEntity<?> adminchangePass(HttpSession session , HttpServletRequest req){
        Hashtable<String,String> data = new Hashtable<String,String>();
        admin c = authServ.getAdmin(session);
        String newPass = req.getParameter("newpass").toString().trim();
        String oldPass = req.getParameter("oldpass").toString().trim();
        int x = authServ.checkCredAndPost(c.getUsername(), oldPass);
        if(x==1){
            aep.updatePass(newPass,c.getAdminId());
            data.put("result", "success");
            return ResponseEntity.ok(data);
        }
        data.put("result", "err");
        data.put("error","wrong Password");
        return ResponseEntity.ok(data);
    }
    @PostMapping("/checkout")
    public ResponseEntity<?> checkout(HttpSession session,Model model){
        Hashtable<String,String> data = new Hashtable<String,String>();
        order o = new order();
        o.setCustomerId(Integer.parseInt(session.getAttribute("userId").toString()));
        o.setOrderStatus(0);
        customer c = authServ.getCustomer(session);
        o.setDeliveryAdd(c.getAddress());
        int orderid = authServ.newId(9);
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd"); 
        LocalDateTime now = LocalDateTime.now();  
        String orderDate =   dtf.format(now);
        int sub =0 ;
        o.setOrderDate(orderDate);
        o.setOrderId(orderid);
        List<orderItemShow> cart = ccs.getCutomerCart(session);
        for(int i=0;i<cart.size();i++){
            orderItemShow x = cart.get(i);
            sub += (x.getItem().getSellingPrice())*(x.getUnits());
        }
        o.setSubtotal(sub);
        cos.saveOrder(o);
        for(int i=0;i<cart.size();i++){
            orderToItems ot = new orderToItems();
            ot.setOrderId(orderid);
            ot.setItemId(cart.get(i).getItem().getItemId());
            ot.setUnits(cart.get(i).getUnits());
            ot.setPrice(cart.get(i).getItem().getSellingPrice());
            cos.saveoti(ot);
            cos.updateItemQuantity(cart.get(i).getUnits(),cart.get(i).getItem().getItemId());
        }
        or = o;
        orderItems = cart;
        return ResponseEntity.ok(data);
    }
    //@PostMapping("/placeorder")
    //public ResponseEntity<?> placeorder(HttpSession session){
    //    Hashtable<String,String> data = new Hashtable<String,String>();
    //    order o = new order();
    //    o.setCoustomerId(Integer.parseInt(session.getAttribute("userId").toString()));
    //    o.setOrderStatus(0);
    //    int orderid = authServ.newId(9);
    //    o.setOrderId(orderid);
    //    cos.saveOrder(o);
    //    List<itemsShow> cart = ccs.getCutomerCart(session);
    //    for(int i=0;i<cart.size();i++){
    //        orderToItems ot = new orderToItems();
    //        ot.setOrderId(orderid);
    //        ot.setItemId(cart.get(i).getItem().getItemId());
    //        ot.setUnits(cart.get(i).getQuantity());
    //        cos.saveoti(ot);
    //    }
    //    return ResponseEntity.ok(data);
    //}
    
    @PostMapping("/increaseOrderStatus")
    public ResponseEntity<?> increaseOrderStatus(HttpServletRequest req ){
        Hashtable<String,String> data = new Hashtable<String,String>();
        int orderId = Integer.parseInt(req.getParameter("id").trim().toString());
        aos.increaseOrderStatus(orderId);
        return ResponseEntity.ok(data);
    }
    @PostMapping("/cancelOrder")
    public ResponseEntity<?> cancelOrder(HttpServletRequest req ){
        Hashtable<String,String> data = new Hashtable<String,String>();
        int orderId = Integer.parseInt(req.getParameter("id").trim().toString());
        aos.cancelOrder(orderId);
        return ResponseEntity.ok(data);
    }
    @PostMapping("/login")
    public ResponseEntity<?> loginAuth(HttpServletRequest req , HttpSession session  , Model model){
        Hashtable<String,String> data = new Hashtable<String,String>();
        String username = req.getParameter("username").trim();
        String password = req.getParameter("password").trim();
        int x = authServ.checkCredAndPost(username , password);
        if( x == 1){
            data.put("result", "success");
            data.put("sendPostTo", "/login-admin");
            return ResponseEntity.ok(data);
        }else if( x == 2){
            data.put("result", "success");
            data.put("sendPostTo", "/login-employee");
            return ResponseEntity.ok(data);
        }else if(x == 3){
            data.put("result", "success");
            data.put("sendPostTo", "/login-customer");
            return ResponseEntity.ok(data);
        }
        data.put("result" , "error");
        data.put("error" , "Invalid credentials");
        return ResponseEntity.ok(data);
    }
    @PostMapping("/logout")
    public ResponseEntity<?> logoutAuth(HttpSession session){
        Hashtable<String,String> data = new Hashtable<String,String>();
        if(authServ.isAdmin(session)){
            data.put("sendPostTo", "/logout-admin");
        }
        if(authServ.isCustomer(session)){
            data.put("sendPostTo", "/logout-customer");
        }
        if(authServ.isEmployee(session)){
            data.put("sendPostTo", "/logout-employee");
        }
        return ResponseEntity.ok(data);
    }
    @GetMapping("/{id}")
    public String itemDisplay(@PathVariable("id") Integer itemId , Model model ){
        items i = authServ.getItem(itemId);
        model.addAttribute("item", i);
        return "item";
    }
    @GetMapping("/items")
    public String itemsDisplay(Model model){
        
        return "items";
    }
    @PostMapping("/addToCart")
    public ResponseEntity<?> addToCart(HttpServletRequest req , HttpSession session){
        Hashtable<String,String> data = new Hashtable<String,String>();
        if(authServ.isLoggedIn(session) && authServ.isCustomer(session)){
            Integer id = Integer.parseInt(req.getParameter("id").toString());
            Integer cid = Integer.parseInt(session.getAttribute("userId").toString());
            Integer quantity = Integer.parseInt(req.getParameter("quantity").toString());
            authServ.addToCart(cid,id,quantity);
            data.put("result", "success");
            return ResponseEntity.ok(data);
        }
        data.put("result", "error");
        return ResponseEntity.ok(data);
    }

    // addToWishlist
    @PostMapping("/addToWishlist")
    public ResponseEntity<?> addToWishlist(HttpServletRequest req , HttpSession session){
        Hashtable<String,String> data = new Hashtable<String,String>();
        if(authServ.isLoggedIn(session)){
            Integer id = Integer.parseInt(req.getParameter("id").toString());
            Integer cid = Integer.parseInt(session.getAttribute("userId").toString());
            authServ.addToWishlist(cid,id);
            data.put("result", "success");
            return ResponseEntity.ok(data);
        }
        data.put("result", "error");
        return ResponseEntity.ok(data);
    }
    //removeFromCart
    @PostMapping("/removeFromCart")
    public ResponseEntity<?> removeFromCart(HttpServletRequest req , HttpSession session){
        Hashtable<String,String> data = new Hashtable<String,String>();
        if(authServ.isLoggedIn(session)){
            Integer id = Integer.parseInt(req.getParameter("id").toString());
            Integer cid = Integer.parseInt(session.getAttribute("userId").toString());
            authServ.removeFromCart(cid,id);
            data.put("result", "success");
            return ResponseEntity.ok(data);
        }
        data.put("result", "error");
        return ResponseEntity.ok(data);
    }
    @PostMapping("/removeFromWishlist")
    public ResponseEntity<?> removeFromWishlist(HttpServletRequest req , HttpSession session){
        Hashtable<String,String> data = new Hashtable<String,String>();
        if(authServ.isLoggedIn(session)){
            Integer id = Integer.parseInt(req.getParameter("id").toString());
            Integer cid = Integer.parseInt(session.getAttribute("userId").toString());
            authServ.removeFromWishlist(cid,id);
            data.put("result", "success");
            return ResponseEntity.ok(data);
        }
        data.put("result", "error");
        return ResponseEntity.ok(data);
    }
    @PostMapping("/clearCart")
    public ResponseEntity<?> clearCart(HttpServletRequest req , HttpSession session){
        Hashtable<String,String> data = new Hashtable<String,String>();
        if(authServ.isLoggedIn(session)){
            Integer cid = Integer.parseInt(session.getAttribute("userId").toString());
            authServ.clearCart(cid);
            data.put("result", "success");
            return ResponseEntity.ok(data);
        }
        data.put("result", "error");
        return ResponseEntity.ok(data);
    }
    @PostMapping("/clearWishlist")
    public ResponseEntity<?> clearWishlist(HttpServletRequest req , HttpSession session){
        Hashtable<String,String> data = new Hashtable<String,String>();
        if(authServ.isLoggedIn(session)){
            Integer cid = Integer.parseInt(session.getAttribute("userId").toString());
            authServ.clearWishlist(cid);
            data.put("result", "success");
            return ResponseEntity.ok(data);
        }
        data.put("result", "error");
        return ResponseEntity.ok(data);
    }

    @PostMapping("/remove")
    public ResponseEntity<?> remove(HttpServletRequest req , HttpSession session){
        Hashtable<String,String> data = new Hashtable<String,String>();
        if(authServ.isLoggedIn(session) && authServ.isAdmin(session)){
            Integer id = Integer.parseInt(req.getParameter("id").toString());
            String what = req.getParameter("what").toString();
            authServ.remove(what, id);
            data.put("result", "success");
            return ResponseEntity.ok(data);
        }
        data.put("result", "error");
        return ResponseEntity.ok(data);
    }
}