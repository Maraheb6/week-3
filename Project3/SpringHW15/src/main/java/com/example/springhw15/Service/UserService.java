package com.example.springhw15.Service;

import com.example.springhw15.Pojo.MerchantStock;
import com.example.springhw15.Pojo.Product;
import com.example.springhw15.Pojo.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class UserService {
    ArrayList<User>users=new ArrayList<>();
    ArrayList<MerchantStock>merchantStocks=new ArrayList<>();
    ArrayList<Product>products=new ArrayList<>();


    public ArrayList<User> getUser(){
        return users;
    }
    public void addUser(User user){
        users.add(user);
    }

    public boolean updateUser(String id,User user){
        for(int i=0;i<users.size();i++){
            if(users.get(i).getId().equals(id)){
                users.set(i,user);
                return true;
            }
        }
        return false;
    }

    public boolean deleteUser(String id){
        for(int i=0;i<users.size();i++){
            if(users.get(i).getId().equals(id)){
                users.remove(i);
                return true;
            }
        }
        return false;
    }

    public int addProduct( String id , String productId, String merchantId , int stock,MerchantStock merchantStock){
        for (int i=0;i<users.size();i++){
            if(users.get(i).getId().equals(id)){
               // if(users.get(i).getId().equals("Admin")){
                    merchantStock.setId(id);
                    merchantStock.setProductid(productId);
                    merchantStock.setMerchantid(merchantId);
                    merchantStock.setStock(stock);


                    return 1;
               // }
               // else return 2;
            }

        }
        return 2;
    }
    public int buyProduct(String id ,String productid,String merchantid,int number,User user,MerchantStock merchantStock) {
        for(int i=0;i<users.size();i++){
            if(users.get(i).getId().equals(id)){
                for (int j=0;j<products.size();j++){
                    if(products.get(j).getId().equals(productid)){
                        for (int k=0;k<merchantStocks.size();k++){
                            if(merchantStocks.get(k).getMerchantid().equals(merchantid)){
                                int stock=merchantStock.getStock();
                                if(stock>=number){
                                   if(users.get(i).getBalance()>=products.get(j).getPrice()) {
                                       merchantStock.setStock(merchantStock.getStock()-number);
                                       user.setBalance(user.getBalance()-products.get(j).getPrice());
                                       //Don buy
                                       return 6;
                                   }
                                   //balance less than price
                                    return 5;
                                }

                                //stock empty
                                return 4;
                            }
                        }
                        //wrong merchant id
                        return 3;
                    }

                }
                //wrong product id
                return 2;
            }
        }
        //wrong user id
      return 1;

    }
}
/*
  for (User user : users) {
            for (Product product : products) {
                for (MerchantStock merchantStock1 : merchantStocks) {
                    if (user.getId().equals(id)) {
                        if (product.getId().equals(productid)) {
                            if (merchantStock1.getMerchantid().equals(merchantid)) {
                                if (merchantStock1.getStock() > 0) {
                                    if (user.getBalance() >= product.getPrice()) {
                                        int newBalance=user.getBalance();
                                        int price=product.getPrice();
                                        int stock=merchantStock1.getStock();
                                        merchantStock1.setStock(stock- 1);
                                        user.setBalance(newBalance - price);
                                        return 1;
                                    }
                                    // user balance less than product price
                                    return 2;
                                }
                                //stock empty
                                return 3;
                            }


                        }

                    }

                }
                return 4;
                //wrong merchant id
            }
            //wrong product id
            return 5;
        }
        //wrong user id
        return 6;
 */