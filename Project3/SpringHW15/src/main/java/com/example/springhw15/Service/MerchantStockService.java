package com.example.springhw15.Service;

import com.example.springhw15.Pojo.MerchantStock;
import com.example.springhw15.Pojo.Product;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class MerchantStockService {
    ArrayList<MerchantStock>merchantStocks=new ArrayList<>();


    public ArrayList<MerchantStock> getMerchantStock(){
        return merchantStocks;
    }
    public void addMerchantStock(MerchantStock merchantStock){
        merchantStocks.add(merchantStock);
    }

    public boolean updateMerchantStock(String id,MerchantStock merchantStock){
        for(int i=0;i<merchantStocks.size();i++){
            if(merchantStocks.get(i).getId().equals(id)){
                merchantStocks.set(i,merchantStock);
                return true;
            }
        }
        return false;
    }

    public boolean deleteMerchantStock(String id){
        for(int i=0;i<merchantStocks.size();i++){
            if(merchantStocks.get(i).getId().equals(id)){
                merchantStocks.remove(i);
                return true;
            }
        }
        return false;
    }
}
