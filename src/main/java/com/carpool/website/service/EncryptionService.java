package com.carpool.website.service;

import com.carpool.website.dao.UserEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.MessageDigest;

/**
 * Created by deado on 2016/11/30.
 */

@Service
public class EncryptionService {
    @Autowired
    private UserEntityRepository userEntityRepository;

    Character[] HEX_DIG = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

    private String encipherPassword(String password) throws Exception{
        try{
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            byte[] bytePw = password.getBytes("UTF8");
            messageDigest.update(bytePw);
            byte[] byteRes = messageDigest.digest();

            StringBuilder ret = new StringBuilder();

            for(int i=0; i<byteRes.length; i++){
                int tempContainer = byteRes[i];
                if(tempContainer < 0){
                    tempContainer+=256;
                }

                ret.append(HEX_DIG[tempContainer/16]);
                ret.append(HEX_DIG[tempContainer%16]);
            }

            return ret.toString();


        }catch(Exception e){
            throw e;
        }
    }


    public boolean comparePW(String userId, String password) throws Exception{
        try{
            String md5Pw = this.userEntityRepository.findById(userId).getPassword();
            String ts = this.encipherPassword(password) + this.encipherPassword(userId);
            if(0 == md5Pw.compareTo(
                    ts
            )){
                return true;
            }
            else{
                return false;
            }
        }catch(Exception e){
            throw e;
        }
    }

    public String encipher(String password) throws Exception{
        try{
            return this.encipherPassword(password);
        }catch(Exception e){
            throw e;
        }
    }

}
