package com.example

import org.joda.time.DateTime
import org.specs2.mutable.Specification
import play.api.libs.json.Json
import spray.testkit.Specs2RouteTest
import tokyo.shymotion.MyService
import tokyo.shymotion.model.table.{InsteadOfTweetTable, InsteadOfTweet}

class MyServiceSpec extends Specification with Specs2RouteTest with MyService with InsteadOfTweetTable {
  def actorRefFactory = system
  
  "MyService" should {

    "case class Json" in {
      val time = DateTime.now()
      val tweet_json = Json.toJson(InsteadOfTweet(0, 0, Some("test"), Some("nick_name"), time))
      tweet_json.toString mustEqual {
           s"""{"instead_of_tweet_id":0,"user_id":0,"body":"test","secret_nick_name":"nick_name","created_at":${time.getMillis}}"""
      }
    }

  }
}
