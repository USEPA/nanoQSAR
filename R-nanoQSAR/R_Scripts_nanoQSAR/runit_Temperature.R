# This script tests the RUnit package.
# Created: 12/13/0217 W. Melendez
# Modified:

# This test checks the temperature conversion of Celsius to Fahrenheit.
test.Celsius_to_Fahrenheit <- function()
{
     checkEquals(Celsius_to_Fahrenheit(10.0), 50.0)
     checkEquals(Celsius_to_Fahrenheit(15.0), 59.0)
     checkEquals(Celsius_to_Fahrenheit(24.0), 75.2)
}

# This test checks the temperature conversion of Fahrenheit to Celsius.
test.Fahrenheit_to_Celsius <- function()
{
     checkEquals(Fahrenheit_to_Celsius(32.0), 0.0)
     checkEquals(Fahrenheit_to_Celsius(41.0), 5.0)
     checkEquals(Fahrenheit_to_Celsius(77.0), 25.0)
}


