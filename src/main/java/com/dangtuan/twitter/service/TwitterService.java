package com.dangtuan.twitter.service;

import com.dangtuan.twitter.constants.Constants;
import com.dangtuan.twitter.constants.PETTYPE;
import com.dangtuan.twitter.model.Pet;
import com.dangtuan.twitter.model.Tweet;
import com.dangtuan.twitter.model.User;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Objects;

/**
 * Class handle twitter: post, get new feed, follow, unfollow
 */
public class TwitterService {

  private static TwitterService twitter;
  private static int timestamp;

  public TwitterService() {
    timestamp = 0;
  }

  /**
   * Method get single instance of TwitterService
   *
   * @return instance of TwitterService
   */
  public static synchronized TwitterService getTwitterInstance() {
    if (twitter == null) {
      twitter = new TwitterService();
    }
    return twitter;
  }

  /**
   * Create new tweet.
   *
   * @param userId  user create tweet
   * @param tweetId tweet id
   * @throws Exception exception user not found
   */
  public void postTweet(final int userId, final int tweetId) throws Exception {
    final User user = UserService.getUserServiceInstance().getUserById(userId);
    if (Objects.isNull(user)) {
      throw new Exception(Constants.EXCEPTION_USER_NOT_FOUND);
    }
    if (user.getPosts() == null) {
      user.setPosts(new ArrayList<>());
    }
    final Tweet tweet = new Tweet(tweetId, timestamp++);
    tweet.setContent(Constants.TEMPLATE_MESSAGE, new Pet(Constants.SAMPLE_PET_NAME, PETTYPE.DOG));
    user.getPosts().add(tweet);
  }

  /**
   * Method get topN most recent tweet of this user and users who the user followed. Ordered by most
   * recent to least recent.
   *
   * @param userId user id
   * @param topN   topN tweet
   * @return list of tweet id
   */
  public List<Integer> getNewsFeed(final int userId, final int topN) {
    final User user = UserService.getUserServiceInstance().getUserById(userId);
    List<Tweet> tweets = new ArrayList<>();
    if (user.getPosts() != null) {
      tweets.addAll(user.getPosts());
    }
    if (user.getFriends() != null) {
      for (Integer id : user.getFriends()) {
        if (UserService.getUserServiceInstance().getUserById(id) != null) {
          tweets.addAll(UserService.getUserServiceInstance().getUserById(id).getPosts());
        }
      }
    }
    Collections.sort(tweets, (p1, p2) -> p2.getTime() - p1.getTime());
    if (tweets.size() > topN) {
      tweets = tweets.subList(0, topN);
    }

    final List<Integer> topRecentTweets = new ArrayList<>();
    for (Tweet p : tweets) {
      topRecentTweets.add(p.getId());
    }
    return topRecentTweets;
  }

  /**
   * Method Follower follows a followee.
   *
   * @param followerId follower id
   * @param followeeId followee id
   * @throws Exception exception follower can't follow themself or not found
   */
  public void follow(final int followerId, final int followeeId) throws Exception {
    if (followerId == followeeId) {
      throw new Exception(Constants.EXCEPTION_USER_FOLLOW_THEMSELF);
    }

    final User follower = UserService.getUserServiceInstance().getUserById(followerId);
    if (Objects.isNull(follower)) {
      throw new Exception(Constants.EXCEPTION_USER_NOT_FOUND);
    }
    if (follower.getFriends() == null) {
      follower.setFriends(new LinkedHashSet<>());
    }
    follower.getFriends().add(followeeId);
  }

  /**
   * Method handle follower unfollows a followee.
   *
   * @param followerId follower id
   * @param followeeId followee id
   * @throws Exception exception follower can't unfollow themself or not found
   */
  public void unfollow(final int followerId, final int followeeId) throws Exception {
    if (followerId == followeeId) {
      throw new Exception(Constants.EXCEPTION_USER_UNFOLLOW_THEMSELF);
    }

    final User follower = UserService.getUserServiceInstance().getUserById(followerId);
    if (Objects.isNull(follower)) {
      throw new Exception(Constants.EXCEPTION_USER_NOT_FOUND);
    }
    if (follower.getFriends() != null) {
      follower.getFriends().remove(followeeId);
    }
  }

}
