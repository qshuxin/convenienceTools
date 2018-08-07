
#### 查看日志
1. 查找ERROR位置
```java
  cat -n catalina.out |grep "NullPointerException"
```

2. 使用行号定位问题
```java
  cat -n catalina.out |tail -n +86400|head -n 200
```

3. 查看文件md5
```java
md5sum ./a.txt
```

4. 查看java相关进程
ps ax | grep java

5. 查看IP对应域名
nslookup ip

6. 启动NG
[shuxin.qin@l-qpx1.qss.dev.cn0 /home/q/nginx]$ sudo sbin/nginx -c conf/nginx.conf

## 翻墙
sslocal -s sg01-22.ssv4.net -p 22222 -b 127.0.0.1 -l 1080 -k password -m aes-256-cfb


`sslocal -s 10.89.32.19 -p 48888 -b 127.0.0.1 -l 1081 -k BlackOps2015 -m aes-256-cfb`

# 门票

select * from cst_cost_item where item_name like '%门票包销成本' and cost_account = '6501.28'\G
