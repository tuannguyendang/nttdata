package com.dangtuan.twitter;

import com.dangtuan.twitter.model.Tweet;
import com.dangtuan.twitter.model.User;
import com.dangtuan.twitter.service.TwitterService;
import com.dangtuan.twitter.service.UserService;
import com.dangtuan.twitter.utils.Utils;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class TwitterServiceTest {

  private static TwitterService twitterService;
  private static UserService userService;
  private static Utils utils;

  @BeforeClass
  public static void setup() {
    twitterService = TwitterService.getTwitterInstance();
    userService = UserService.getUserServiceInstance();
    utils = new Utils();
    utils.initData();
  }

  @Test
  public void testPostTweetSuccess() throws Exception {
    twitterService.postTweet(1, 1);

    final User user = userService.getUserById(1);
    final List<Tweet> posts = user.getPosts();
    Assert.assertNotNull(posts);

    final Optional<Tweet> tweet = posts.stream().filter(post -> post.getId() == 1).findFirst();
    Assert.assertTrue(tweet.isPresent());
    Assert.assertEquals(1, tweet.get().getId());
  }

  @Test(expected = Exception.class)
  public void testPostTweetWithUserNotExistFail() throws Exception {
    twitterService.postTweet(10, 1);
  }

  @Test
  public void testgetNewFeedSuccess() throws Exception {
    twitterService.postTweet(1, 5);
    final List<Integer> topTweets = twitterService.getNewsFeed(1, 10);
    Assert.assertNotNull(topTweets);
    Assert.assertTrue(topTweets.get(0).equals(5));
  }

  @Test
  public void testFollowSuccess() throws Exception {
    twitterService.follow(1, 2);

    final User follower = userService.getUserById(1);
    Set<Integer> friends = follower.getFriends();
    Assert.assertNotNull(friends);
    Assert.assertTrue(friends.contains(2));
  }

  @Test(expected = Exception.class)
  public void testFollowWithFollowThemselfFail() throws Exception {
    twitterService.follow(1, 1);
  }

  @Test(expected = Exception.class)
  public void testFollowWithFollowerNotExistfFail() throws Exception {
    twitterService.follow(1000, 1);
  }

  @Test()
  public void testUnFollowWithSuccess() throws Exception {
    twitterService.follow(1, 2);
    twitterService.unfollow(1, 2);

    final User follower = userService.getUserById(1);
    Set<Integer> friends = follower.getFriends();
    Assert.assertNotNull(friends);
    Assert.assertTrue(!friends.contains(2));
  }

  @Test(expected = Exception.class)
  public void testUnFollowWithFollowerNotExistfFail() throws Exception {
    twitterService.unfollow(1000, 1);
  }

  @Test(expected = Exception.class)
  public void testUnFollowThemselfFail() throws Exception {
    twitterService.unfollow(1, 1);
  }
}
