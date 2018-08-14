(* Coursera Programming Languages, Homework 3, Provided Code *)

exception NoAnswer

datatype pattern = Wildcard
                 | Variable of string
                 | UnitP
                 | ConstP of int
                 | TupleP of pattern list
                 | ConstructorP of string * pattern

datatype valu = Const of int
              | Unit
              | Tuple of valu list
              | Constructor of string * valu

(**** for the challenge problem only ****)

datatype typ = Anything
             | UnitT
             | IntT
             | TupleT of typ list
             | Datatype of string

(* 1 *)
fun only_capitals xs =
    List.filter (fn x => Char.isUpper(String.sub(x, 0))) xs

(* 2 *)
fun longest_string1 xs =
    foldl (fn (x, y) => if(String.size x > String.size y) then x else y) "" xs

(* 3 *)
fun longest_string2 xs =
    foldl (fn (x, y) => if(String.size x >= String.size y) then x else y) "" xs

(* 4 *)
fun longest_string_helper f xs =
    foldl (fn (x, y) => if(f(String.size x, String.size y)) then x else y) "" xs

(* 4a *)
val longest_string3 =
    longest_string_helper (fn (x, y) => x > y)

(* 4b *)
val longest_string4 =
    longest_string_helper (fn (x, y) => x >= y)

(* 5 *)
val longest_capitalized =
    longest_string1 o only_capitals

(* 6 *)
val rev_string =
    String.implode o List.rev o String.explode

(* 7 *)
fun first_answer f l =
    case l of
        [] => raise NoAnswer
      | x::xs => case f(x) of
                    SOME x => x
                  | NONE => first_answer f xs

(* 8 *)
fun all_answers f al =
    let fun helper(a, b) =
        case b of
            NONE => NONE
          | SOME q => SOME (a @ q)
    in
        case al of
            [] => SOME []
          | x::xs => case f(x) of
                       NONE => NONE
                     | SOME p => helper(p, all_answers f xs)
    end

(* 9 *)
fun g f1 f2 p =
    let
        val r = g f1 f2
    in
        case p of
            Wildcard          => f1 ()
          | Variable x        => f2 x
          | TupleP ps         => List.foldl (fn (p,i) => (r p) + i) 0 ps
          | ConstructorP(_,p) => r p
          | _                 => 0
    end

(* 9a *)
fun count_wildcards p =
    g (fn () => 1) (fn (x) => 0) p

(* 9b *)
fun count_wild_and_variable_lengths p =
    g (fn () => 1) String.size p

(* 9c *)
fun count_some_var (s, p) =
    g (fn () => 0) (fn (x) => if(x=s) then 1 else 0) p

(* 10 *)
fun check_pat p =
    let fun all_strings p =
            case p of
                Variable x => [x]
              | TupleP ps => List.foldl (fn (p, qs) => qs @ all_strings(p)) [] ps
              | ConstructorP(_,p) => all_strings(p)
              | _ => []
        fun has_repeats strs =
            case strs of
                [] => false
              | x::xs => List.exists (fn (t) => t = x) xs orelse has_repeats(xs)
    in
        not(has_repeats(all_strings(p)))
    end

(* 11 *)
fun match (v, p) =
    case (v,p) of
        (_,Wildcard) => SOME []
      | (_,Variable x) => SOME [(x, v)]
      | (Unit,UnitP) => SOME []
      | (Const j,ConstP i) => if(j = i) then SOME [] else NONE
      | (Tuple us, TupleP ps) => if(List.length us = List.length ps) then all_answers match (ListPair.zip(us, ps)) else NONE
      | (Constructor(cs, cq), ConstructorP (s,q)) => if(cs = s) then match(cq, q) else NONE
      | _ => NONE

(* 12 *)
fun first_match v ps =
    SOME (first_answer (fn (x) => match(v, x)) ps)
    handle NoAnswer => NONE
