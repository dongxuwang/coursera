use "hw1.sml";

val test1 = is_older ((1,2,3), (2,3,4)) = true
val test2 = is_older ((1,2,3), (1,2,3)) = false
val test3 = is_older ((2,3,4), (1,2,3)) = false

val test4 = number_in_month([(1,2,3)], 1) = 0
val test5 = number_in_month([(2012,10,10),(2018,11,12)], 10) = 1

val test6 = number_in_months([(2012,10,10),(2012,11,11)],[10,11]) = 2
val test7 = number_in_months([(2013,11,11),(2013,11,12),(2014,11,14),(2018,9,1)],[11]) = 3

val test8 = dates_in_month([(2018,11,11),(2017,11,12)], 11) = [(2018,11,11),(2017,11,12)]

val test9 = dates_in_months([(2018,11,11),(2017,11,12)], [11]) = [(2018,11,11),(2017,11,12)]

val test10 = get_nth(["123","abc","!@#"], 2) = "abc"

val test11 = date_to_string (2012,12,12) = "December 12, 2012"

val test12 = number_before_reaching_sum(6, [6]) = 0
val test13 = number_before_reaching_sum(6, [3,2,1]) = 2


val test14 = what_month(34) = 2


val atest1 = is_older ((1,2,3),(2,3,4)) = true

val atest2 = number_in_month ([(2012,2,28),(2013,12,1)],2) = 1

val atest3 = number_in_months ([(2012,2,28),(2013,12,1),(2011,3,31),(2011,4,28)],[2,3,4]) = 3

val atest4 = dates_in_month ([(2012,2,28),(2013,12,1)],2) = [(2012,2,28)]

val atest5 = dates_in_months ([(2012,2,28),(2013,12,1),(2011,3,31),(2011,4,28)],[2,3,4]) = [(2012,2,28),(2011,3,31),(2011,4,28)]

val atest6 = get_nth (["hi", "there", "how", "are", "you"], 2) = "there"

val atest7 = date_to_string (2013, 6, 1) = "June 1, 2013"

val atest8 = number_before_reaching_sum (10, [1,2,3,4,5]) = 3

val atest9 = what_month 70 = 3

val atest10 = month_range (31, 34) = [1,2,2,2]

val atest11 = oldest([(2012,2,28),(2011,3,31),(2011,4,28)]) = SOME (2011,3,31)

val ctest1 = is_older((2013,11,13), (2013,11,13)) = false
val ctest2 = is_older((0,0,0),(0,0,0)) = false
val ctest3 = is_older((9998,12,12),(9999,12,12)) = true

val ctest4 = number_in_month([(2018,1,1),(2019,1,1)], 1) = 2
val ctest5 = number_in_month([], 1) = 0
val ctest6 = number_in_month([], 0) = 0
val ctest7 = number_in_month([(1,1,1)], 1) = 1

val ctest8 = number_in_months([],[]) = 0
val ctest9 = number_in_months([], [1]) = 0
val ctest10 = number_in_months([(1,1,1)],[1]) = 1
val ctest11 = number_in_months([(1,1,1)],[1,2,3]) = 1
val ctest12 = number_in_months([(1,1,1),(1,2,3),(1,3,3),(1,1,1)],[1,2,3]) = 4

val ctest13 = dates_in_month([(1,1,1)],1) = [(1,1,1)]
val ctest14 = dates_in_month([], 1) = []
val ctest15 = dates_in_month([(1,1,1),(2,1,2)], 1) = [(1,1,1), (2,1,2)]
val ctest16 = dates_in_month([(1,1,1),(1,1,1),(2,1,2)],1) = [(1,1,1),(1,1,1),(2,1,2)]

val ctest17 = dates_in_months([(1,1,1)], []) = []
val ctest18 = dates_in_months([],[1,2]) = []
val ctest19 = dates_in_months([(1,2,3),(2,2,2)], [2,3]) = [(1,2,3),(2,2,2)]

val ctest20 = get_nth(["1","2","3"], 2) = "2"
val ctest21 = get_nth([""], 1) = ""

val ctest22 = date_to_string (2018,5,16) = "May 16, 2018"

val ctest23 = number_before_reaching_sum(1, [1,2]) = 0
val ctest24 = number_before_reaching_sum(10, [1,2,3,4]) = 3

val ctest25 = what_month 1 = 1
val ctest26 = what_month 2 = 1
val ctest27 = what_month 31 = 1
val ctest28 = what_month 365 = 12
val ctest29 = what_month 333 = 11

val ctest30 = month_range(1,1) = [1]
val ctest31 = month_range(2,2) = [1]
val ctest32 = month_range(1,3) = [1,1,1]
val ctest33 = month_range(31,33) = [1,2,2]
val ctest34 = month_range(364,365) = [12,12]

val ctest35 = oldest()
