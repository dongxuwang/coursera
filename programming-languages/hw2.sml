(* Dan Grossman, Coursera PL, HW2 Provided Code *)

(* if you use this function to compare two strings (returns true if the same
   string), then you avoid several of the functions in problem 1 having
   polymorphic types that may be confusing *)
fun same_string(s1 : string, s2 : string) =
    s1 = s2

(* 1a *)
fun all_except_option(a : string, xs : string list) : string list option =
    case xs of
        [] => NONE
      | x::[] => if same_string(a, x) then SOME [] else NONE
      | x::y::xs' => if same_string(a, x) then
                        SOME(y::xs') else
                    if same_string(a, y) then
                        SOME(x::xs') else
                    case all_except_option(a, xs') of
                        NONE => NONE
                      | SOME lst => SOME (x::y::lst)

(* 1b *)
fun get_substitutions1(substitutions : string list list, s : string) =
    case substitutions of
        [] => []
      | x::xs' =>
        case all_except_option(s, x) of
            SOME lst => lst @ get_substitutions1(xs', s)
          | NONE => get_substitutions1(xs', s)
(* 1c *)
fun get_substitutions2(substitutions : string list list, s : string) =
    let fun aux(subs : string list list, s : string, acc : string list) =
            case subs of
                [] => acc
              | x::xs' =>
                case all_except_option(s, x) of
                    NONE => aux(xs', s, acc)
                  | SOME lst => aux(xs', s, acc @ lst)
    in
        aux(substitutions, s, [])
    end

fun similar_names(substitutions : string list list, name : {first : string, middle : string, last : string}) =
    let fun get_first {first = x, middle = y, last = z} = x
        fun get_full ({first = x, middle = y, last = z}, first_name: string) =
            {first = first_name, middle = y, last = z}
        val subs = get_substitutions2(substitutions, get_first(name))
    in
        let fun s_n (names: string list) =
                case names of
                    [] => []
                  | x::xs' => get_full(name, x) :: s_n(xs')
        in
            name :: s_n(subs)
        end
    end
(* Put your solutions for problem 1 here *)

(* you may assume that Num is always used with values 2, 3, ..., 10
   though it will not really come up *)
datatype suit = Clubs | Diamonds | Hearts | Spades
datatype rank = Jack | Queen | King | Ace | Num of int 
type card = suit * rank

datatype color = Red | Black
datatype move = Discard of card | Draw 

exception IllegalMove

              (* put your solutions for problem 2 here *)
