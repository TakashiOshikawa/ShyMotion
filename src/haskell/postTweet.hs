import System.Process

main = do
        print "user ID"
        user_id <- getLine
        print "body"
        body <- getLine
        let curlCmd = "curl --data user_id=" ++ user_id ++ " --data-urlencode body=" ++ body ++ " http://127.0.0.1:8080/tweet"
        system curlCmd

