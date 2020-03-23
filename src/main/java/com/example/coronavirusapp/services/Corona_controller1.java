package com.example.coronavirusapp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@RestController
public class Corona_controller1 {
    @Autowired
    CornonavirusServices cornonavirusServices;
@RequestMapping(value = "/path2")
    public String showdata(@ModelAttribute("mydata") Object obj) throws IOException, InterruptedException {

//        return cornonavirusServices.fetchVirusData();
    return "mde";
    }
}
