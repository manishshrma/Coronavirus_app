package com.example.coronavirusapp.models;

public class Location_stats {
    private String state;
    private String country;
    private int latest_stats;
    private int difffromprevday;
    private int totalcase_today;
public Location_stats()
{

}
    public Location_stats(String state, String country, int latest_stats,int difffromprevday) {
        this.state = state;
        this.country = country;
        this.latest_stats = latest_stats;
        this.difffromprevday=difffromprevday;
    }

    public String getStates() {
        return state;
    }

    public void setStates(String state) {
        this.state = state;
}

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public int getLatest_stats() {
        return latest_stats;
    }

    public void setLatest_stats(int latest_stats) {
        this.latest_stats = latest_stats;
    }

    public int getDifffromprevday() {
        return difffromprevday;
    }

    public void setDifffromprevday(int difffromprevday) {
        this.difffromprevday = difffromprevday;
    }

    public int getTotalcase_today() {
        return totalcase_today;
    }

    public void setTotalcase_today(int totalcase_today) {
        this.totalcase_today = totalcase_today;
    }

    @Override
    public String toString() {
        return "Location_stats{" +
                "state='" + state + '\'' +
                ", country='" + country + '\'' +
                ", latest_stats=" + latest_stats +
                '}';
    }
}
