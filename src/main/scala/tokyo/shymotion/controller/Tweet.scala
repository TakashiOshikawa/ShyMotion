package tokyo.shymotion.controller

import twitter4j.Status

/**
 * Created by oshikawatakashi on 2015/10/12.
 */
object TweetController {


  // Twitter投稿
  def tweetPostSentence(sen: String) = {
    val twitter = TwitterSingleton.getInstance
    val status: Status = twitter.updateStatus(sen)
  }

  // 指定ユーザに対してのツイートを行う
  def tweetForUserID(user_id: String, sen: String) = {
    val twitter = TwitterSingleton.getInstance
    val status: Status = twitter.updateStatus(putAtMarkToTheUserID(user_id) + " " + sen)
    status
  }

  // ユーザIDを取得
  def getUserID() = {
    val twitter = TwitterSingleton.getInstance
    twitter.getId
  }

  def putAtMarkToTheUserID(twitter_user_id: String): String =
    "@" + twitter_user_id.trim.replaceAll("@", "")

}
