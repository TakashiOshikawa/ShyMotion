import System.Process

main = do
        print "instead of tweet id"
        instead_of_tweet_id <- getLine
        let curlCmd = "curl http://127.0.0.1:8080/tweet/" ++ instead_of_tweet_id
        system curlCmd

