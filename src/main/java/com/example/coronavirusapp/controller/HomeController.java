package com.example.coronavirusapp.controller;

import com.example.coronavirusapp.models.Location_stats;
import com.example.coronavirusapp.services.CornonavirusServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class HomeController {
    @Autowired
    private CornonavirusServices cornonavirusServices;
@GetMapping(value = "/")
public String Home(Model model)
{
    List<Location_stats> tsum=cornonavirusServices.getAllstats();
    int total=tsum.stream().mapToInt(stat->stat.getLatest_stats()).sum();
    model.addAttribute("locationstats",cornonavirusServices.getAllstats());
    model.addAttribute("totalcases",total);
    int changesincelastday=tsum.stream().mapToInt(stat->stat.getTotalcase_today()).sum();

//    Location_stats location_stats=new Location_stats();
//    int result=location_stats.getTotalcase_today();
    model.addAttribute("totalcase_change",changesincelastday);
    return "home";
}
// on click home in navbar go to another page india
    // it show india corona updates
@GetMapping(value = "/myhome")
public String gethome(Model model)
{
    List<Location_stats> alldata=  cornonavirusServices.getAllstats();

    List<Location_stats> mylist=alldata.stream().filter(x->x.getCountry().equals("India")).collect(Collectors.toList());
    System.out.println(mylist);
    model.addAttribute("india_data",mylist);
    return "india";

}
@GetMapping(value = "/getfill")
public String filldetails(Model model)
{
   List<Location_stats> alldata=  cornonavirusServices.getAllstats();

   List<Location_stats> mylist=alldata.stream().filter(x->x.getCountry().equals("India")).collect(Collectors.toList());
    System.out.println(mylist);
    model.addAttribute("india_datat",mylist);
    return "redirect:/myhome";


}

}
