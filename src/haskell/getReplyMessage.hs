import System.Process

main = do
        print "insteadOfTweetID"
        ins_id <- getLine
        print "start number"
        start <- getLine
        print "get reply length"
        num <- getLine
        let curlCmd = "curl http://127.0.0.1:8080/reply/" ++ ins_id ++ "/" ++ start ++ "/" ++ num
        system curlCmd

