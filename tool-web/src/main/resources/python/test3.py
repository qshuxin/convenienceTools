#!/usr/bin/env python
#coding=utf-8

#斐波那契数列

x=0
y=1
print x,y,
for i in range(1,100):
  z=x+y
  x=y
  y=z
  print z,

