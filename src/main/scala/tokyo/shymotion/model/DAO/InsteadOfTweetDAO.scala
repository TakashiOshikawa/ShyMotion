package tokyo.shymotion.model.DAO

import org.joda.time.DateTime
import scalikejdbc._
import tokyo.shymotion.model.DBAccess
import tokyo.shymotion.model.table.{InsteadOfTweet, InsteadOfTweetTable}

/**
 * Created by oshikawatakashi on 2015/10/12.
 */
object InsteadOfTweetDAO extends DBAccess {


  def findTweetByInsTweetID(instead_of_tweet_id: Long): Option[List[InsteadOfTweet]] = {
    val tweet = DB readOnly { implicit session =>
      sql"SELECT * FROM instead_of_tweet WHERE instead_of_tweet_id = ${instead_of_tweet_id}"
        .map(InsteadOfTweetTable.allColumn).list.apply()
    }
    Some(tweet)
  }

  def findTweetByUserID(user_id: Long): List[InsteadOfTweet] = {
    DB readOnly { implicit session =>
      sql"SELECT * FROM instead_of_tweet WHERE user_id = ${user_id}"
        .map(InsteadOfTweetTable.allColumn).list.apply()
    }
  }

  def insertTweet(user_id: Long, body: Option[String], secret_nick_name: Option[String]): InsteadOfTweet = {
    DB localTx { implicit session =>
      val instead_of_tweet_id =
        sql"INSERT INTO instead_of_tweet (user_id, body, secret_nick_name) values (${user_id}, ${body.getOrElse("")}, ${secret_nick_name.getOrElse("")})"
        .updateAndReturnGeneratedKey.apply()
      InsteadOfTweet(instead_of_tweet_id, user_id, body, secret_nick_name, DateTime.now())
    }
  }


}
