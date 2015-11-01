import System.Process

main = do
        print "twitter_user_id"
        twitter_user_id <- getLine
        print "start number"
        start <- getLine
        print "get tweetbyuserid length"
        num <- getLine
        let curlCmd = "curl http://127.0.0.1:8080/tweetbyuserid/" ++ twitter_user_id ++ "/" ++ start ++ "/" ++ num
        system curlCmd

