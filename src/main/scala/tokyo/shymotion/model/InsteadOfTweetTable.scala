package tokyo.shymotion.model

import org.joda.time.DateTime
import scalikejdbc.WrappedResultSet

/**
 * Created by oshikawatakashi on 2015/10/12.
 */

case class InsteadOfTweet
     (
       instead_of_tweet_id: Long,
       user_id: Long,
       body: Option[String] = None,
       created_at: DateTime
     )

object InsteadOfTweetTable {

  val allColumn = (res: WrappedResultSet) => InsteadOfTweet(
    instead_of_tweet_id = res.long("instead_of_tweet_id"),
    user_id = res.long("user_id"),
    body = res.stringOpt("body"),
    created_at = res.jodaDateTime("created_at")
  )

}
