package tokyo.shymotion.model

import scalikejdbc.ConnectionPool

/**
 * Created by oshikawatakashi on 2015/10/12.
 */
trait DBAccess {

  //DB接続設定
  Class.forName("com.mysql.jdbc.Driver")
  ConnectionPool.singleton("jdbc:mysql://localhost/draftt", "scaler", "scaler")

}

