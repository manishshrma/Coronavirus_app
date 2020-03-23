package com.example.coronavirusapp.services;
import com.example.coronavirusapp.models.Location_stats;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.StringReader;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

@Service
public class CornonavirusServices {
    Location_stats location_stats=new Location_stats();
    List<Location_stats> mylist=new ArrayList<>();
    // below is the string of url(csv file data url)
    private static final String VIRUS_DATA_URL = "https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/csse_covid_19_data/csse_covid_19_time_series/time_series_19-covid-Confirmed.csv";
    private List<Location_stats> allstats=new ArrayList<>();

    public List<Location_stats> getAllstats() {
        return allstats;
    }

    @PostConstruct
    @Scheduled(cron = "* * * * * *")
    public void fetchVirusData() throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(VIRUS_DATA_URL))
                .build();
        HttpResponse<String> httpResponse = client.send(request, HttpResponse.BodyHandlers.ofString());
        // string reader get the httpresponse and get it body
        StringReader csvrecords = new StringReader(httpResponse.body());
//    ModelAndView modelAndView=new ModelAndView();
//    modelAndView.addObject("mydata",httpResponse);
//    httpResponse.body();
        Iterable<CSVRecord> records = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(csvrecords);//have all the data(rowwise)
//        Location_stats location_stats=new Location_stats();
        //////////////////////////////////total case changes on daily basis(or yor crown)//////////////////
        // Doubt why this code make all the value 0 in my application?? why not working??
        //this is bad programming practice
//        int csum=0; int psum=0;
//        for (CSVRecord myrecord : records) {
//            String data1=myrecord.get(myrecord.size()-1);
//            String data2=myrecord.get(myrecord.size()-2);
//
//            csum+=Integer.parseInt(data1);
//            psum+=Integer.parseInt(data2);
//
//        }
//        int diff=Math.abs(csum-psum);
//        Location_stats totalcasetoday=new Location_stats();
//        totalcasetoday.setTotalcase_today(diff);
/////////////////////////////////////////////end////////////////////////////////////////////////
        for (CSVRecord record : records) {
            // records have data in like table forms(consider it have 10 rows)
            // record have the first row (as data) first row not in string form but in form of array.
            Location_stats location_stats=new Location_stats();
            // created obj so that each row data get mapped to the fields in this locations_stats instance
            // for each row data , new instances get created and all the members get filled.
            // and then one by one all the instances get added to the list

            String states_data = record.get("Province/State"); // record
            String country_data = record.get("Country/Region");
            String latest_totalcases = record.get(record.size()-1);
            String latest_date_data=record.get(record.size()-1);
            String prev_from_curr=record.get(record.size()-2);
            int change_since_lastday =Integer.parseInt(latest_date_data)-Integer.parseInt(prev_from_curr);

            location_stats.setStates(states_data);
            location_stats.setCountry(country_data);
            location_stats.setLatest_stats(Integer.parseInt(latest_totalcases));
            location_stats.setDifffromprevday(change_since_lastday);
            location_stats.setTotalcase_today(change_since_lastday);
//            System.out.println(location_stats);
            mylist.add(location_stats);
//            System.out.println(mylist);
        }
        this.allstats=mylist;
    }
}
