- jdbc找不到
- 下载jar包

1.我们现在要做的是新思路2.0，
1.新思路1 和 新思路2的数据库是

项目本身：
1.我本地安装了MySQL，secureCRT，IDEA
1.如何跑起来
我把本地的数据库配置了一下，选择开发dev模式，同时把sql文件夹下的两个文件注入到了数据库，但是没有跑起来。
提示一些数据表不存在

十月底-论文比赛
- 2023.10.15 第一次开会
教研论文：1 
- 10：
- 20号开始提交，  10：31号 17：00截至（停止提交）
- 结题 11.1 开始提交， 11.5号截至
- 初审：10.31号  18：00——22：00预评
- 评委网评
- 评委现评
- 面向

四类用户
论文没有现评，有网评；其他都是四大步骤
- 进程管理
- 校外用户（评委），添加，删除等管理
- 增加校内评委，授权。给评委登录的界面（非统一认证）
- 成绩管理：给老师的成绩（评委1，2），评委评分：1）下载空模板，填写分数，上传成绩；2）页面上直接填分；（测试）
- 奖项管理：获奖奖项1，2；技术奖，设计奖。论文：1，2，3等奖，组别
- 结题：优秀，一般
用户

栏目：优秀案例（25个）
录入奖项


10.19日
-教研论文页面 王老师验证需求：
1.论文打开（10.19——10.31:22.00）
2.论文方向：项目编号改成2023；论文方向增加一个
3.老师（以后修改）：保存，提交：提交后就不能修改了（提交时的二次确认）；
4.初审人员（0006170278，必须功能，需验证）：可以打回修改。
5.论文评委：评委评分。
6.管理员：论文进程开和关；论文评委指定，新建论文外审评委，评委评分；成绩统计（整个的验证）。
——————————————————————————————————————————————————————————————————————
开发人员：
1.项目的整体逻辑，开发时候的重点关注那些文件，哪些数据，
2.许多页面的作用和功能，尤其是管理员，评审人员页面。
3.修改代码后怎么重新部署，重启服务。
4.开放几个页面的权限应该如何修改，在哪里修改。
5.以新增加进程管理功能为例，此功能应该具体怎么实现。


1.在页面中添加上述代码即可，点击提交按钮后，在提交的时候，进行二次确认，确认要提交吗，
提交之后不可以再点击保存和提交按钮。
2.在该页面中，根据数据库中的一个数值显示页面；如果数值是true，那么保存和提交按钮不可以再点击，如果数值是false，那么可以点击。

设计一个数

- 非校内评委登录
HTML页面需要进行修改
新建一个judgeAction，判断是否相同，如果相同的话，返回judge页面，不相同的话，返回到主机页面


apply_files:filenames,格式化名称（）
[innovationResearchEnroll.jsp](web%2FWEB-INF%2Fjsp%2Fresearch%2FinnovationResearchEnroll.jsp)

数据库
增加校外评委：页面  jsp,对应的方法
校外评委登录：页面


http://localhost:8080/xinsilu2/admin/index.action
http://localhost:8080/xinsilu2/judge/externelLoginJSP.action
