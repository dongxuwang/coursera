fun is_older (d1: int*int*int , d2: int*int*int) =
    if (#1 d1) < (#1 d2)
    then true
    else
        if(#1 d1) > (#1 d2)
        then false
        else
            if(#2 d1) < (#2 d2)
            then true
            else
                if(#2 d1) > (#2 d2)
                then false
                else
                    if(#3 d1) < (#3 d2)
                    then true
                    else false


fun number_in_month (dates: (int*int*int) list, month: int) =
    if null dates
    then 0
    else
        if (#2 (hd dates)) = month
        then 1 + number_in_month((tl dates), month)
        else 0 + number_in_month((tl dates), month)

fun number_in_months(dates: (int*int*int) list, months: int list) =
    if null dates orelse null months
    then 0
    else
        number_in_month(dates, (hd months)) + number_in_months(dates, (tl months))

fun dates_in_month(dates: (int*int*int) list, month: int) =
    if null dates
    then []
    else
        if(#2 (hd dates)) = month
        then (hd dates) :: dates_in_month((tl dates), month)
        else dates_in_month((tl dates), month)

fun dates_in_months(dates: (int*int*int) list, months: int list) =
    if null dates orelse null months
    then []
    else
        dates_in_month(dates, (hd months)) @ dates_in_months(dates, (tl months))

fun get_nth(strings: string list, n: int) =
    if n = 1
    then (hd strings)
    else get_nth((tl strings), n - 1)

fun date_to_string(date: int*int*int) =
    let val months = ["January ", "February ", "March ", "April ", "May ", "June ", "July ", "August ", "September ", "October ", "November ", "December "]
    in
        get_nth(months, (#2 date)) ^ Int.toString(#3 date) ^ ", " ^ Int.toString(#1 date)
    end

fun number_before_reaching_sum(sum: int, numbers: int list) =
    if (hd numbers) >= sum
    then 0
    else 1 + number_before_reaching_sum(sum - (hd numbers), (tl numbers))

fun what_month(dayOfYear: int) =
    let val monthDay = [31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31]
    in
        1 + number_before_reaching_sum(dayOfYear, monthDay)
    end

fun month_range(day1: int, day2: int) =
    if day1 > day2
    then []
    else what_month(day1) :: month_range(day1 + 1, day2)

fun oldest(dates: (int*int*int) list) =
    if null dates
    then NONE
    else
        let val tl_oldest = oldest(tl dates)
        in if isSome tl_oldest andalso is_older(valOf(tl_oldest), (hd dates))
           then tl_oldest
           else SOME (hd dates)
        end
