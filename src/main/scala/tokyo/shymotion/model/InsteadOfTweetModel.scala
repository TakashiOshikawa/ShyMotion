package tokyo.shymotion.model

import org.joda.time.DateTime
import play.api.libs.json.{JsValue, Json}
import tokyo.shymotion.controller.TweetController
import tokyo.shymotion.model.DAO.{InsteadOfTweetDAO, UserDAO}
import tokyo.shymotion.model.table.{ResInsteadOfTweet, InsteadOfTweetTable, InsteadOfTweet, User}

/**
 * Created by oshikawatakashi on 2015/10/12.
 */
object InsteadOfTweetModel extends InsteadOfTweetTable {

  // 投稿ツイート登録処理 Tweet内容をJSON形式で返却する
  def createPostTweet(twitter_user_id: String, body: String): JsValue = {
    val user = UserDAO.isExistTwitterUserID(twitter_user_id).headOption
    val user_id = user match {
      case Some(User(twitter_user_id, body)) => user.get.user_id
      case _    => UserDAO.createUser(twitter_user_id).user_id
    }

    TweetController.tweetForUserID(twitter_user_id, body)
    val ins_tweet = InsteadOfTweetDAO.insertTweet(user_id, Some(body))
    val url = generateURL(ins_tweet.instead_of_tweet_id)
    val tweet_json = Json.toJson( ResInsteadOfTweet(twitter_user_id, url, ins_tweet ) )
    tweet_json
  }


  // TEST FUNCTION TODO 後々削除
  def getTestJsonVal() = {
    val tweet = Json.toJson( InsteadOfTweet(1,1,Some("sss"), DateTime.now() ))
    val res_tweet = ResInsteadOfTweet("testTwitterID", "http://shymotion.tokyo", InsteadOfTweet(1,1,Some("sss"), DateTime.now()) )
    Json.toJson( res_tweet )
  }


  // 投稿ツイート取得処理
  def findPostTweet(instead_of_tweet_id: Long): JsValue = {
    val tweet: Option[List[InsteadOfTweet]] = InsteadOfTweetDAO.findTweetByInsTweetID(instead_of_tweet_id)
    tweet match {
      case Some(List(InsteadOfTweet(_,_,_,_))) => Json.toJson(tweet.head)
      case Some(_)                    => Json.toJson(InsteadOfTweet(0,0,Some(""),DateTime.now()))
      case None => Json.toJson(InsteadOfTweet(0,0,Some(""),DateTime.now()))
    }
  }


  // 投稿時URL生成処理
  def generateURL(ins_tweet_id: Long): String =
    "http://shymotion.tokyo/tweet/" + ins_tweet_id


}
