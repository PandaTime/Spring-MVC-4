package com.springbook.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import javax.annotation.PostConstruct;
import org.springframework.social.twitter.api.Twitter;
import org.springframework.social.twitter.api.impl.TwitterTemplate;
import org.springframework.social.twitter.api.Tweet;
import org.springframework.social.twitter.api.SearchResults;

import javax.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class TweetController {

  @Value("${spring.social.twitter.consumerKey}")
  private String consumerKey;
  
  @Value("${spring.social.twitter.consumerSecret}")
  private String consumerSecret;

  private Twitter twitter;

  @PostConstruct
  public void init() {
    twitter = new TwitterTemplate(consumerKey, consumerSecret);
  }

  @RequestMapping("/search")
  public String home() {
    return "searchPage";
  }

  @RequestMapping("/result")
  public String helloTwitter(@RequestParam(defaultValue = "Trump") String search, Model model) {
    SearchResults searchResults = twitter.searchOperations().search(search);
    // List<Tweet> tweets = searchResults.getTweets();
    // model.addAttribute("message", tweets.size() > 0 ? tweets.get(0).getText() : "No tweets found :(");
    // List<String> tweets = searchResults.getTweets().stream().map(Tweet::getText).collect(Collectors.toList());
    List<Tweet> tweets = searchResults.getTweets();
    model.addAttribute("tweets", tweets);
    model.addAttribute("search", search);

    return "resultPage";
  }

  @RequestMapping(value = "/postSearch", method = RequestMethod.POST)
   public String postSearch(HttpServletRequest request, RedirectAttributes redirectAttributes) {
    String search = request.getParameter("search");
    if (search.toLowerCase().contains("struts")) {
      redirectAttributes.addFlashAttribute("error", "Try using spring instead!");
      return "redirect:/search";
    }
    redirectAttributes.addAttribute("search", search);
    return "redirect:result";
  }

}