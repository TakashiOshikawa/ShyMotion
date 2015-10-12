package tokyo.shymotion.controller

import twitter4j.{Twitter, TwitterFactory}
import twitter4j.conf.ConfigurationBuilder

/**
 * Created by oshikawatakashi on 2015/10/12.
 */
object TwitterSingleton {

  private val cb: ConfigurationBuilder = new ConfigurationBuilder()
  cb.setDebugEnabled(true)
    .setOAuthConsumerKey("--------------------------")
    .setOAuthConsumerSecret("--------------------------")
    .setOAuthAccessToken("--------------------------")
    .setOAuthAccessTokenSecret("--------------------------")
  private val tf: TwitterFactory = new TwitterFactory(cb.build())
  def getInstance: Twitter = tf.getInstance()

}
