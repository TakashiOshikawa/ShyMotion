import System.Process

{-
  1.shymotionデータベースを削除
  2.shymotionデータベースを作成
  3.scalerにshymotionデータベースの操作権限付与
 -}

main = do
           system "mysql -u root -e \"DROP DATABASE shymotion;\""
           system "mysql -u root < /Users/oshikawatakashi/various/scala_spray/shymotion/src/haskell/exe_shymotion_scheme.sql"
           system "mysql -u root -e \"GRANT ALL PRIVILEGES ON shymotion.* TO scaler@localhost;\""
