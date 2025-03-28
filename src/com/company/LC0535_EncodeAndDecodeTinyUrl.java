package com.company;

import java.util.HashMap;
import java.util.Map;

public class LC0535_EncodeAndDecodeTinyUrl {
    private final static String CODE_CHARS = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

    private final static String TINY_BASE_URL = "http://tinyurl.com/";

    private Map<String, String> codeToUrl = new HashMap<>();
    private Map<String, String> urlToCode = new HashMap<>();

    // Encodes a URL to a shortened URL.
    public String encode(String longUrl) {
        if(urlToCode.containsKey(longUrl)) return urlToCode.get(longUrl);

        String code = getShortCode();
        while(codeToUrl.containsKey(code)) code = getShortCode();

        String codedUrl = getShortUrl(code);

        codeToUrl.put(codedUrl, longUrl);
        urlToCode.put(longUrl, codedUrl);

        return codedUrl;
    }

    // Decodes a shortened URL to its original URL.
    public String decode(String shortUrl) {
        return codeToUrl.get(shortUrl);
    }

    private String getShortCode(){
        StringBuffer sb = new StringBuffer();
        for(int i=0; i<6; i++){
            int index = (int) (Math.random() * 62);
            sb.append(CODE_CHARS.charAt(index));
        }
        return sb.toString();
    }

    private String getShortUrl(String code){
        return String.format("%s%s", TINY_BASE_URL, code);
    }
}
