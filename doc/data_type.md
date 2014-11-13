<link rel="stylesheet" href="http://yandex.st/highlightjs/6.1/styles/default.min.css">
<script src="http://yandex.st/highlightjs/6.1/highlight.min.js"></script>
<script>
hljs.tabReplace = ' ';
hljs.initHighlightingOnLoad();
</script>
#传输数据类型说明

##简要说明
REQUEST和RESPOND的类型都是WrapperData

    {
        "callStatus": "",
        "errorCode": "",
        "token": "",
        "accountId": "",
        "data": {},
        "numPerPage": 0,
        "currPageNum": 0,
        "totalItemNum": 0,
        "totalPageNum": 0
    }    
下面只列出有用的属性

##具体说明

###用户

####用户登录

`POST`
__Account/login__

    {  
        "data": {
              "accountNumber":"123",
              "accountPassword":"123"
            }
    }

`RETURN`

    {
        "callStatus": "SUCCEED",
        "errorCode": "No_Error",
        "token": "a6d0a32ab22166fa1759e7b392eee6fb:2014/12/13",
        "data": {
            "id": 1,
            "create_time": 1414860396000,
            "modify_time": 1415843230874,
            "accountNumber": "123",
            "accountPassword": "123",
            "accountName": "sau测试",
            "accountEmail": "sau@ccq.com",
            "accountCharacter": "ADMINISTRATOR",
            "studentGrade": null,
            "entranceYearMonth": null,
            "lastLoginTime": 1415843230841,
            "isActive": true,
            "loginToken": "a6d0a32ab22166fa1759e7b392eee6fb:2014/12/13",
            "classes": [],
            "studentClass": null,
            "studentReservation": null
        }
    }
`ERROR`

    {
        "callStatus": "FAILED",
        "errorCode": "Account_Not_Exist" || "Password_Wrong" || "Account_Not_Active"
    }

####用户登出

`POST`
__Account/logout__

    {
    	"token":"a6d0a32ab22166fa1759e7b392eee6fb:2014/12/13"
    }
`RETURN`

    {
        "callStatus": "SUCCEED",
        "errorCode": "No_Error"
    }
`ERROR`

    {
        "callStatus": "FAILED",
        "errorCode": "Token_Expired" || "Token_Invalid"
    }
    
####获取用户 Profile

`POST`
__Account/profile__

    {
    	"token":"a6d0a32ab22166fa1759e7b392eee6fb:2014/12/13"
    }
    
`RETURN`

    {
        "callStatus": "SUCCEED",
        "errorCode": "No_Error"
        "data": {
            "id": 1,
            "create_time": 1414860396000,
            "modify_time": 1415843394000,
            "accountNumber": "123",
            "accountPassword": "123",
            "accountName": "sau测试",
            "accountEmail": "sau@ccq.com",
            "accountCharacter": "ADMINISTRATOR",
            "studentGrade": null,
            "entranceYearMonth": null,
            "lastLoginTime": 1415843394000,
            "isActive": true,
            "loginToken": "a6d0a32ab22166fa1759e7b392eee6fb:2014/12/13",
            "classes": [],
            "studentClass": null,
            "studentReservation": null
        }
    }
    
`ERROR`

    {
        "callStatus": "FAILED",
        "errorCode": "Token_Expired" || "Token_Invalid"
    }
    
####修改密码 （输入原始密码和新密码，错误需要返回错误的message）

`POST`
__Account/reset__

    {
      "token":"a6d0a32ab22166fa1759e7b392eee6fb:2014/12/13",
      "data":
            {
              "oldPassword":"123",
              "newPassword":"456"
            }
    }
`RETURN`

    {
        "callStatus": "SUCCEED",
        "errorCode": "No_Error"
    }
`ERROR`

    {
        "callStatus": "FAILED",
        "errorCode": "Token_Expired" || "Token_Invalid" || "Password_Wrong"
    }

#####_下面的需要管理员权限控制，没有权限提示错误信息_

####管理员获取用户列表

`POST`
__Account/all__

    {
        "callStatus": "SUCCEED",
        "errorCode": "No_Error",
        "data": [
            {
                "id": 4,
                "create_time": 1415617651000,
                "modify_time": 1415617651000,
                "accountNumber": "121",
                "accountPassword": "qwe",
                "accountName": "sau测试",
                "accountEmail": "sau@ccq.com",
                "accountCharacter": "TEACHER",
                "studentGrade": null,
                "entranceYearMonth": null,
                "lastLoginTime": null,
                "isActive": true,
                "loginToken": null,
                "classes": [],
                "studentClass": null,
                "studentReservation": null
            },
            ...
        ]
    }
    

####添加一个用户  （估计接口需要修改成post form 的形式）
`POST`
__Account/add__

    {
      "token": "a6d0a32ab22166fa1759e7b392eee6fb:2014/12/13",
      "data":
            {
              "accountNumber":"129",
              "accountPassword":"qwe",
              "accountName":"sau测试",
              "accountEmail":"sau@ccq.com",
              "accountCharacter":"TEACHER" 
            }
    }
    
`RETURN`

    {
        "callStatus": "SUCCEED",
        "errorCode": "No_Error",
        "data": {
            "id": 6,
            "create_time": 1415860128592,
            "modify_time": 1415860128658,
            "accountNumber": "129",
            "accountPassword": "qwe",
            "accountName": "sau测试",
            "accountEmail": "sau@ccq.com",
            "accountCharacter": "TEACHER",
            "studentGrade": null,
            "entranceYearMonth": null,
            "lastLoginTime": null,
            "isActive": true,
            "loginToken": null,
            "classes": [],
            "studentClass": null,
            "studentReservation": null
        }
    }
    
####删除一个用户（isActive 设置为false）
`DELETE`
__Account/delete/{id}__
    
    {
	    "token":"a6d0a32ab22166fa1759e7b392eee6fb:2014/12/13"
    }
    
`RETURN`

    {
        "callStatus": "SUCCEED",
        "errorCode": "No_Error",
        "data": {
            "id": 2,
            "create_time": 1414860680000,
            "modify_time": 1414860680000,
            "accountNumber": "124",
            "accountPassword": "qwe",
            "accountName": "sau测试2",
            "accountEmail": "sau@ccq.com",
            "accountCharacter": "TEACHER",
            "studentGrade": null,
            "entranceYearMonth": null,
            "lastLoginTime": null,
            "isActive": false,
            "loginToken": null,
            "classes": [],
            "studentClass": null,
            "studentReservation": null
        }
    }
    
`ERROR`

    {
        "callStatus": "FAILED",
        "errorCode": "Token_Expired" || "Token_Invalid" || "Access_Denied" || "Account_Not_Exist"
    }
    
####查看用户信息
`POST`
__Account/profile/{id}__
    
    {
        "token":"a6d0a32ab22166fa1759e7b392eee6fb:2014/12/13"
    }
    
`RETURN`

    {
        "callStatus": "SUCCEED",
        "errorCode": "No_Error",
        "data": {
            "id": 2,
            "create_time": 1414860680000,
            "modify_time": 1414860680000,
            "accountNumber": "124",
            "accountPassword": "qwe",
            "accountName": "sau测试2",
            "accountEmail": "sau@ccq.com",
            "accountCharacter": "TEACHER",
            "studentGrade": null,
            "entranceYearMonth": null,
            "lastLoginTime": null,
            "isActive": false,
            "loginToken": null,
            "classes": [],
            "studentClass": null,
            "studentReservation": null
        }
    }
    
`ERROR`

    {
        "callStatus": "FAILED",
        "errorCode": "Token_Expired" || "Token_Invalid" || "Access_Denied" || "Account_Not_Exist"
    }

####修改用户角色
	
    enum AccountCharacter {
        
        ADMINISTRATOR,
        TEACHER,
        STUDENT
    }
	
`POST`
__Account/updateCharacter__
    
    {
      "token":"a6d0a32ab22166fa1759e7b392eee6fb:2014/12/13",
      "data":
            {
              "accountCharacter":"STUDENT"
            }
    }

`RETURN`

    {
        "callStatus": "SUCCEED",
        "errorCode": "No_Error",
        "data": {
            "id": 3,
            "create_time": 1415546733000,
            "modify_time": 1415869497392,
            "accountNumber": "125",
            "accountPassword": "123",
            "accountName": "sau测试",
            "accountEmail": "sau@ccq.com",
            "accountCharacter": "STUDENT",
            "studentGrade": null,
            "entranceYearMonth": null,
            "lastLoginTime": 1415806301000,
            "isActive": true,
            "loginToken": "e8fea4dba932e67977a47ba380ad5b44:2014/12/12",
            "classes": [],
            "studentClass": null,
            "studentReservation": null
        }
    }

    {
        "callStatus": "FAILED",
        "errorCode": "Token_Expired" || "Token_Invalid" || "Access_Denied" || "Account_Not_Exist"
    }
            
####更新用户信息
`POST`
__Account/update/{id}__
    
    {
      	"token": "a6d0a32ab22166fa1759e7b392eee6fb:2014/12/13",
        "data": {
            "id": 2,
            "create_time": 1414860680000,
            "modify_time": 1414860680000,
            "accountNumber": "124",
            "accountPassword": "qwe",
            "accountName": "sau2",
            "accountEmail": "sau@ccq.com",
            "accountCharacter": "TEACHER",
            "studentGrade": null,
            "entranceYearMonth": null,
            "lastLoginTime": null,
            "isActive": false,
            "loginToken": null,
            "classes": [],
            "studentClass": null,
            "studentReservation": null
        }
    }
`RETURN`

    {
        "callStatus": "SUCCEED",
        "errorCode": "No_Error",
        "data": {
            "id": 2,
            "create_time": 1414860680000,
            "modify_time": 1415870456608,
            "accountNumber": "124",
            "accountPassword": "qwe",
            "accountName": "sau2",
            "accountEmail": "sau@ccq.com",
            "accountCharacter": "TEACHER",
            "studentGrade": null,
            "entranceYearMonth": null,
            "lastLoginTime": null,
            "isActive": false,
            "loginToken": null,
            "classes": [],
            "studentClass": null,
            "studentReservation": null
        }
    }
    
`ERROR`

    {
        "callStatus": "FAILED",
        "errorCode": "Token_Expired" || "Token_Invalid" || "Access_Denied" || "Account_Not_Exist"
    }

### 实验室
####添加一个实验室
####删除一个实验室 （不是真删除，目前看来 设置一个判断是否启用 column的可以了）
####列出实验室 （ 列出实验室列表，没有启用的不用列出）
####查看实验室详情
####更细实验室信息


####添加一个课程
####删除课程  （同样不是真删除，设置一个停用column）
####列出所有课程 
####查看课程详情
####更新课程

####添加一门实验
####删除实验 （同样不是真删除，设置一个停用column）
####列出所有实验
####查看实验详情
####更新实验信息
