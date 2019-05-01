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
<http://139.199.126.58:9000/checkMessage/{id}>