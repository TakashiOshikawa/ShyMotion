package spec.tokyo.shymotion

import org.specs2.mutable.Specification
import tokyo.shymotion.controller.TweetController

/**
 * Created by oshikawatakashi on 2015/10/24.
 */
class TweetSpec extends Specification {

  val correct_user_id = "@ShyMotion"

  val atmark_contain_id = "@ShyMotion"
  "@ contain user id '" + atmark_contain_id + "'" should {
    correct_user_id in {
      TweetController.putAtMarkToTheUserID(atmark_contain_id) mustEqual "@ShyMotion"
    }
  }

  val atmark_not_contain_id = "ShyMotion"
  "@ not contain user id '" + atmark_not_contain_id + "'" should {
    correct_user_id in {
      TweetController.putAtMarkToTheUserID(atmark_not_contain_id) mustEqual "@ShyMotion"
    }
  }

  val space_contain_id = "  @ShyMotion  "
  "space contain user id '" + space_contain_id + "'" should {
    correct_user_id in {
      TweetController.putAtMarkToTheUserID(space_contain_id) mustEqual "@ShyMotion"
    }
  }

  val space_contain_no_atmark_id = "   ShyMotion  "
  "space contain user id and not contain @ '" + space_contain_no_atmark_id + "'" should {
    correct_user_id in {
      TweetController.putAtMarkToTheUserID(space_contain_no_atmark_id) mustEqual "@ShyMotion"
    }
  }

}