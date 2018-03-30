# This script contains a function that converts temperature in degrees Celsius 
# to degrees Fahrenheit.
# Created: 12/13/2017 W. Melendez
#

# This function converts Celsius to Fahrenheit
Celsius_to_Fahrenheit <- function(TempC)
{
    TempF <- 9.0 * TempC / 5.0 + 32.0
    return(TempF)
}

# This function converts Fahrenheit to Celsius
Fahrenheit_to_Celsius <- function(TempF)
{
    TempC <- (5.0 / 9.0) * (TempF - 32.0)
    return(TempC)
}

