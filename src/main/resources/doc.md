# 项目约定
- 响应的状态码： 1 正确 ，0 错误

# 基本概念

|名词|英文单词|数据库表|对应实体类|
|----|----|----|---|
|审批阶段|process  /  step|ap_step|Process|
|审批模板/审批事件| template  / event |ap_event|Event|
|发起人|originator/creator/eventCreator|ap_event_creator|EventCreator|
|审批人|auditor/stepStaff|ap_step_staff|StepStaff/Auditor|
## 关于其他表格的说明
- 用户登录表: USERID  账号LOGINID 密码PASSWD
- 审批人表/发起人表： TCCOM001
- 用户登录表 和 审批人/发起人表 通过 LOINGID　字段　连接
- 单据类型表：ap_event_bill
- 部门表 FI023

# 实体关系

- 审批模板/审批事件:审批阶段 = 1:n
- 审批模板/审批事件:发起人 = 1:n
- 审批阶段:审批人 = 1:n


一个单据类型只能被一个单据模板占用
同类型模板只能有一个处于激活状态


-------------------------------------------------------------
通用流程审批软件

# 流程说明

## 添加审批阶段  
阶段代码（id/IDNO自生成） 阶段名 阶段描述 需要通过的次数 创建人 创建日期
添加该阶段的批准人（多个，批准人列表从后端获取）
批准人应大于等于通过次数

说明：审批阶段的实体类是com.prd.approval.entity.Process，数据库中的表是AP_STEP,
     外键是 IDNO，对应AP_STEP_STAFF 的HID
     批准人列表（批准人 | 部门 ）的获取：从TCCOM001 中获取
     TCCOM001.depa = FI023.depa


创建审批模板
	模板名称 模板描述 创建人 创建日期 是否激活  审批发起人(不同于阶段的审核人) 单据类型（） 审批阶段


审批
	登陆后审批
	显示：发送人 标题 发送时间 标记（已读/未读） 执行标记（已执行/未执行）
	审批发起人 单据 审批时间名  审批次序号 发起日期 备注（来自数据库） 阶段名 阶段次序 审批结果（填入） 备注（填入，拒绝时不能为空）

    执行审批




审批事件
	事件名（单据类型名+编码） 描述 目标单据  发起人 创建日期
------------------------------------------------
管理员登陆
	维护审批阶段、维护审批模板、执行审批事件、查看审批记录
非管理员登陆
	维护审批阶段、维护审批模板
-------------------------------------------------
功能需求：
	获取阶段代码
	添加阶段实体
	获取批准人们



-------------------------------------------------



## 执行审批的逻辑
1. 当前阶段  current ,下一阶段 next，当前审批事件 event
2. 当前阶段需要通过的审批次数  timeCount
3. 当前阶段仍需通过的次数  timeRemain
4. 当前阶段的审批人auditorList
5. 当前事件的发起人eventCreatorList
6. 当前阶段的当前执行人 stepStaff
1. 当auditorList[i] 的审批结果为拒绝时， 
    stepStaff.comment = '审批备注'
    stepStaff.result = '拒绝' 
    1.1 如果 current.timeRemain > 该阶段的未审批的审批人数量
        current.status = '不通过'
        event.status = '不通过'
        发消息给eventCreatorList
    1.2 如果 current.timeRemain <= 未审批的审批人数量
         继续执行审批
2. 当auditorList[i] 的审批结果为通过时，
    current.timeRemain--;
    current.status = '正在执行';
    stepStaff.comment = '审批备注'
    stepStaff.result = '审批通过'
     2.1 如果current.timeRemain == 0，
        current.status = '通过'
        2.1.1 如果   next == null 
            event.status = '通过'
        2.1.2 如果 next != null
             event.currentStepId = next.id
             event.currentStepSortNo += 1     
     2.2 如果 current.timeRemain > 0
         继续执行审批



# 其他

spring boot  默认 模板 引擎：thymeleaf，
需要我们把需要被该模板引擎渲染的视图（html页面或者JSP）放到resources/templates/这里

需要被渲染的html页面加上：<html xmlns:th="http://www.thymeleaf.org">




