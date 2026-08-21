aligned(Cells) :- vertical(Cells) | 
                  horizontal(Cells) | 
                  diagonal(Cells) | 
                  antidiagonal(Cells).
(*@\meta{alignment}@*)([cell(X, Y, S)]) :- cell(X, Y, S).
(*@\meta{alignment}@*)([cell(X, Y, S1), cell(A, B, S2) | OtherCells]) :- 
                  cell(X, Y, S1) & cell(A, B, S2) & A-X=(*@\meta{dx}@*) & B-Y=(*@\meta{dy}@*) &
                  (*@\meta{alignment}@*)([cell(A, B, S2) | OtherCells]).