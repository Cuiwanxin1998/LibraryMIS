图书管理系统
====
# 1概述
## 1.1设计目的
通过对图书管理系统的系统分析、系统设计、编码和调试等工作的实践，熟悉管理信息系统的开发过程、设计方法及相关编程技术，熟练掌握数据库设计的基本理论及方法。
## 1.2设计任务
要求完成一个具有一定实用价值的图书管理系统，主要任务包括：
* 在Msql环境下建立图书管理系统所使用的数据库，利用企业管理器或查询分析器建立各种数据库对象，包括：数据表、视图、约束、存储过程和触发器等；
* 掌握JDBC编程技术，对MYSQL Server数据库进行连接和操纵；
* 掌握使用Java语言开发一个数据库应用系统的基本方法和步骤，熟悉一些基础功能的实现方法，如：数据维护（插删改等操作），数据查询、浏览和Excel导出，统计与报表，用户登录和权限管理等。
* 了解C/S或B/S应用程序的多层体系结构及三层架构方案设计思想，了解迭代式开发，熟悉面向对象设计方法及其分析与设计过程，了解UML文档及其开发过程中的作用。
## 1.3设计准备
①	系统准备
操作系统：Windows xp/7/8
数据库系统：MYSQL Server数据库
客户端开发工具：Eclipse KEPLER
②	知识准备
熟悉MYSQL Server的使用；
熟悉Java语言及其数据库编程技术。
了解：迭代式开发过程、UML设计文档、设计模式；以及图书馆相关业务知识。
##1.4迭代式开发
迭代式开发（统一过程UP） 系统开发被组织成一系列固定的短期（一段为2-6周）小项目，称为迭代；每次迭代都产生可执行的系统。每次迭代都包括计划、需求、分析、设计、编码、测试等过程以及文档编写工作；第一次迭代考虑系统的核心功能，随后的迭代逐步扩展系统功能；每次迭代的成果（含需求、分析、设计、代码和文档等）均为下一次迭代的工作基础，直至满足最终需求。这种开发过程是基于面向对象方法的。

# 2系统分析与设计
## 2.1系统边界与约定
(1)	系统限定在实体书库的借阅和管理等业务范围；
(2)	不考虑图书馆的电子书库、订购、情报、人事管理等业务；
(3)	不考虑图书馆的跨区分布情况，如长江大学图书馆包括多个校区图书馆；
(4)	不考虑图书的通借通还，如读者可在湖北省高校任何一家图书馆借还图书；
(5)	不考虑珍藏图书的借阅业务；
(6)	暂不考虑与校园一卡通系统的外部接口。一卡通系统为外部系统（外部参与者），卡内记录有身份及相关信息，该系统负责身份验证工作。
(7)	期刊库和论文库的借阅和管理等业务可作为二期项目目标，视本系统使用情况而定。
## 2.2 概念模型
*	读者管理
读者管理即借书证管理，包括的业务（即用例）有：办理借书证、借书证变更、借书证挂失、解除挂失、补办借书证、注销借书证
借书证（读者）可分为2种类别：教师、学生。
借书证（教师）=借书证号、姓名、性别、所在单位、办证日期、照片等。
借书证（学生）=借书证号、学号、姓名、性别、专业、班级、办证日期、有效期、照片等。其中，有效期由学生类别决定，本科生4年、专科生3年、硕士生3年等。
相关业务规则：(1)读者凭借书证借书；(2)教师最多借书12本，借书期限最长为60天，可续借2次；学生最多借书8本，借书期限最长为30天，可续借1次；(3)处于挂失、注销状态的读者不能借书；(4)未归还图书者不能注销其借书证。
读者=借书证号、姓名、性别、所在单位、读者类别、办证日期、照片等。（另可加：电话、邮箱等）
读者类别=读者类别号、类别名称、可借书本数、可借书天数、可续借次数。

*	图书管理
包括业务（用例）：图书编目、新书入库、图书信息维护等。
图书信息=书号、书名、作者、出版社、出版日期、ISBN、分类号、语言、页数、单价、内容简介、图书封面、图书状态等；（图书状态包括：在馆、借出、遗失、变卖、销毁）

*	借阅管理
包括业务用例：借书、续借、还书等。还书过程涉及超期罚款、遗失图书罚款等业务规则。
罚款规则：（1）超期罚款规则 应罚款金额=超期天数*罚款率，罚款率=0.05元/天，罚款率可能随时间或读者类别而变化；实际罚款金额<=应罚款金额，根据实际情况可以进行减免。（2）遗失罚款规则 遗失图书应罚款金额=3*图书单价；实际罚款金额在（1*图书单价，3*图书单价）之间。（3）遗失罚款规则优先于超期罚款规则。
借书记录=借书证号、书号、借书操作员、借书日期、应还日期
续借记录=借书证号、书号、续借操作员、续借日期、应还日期，续借次数
还书记录=借书证号、书号、还书操作员、还书日期、应还日期，超期天数、应罚款金额，实际罚款金额
借阅信息=借书顺序号、借书证号、书号、借书操作员、借书日期、应还日期，续借次数、还书操作员、还书日期，超期天数、应罚款金额，实际罚款金额

*	用户登陆
包括用例：用户登录、密码修改，为本系统的基础和主要功能。
用户包括2类：读者、管理员。其中，管理员用户权限是4种角色的组合：借书证管理、图书管理、借阅管理、系统管理；系统管理员负责所有管理员用户及其权限的管理，借书证管理员负责读者管理（即借书证管理）。
管理员是读者，但读者不一定是管理员；读者与管理员间存在(1对0..1)联系。
读者信息+=密码。
管理员信息=用户号、用户名、密码、管理角色
管理角色设计：可采用4位二进制，借书证管理(0001)2=1、图书管理(0010)2=2、借阅管理(0100)2=4、系统管理(1000)2=8。如表示图书管理和借阅管理权限：2+4=6；判断7是否具备图书管理权限：7位与2，即(0111)2位与(0010)2=(0010)2，表示有此权限。
读者信息+=密码、管理角色。

*	系统功能与用户角色
表 1 系统功能与用户角色关系表
序号	系统功能	借书证管理	图书管理	借阅管理	系统管理	读者
1	读者管理	√			（√）	
2	图书管理		√			
3	借阅管理			√		
4	用户登录	√	√	√	√	√
5	密码修改	√	√	√	√	√
6	用户管理				√	
7	读者预约	√	√	√	√	√
8	统计与报表	√	√	√		√
9	数据备份				√	

*	领域模型
读者=读者号（借书证号）、姓名、性别、所在单位、电话、邮箱地址、办证日期、照片、借书证状态、已借书数量、密码、管理角色。（借书证状态：有效、挂失、注销）
读者类别=读者类别号、读者类别名称、可借书数量、可借书天数、可续借次数，罚款率，证件有效期。
图书=书号、书名、作者、出版社、出版日期、ISBN、分类号、语言、页数、单价、内容简介、图书封面、图书状态。（图书状态包括：在馆、借出、遗失、销毁、卖出）
借阅信息=借书顺序号、读者号、书号、借书操作员、借书日期、应还日期、续借次数、还书操作员、还书日期，超期天数、应罚款金额、实际罚款金额。



2.3 数据表的设计
*	读者类别表(TB_ReaderType)
序号	字段名	数据类型	说明
1	rdType	SmallInt	读者类别【主键】
2	rdTypeName	Nvarchar(20)	读者类别名称【唯一、非空】
3	CanLendQty	Int	可借书数量
4	CanLendDay	Int	可借书天数
5	CanContinueTimes	Int	可续借的次数
6	PunishRate	Float	罚款率（元/天）
7	DateValid	SmallInt	证书有效期（年）【非空，0表示永久有效】


*	读者信息表(TB_Reader)
序号	字段名	数据类型	说明
1	rdID	Int	读者编号/借书证号【主键】
2	rdName	nvarchar(20)	读者姓名
3	rdSex	nchar(1)	性别，男/女
4	rdType	SmallInt	读者类别【外键TB_ReaderType】【非空】
5	rdDept	nvarchar (20)	单位代码/单位名称
6	rdPhone	nvarchar(25)	电话号码
7	rdEmail	nvarchar(25)	电子邮箱
8	rdDateReg	datetime	读者登记日期/办证日期
9	rdPhoto	image	读者照片
10	rdStatus	nchar(2)	证件状态，3个：有效、挂失、注销
11	rdBorrowQty	Int	已借书数量(缺省值0)
12	rdPwd	nvarchar (20)	读者密码(初值123)，可加密存储
13	rdAdminRoles	SmallInt	管理角色，0-读者、1-借书证管理、2-图书管理、4-借阅管理、8-系统管理，可组合
*	图书信息表(TB_Book)
序号	字段名	数据类型	说明
1	bkID	Int	图书序号【标识列，主键】
2	bkCode	Nvarchar (20)	图书编号或条码号（前文中的书号）
3	bkName	Nvarchar(50)	书名
4	bkAuthor	Nvarchar(30)	作者
5	bkPress	Nvarchar(50)	出版社
6	bkDatePress	datetime	出版日期
7	bkISBN	Nvarchar (15)	ISBN书号
8	bkCatalog	Nvarchar(30)	分类号（如：TP316-21/123）
9	bkLanguage	SmallInt	语言，0-中文，1-英文，2-日文，3-俄文，4-德文，5-法文
10	bkPages	Int	页数
11	bkPrice	Money	价格
12	bkDateIn	DateTime	入馆日期
13	bkBrief	Text	内容简介
14	bkCover	image	图书封面照片
15	bkStatus	NChar(2)	图书状态，在馆、借出、遗失、变卖、销毁
*	借阅信息表(TB_Borrow)
序号	字段名	数据类型	说明
1	BorrowID	Numeric(12,0)	借书顺序号【主键】
2	rdID	Int	读者序号【外键TB_Reader】
3	bkID	Int	图书序号【外键TB_Book】
4	ldContinueTimes	Int	续借次数（第一次借时，记为0）
5	ldDateOut	DateTime	借书日期
6	ldDateRetPlan	DateTime	应还日期
7	ldDateRetAct	DateTime	实际还书日期
8	ldOverDay	Int	超期天数
9	ldOverMoney	Money	超期金额（应罚款金额）
10	ldPunishMoney	Money	罚款金额
11	lsHasReturn	Bit	是否已经还书，缺省为0-未还
12	OperatorLend	Nvarchar(20)	借书操作员
13	OperatorRet	Nvarchar(20)	还书操作员
## 2.4 三层架构
采用三层体系结构，即表示层、业务逻辑层和数据访问层
 

表示层（USL）：也称UI，提供交互式界面，形式：JFrame。
业务逻辑层（BLL）：实现业务功能，为表示层提供服务，形式：类库。
数据访问层（DAL）：实现数据访问功能（如数据库、文件等数据的读取、保存和更新），为业务逻辑层提供服务，形式：类。edu.yangtzeu.lmis.dal.SQLHelper类提供了对SQL Server数据库的一般访问方法。
实体类(Model)：描述一个业务实体的类，也即应用系统所涉及的业务对象。对数据库来讲，每个数据表对应于一个实体类，数据表的每个字段对应于类的一个属性。
表示层、业务逻辑层、数据访问层都依赖于业务实体。各层之间数据的传递主要是实体对象，业务信息封装在实体对象中。

## 2.5类的设计

* 实体类模块
（Model）	AbstractModel	抽象实体类，用于统一抽象数据访问类的方法接口
	ReaderType	读者类型实体类
	Reader	读者实体类
	Book	图书实体类
	Borrow	借阅记录实体类
	DepartmentType	读者单位类型实体类
* 数据访问层
（DAL）	AbstractDAL	抽象数据访问类，用于统一数据访问类的方法接口
	ReaderTypeDAL	读者类型数据表访问类（插、删、改、查、存储过程等）
	ReaderDAL	读者数据表访问类（插、删、改、查、存储过程等）
	BookDAL	图书数据表访问类（插、删、改、查、存储过程等）
	BorrowDAL	借阅数据表访问类（插、删、改、查、存储过程等）
	SQLHelper	对SQL Server数据库进行访问的通用类
* 业务逻辑层
（BLL）	LibraryBLL	通用业务逻辑类，用于提供各业务逻辑类的通用方法。其他业务逻辑类均扩展此类。
	ReaderTypeAdmin	读者类型管理类，实现插、删、改、查等信息维护操作。
	ReaderAdmin	读者管理类，实现借书证办理、变更、补办、挂失、解除挂失、注销等用例中的各种系统操作。
	BookAdmin	图书管理类，实现图书管理各用例中的系统操作
	BorrowAdmin	借阅管理类，实现借书、还书、续借等用例中的系统操作。
	UserAdmin	用户管理类，
* 表示层
（UI）	Login	登录窗口类
	Main	程序主窗口类，含菜单、工具栏、状态栏等
	ReaderPanel	读者管理界面类
	…	

其中，实体类、数据访问和业务逻辑模块分别设置了一个抽象类（业务逻辑层为通用类），供模块中其他类继承和扩展。这有利于统一方法接口，便于不同层次之间的调用。

