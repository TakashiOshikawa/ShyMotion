package tokyo.shymotion.model

import tokyo.shymotion.controller.TweetController
import tokyo.shymotion.model.DAO.{InsteadOfTweetDAO, UserDAO}
import tokyo.shymotion.model.table.{InsteadOfTweet, User}

/**
 * Created by oshikawatakashi on 2015/10/12.
 */
object InsteadOfTweet {


  // 投稿ツイート登録処理
  def createPostTweet(twitter_user_id: String, body: String): InsteadOfTweet = {
    val user = UserDAO.isExistTwitterUserID(twitter_user_id).headOption
    val user_id = user match {
      case Some(User(twitter_user_id, body)) => user.get.user_id
      case _    => UserDAO.createUser(twitter_user_id).user_id
    }

    TweetController.tweetForUserID(twitter_user_id, body)
    InsteadOfTweetDAO.createTweet(user_id, Some(body))
  }


  // 投稿ツイート取得処理
  def findPostTwitter(instead_of_tweet_id: Long): InsteadOfTweet =
    InsteadOfTweetDAO.findTweetByInsTweetID(instead_of_tweet_id)


}
