package tokyo.shymotion.model

import org.joda.time.DateTime
import scalikejdbc._

/**
 * Created by oshikawatakashi on 2015/10/12.
 */
object InsteadOfTweetDAO extends DBAccess {

  val members: List[InsteadOfTweet] = DB readOnly { implicit session =>
    SQL("select * from student").map(InsteadOfTweetTable.allColumn).list.apply()
  }

  def insertTweet(user_id: String, body: Option[String]): InsteadOfTweet = {
    DB localTx { implicit session =>
      val instead_of_tweet_id = sql"insert into student (user_id, body) values (${user_id}, ${body})"
        .updateAndReturnGeneratedKey.apply()
      InsteadOfTweet(instead_of_tweet_id, 0, body, DateTime.now())
    }
  }

}
