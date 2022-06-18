package com.company;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LC1268_SearchSuggestionSystem {
    public static void main(String[] args){
        String[] products = {"mobile","mouse","moneypot","monitor","mousepad"};
        String searchWord = "mouse";
        System.out.println(suggestedProducts(products, searchWord));
    }

    public static List<List<String>> suggestedProducts(String[] products, String searchWord) {
        Arrays.sort(products);

        List<List<String>> result = new ArrayList<>();

        for(int i = 1; i <= searchWord.length(); i++){
            String searchPrefix = searchWord.substring(0, i);
            List<String> searchResult = search(searchPrefix, products);
            result.add(searchResult);
        }

        return result;
    }


    public static List<String> search(String searchWord, String[] products){
        List<String> result = new ArrayList<>();
        int k=0;
        for(String product: products){
            if(product.startsWith(searchWord)){
                result.add(product);
                k++;
            }
            if(k == 3) break;
        }
        return result;
    }
}
