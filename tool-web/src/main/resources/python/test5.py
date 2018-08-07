#!/usr/bin/env python
#coding=utf-8

#水仙花

for i in range(100,1000):
  x=i/100
  y=(i%100)/10
  z=i%10
  if (x*x*x + y*y*y + z*z*z == i):
    print i
