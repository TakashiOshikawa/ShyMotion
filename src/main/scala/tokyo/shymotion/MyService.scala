package tokyo.shymotion

import akka.actor.Actor
import spray.http.MediaTypes._
import spray.routing._
import tokyo.shymotion.controller.TweetController
import tokyo.shymotion.model.InsteadOfTweetDAO

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
    path("find") {
      get {
        complete {
          val student = InsteadOfTweetDAO.members
          println( student )
          "ss"
        }
      }
    } ~
    path("insert") {
      formFields('user_id, 'body) { (user_id, body) =>
        post {
          complete {
            val gen = InsteadOfTweetDAO.insertTweet(user_id, Some(body))
            println( gen )
            "" + gen
          }
        }
      }
    }

}