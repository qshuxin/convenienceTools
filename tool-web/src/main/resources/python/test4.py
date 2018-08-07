#!/usr/bin/env python
#coding=utf-8

#素数
x = []
for i in range(2,200):
  flag = 1  
  for j in x:
    if(i%j==0):
      flag=0
      break
  if(flag == 1):    
    x.append(i)
for k in x:
  if(k>100):
    print k
