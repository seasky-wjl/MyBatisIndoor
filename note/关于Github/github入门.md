# 随记

## 2023  
### 9/10
git报错error:

```bash
remote: error: Trace: 0d149ed037307b0a5d1b3bedcae01c5b586f1d26eda816431dbb7d48fc36f321
remote: error: See https://gh.io/lfs for more information.
remote: error: File datasets/fec/P00000001-ALL.csv is 150.56 MB; this exceeds GitHub's file size limit of 100.00 MB
remote: error: GH001: Large files detected. You may want to try Git Large File Storage - https://git-lfs.github.com.
```

**法1：**

直接不上传该文件！[参考链接]([忽略文件 - GitHub 文档](https://docs.github.com/zh/get-started/getting-started-with-git/ignoring-files))

在根目录下创建.gitignore，内容为:

```tex
# 写下要排除文件或者文件夹名称
datasets/
```
如果想要忽略已检入的文件，则必须在添加忽略该文件的规则之前取消跟踪它。 从终端取消跟踪文件。
```bash
git rm --cached FILENAME
```

> 注意：如果之后需要上传目录下的文件，修改该文件之后，再重新add并commit即可。
>
> .gitignore也可以忽略它自己，方法同上。

**法2：**

使用Git Large File Storage，[参考链接]([安装 Git Large File Storage - GitHub 文档](https://docs.github.com/zh/repositories/working-with-files/managing-large-files/installing-git-large-file-storage))。

1. 安装GLFS，[下载地址]([Git Large File Storage | Git Large File Storage (LFS) replaces large files such as audio samples, videos, datasets, and graphics with text pointers inside Git, while storing the file contents on a remote server like GitHub.com or GitHub Enterprise. (git-lfs.com)](https://git-lfs.com/))，下载完成点击安装。
2. 在bash中验证：

```bash
$ git lfs install

Updated Git hooks.
Git LFS initialized.
```

3. 配置GLFS，[参考链接]([配置 Git Large File Storage - GitHub 文档](https://docs.github.com/zh/repositories/working-with-files/managing-large-files/configuring-git-large-file-storage))。

​		要将存储库中的文件类型与 Git LFS 相关联，请输入 `git lfs track`，后跟要自动上传到 Git LFS 的文件扩展名的名称。

例如，要关联 .psd 文件，请输入以下命令：

```bas
$ git lfs track "*.psd"
> Adding path *.psd
```

​			需要将每种要与 Git LFS 关联的文件类型和 `git lfs track` 一起添加。 此命令将修改存储库的 .gitattributes 文件，并将大文件与 Git LFS 相关联。

> 注意：强烈建议将本地 .gitattributes 文件提交到存储库中。
>
> - 如果依赖与 Git LFS 关联的全局 .gitattributes 文件，可能会导致在参与其他 Git 项目时发生冲突。
> - 在存储库中包含 .gitattributes 文件允许创建分支或新克隆的人员更轻松地使用 Git LFS 进行协作。
> - 在存储库中包含 .gitattributes 文件允许将 Git LFS 对象选择性地包含在 ZIP 文件和 tarball 存档中。

​			再将文件添加到与关联的扩展名相匹配的仓库：

```bash
git add path/to/file.psd
```

​			提交文件并将其推送到 GitHub：

```bash
git commit -m "add file.psd"
git push
```



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

















