package tokyo.shymotion.model.DAO

import scalikejdbc._
import tokyo.shymotion.model.DBAccess
import tokyo.shymotion.model.table.ReplyMessage

/**
 * Created by oshikawatakashi on 2015/10/12.
 */
object ReplyMessageDAO extends DBAccess {

  def createReplyMessage(instead_of_tweet_id: Long, body: Option[String]): ReplyMessage = {
    DB localTx { implicit session =>
      val reply_message_id = sql"INSERT INTO reply_message (instead_of_tweet_id, body) values (${instead_of_tweet_id}, ${body})"
        .updateAndReturnGeneratedKey.apply()
      ReplyMessage(reply_message_id, instead_of_tweet_id, body)
    }
  }

}
