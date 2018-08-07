# -*- coding:UTF-8 -*-
import re
import subprocess as sp


def initpattern():
    #匹配丢包数
    #lose_time = re.compile(u"丢失 = (\d+)", re.IGNORECASE)
    lose_time = re.compile(u"(\d+)% packet loss", re.IGNORECASE)
    #匹配平均时间
    waste_time = re.compile(u"平均 = (\d+)ms", re.IGNORECASE)
    return lose_time, waste_time

if __name__ == '__main__':

    lose_time, waste_time = initpattern()

    cmd = "ping -w 3 127.0.0.1"
    #执行命令
    p = sp.Popen(cmd, stdin=sp.PIPE, stdout=sp.PIPE, stderr=sp.PIPE, shell=True)
    #获得返回结果并解码
    out = p.stdout.read().decode("gbk")

    lose_time = lose_time.findall(out)

    print(lose_time)
    print(out)