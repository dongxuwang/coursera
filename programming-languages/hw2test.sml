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
