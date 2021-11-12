package com.example.demo.controller.admin;

import java.util.Hashtable;

import javax.servlet.http.HttpServletRequest;

import com.example.demo.model.itemImage;
import com.example.demo.model.items;
import com.example.demo.service.FileUploadUtil;
import com.example.demo.service.authService;
import com.example.demo.service.admin.adminItemService;
import com.example.demo.service.admin.itemImageService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class adminItemsController {
    // addItem
    @Autowired
    private adminItemService ais;
    @Autowired
    private authService auth;
    @Autowired
    private FileUploadUtil fuu;
    @Autowired
    private itemImageService iis;
    @PostMapping("/addItem")
    public ResponseEntity<?> addItem(HttpServletRequest req,@RequestParam("image")MultipartFile[] uploadFile){
        Hashtable<String,String> data = new Hashtable<String,String>();
        String itemName  = req.getParameter("itemName").trim();
        String itemDescription  = req.getParameter("itemDescription").trim();
        int sellingPrice  = Integer.parseInt(req.getParameter("sellingPrice").trim());
        int buyingPrice  = Integer.parseInt(req.getParameter("buyingPrice").trim());
        int categoryId  = Integer.parseInt(req.getParameter("categoryId").trim());
        int quantity  = Integer.parseInt(req.getParameter("quantity").trim());
        int itemId = auth.newId(4);
        items  i = new items(); 
        i.setBuyingPrice(buyingPrice);
        i.setCategoryId(categoryId);
        i.setItemDescription(itemDescription);
        i.setItemId(itemId);
        i.setItemName(itemName);
        i.setQuantity(quantity);
        i.setSellingPrice(sellingPrice);
        String err = "";
        if(itemDescription.length() < 2){
            err = "description too short must contain 20 words";
        }else if( itemName.length() < 2 ){
            err = "item name too short";
        }else{
            data.put("result" , "success");
            ais.addItem(i);
            int imid = auth.newId(7);
            for(int j=0;j<uploadFile.length;j++){
                itemImage ii = new itemImage();
                ii.setItemId(itemId);
                String ofname = uploadFile[j].getOriginalFilename();
                String extension = "";
                int k = ofname.lastIndexOf('.');
                if (k > 0) {
                    extension = ofname.substring(k);
                }
                try{
                    fuu.saveFile(itemId, "items", uploadFile[j], String.valueOf(imid)+extension);
                    ii.setImage(String.valueOf(imid)+extension);
                    imid++;
                }catch(Exception e){
                    ii.setImage("#");
                }
                iis.saveItemImage(ii);
            }
            if(uploadFile.length== 0){
                itemImage ii = new itemImage();
                ii.setImage("#");
                ii.setItemId(itemId);
            }
            return ResponseEntity.ok(data);
        }
        data.put("result", "error");
        data.put("error", err);
        return ResponseEntity.ok(data);
    }
    // remove items
    // update items
    @PostMapping("/editItem/{itemId}")
    public String editItems(@PathVariable("itemId") int itemId, HttpServletRequest req){

        String itemName  = req.getParameter("itemName").trim();
        String itemDescription  = req.getParameter("itemDescription").trim();
        int sellingPrice  = Integer.parseInt(req.getParameter("sellingPrice").trim());
        int buyingPrice  = Integer.parseInt(req.getParameter("buyingPrice").trim());
        int categoryId  = Integer.parseInt(req.getParameter("categoryId").trim());
        int quantity  = Integer.parseInt(req.getParameter("quantity").trim());
        items  i = new items(); 
        i.setBuyingPrice(buyingPrice);
        i.setCategoryId(categoryId);
        i.setItemDescription(itemDescription);
        i.setItemId(itemId);
        i.setItemName(itemName);
        i.setQuantity(quantity);
        i.setSellingPrice(sellingPrice);
        ais.updateItem(i);
        return "redirect:/";
    }
}