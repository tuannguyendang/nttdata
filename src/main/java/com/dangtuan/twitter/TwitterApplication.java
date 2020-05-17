package com.dangtuan.twitter;

import static java.util.stream.Collectors.joining;

import com.dangtuan.twitter.service.TwitterService;
import com.dangtuan.twitter.utils.Utils;
import java.util.List;
import java.util.logging.Logger;

public class TwitterApplication {

  private final static Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

  public static void main(String[] args) {
    final Utils utils = new Utils();
    utils.initData();

    final TwitterService twitter = TwitterService.getTwitterInstance();
    try {
      System.out.println("Starting Application Twitter!");

      twitter.postTweet(1, 5);
      System.out.println(String.format("Twitter Posted with userId = {%d}, tweetId = {%d}", 1, 5));

      final List<Integer> topRecentTweets = twitter.getNewsFeed(1, 10);
      System.out.println(String.format("Top {%d} Twitter Posted with userId = {%d} is {%s}", 10, 1,
          topRecentTweets.stream().map(String::valueOf).collect(joining())));

      twitter.follow(1, 2);
      System.out.println(String.format("userId = {%d} follow followeeId = {%d}", 1, 2));

      twitter.postTweet(2, 6);
      twitter.getNewsFeed(1, 10);

      twitter.unfollow(1, 2);
      System.out.println(String.format("userId = {%d} unfollow followeeId = {%d}", 1, 2));
      twitter.getNewsFeed(1, 10);
      System.out.println("Application Twitter Started!");
    } catch (Exception ex) {
      System.out.println("Application Twitter Start Fail!");
      LOGGER.severe(ex.getMessage());
    }
  }
}
