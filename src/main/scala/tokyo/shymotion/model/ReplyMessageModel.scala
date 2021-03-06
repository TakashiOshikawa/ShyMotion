package tokyo.shymotion.model

import org.joda.time.DateTime
import play.api.libs.json.{JsValue, Json}
import tokyo.shymotion.model.DAO.ReplyMessageDAO
import tokyo.shymotion.model.table.{ReplyMessage, ReplyMessageTable}

/**
 * Created by oshikawatakashi on 2015/10/15.
 */
object ReplyMessageModel extends ReplyMessageTable {

  def insertReplyMessage(instead_of_tweet: Long, body: Option[String], secret_nick_name: Option[String]): JsValue = {
    val rep_msg = ReplyMessageDAO.insertReplyMessage(instead_of_tweet, body, secret_nick_name)
    Json.toJson(rep_msg)
  }

  def findReplyMessage(instead_of_tweet_id: Long, start: Long, num: Long): JsValue = {
    val res_msg = ReplyMessageDAO.findReplysByInsTweetID(instead_of_tweet_id, start, num)
    res_msg match {
      case Some( List(ReplyMessage(_,_,_,_,_), _*) ) => Json.toJson(res_msg)
      case Some(_)                                 => Json.toJson(ReplyMessage(0,0,Some(""),Some(""),DateTime.now()))
      case None                                    => Json.toJson(ReplyMessage(0,0,Some(""),Some(""),DateTime.now()))
    }
  }

  def getReplyMessageAtNewOrder(start: Long, num: Long): JsValue = {
    val res_msg = ReplyMessageDAO.getReplyMessagesAtNewOrder(start, num)
    res_msg match {
      case Some( List(ReplyMessage(_,_,_,_,_), _*) ) => Json.toJson(res_msg)
      case Some(_)                                   => Json.toJson(ReplyMessage(0,0,Some(""),Some(""),DateTime.now()))
      case None                                      => Json.toJson(ReplyMessage(0,0,Some(""),Some(""),DateTime.now()))
    }
  }

}
