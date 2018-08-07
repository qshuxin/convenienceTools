#!/usr/bin/env python
#coding=utf-8

# 100米小球

start_len = 100.0
sum_len = 100.0
high = 100.0

for i in range(2,11):
  start_len =start_len /2.0
  sum_len = sum_len + start_len * 2
  
print sum_len,start_len /2.0
