# 随记

## 2023  
### 2/19  
git报错 warning: LF will be replaced by CRLF in  
+ 原因：查阅资料后了解到具体原因是linux和Unix系统的换行符与win系统的换行符不同在转换的时候会发生错误，但是Git 可以在你提交时自动地把回车（CR）和换行（LF）转换成换行（LF），而这个功能是默认开启的，所以即使当你没有修改代码的时候他也会提示你warning: LF will be replaced by CRLF in即警告：LF 将被 CRLF 替换。  
+ 解决：git config --global core.autocrlf false (全局有效）
  git config core.autocrlf false (仅对当前git仓库有效）

### 2/14  
Merge the incoming changes into the current branch:将传入的更改合并到当前分支中
    
Rebase the current branch on top of the incoming changes:在传入更改的基础上重新建立当前分支

### 2/13  
快捷键：  
https://docs.github.com/zh/get-started/using-github/keyboard-shortcuts  
ctrl+k 打开命令面板  

阅读代码技巧：  
+ 在仓库详情页按下。（句号键），项目会在网页版VSCode中打开。  
 
在线运行项目：  
+ 项目在网页版VsCode打开之后，在项目地址前加上：gitpod.io/#前缀
例如：https://github.dev/seasky-wjl/MyBatisIndoor 加上前缀  https://gitpod.io/#https://github.com/seasky-wjl/MyBatisIndoor

 


### 2/3

+ 根据分支名push到指定分支  
```  
git push -u origin branchName
```

+ 查看当前分支  
```  
git branch  
```  
#### 重命名本地分支
+ 在当前分支时  

```
git branch -m new_branch_name
```  

+ 不在当前分支时  
```
git branch -m old_branch_name new_branch_name
```
#### 重命名远端分支(假设是在当前分支，并且远端分支与本地分支名是一致的)  
思路是：改本地分支名，删除远端分支，再把改过的分支推到远端，最后关联这两个分支
+ 重命名本地分支  
```  
git branch -m new_branch_name  
``` 
+ 删除远程分支  
```   
 git push --delete origin old_branch_name  
``` 
+ 上传新命名的本地分支  
``` 
git push  origin new_branch_name  
``` 
+ 关联修改后的本地分支与远程分支  
```   
git branch --set-upstream-to origin/new_branch_name  
``` 

项目由Github托管，团队协同工作的流程大致是：  
1. 远端有更新，每天打开电脑开发之后，首先把远端的内容同步到本地，并在此基础上进行开发。
2. 开发时注意切换到dev分支，开发完成之后提交更新内容，并合并到main分支，最后将所有提交同步到远端。

















