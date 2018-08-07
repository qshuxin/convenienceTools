#!/usr/bin/env python
#coding=utf-8

year = int(raw_input('年：'))
month = int(raw_input('月：'))
day = int(raw_input('日：'))

monthList = [31,28,31,30,31,30,31,31,30,31,30,31]
if(year%400 == 0 or (year % 4 == 0 and year % 100 != 0 )):
  monthList[1]=29
time = 0
for i in range(0,month-1):
  time += monthList[i]

time += day

print time
