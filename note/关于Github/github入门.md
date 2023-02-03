# 随记

## 2023
### 2/3

#### 重命名本地分支

+ 查看当前分支  
```  
git branch  
```  

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