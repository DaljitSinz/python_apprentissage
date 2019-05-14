def is_leap(year):
    leap = False
    if year >= 1900 and year <= 10**5:
        if year % 4 == 0:
            return("True")
        else:
            return leap
    else:
        return leap
year = int(input())
print(is_leap(year))