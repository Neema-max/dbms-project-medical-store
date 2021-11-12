package com.example.demo.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import com.example.demo.model.admin;
import com.example.demo.model.category;
import com.example.demo.model.customer;
import com.example.demo.model.employee;
import com.example.demo.model.items;
import com.example.demo.model.reviews;
import com.example.demo.model.reviewsShow;
import com.example.demo.model.itemImage;

import com.example.demo.model.itemShow.itemsShow;
import com.example.demo.repository.authRepo;
import com.example.demo.repository.admin.adminAddingRepo;
import com.example.demo.repository.admin.adminCategoryRepository;
import com.example.demo.repository.admin.adminItemRepository;
import com.example.demo.repository.admin.employeeAddingRepository;
import com.example.demo.repository.customer.customerOrderRepo;
import com.example.demo.repository.customer.customerRegisterRepo;

import org.jvnet.hk2.annotations.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
@Component
@Service
public class authService {
    @Autowired
    private employeeAddingRepository ear;
    @Autowired
    private adminItemRepository atr;
    @Autowired
    private authRepo auth;
    @Autowired
    private adminAddingRepo aar;
    @Autowired 
    private customerRegisterRepo crr;
    @Autowired
    private adminCategoryRepository acr;
    @Autowired
    private customerOrderRepo cor;
    // is already loggedin
    public Boolean isLoggedIn(HttpSession session){
        return session.getAttribute("userId")!=null;
    }
    // is customer
    public Boolean isCustomer(HttpSession session){
        if(isLoggedIn(session))
            return (session.getAttribute("userId").toString()).charAt(2)=='1';
        return false;
    }
    public Boolean isCustomer(String username){
        return auth.findCustomer(username)!=null;
    }
    public Boolean isCustomer(String email ,int  x){
        return auth.findCustomerByEmail(email )!=null;
    }
    public customer getCustomer(HttpSession session){
        return auth.findCustomerById(Integer.parseInt(session.getAttribute("userId").toString()) );
    }
    // is admin
    public Boolean isAdmin(HttpSession session){
        if(isLoggedIn(session))
            return (session.getAttribute("userId").toString()).charAt(2)=='3';
        return false;
    }
    public Boolean isAdmin(String username){
        return auth.findAdmin(username)!=null;
    }
    public Boolean isAdmin(String email ,int  x){
        return auth.findAdminByEmail(email)!=null;
    }
    public admin getAdmin(HttpSession session){
        return auth.findAdminById(Integer.parseInt(session.getAttribute("userId").toString()) );
    }
    // is employee
    public Boolean isEmployee(HttpSession session){
        if(isLoggedIn(session))
            return (session.getAttribute("userId").toString()).charAt(2)=='2';
        return false;
    }
    public Boolean isEmployee(String username){
        return auth.findEmployee(username)!=null;
    }
    public Boolean isEmployee(String email ,int  x ){
        return auth.findEmployeeByEmail(email)!=null;
    }
    public employee getEmployee(HttpSession session){
        return auth.findEmployeeById(Integer.parseInt(session.getAttribute("userId").toString()) );
    }
    // check credentials
    public int checkCredAndPost(String username , String password){
        if(isAdmin(username) && auth.findAdmin(username).getPassword().equals(password) ){
            return 1;
        }else if( isCustomer(username) && auth.findCustomer(username).getPassword().equals(password) ){
            return 3;
        }else if(isEmployee(username) && auth.findEmployee(username).getPassword().equals(password) ){
            return 2;
        }
        return 0;
    }
    public items getItem(int id){
        return auth.getItem(id);
    }
    public int checkUsernameExistance(String username){
        if(isAdmin(username)){
            return 1;
        }else if( isCustomer(username) ){
            return 3;
        }else if(isEmployee(username) ){
            return 2;
        }
        return 0;
    }
    public int checkEmailExistance(String email){
        if(isAdmin(email,1)){
            return 1;
        }else if( isCustomer(email,1) ){
            return 3;
        }else if(isEmployee(email,1) ){
            return 2;
        }
        return 0;
    }
    public int newId(int x){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss"); 
        LocalDateTime now = LocalDateTime.now();  
        String s =   dtf.format(now);
        int id = (s.charAt(2) -'0')*100000 + (s.charAt(3)-'0')*10000 + x*1000;
        int last = 0;
        if(x == 1){
            last = crr.getLastCustomerID();
        }else if(x ==2){
            try{
                last = ear.getLastEmployeeId();
            }catch(Exception e){
                last = 0;
            }
            
        }else if(x==3){
            last = aar.getLastAdminID();
        }else if(x==4){
            try{
                last = atr.getLastItemID();
            }catch(Exception e){
                last = 0;
            }
            
        }else if(x==5){
            last = acr.getLastCategoryID();            
        }else if(x == 7){
            try{
                last = atr.lastItemImage();
            }catch(Exception e){
                last = 0;
            }
        }else if(x == 9){
            try{
                last = cor.lastOrderId();
            }catch(Exception e){
                last= 0 ;
            }
        }
        last = last%1000;
        last++;
        id = id + last;
        return id;
    }
    public int calcuateAge(String dob){
        // yyyy-mm-dd
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd"); 
        LocalDateTime now = LocalDateTime.now();  
        String s =   dtf.format(now);
        int cy = (s.charAt(0)-'0')*1000 + (s.charAt(1)-'0')*100 + (s.charAt(2)-'0') * 10 + (s.charAt(3)-'0'); 
        int by = (dob.charAt(0)-'0')*1000 + (dob.charAt(1)-'0')*100 + (dob.charAt(2)-'0') * 10 + (dob.charAt(3)-'0'); 
        int cm = (s.charAt(5)-'0')*10 + (s.charAt(6)-'0');
        int cd = (s.charAt(8)-'0')*10 + (s.charAt(9)-'0');
        int bm = (dob.charAt(5)-'0')*10 + (dob.charAt(6)-'0');
        int bd = (dob.charAt(8)-'0')*10 + (dob.charAt(9)-'0');
        int age = cy - by;
        if( bm > cm || ( bm == cm && bd > cd ) ){
            age--;
        }
        return age;
    }
    public List<category> getAllCategories(){
        return auth.getAllCategories();
    }
    public List<itemsShow> getSearchResult(String s){
        List<items> items = auth.getSearchResult(s);
        List<itemsShow> ll = new ArrayList<itemsShow>();
        for(int i=0;i<items.size();i++){
            itemsShow e = new itemsShow();
            items item = items.get(i);
            e.setItem(item);
            e.setItemImage(auth.getItemImage(item.getItemId()));
            ll.add(e);
        }
        return ll;
    }
    public List<itemsShow> getCatItems(int catId){
        List<items> items = auth.getCatItems(catId);
        List<itemsShow> ll = new ArrayList<itemsShow>();
        for(int i=0;i<items.size();i++){
            itemsShow e = new itemsShow();
            items item = items.get(i);
            e.setItem(item);
            e.setItemImage(auth.getItemImage(item.getItemId()));
            ll.add(e);
        }
        return ll;
    }
    public List<itemImage> getItemImage(int id){
        List<itemImage> l = auth.getItemImage(id);
        return l;
    }
    public String getCatById(int id){
        return auth.getCatById(id);
    }
    public category getCategoryById(int catId){
        return auth.getCategoryById(catId);
    }
    public List<itemsShow> getAllItems(){
        List<items> items = auth.getAllItems();
        List<itemsShow> itemshow = new ArrayList<itemsShow>();
        for(int i=0;i<items.size();i++){
            itemsShow f = new itemsShow();
            f.setItem(items.get(i));
            f.setItemImage(auth.getItemImage(items.get(i).getItemId()));
            itemshow.add(f);
        }
        return itemshow;
    }
    public void addToCart(int cid, int iid, int quantity){
        if(!auth.existInCart(cid,iid)){
            auth.addToCart(cid,iid,quantity);
        }
    }
    //addToWishlist
    public void addToWishlist(int cid, int iid){
        if(!auth.existInWishlist(cid,iid)){
            auth.addToWishlist(cid,iid);
        }
    }
    // removeFromCart
    public void removeFromCart(int cid, int iid){
        //if(!auth.existInWishlist(cid,iid)){
            auth.removeFromCart(cid,iid);
        //}
    }
    public void removeFromWishlist(int cid, int iid){
        //if(!auth.existInWishlist(cid,iid)){
            auth.removeFromWishlist(cid,iid);
        //}
    }
    //clearCart
    public void clearCart(int cid){
        //if(!auth.existInWishlist(cid,iid)){
            auth.clearCart(cid);
        //}
    }
    public void clearWishlist(int cid){
        //if(!auth.existInWishlist(cid,iid)){
            auth.clearWishlist(cid); 
        //}
    }
    public List<employee> getAllEmployee(){
        return auth.getAllEmployee();
    }
    public List<customer> getAllCustomer(){
        return auth.getAllCustomer();
    }
    // remove all 
    public void remove(String what , int id){
        if(what.equals("emp")){
            auth.remove("employee" , id, "empId");
        }else if(what.equals("cust")){
            auth.remove("customer" , id, "customerId");
        }else if(what.equals("item")){
            auth.remove("items" , id , "itemId");
        }else if(what.equals("cat")){
            auth.remove("category", id, "catId");
        }
    } 
    public List<reviewsShow> getItemReviews(int id){
        List<reviews> r = auth.getReviews(id);
        List<reviewsShow> ll = new ArrayList<reviewsShow>();
        for(int i=0;i<r.size();i++){
            reviewsShow rs = new reviewsShow();
            reviews rr = r.get(i);
            rs.setCust(auth.findCustomerById(rr.getCustomerId()));
            rs.setReview(rr);
            ll.add(rs);
        }
        return ll;
    }
    public void addReview(reviews r){
        auth.addReview(r);
    } 
}