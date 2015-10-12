package tokyo.shymotion.model

import scalikejdbc._
import tokyo.shymotion.model.table.User

/**
 * Created by oshikawatakashi on 2015/10/12.
 */
object UserDAO extends DBAccess {

  def createUser(twitter_user_id: Option[String]): User = {
    DB localTx { implicit session =>
      val user_id = sql"INSERT INTO user (twitter_user_id) values (${twitter_user_id})"
        .updateAndReturnGeneratedKey.apply()
      User(user_id, twitter_user_id)
    }
  }

}
