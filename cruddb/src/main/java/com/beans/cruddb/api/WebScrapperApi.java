package com.beans.cruddb.api;

import com.beans.cruddb.service.WebScrapperService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/scrapper")
public class WebScrapperApi {

    private final WebScrapperService webScrapperService;

    public WebScrapperApi(WebScrapperService webScrapperService) {
        this.webScrapperService = webScrapperService;
    }

    @GetMapping("/title")
    public String getTitle(@RequestParam String url){
        return webScrapperService.getPageTittle(url);
    }

    @GetMapping("/heading")
    public List<String> getHeadings(@RequestParam String url){
        return webScrapperService.getHeadings(url);
    }

    @GetMapping("/links")
    public List<String> getLinks(@RequestParam String url){
        return webScrapperService.getLinks(url);
    }

    @GetMapping("/details")
    public Map<String, Object> getAllDetails(@RequestParam String url){
        return webScrapperService.getAllDetails(url);
    }
}
