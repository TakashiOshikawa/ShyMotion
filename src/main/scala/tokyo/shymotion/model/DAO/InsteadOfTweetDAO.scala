package tokyo.shymotion.model.DAO

import org.joda.time.DateTime
import scalikejdbc._
import tokyo.shymotion.model.DBAccess
import tokyo.shymotion.model.table.{UserJoinInsteadOfTweet, InsteadOfTweet, InsteadOfTweetTable}

/**
 * Created by oshikawatakashi on 2015/10/12.
 */
object InsteadOfTweetDAO extends DBAccess {


  def findTweetByInsTweetID(instead_of_tweet_id: Long): Option[List[UserJoinInsteadOfTweet]] = {
    val tweet = DB readOnly { implicit session =>
      sql"SELECT * FROM instead_of_tweet LEFT JOIN user USING (user_id) WHERE instead_of_tweet_id = ${instead_of_tweet_id}"
        .map(InsteadOfTweetTable.userJoinColumn).list.apply()
    }
    Some(tweet)
  }

  def findTweetByUserID(user_id: Long, start: Long, length: Long): Option[List[UserJoinInsteadOfTweet]] = {
    val tweet = DB readOnly { implicit session =>
      sql"SELECT * FROM instead_of_tweet LEFT JOIN user USING (user_id) WHERE user_id = ${user_id} ORDER BY instead_of_tweet_id DESC LIMIT ${start-1}, ${length}"
        .map(InsteadOfTweetTable.userJoinColumn).list.apply()
    }
    Some(tweet)
  }

  def insertTweet(user_id: Long, body: Option[String], secret_nick_name: Option[String]): InsteadOfTweet = {
    val ins_nick_name = if (secret_nick_name.getOrElse("").length == 0) "名無しさん" else secret_nick_name.getOrElse("")
    DB localTx { implicit session =>
      val instead_of_tweet_id =
        sql"INSERT INTO instead_of_tweet (user_id, body, secret_nick_name) values (${user_id}, ${body.getOrElse("")}, ${ins_nick_name})"
        .updateAndReturnGeneratedKey.apply()
      InsteadOfTweet(instead_of_tweet_id, user_id, body, secret_nick_name, DateTime.now())
    }
  }


}
