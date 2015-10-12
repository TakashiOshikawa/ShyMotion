import System.Process

main = do
        print "Email"
        e_mail <- getLine
        print "password"
        password <- getLine
        let curlCmd = "curl --data e_mail=" ++ e_mail ++ " --data-urlencode password=" ++ password ++ " http://127.0.0.1:8080/insert"
        system curlCmd

