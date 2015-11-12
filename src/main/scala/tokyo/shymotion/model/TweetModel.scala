package tokyo.shymotion.model

/**
 * Created by oshikawatakashi on 2015/11/09.
 */
object TweetModel {

  /*
    @Shymotion あなたへメッセージが届いています。
    メッセージ本文先頭30文字
    http://shymotion/instead_tweet/3242 40 文字
  */
  def genTweetContent[S <: String](user_id: S, sentence: S, url: S, secret_nickname: String): String =
    (sentence.length, secret_nickname) match {
      case (n, name) if (n <= 30 && name.length > 0) => user_id + " " + name + "さんからメッセージが届いています。\n" + sentence + "\n" + url
      case (n, name) if (n >  30 && name.length > 0) => user_id + " " + name + "さんからメッセージが届いています。\n" + sentence.take(30) + "…\n" + url
      case (n, name) if  n <= 30                     => user_id + " あなたへメッセージが届いています。\n" + sentence + "\n" + url
      case (n, name) if  n >  30                     => user_id + " あなたへメッセージが届いています。\n" + sentence.take(30) + "…\n" + url
    }

}
