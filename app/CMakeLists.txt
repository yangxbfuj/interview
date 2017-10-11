# 更多在 Android Studio 中使用 CMake的内容，可以查看如下文档:
# https://d.android.com/studio/projects/add-native-code.html

# 设置变异本地库时需要使用的最小的 CMake 版本.

cmake_minimum_required(VERSION 3.4.1)

# 创建和命名一个库，将其设置为 STATIC 的或者是 SHARE 的,同时提供一个其源码的相对路径.
# 你可以定义多个库,CMake 同样也会为你编译他们.
# Gradle 自动为你的 APK 打包 shared 的库.

add_library( # 设置库名.
             native-lib

             # 设置库作为 share 库.
             SHARED

             # 提供源码路径的相对路径地址.
             src/main/cpp/native-lib.cpp )
add_library(
             yxb-lib

             STATIC

             src/main/cpp/yxb.cpp )

# 搜索一个制定的预编译的库,并且将其路径存储为一个变量.
# 因为 CMake 在默认情况下的搜索路径中包含了系统库,所以你只需要特别声明你需要加入的公共 NDK 库.
# CMake 在完成其编译工作之前将识别你的添加的库.

# find_library( # 设置路径变量名.
#               log-lib
#               # 指明你需要 CMake 放置 NDK 库的名称.
#               log )

# 指明需要 CMake 链接到目标库的库.你客运链接多个库,就像你在 build 脚本中定义的那样,预创建的第三方库或者系统库.

# target_link_libraries( # 指明目标库.
#                        native-lib
#
#                        # 链接目标库到 NDK 中的 log 库
#                        ${log-lib} )