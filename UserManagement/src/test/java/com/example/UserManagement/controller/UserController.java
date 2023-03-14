package com.example.UserManagement.controller;

import com.example.UserManagement.model.User;
import com.example.UserManagement.service.UserService;
import jakarta.annotation.Nullable;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping(value ="user")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping(value = "/allUser")
    public String getUser() {
        String str = userService.getAllUser();
        return str;
    }

    @DeleteMapping(value ="{/id}")
    public String DeleteUser(@PathVariable int id){
        userService.deleteuser(id);
        return "user deleted";
    }

    @PostMapping("/saveuser")
    public ResponseEntity saveUser(@RequestBody String user) throws JSONException {
        JSONObject jsonobject = new JSONObject(user);


        List<String> errorlist = validation(jsonobject);

        if (errorlist.isEmpty()) {

            User newuser = setuser(jsonobject);


            userService.saveuser(newuser);

            return new ResponseEntity("user saved", HttpStatus.CREATED);
        }
        else {

            String[] arr = Arrays.copyOf(

                    errorlist.toArray(), errorlist.size(), String[].class);

            return new ResponseEntity<>(Arrays.toString(arr), HttpStatus.BAD_REQUEST);
        }
    }

    private User setuser(JSONObject jsonobject) throws JSONException {
        User user = null;
        user.setUserId(Integer.valueOf(jsonobject.getString("userId")));
        user.setUserName(jsonobject.getString("userName"));
        user.setTime(jsonobject.getString("time"));
        user.setEMail(jsonobject.getString("eMail"));
         String str =jsonobject.getString("number");
         user.setNumber(str);
         String string = jsonobject.getString("dob");
         user.setDob(string);
           return user;
    }

    public List<String> validation(JSONObject user){
        List<String>  errorlist=new ArrayList<>();
        if(!user.has("userId")){
            errorlist.add("userId");
        }
        if(!user.has("userName")){
            errorlist.add("userName");
        }
        if(!user.has("eMail")){
            errorlist.add("eMail");
        }
        if(!user.has("number") ){
            errorlist.add("number");

        }
        return errorlist;
    }

}