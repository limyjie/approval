响应
    1 正确 ，0 错误

用户登录表: USERID  账号LOGINID 密码PASSWD

审批人表： TCCOM001
审批阶段表 ap_step
审批人-审批阶段关系表 ap_step_staff

审批模板/审批事件表 ap_event
审批
部门表 FI023

-------------------------------------------------------------
通用流程审批软件


添加审批阶段
	阶段代码（id/IDNO自生成） 阶段名 阶段描述 需要通过的次数 创建人 创建日期
	添加该阶段的批准人（多个，批准人列表从后端获取）
	批准人应大于等于通过次数

说明：审批阶段的实体类是com.prd.approval.entity.Process，数据库中的表是AP_STEP,
     外键是 IDNO，对应AP_STEP_STAFF 的HID
     批准人列表（批准人 | 部门 ）的获取：从TCCOM001 中获取
     TCCOM001.depa = FI023.depa

    应当向数据库添加的数据：
    AP_STEP:
        idno varchar2(20) primary key,
        event_id varchar2(20),ap_event.idno   ??
        sort_no number(5),次序号，事件见按此顺序执行阶段，线性  ??
        step_count number(5),当前事件总共的阶段数
        source_step_id varchar2(20),当前记录的复制来源 ap_step.idno  ??
        step_code varchar2(20),阶段代码，若是独立阶段(及不依附于实例事件)，那么必须唯一  ??
        step_type number(5) default 2, 类型，1 = 事件阶段，2 = 模板(独立)阶段
        step_name varchar2(40),名
        step_description varchar2(200),名
        times_count number(5),通过当前阶段需要的通过次数
        status varchar2(10),1=新建，2=  正在执行(开启),3= 通过，4 = 被拒绝
        times_remain number(5),仍需要的通过次数(此数值会随着每个审批人的审批通过而变化，初始值 = times_count )
        create_by varchar2(20),记录创建人
        create_date date
        );
    AP_STEP_STAFF:
        idno varchar2(20) primary key,
        hid varchar2(20), ap_step.idno
        status varchar2(10) default '1',  1 = 未执行，2 = 已执行(通过或拒绝)
        sort_no number(5),排序号   ??
        staff_no varchar2(20),审批人编码 tccom001.emno
        staff_name varchar2(40),tccom001.nama
        staff_depa varchar2(20),部门信息，暂时闲置
        staff_depa_name varchar2(20),
        ap_result varchar(10) default '1',审批结果 1 = 未审批， 3 = 审批通过， 4 = 拒绝
        ap_comment varchar(200),审批备注
        ap_date date审批时间


创建审批模板
	模板名称 模板描述 创建人 创建日期 是否激活  审批发起人 单据类型（） 审批阶段

	create table ap_event(
    idno varchar2(20) primary key,主键
    is_model number(5) default 0,0 = 事件，1 = 模板
    model_id varchar2(20), 事件独有，记录它的复制来源的 ap_event.idno
    event_name varchar2(40),名字
    event_description varchar2(200),名字详细
    is_active number(5) default 1, = 1 未启用状态，= 0 为不启用
    is_update number(5) default 1, 修改更新字段，暂时闲置
    status varchar2(10) default '1',事件独有，状态字段 =1 新建， = 3 通过审批， = 4 被拒绝， = 5 被中止
    sort_no number(5),事件独有，顺序号，如果一个单据被多次提交审核，这里记录先后次序 1，2，3，4，5
    current_step_id varchar2(20),事件独有，当前正在进行的阶段的 ap_step.idno
    current_step_sort_no number,事件独有，当前正在进行的阶段的 ap_step.sort_no
    bill_no varchar2(40),事件独有，目标单据的号码
    bill_code varchar2(40),事件独有，目标单据的类型的代码
    bill_name varchar2(40),事件独有，目标单据类型的名字
    creator_no varchar2(20),事件独有，触发人
    creator_name varchar2(40),事件独有，触发时间
    create_by varchar2(20)，当前记录的创建人
    create_date date
    );



审批
	登陆后审批
	显示：发送人 标题 发送时间 标记（已读/未读） 执行标记（已执行/未执行）
	审批发起人 单据 审批时间名  审批次序号 发起日期 备注（来自数据库） 阶段名 阶段次序 审批结果（填入） 备注（填入，拒绝时不能为空）

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
一个审批有多个阶段
一个单据类型只能被一个单据模板占用
同类型模板只能有一个处于激活状态












