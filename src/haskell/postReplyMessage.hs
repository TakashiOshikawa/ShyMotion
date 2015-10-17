import System.Process

main = do
        print "insteadOfTweetID"
        ins_id <- getLine
        print "body"
        body <- getLine
        let curlCmd = "curl --data-urlencode body=" ++ body ++ " http://127.0.0.1:8080/reply/" ++ ins_id
        system curlCmd

