package tokyo.shymotion

import akka.actor.Actor
import spray.http.MediaTypes._
import spray.routing._
import tokyo.shymotion.controller.TweetController
import tokyo.shymotion.model.DAO.UserDAO
import tokyo.shymotion.model.InsteadOfTweet

// we don't implement our route structure directly in the service actor because
// we want to be able to test it independently, without having to spin up an actor
class MyServiceActor extends Actor with MyService {

  // the HttpService trait defines only one abstract member, which
  // connects the services environment to the enclosing actor or test
  def actorRefFactory = context

  // this actor only runs our route, but you could add
  // other things here, like request stream processing
  // or timeout handling
  def receive = runRoute(myRoute)
}


// this trait defines our service behavior independently from the service actor
trait MyService extends HttpService {

  val myRoute =
    path("") {
      get {
        respondWithMediaType(`text/html`) { // XML is marshalled to `text/xml` by default, so we simply override here
          complete {
            <html>
              <body>
                <h1>Say hello to <i>spray-routing</i> on <i>spray-can</i>!</h1>
              </body>
            </html>
          }
        }
      }
    } ~
    path("test") {
      formFields('user_id, 'sentence) { (user_id, sentence) =>
          post {
            complete {
              //            println(TweetController.getUserID)
              //  println( TweetController.tweetPostSentence("RedBullだぜ") )
              println(TweetController.tweetForUserID(user_id , sentence))
              "www"
            }
          }
      }
    } ~
    path("insert") {
      formFields('user_id) { (user_id) =>
        post {
          complete {
//            val existUser = UserDAO.isExistTwitterUserID(user_id)
            val user = UserDAO.createUser(user_id)
            println( user )
            "" + user
          }
        }
      }
    } ~
    path("posttweet") {
      formFields('user_id, 'body) { (user_id, body) =>
        validate(user_id.nonEmpty && body.nonEmpty, s"Invalid Request") {
          post {
            complete {
              val tweet = InsteadOfTweet.createPostTweet(user_id, body)
              // TODO JSON形式で返却
              "" + tweet
            }
          }
        }
      }
    }

}