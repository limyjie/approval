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

