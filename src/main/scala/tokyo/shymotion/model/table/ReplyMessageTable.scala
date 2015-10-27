package tokyo.shymotion.model.table

import org.joda.time.DateTime
import play.api.libs.json.Json
import scalikejdbc.WrappedResultSet

/**
 * Created by oshikawatakashi on 2015/10/12.
 */

trait ReplyMessageTable {

  implicit def jsonWrites = Json.writes[ReplyMessage]
  implicit def jsonReads = Json.reads[ReplyMessage]

}

case class ReplyMessage
    (
      reply_message_id: Long,
      instead_of_tweet_id: Long,
      body: Option[String],
      secret_nick_name: Option[String],
      created_at: DateTime
    )

object ReplyMessageTable {

  val allColumn = (res: WrappedResultSet) => ReplyMessage(
    reply_message_id = res.long("reply_message_id"),
    instead_of_tweet_id = res.long("instead_of_tweet_id"),
    body = res.stringOpt("body"),
    secret_nick_name = res.stringOpt("secret_nick_name"),
    created_at = res.jodaDateTime("created_at")
  )

}
