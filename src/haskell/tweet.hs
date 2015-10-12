import System.Process

main = do
        print "user ID"
        user_id <- getLine
        print "sentence"
        sen <- getLine
        --let curlCmd = "curl 127.0.0.1:8080/test -X POST -d \"user_id=" ++ user_id ++ "&sentence='" ++ sen ++ "'\""
        let curlCmd = "curl --data user_id=" ++ user_id ++ " --data-urlencode sentence=" ++ sen ++ " http://127.0.0.1:8080/test"
        system curlCmd

