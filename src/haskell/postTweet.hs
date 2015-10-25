import System.Process

main = do
        print "user ID"
        user_id <- getLine
        print "body"
        body <- getLine
        print "secret_nick_name"
        secret_nick_name <- getLine
        let curlCmd = "curl --data user_id=" ++ user_id ++ " --data-urlencode body=" ++ body ++ " --data-urlencode secret_nick_name=" ++ secret_nick_name ++ " http://127.0.0.1:8080/tweet"
        system curlCmd

