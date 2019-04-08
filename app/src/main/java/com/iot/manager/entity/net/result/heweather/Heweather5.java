package com.iot.manager.entity.net.result.heweather;
import java.util.List;
/**
 * Auto-generated: 2019-03-20 18:28:28
 *
 * @author www.jsons.cn 
 * @website http://www.jsons.cn/json2java/ 
 */
public class Heweather5 {

    private Aqi aqi;
    private Basic basic;

    private List<DailyForecast> dailyForecast;
    private Now now;
    private String status;
    private Suggestion suggestion;
    public void setAqi(Aqi aqi) {
         this.aqi = aqi;
     }
     public Aqi getAqi() {
         return aqi;
     }

    public void setBasic(Basic basic) {
         this.basic = basic;
     }
     public Basic getBasic() {
         return basic;
     }

    public void setDailyForecast(List<DailyForecast> dailyForecast) {
         this.dailyForecast = dailyForecast;
     }
     public List<DailyForecast> getDailyForecast() {
         return dailyForecast;
     }

    public void setNow(Now now) {
         this.now = now;
     }
     public Now getNow() {
         return now;
     }

    public void setStatus(String status) {
         this.status = status;
     }
     public String getStatus() {
         return status;
     }

    public void setSuggestion(Suggestion suggestion) {
         this.suggestion = suggestion;
     }
     public Suggestion getSuggestion() {
         return suggestion;
     }

}