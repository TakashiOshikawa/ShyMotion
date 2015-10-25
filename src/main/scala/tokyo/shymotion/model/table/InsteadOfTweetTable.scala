package tokyo.shymotion.model.table

import org.joda.time.DateTime
import play.api.libs.json.Json
import scalikejdbc.WrappedResultSet

/**
 * Created by oshikawatakashi on 2015/10/12.
 */

trait InsteadOfTweetTable {

  implicit def jsonWrites = Json.writes[InsteadOfTweet]
  implicit def jsonReads  = Json.reads[InsteadOfTweet]

  implicit def jsonResWrites = Json.writes[ResInsteadOfTweet]
  implicit def jsonesReads   = Json.reads[ResInsteadOfTweet]

  implicit def jsonUserJoinWrites = Json.writes[UserJoinInsteadOfTweet]
  implicit def jsonUserJoinReads  = Json.reads[UserJoinInsteadOfTweet]

}


case class InsteadOfTweet
  (
    instead_of_tweet_id: Long,
    user_id: Long,
    body: Option[String] = None,
    secret_nick_name: Option[String] = None,
    created_at: DateTime
  )

case class UserJoinInsteadOfTweet
  (
    instead_of_tweet_id: Long,
    user_id: Long,
    body: Option[String] = None,
    secret_nick_name: Option[String] = None,
    created_at: DateTime,
    twitter_user_id: Option[String]
  )

case class ResInsteadOfTweet
  (
    twitter_user_id: String,
    generated_url: String,
    tweet: InsteadOfTweet
  )


object InsteadOfTweetTable {

  val allColumn = (res: WrappedResultSet) => InsteadOfTweet(
    instead_of_tweet_id = res.long("instead_of_tweet_id"),
    user_id = res.long("user_id"),
    body = res.stringOpt("body"),
    secret_nick_name = res.stringOpt("secret_nick_name"),
    created_at = res.jodaDateTime("created_at")
  )

  val userJoinColumn = (res: WrappedResultSet) => UserJoinInsteadOfTweet(
    instead_of_tweet_id = res.long("instead_of_tweet_id"),
    user_id = res.long("user_id"),
    body = res.stringOpt("body"),
    secret_nick_name = res.stringOpt("secret_nick_name"),
    created_at = res.jodaDateTime("created_at"),
    twitter_user_id = res.stringOpt("twitter_user_id")
  )

}
