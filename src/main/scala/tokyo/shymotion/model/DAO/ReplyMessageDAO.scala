package tokyo.shymotion.model.DAO

import scalikejdbc._
import org.joda.time.DateTime
import tokyo.shymotion.model.DBAccess
import tokyo.shymotion.model.table.{ReplyMessageTable, ReplyMessage}

/**
 * Created by oshikawatakashi on 2015/10/12.
 */
object ReplyMessageDAO extends DBAccess {

  def findReplysByInsTweetID(ins_tweet_id: Long, start: Long, num: Long): Option[List[ReplyMessage]] = {
    val rep_msg = DB readOnly { implicit session =>
      sql"SELECT * FROM reply_message WHERE instead_of_tweet_id = ${ins_tweet_id} ORDER BY reply_message_id DESC LIMIT ${start-1}, ${num} "
        .map(ReplyMessageTable.allColumn).list.apply()
    }
    Some(rep_msg)
  }

  def getReplyMessagesAtNewOrder(start: Long, num: Long): Option[List[ReplyMessage]] = {
    val rep_msg = DB readOnly { implicit session =>
      sql"SELECT * FROM reply_message ORDER BY reply_message_id DESC LIMIT ${start-1}, ${num} "
        .map(ReplyMessageTable.allColumn).list.apply()
    }
    Some(rep_msg)
  }

  def insertReplyMessage(instead_of_tweet_id: Long, body: Option[String], secret_nick_name: Option[String]): ReplyMessage = {
    DB localTx { implicit session =>
      val reply_message_id = sql"INSERT INTO reply_message (instead_of_tweet_id, body, secret_nick_name) values (${instead_of_tweet_id}, ${body}, ${secret_nick_name})"
        .updateAndReturnGeneratedKey.apply()
      ReplyMessage(reply_message_id, instead_of_tweet_id, body, secret_nick_name, DateTime.now)
    }
  }

}
