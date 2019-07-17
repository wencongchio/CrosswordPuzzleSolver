:-[word].
crossword(V1,V2,V3,H1,H2,H3) :-
 word(V1, _, V12, _, V14, _, V16, _),
 word(V2, _, V22, _, V24, _, V26, _),
 word(V3, _, V32, _, V34, _, V36, _),
 word(H1, _, V12, _, V22, _, V32, _),
 word(H2, _, V14, _, V24, _, V34, _),
 word(H3, _, V16, _, V26, _, V36, _),
 V1 \= H1.