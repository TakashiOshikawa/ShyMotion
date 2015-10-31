package tokyo.shymotion

import akka.actor.Actor
import play.api.libs.json.JsValue
import spray.http.HttpHeaders.RawHeader
import spray.http.MediaTypes._
import spray.routing._
import tokyo.shymotion.model.{InsteadOfTweetModel, ReplyMessageModel}

// we don't implement our route structure directly in the service actor because
// we want to be able to test it independently, without having to spin up an actor
class MyServiceActor extends Actor with MyService {

  // the HttpService trait defines only one abstract member, which
  // connects the services environment to the enclosing actor or test
  def actorRefFactory = context

  // this actor only runs our route, but you could add
  // other things here, like request stream processing
  // or timeout handling
  def receive = runRoute(route)
}


// this trait defines our service behavior independently from the service actor
trait MyService extends HttpService {

  // TODO ディレクティブを分ける

  val route =
    path("") {
      get {
        respondWithMediaType(`text/html`) { // XML is marshalled to `text/xml` by default, so we simply override here
          complete {
            <html>
              <body>
                <h1>Hello Shymotion :)</h1>
              </body>
            </html>
          }
        }
      }
    } ~
    path("tweet") {
      respondWithHeader(RawHeader("Access-Control-Allow-Origin", "http://127.0.0.1:4000")) {
        formFields('user_id, 'body, 'secret_nick_name) { (user_id, body, secret_nick_name) =>
          validate(user_id.nonEmpty && body.nonEmpty, s"Invalid Request") {
            post {
              respondWithMediaType(`application/json`) {
                complete {
                  val tweet = InsteadOfTweetModel.createPostTweet(user_id, body, secret_nick_name)
                  "" + tweet
                }
              }
            }
          }
        }
      }
    } ~
    path("tweet" / IntNumber ) { instead_of_tweet_id =>
      respondWithHeader(RawHeader("Access-Control-Allow-Origin", "http://127.0.0.1:4000")) {
        get {
          respondWithMediaType(`application/json`) {
            complete {
              val tweet: JsValue = InsteadOfTweetModel.findPostTweet(instead_of_tweet_id)
              tweet + ""
            }
          }
        }
      }
    } ~
    path("reply" / IntNumber ) { instead_of_tweet_id =>
      respondWithHeader(RawHeader("Access-Control-Allow-Origin", "http://127.0.0.1:4000")) {
        formFields('body, 'secret_nick_name) { (body, secret_nick_name) =>
          post {
            respondWithMediaType(`application/json`) {
              complete {
                val res_msg = ReplyMessageModel.insertReplyMessage(instead_of_tweet_id, Some(body), Some(secret_nick_name))
                res_msg + ""
              }
            }
          }
        }
      }
    } ~
    path("reply" / IntNumber / IntNumber / IntNumber ) { (instead_of_tweet_id, start, num) =>
      respondWithHeader(RawHeader("Access-Control-Allow-Origin", "http://127.0.0.1:4000")) {
        validate(start >= 1, s"Invalid Request!") {
          get {
            respondWithMediaType(`application/json`) {
              complete {
                val rep_msg = ReplyMessageModel.findReplyMessage(instead_of_tweet_id, start, num)
                rep_msg + ""
              }
            }
          }
        }
      }
    } ~
    path("tweetbyuserid" / Segment ) { twitter_user_id =>
      respondWithHeader(RawHeader("Access-Control-Allow-Origin", "http://127.0.0.1:4000")) {
        validate(twitter_user_id.nonEmpty, s"Invalid Request") {
          get {
            respondWithMediaType(`application/json`) {
              complete {
                val tweets = InsteadOfTweetModel.findTweetByUserID(twitter_user_id)
                tweets + ""
              }
            }
          }
        }
      }
    }

}
