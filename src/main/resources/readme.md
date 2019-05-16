# 用户登陆
post  
/user/login
   {
    	"id":"admin",
    	"userName":"",
    	"password":"bb"
    }


# 审批人
## 获取
get  
<http://139.199.126.58:9000/auditor/getAll>
# 审批阶段
## 添加
post  
<http://139.199.126.58:9000/process/add>
```json
{
    "process":{
    	"stepName":"ggg",
    	"stepDescription":"ggg",
    	"timesCount":"8",
    	"createBy":"ggg"
    },
    "list":[103,102,101]
}
```

## 获取
- get 获取所有审批阶段:  
<http://139.199.126.58:9000/process/getAll>
- get 获取ID为51799的审批阶段：  
<http://139.199.126.58:9000/process/get/51799>

## 修改
post  
<http://139.199.126.58:9000/process/modify>  
```json
{
    "process":{
    	"id":"57222",
    	"stepName":"ggg",
    	"stepDescription":"ggg",
    	"timesCount":"2",
    	"createBy":"ggg"
    },
    "list":[103,102,101]
}
```

## 删除
post  
<http://139.199.126.58:9000/process/remove/57199>

# 审批模板

## 获取所有模板表
<http://139.199.126.58:9000/template/getAll> 

## 获取所有激活状态的模板
<http://139.199.126.58:9000/template/getAll/active>

## 获取改模板对应的审批阶段表

## 根据模板ID查看详情
get  
<http://139.199.126.58:9000/template/get/57039>

## 添加模板
post  
<http://139.199.126.58:9000/template/add>
```json
{
    "template":{
    	 "eventName":"eventName",
    	 "isActive":"1",
		 "eventDescription":"eventDescription",
		 "createBy":"9999"
    },
    "originatorIdList":[4,5,6],//发起人ID
    "processIdList":[1,2,3]//审批阶段ID
}
```

## 删除模板
post  
<http://139.199.126.58:9000/template/remove/57294>

## 修改模板
post  
<http://139.199.126.58:9000/template/modify>
```json
{
    "template":{
    "id":"123",
    "eventName":"eventName",
    "isActive":"1",
	"eventDescription":"eventDescription",
	"createBy":"9999"
    },
    "originatorIdList":[4,5,6],//发起人ID
    "processIdList":[1,2,3]//审批阶段ID
}
```

## 执行审批
result 为 审批结果 ，可选项：pass  notPass 
<http://139.199.126.58:9000/user/doApproval>

```json
{
	"auditorId":"101",
	"eventId":"57692",
	"result":"pass",
	"remarks":"bb"
}
```

## 查看目标单据
get  
<http://139.199.126.58:9000/user/getTargetBill/{id}>


## 根据用户ID查看是否有需要审批的事件
post  
<http://139.199.126.58:9000/user/checkMessage/{id}>

## 获取消息
get  
userId 指的是 101、102
<http://139.199.126.58:9000/user/getMessage/{userId}>

## 发送消息
post  
<http://139.199.126.58:9000/user/sendMessage>
```json
    {
    	"fromUser":"102",
    	"toUser":"101",
    	"subject":"subject",
    	"content":"content"
    }
```

## 1 根据已执行、全部、未执行查询审批事件
只能查询到登陆者的记录，其他人的无法看到
传给后台数据：已执行、全部、未执行
post  
<http://139.199.126.58:9000/template/event/byStatus>
status: 
    已执行：'done'
     全部：'all' 
     未执行：todo'
```json
 {
    	"userId":"101",
    	"status":"done"
    
    }
```

## 2、需求：根据事件ID获取事件(事件、发起人)
获取数据：事件名、描述、目标单据、发起人、创建日期
get
<http://139.199.126.58:9000/user/event/{eventId}>


## 3、根据ID（message id 或者 step staff id）获取审批（事件、发起人、阶段）
获取数据：详细数据：包括事件、阶段、发起人（此部分在执行审批已有）
post  
<http://139.199.126.58:9000/user/event/detail>
queryMethod: message / stepStaff
```json
{
	"queryId":"57703",
	"queryMethod":"message"
}
```
## 查看审批记录（可以看到所有记录）
   1、需求：根据单据编号、发起人、事件状态等任意某个或多个数据查询审批记录
   获取数据：发起人、事件名[]
post  
status： 1新建  3 通过审批  4 被拒绝  5 被中止
<http://139.199.126.58:9000/user/event/case>
```json
   {
    	"billNo":"",
    	"creator":"101",
    	"status":""
    }
```

## 2、根据事件ID获取记录（此需求上部分已有）
   获取数据：事件名、描述、目标单据、发起人、创建日期

<http://139.199.126.58:9000/user/event/allDetail>
## 3、根据事件ID获取事件详情（事件、发起人、所有阶段、审批人）
   获取数据：事件名、描述、目标单据、发起人、创建日期
   事件阶段[]、
   阶段名、阶段描述、需要通过次数、已通过、创建人、创建日期、
   审批人、结果、审批日期、备注、部门的表格[]


