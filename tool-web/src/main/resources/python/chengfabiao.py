#!/usr/bin/env python

x = 1
while ( x < 10 ): 
  y = 1
  while ( y <= x ):
    print y,"*",x,"=", x * y,
    y = y + 1
  x = x + 1
  print ""
