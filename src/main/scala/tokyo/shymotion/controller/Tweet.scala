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
    System.out.println("Successfully updated the status to [" + status.getText() + "].")
  }

  // 指定ユーザに対してのツイートを行う
  def tweetForUserID(userID: String, sen: String) = {
    val twitter = TwitterSingleton.getInstance
    val status: Status = twitter.updateStatus("@" + userID + " " + sen)
    System.out.println("Successfully updated the status to [" + status.getText() + "].")
  }

  // ユーザIDを取得
  def getUserID() = {
    val twitter = TwitterSingleton.getInstance
    twitter.getId
  }


}
