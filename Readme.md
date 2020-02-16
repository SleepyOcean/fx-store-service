
## 接口描述

1. 用户：
    * 字段定义
        * 用户标识: userID
        * 用户名称: userName
        * 收货信息（联系方式:收货地址,多个以逗号分隔）: deliveryInfo
    * 接口定义
        1. 获取用户信息： /user/get
        ```JSON
        POST {
            "userID": "XXXX"
        }
        ```
        2. 保存或更新用户信息： /user/save
        ```JSON
        POST {
            "userID": "XXXX",
            "userName": "XXXX",
            "deliveryInfo": "XXXX"
        }
        ```
        3. 删除用户信息： /user/delete
        ```JSON
        POST {
            "userID": "XXXX"
        }
        ```
      
2. 商品

3. 购物车

4. 订单
    * 用户名称：
    * 配送地址：
    * 商品明细：
    * ？账户信息： 是否为店铺用户（批量购买）
 
