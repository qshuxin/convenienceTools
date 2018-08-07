#!/usr/bin/env python
#coding=utf-8

# 阶乘和

def a(x):
  if (x == 1 or x ==0):
    return 1
  return a(x-1)*x

sum_a = 0
for i in range(1,21):
  sum_a += a(i)
print sum_a
