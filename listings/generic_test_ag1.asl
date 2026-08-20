!ping.

+!ping <- 
    .revealCurrentThread("intention 1");
    .send(pong, tell, ball);
    !!showThread(2);
    .revealCurrentThread("intention 1").

+ball <-
    !!showThread(4);
    .revealCurrentThread("intention 3").

+!showThread(X) <- .revealCurrentThread("intention " + X).