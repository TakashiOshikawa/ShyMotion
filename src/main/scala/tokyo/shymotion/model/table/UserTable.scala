package tokyo.shymotion.model.table

import play.api.libs.json.Json
import scalikejdbc.WrappedResultSet

/**
 * Created by oshikawatakashi on 2015/10/12.
 */

trait UserTable {

  implicit def jsonWrites = Json.writes[User]
  implicit def jsonReads = Json.reads[User]

}

case class User
    (
      user_id: Long,
      twitter_user_id: String
    )

object UserTable {

  val allColumn = (res: WrappedResultSet) => User(
    user_id = res.long("user_id"),
    twitter_user_id = res.string("twitter_user_id")
  )

}
