package tokyo.shymotion.model

import org.joda.time.DateTime
import play.api.libs.json.{JsValue, Json}
import tokyo.shymotion.controller.TweetController
import tokyo.shymotion.model.DAO.{InsteadOfTweetDAO, UserDAO}
import tokyo.shymotion.model.table._

/**
 * Created by oshikawatakashi on 2015/10/12.
 */
object InsteadOfTweetModel extends InsteadOfTweetTable {

  // 投稿ツイート登録処理 Tweet内容をJSON形式で返却する
  def createPostTweet(twitter_user_id: String, body: String, secret_nick_name: String): JsValue = {
    val user = UserDAO.isExistTwitterUserID(twitter_user_id).headOption
    val user_id = user match {
      case Some(User(twitter_user_id, body)) => user.get.user_id
      case _    => UserDAO.createUser(twitter_user_id).user_id
    }

    val ins_tweet = InsteadOfTweetDAO.insertTweet(user_id, Some(body), Some(secret_nick_name))
    val url = generateURL(ins_tweet.instead_of_tweet_id)

    val tweet_status = TweetController.tweetForUserID(twitter_user_id, body, url, secret_nick_name)
    val tweet_json = Json.toJson( ResInsteadOfTweet(twitter_user_id, url, ins_tweet ) )
    tweet_json
  }


  // TEST FUNCTION TODO 後々削除
  def getTestJsonVal() = {
    val tweet = Json.toJson( InsteadOfTweet(1,1,Some("sss"), Some("ddd"), DateTime.now() ))
    val res_tweet = ResInsteadOfTweet("testTwitterID", "http://shymotion.tokyo", InsteadOfTweet(1,1,Some("sss"), Some("ddd"), DateTime.now()) )
    Json.toJson( res_tweet )
  }


  // 投稿ツイート取得処理
  def findPostTweet(instead_of_tweet_id: Long): JsValue = {
    val tweet: Option[List[UserJoinInsteadOfTweet]] = InsteadOfTweetDAO.findTweetByInsTweetID(instead_of_tweet_id)
    tweet match {
      case Some(List(UserJoinInsteadOfTweet(_,_,_,_,_,_))) => Json.toJson(tweet.head)
      case Some(_)                    => Json.toJson(UserJoinInsteadOfTweet(0,0,Some(""),Some(""),DateTime.now(),Some("")))
      case None => Json.toJson(UserJoinInsteadOfTweet(0,0,Some(""),Some(""),DateTime.now(),Some("")))
    }
  }


  // TwitterユーザIDからTweetを取得
  def findTweetByTwitterUserID(twitter_user_id: String, start: Long, length: Long): JsValue = {
    val user = UserDAO.isExistTwitterUserID(twitter_user_id).headOption
    user match {
      case Some(_) => Json.toJson( InsteadOfTweetDAO.findTweetByUserID(user.get.user_id, start, length) )
      case None => Json.toJson( UserJoinInsteadOfTweet(0,0,Some(""),Some(""),DateTime.now(),Some("")) )
    }
  }


  // 投稿時URL生成処理
  def generateURL(ins_tweet_id: Long): String =
    "http://shymotion.tokyo/instead_tweet/" + ins_tweet_id


}
