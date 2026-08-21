+turn(x) : aligned([cell(_,_,x),cell(_,_,x),cell(_,_,x)]) <- .print('I won')
+turn(x) : aligned([cell(_,_,o),cell(_,_,o),cell(_,_,o)]) <- .print('I lost')
+turn(x) : cell(X,Y,e) <- put(X,Y,x)

+turn(x) : aligned([cell(_,_,x),cell(_,_,x),cell(X,Y,e)]) <- put(X,Y,x)
+turn(x) : aligned([cell(_,_,x),cell(X,Y,e),cell(_,_,x)]) <- put(X,Y,x)
+turn(x) : aligned([cell(X,Y,e),cell(_,_,x),cell(_,_,x)]) <- put(X,Y,x)

+turn(x) : aligned([cell(_,_,o),cell(_,_,o),cell(X,Y,e)]) <- put(X,Y,x)
+turn(x) : aligned([cell(_,_,o),cell(X,Y,e),cell(_,_,o)]) <- put(X,Y,x)
+turn(x) : aligned([cell(X,Y,e),cell(_,_,o),cell(_,_,o)]) <- put(X,Y,x)
