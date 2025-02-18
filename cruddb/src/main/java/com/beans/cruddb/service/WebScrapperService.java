    package com.beans.cruddb.service;

    import org.jsoup.Jsoup;
    import org.jsoup.nodes.Document;
    import org.jsoup.nodes.Element;
    import org.jsoup.select.Elements;
    import org.springframework.stereotype.Service;

    import java.io.IOException;
    import java.util.ArrayList;
    import java.util.HashMap;
    import java.util.List;
    import java.util.Map;

    @Service
    public class WebScrapperService {

        String url = "https://en.wikipedia.org/wiki/Web_scraping";

        public String getPageTittle(String url) {

            try {
                Document doc = Jsoup.connect(url).get();
                return doc.title();

            } catch (Exception e) {
                e.printStackTrace();
                return "Error fetching title";
            }
        }

        public List<String> getHeadings(String url) {
            List<String> headingList = new ArrayList<>();
            try {
                Document doc = Jsoup.connect(url).get();
                Elements headings = doc.select("h2");

                for (Element heading : headings) {
                    headingList.add(heading.text());
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            return headingList;
        }

        public List<String> getLinks(String url) {
            List<String> linkList = new ArrayList<>();

            try {
                Document doc = Jsoup.connect(url).get();
                Elements links = doc.select("a[href]");

                for (Element link : links) {
                    linkList.add(link.attr("href") + " - " + link.text());
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return linkList;
        }

        public Map<String, Object> getAllDetails(String url) {

            Map<String, Object> details = new HashMap<>();

            details.put("title", getPageTittle(url));
            details.put("headings", getHeadings(url));
            details.put("links", getLinks(url));

            return details;
        }
    }
