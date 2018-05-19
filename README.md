# ThreadsProject2
OS Course in Afeka

This is my project for Afeka, Operating Systems course, working with threads.
the aim of this project is to find primes within a range in two ways;

one is in a Static Distribution;
each thread is assigned a subrange of numbers to check for primes

the other is in a Dynamic Distribution;
beginning at the first number, each thread takes a number and checks if it's a prime, if not it takes the next unchecked until everything is checked

both implementations stop when the first prime is found


To execute in eclipse, pass three (3) arguments:
for range a-b and number of threads to be working with 
PASS A B N
for example:

50 100 6

is going to make it check the range 50-100 with 6 threads for a prime, the methods will stop at the first prime found
