package tokyo.shymotion.model.table

import scalikejdbc.WrappedResultSet

/**
 * Created by oshikawatakashi on 2015/10/12.
 */

case class ReplyMessage
    (
      reply_message_id: Long,
      instead_of_tweet_id: Long,
      body: Option[String]
    )

object ReplyMessageTable {

  val allColumn = (res: WrappedResultSet) => ReplyMessage(
    reply_message_id = res.long("reply_message_id"),
    instead_of_tweet_id = res.long("instead_of_tweet_id"),
    body = res.stringOpt("body")
  )

}
