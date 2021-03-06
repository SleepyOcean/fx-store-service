[toc]



## 数据库常量表对应

1. 菜类商品分类：

| 键   | 值       |
| ---- | -------- |
| 1    | 时令水果 |
| 2    | 新鲜蔬菜 |
| 3    | 肉蛋水产 |
| 4    | 方便速食 |
| 5    | 米面粮油 |
| 6    | 酒水饮料 |
| 7    | 调味品   |
| 8    | 休闲零食 |
| 9    | 厨房用品 |



## 接口列表

* 用户接口

| 接口简述                | 接口url         | 接口描述 |  备注 |
| ----------------------- | --------------- | -------- | -------- |
| 保存用户信息            | /user/save      |          ||
| 更新用户信息            | /user/update    |          ||
| 通过appcode获取用户信息 | /user/getByCode |          ||
| 通过userId获取用户信息  | /user/getById   |          ||
|                         |                 |          ||
|                         |                 |          ||

* 地址接口

| 接口简述     | 接口url                  | 接口描述 |  备注 |
| ------------ | ------------------------ | -------- | -------- |
| 添加地址     | /user/address/add        |          ||
| 获取地址     | /user/address/get        |          ||
| 更新地址     | /user/address/update     |          ||
| 设置默认地址 | /user/address/setDefault |          ||
| 删除地址     | /user/address/delete     |          ||


* 商品接口

| 接口简述     | 接口url        | 接口描述 |  备注 |
| ------------ | -------------- | -------- | -------- |
| 获取商品     | /goods/get     |          ||
| 保存商品     | /googs/save    |          ||
| 批量保存商品 | /goods/saveAll |          ||
| 搜索商品     | /goods/search  |          ||
| **更新商品信息** | /goods/update |          |未实现|
| **删除商品** | /goods/delete | |未实现|


* 购物车接口

| 接口简述       | 接口url          | 接口描述 | 备注 |
| -------------- | ---------------- | -------- | ---- |
| 获取购物车     | /cart/get        |          |      |
| 更新购物车     | /cart/update     |          |      |
| 删除购物车商品 | /cart/delete     |          |      |
| 购物车结算     | /cart/settlement |          |      |
|                |                  |          |      |


* 订单接口

| 接口简述         | 接口url           | 接口描述                                      | 备注   |
| ---------------- | ----------------- | --------------------------------------------- | ------ |
| 保存订单         | /order/save       |                                               |        |
| 获取指定订单     | /order/get        |                                               |        |
| 获取订单列表     | /order/list       |                                               |        |
| **删除订单**     | /order/delete     |                                               | 未实现 |
| **订单状态更新** | /order/update     | 更新订单状态：1. 付款状态，2. 配送状态 。。。 | 未实现 |
| **订单分配**     | /order/allocation | 分配订单配送                                  | 未实现 |
|                  |                   |                                               |        |
|                  |                   |                                               |        |

