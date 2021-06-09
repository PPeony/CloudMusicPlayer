# CloudMusicPlayer
> 可以作为：数据库作业/课程设计等软件作业

__docker:[my docker repository](https://hub.docker.com/repository/docker/cpeony/cloud-music-player)__

You can store your music on cloud.You can upload them by url or files. And pay attention, one person can only listen the music which uploaded by himself.That is to say , Bob uploads the music *Queen-Bohemian Rhapsody* , Alice has never uploaded this file, so Alice cannot listen *Bohemian Rhapsody* .What a pity ! (I don't want to have unnecessary copyright disputes, so I deliberately designed it like this)
## How to run?
1. ```docker run cpeony/cloud-music-player```.If you don't have this image ,it will pull from repository automatically.
2.  You can visit "ip:8080/index.html" to see the view.
## These are final results.
### This is the page when you are not logged in. At this time, you need to click on the top right corner to register or log in.
![not logged in view](https://img-blog.csdnimg.cn/20210608162241806.PNG?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzE1NzY0NDc3,size_16,color_FFFFFF,t_70#pic_center)
### When you click "登录" button, you will be redirect to login view.
![login](https://img-blog.csdnimg.cn/20210608161621443.PNG?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzE1NzY0NDc3,size_16,color_FFFFFF,t_70#pic_center)
### This view is to upload music, you can upload files or input url.
![upload fils](https://img-blog.csdnimg.cn/20210608163415229.PNG?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzE1NzY0NDc3,size_16,color_FFFFFF,t_70#pic_center)

## Others
__Here is only backend code, front code click here:[https://github.com/PPPkanZTouti3/CloudMusicPlayer](https://github.%20com/PPPkanZTouti3/CloudMusicPlayer)__


# database structure
database structure locates at src/main/resources/static/db.sql, 4 tables:user,backgroundPicture(not implemented),detail and list
