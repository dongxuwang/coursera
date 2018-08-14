use "hw2.sml";

val test1 = all_except_option("string1", ["string1"]) = SOME [];
val test2 = all_except_option("abc", ["123","abc","hello"]) = SOME ["123","hello"];
val test3 = get_substitutions1([["foo"],["there"]], "foo") = []
val test4 = get_substitutions1([["abc","foo"],["bcd"],["foo","bar","bzz"]], "foo") = ["abc","bar","bzz"]
val test5 = get_substitutions2([["foo"],["there"]], "foo") = []
val test6 = get_substitutions2([["foo"],["foo","bar"]], "foo") = ["bar"]
val test7 = get_substitutions1([["Fred","Fredrick"],["Elizabeth","Betty"],["Freddie","Fred","F"]], "Fred") = ["Fredrick","Freddie","F"]
val test8 = similar_names ([["Fred","Fredrick"],["Elizabeth","Betty"],["Freddie","Fred","F"]], {first="Fred", middle="W", last="Smith"}) =
            [{first="Fred", last="Smith", middle="W"}, {first="Fredrick", last="Smith", middle="W"},
             {first="Freddie", last="Smith", middle="W"}, {first="F", last="Smith", middle="W"}]


val test9 = card_color(Clubs, Jack) = Black
val test10 = card_color(Diamonds, Queen) = Red

val test11 = card_value(Clubs, Num 1) = 1
val test12 = card_value(Clubs, Ace) = 11
val test13 = card_value(Clubs, King) = 10
val test14 = card_value(Diamonds, Jack) = 10

val test15 = remove_card([(Clubs,Jack),(Clubs,Queen),(Diamonds,King)], (Diamonds,King), IllegalMove) = [(Clubs,Jack),(Clubs,Queen)]

val test16 = all_same_color [(Clubs,Jack),(Spades,Jack),(Clubs,King)] = true
val test17 = all_same_color [(Clubs,Jack),(Hearts,Jack)] = false

val test18 = sum_cards [(Clubs, Jack),(Clubs, Num 2)] = 12

val test19 = ((officiate([(Clubs,Jack),(Spades,Num(8))],
                         [Draw,Discard(Hearts,Jack)],
                         42);
               false)
              handle IllegalMove => true)
