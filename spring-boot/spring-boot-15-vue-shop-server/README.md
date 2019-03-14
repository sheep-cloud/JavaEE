## 1. Linux系统 图片验证码乱码问题解决

- 问题原因: Linux系统缺少字体库

- Linux添加字体库

  ```ini
  1. 查看Linux系统字体库: fc-list
  2. 将本地字体放入Linxu: /usr/share/fonts 目录, 在该目录下执行: fc-cache
  3. 将本地字体放入Jdk: /usr/local/jdk/jdk1.8.0_161/jre/lib/fonts, 在该目录下执行: fc-cache
  ```

