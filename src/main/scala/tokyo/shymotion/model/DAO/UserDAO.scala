package tokyo.shymotion.model.DAO

import scalikejdbc._
import tokyo.shymotion.model.DBAccess
import tokyo.shymotion.model.table.{User, UserTable}

/**
 * Created by oshikawatakashi on 2015/10/12.
 */
object UserDAO extends DBAccess {


  // ユーザが存在するかチェック
  def isExistTwitterUserID(twitter_user_id: String): List[User] = {
    DB readOnly { implicit session =>
      sql"SELECT * FROM user WHERE twitter_user_id = ${twitter_user_id}"
        .map(UserTable.allColumn).list.apply()
    }
  }


  def createUser(twitter_user_id: String): User = {
    DB localTx { implicit session =>
      val user_id = sql"INSERT INTO user (twitter_user_id) values (${twitter_user_id})"
        .updateAndReturnGeneratedKey.apply()
      User(user_id, twitter_user_id)
    }
  }

}
