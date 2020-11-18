# api
- [User](#User)
- [Music](#Music)
- [MusicList](#MusicList)
- [BackgroundPicture](#BackgroundPicture)

## Attention
__1. http头部需要携带token:""__ <br>
__2. 未登录的操作全部返回如下形式，带有*为需登录才能操作的接口:__
```json
{
    "message":"无token，请重新登录",
    "code":401,
    "data":null
}
```
<details>
<summary>测试</summary>
<pre><code>
System.ouy.ptinyln();
System.ouy.ptinyln();
</code></pre>
</details>

## User
|说明|url|请求方式|example|successResult|400
|---|---|---|---|---|---|
|登录|/user/login|post|{<br>"userPassword": "demoData",<br>"userName": "demoData"<br>}|{<br>"message":"success",<br>"code":200,<br>"data":null<br>}|{<br>"message":"请输入用户名或密码",<br>"code":400,<br>"data":null<br>}<br>{<br>"message":"用户名或者密码错误",<br>"code":400,<br>"data":null<br>}
|注册|/user/register|post|{<br/>"userPassword": "demoData",<br/>"userName": "demoData",<br>"userEmail": "demoData"<br>}|{<br>"message":"success",<br>"code":201,<br>"data":null<br>}|{<br>"message":"用户名或者邮箱已存在",<br> "code":400,<br>"data":null<br>}
|找回密码<br>输入正确的用户名和邮箱就能重设密码|/user/findPassword|put|{<br>"userPassword": "newPassword",<br>"userName": "demoData",<br>"userEmail": "demoData"<br>}|{<br>"message":"success",<br>"code":200,<br>"data":null<br>}|{<br>"message":"请输入用户名或密码",<br>"code":400,<br>"data":null<br>}<br>对应的用户名加上邮箱查不到：<br>{<br>"message":"用户名或者邮箱输入错误",<br>"code":400,<br>"data":null<br>}
|修改个人信息|/user/modify|put|{<br>"userPassword": "newPassword",<br>"userName": "demoData",<br>"userEmail": "demoData"<br>}|{<br>"message":"success",<br>"code":201,<br>"data":null<br>}|{<br>"message":"用户名或者邮箱已存在",<br> "code":400,<br>"data":null<br>}

## Music
|说明|url|请求方式|example|results|400|
|---|---|---|---|---|---|
|根据musicId得到一个music的信息|/music/{musicId}|get|/music/123|{<br>&nbsp;&nbsp;&nbsp;&nbsp;"message":"success",<br>&nbsp;&nbsp;&nbsp;&nbsp;"code":200,<br>&nbsp;&nbsp;&nbsp;&nbsp;"data":<br>&nbsp;&nbsp;&nbsp;&nbsp;{<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"musicId":1,<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"userId":1,<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"musicName":"1",<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"musicSinger":"1",<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"musicTime":1,<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"musicLyrics":null,<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"musicPath":"1",<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"gmtCreated":null,<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"isDeleted":0<br>&nbsp;&nbsp;&nbsp;&nbsp;}<br><br>}<br>|{<br>"message":"no such id of music",<br>"code":400,<br>"data":null<br>}
|*根据用户信息得到该用户所有歌曲|/music|get|可选：type:music_name,music_singer（type默认为music_name）。pageNum（默认为1）。pageSize（默认为10）<br>样例：<br>music?musicName=1|<details><summary>json太长，已折叠</summary><pre><code>
{
                                                                                                                                                                                               "message":"success",
                                                                                                                                                                                               "code":200,
                                                                                                                                                                                               "data":
                                                                                                                                                                                               {
                                                                                                                                                                                                   "total":5,
                                                                                                                                                                                                   "list":
                                                                                                                                                                                                   [
                                                                                                                                                                                                       {
                                                                                                                                                                                                           "musicId":2,
                                                                                                                                                                                                           "userId":1,
                                                                                                                                                                                                           "musicName":"2",
                                                                                                                                                                                                           "musicSinger":"zhangsan",
                                                                                                                                                                                                           "musicTime":90,
                                                                                                                                                                                                           "musicLyrics":null,
                                                                                                                                                                                                           "musicPath":"www.baidu.com",
                                                                                                                                                                                                           "gmtCreated":null,
                                                                                                                                                                                                           "isDeleted":0
                                                                                                                                                                                                       },
                                                                                                                                                                                                       {
                                                                                                                                                                                                           "musicId":3,
                                                                                                                                                                                                           "userId":1,
                                                                                                                                                                                                           "musicName":"3",
                                                                                                                                                                                                           "musicSinger":"unknown3",
                                                                                                                                                                                                           "musicTime":null,
                                                                                                                                                                                                           "musicLyrics":null,
                                                                                                                                                                                                           "musicPath":"3",
                                                                                                                                                                                                           "gmtCreated":null,
                                                                                                                                                                                                           "isDeleted":0
                                                                                                                                                                                                       },
                                                                                                                                                                                                       {
                                                                                                                                                                                                           "musicId":4,
                                                                                                                                                                                                           "userId":1,
                                                                                                                                                                                                           "musicName":"4",
                                                                                                                                                                                                           "musicSinger":"unknown4",
                                                                                                                                                                                                           "musicTime":null,
                                                                                                                                                                                                           "musicLyrics":null,
                                                                                                                                                                                                           "musicPath":"4",
                                                                                                                                                                                                           "gmtCreated":null,
                                                                                                                                                                                                           "isDeleted":0
                                                                                                                                                                                                       },
                                                                                                                                                                                                       {
                                                                                                                                                                                                           "musicId":5,
                                                                                                                                                                                                           "userId":1,
                                                                                                                                                                                                           "musicName":"5",
                                                                                                                                                                                                           "musicSinger":"unknown5",
                                                                                                                                                                                                           "musicTime":null,
                                                                                                                                                                                                           "musicLyrics":null,
                                                                                                                                                                                                           "musicPath":"5",
                                                                                                                                                                                                           "gmtCreated":null,
                                                                                                                                                                                                           "isDeleted":0
                                                                                                                                                                                                       },
                                                                                                                                                                                                       {
                                                                                                                                                                                                           "musicId":6,
                                                                                                                                                                                                           "userId":1,
                                                                                                                                                                                                           "musicName":"demoData",
                                                                                                                                                                                                           "musicSinger":"demoData",
                                                                                                                                                                                                           "musicTime":null,
                                                                                                                                                                                                           "musicLyrics":null,
                                                                                                                                                                                                           "musicPath":"http://www.wanyiyun.com/111",
                                                                                                                                                                                                           "gmtCreated":"2020-11-18",
                                                                                                                                                                                                           "isDeleted":0
                                                                                                                                                                                                       }
                                                                                                                                                                                           
                                                                                                                                                                                                   ],
                                                                                                                                                                                                   "pageNum":1,
                                                                                                                                                                                                   "pageSize":5,
                                                                                                                                                                                                   "size":5,
                                                                                                                                                                                                   "startRow":0,
                                                                                                                                                                                                   "endRow":4,
                                                                                                                                                                                                   "pages":1,
                                                                                                                                                                                                   "prePage":0,
                                                                                                                                                                                                   "nextPage":0,
                                                                                                                                                                                                   "isFirstPage":true,
                                                                                                                                                                                                   "isLastPage":true,
                                                                                                                                                                                                   "hasPreviousPage":false,
                                                                                                                                                                                                   "hasNextPage":false,
                                                                                                                                                                                                   "navigatePages":8,
                                                                                                                                                                                                   "navigatepageNums":
                                                                                                                                                                                                   [
                                                                                                                                                                                                       1
                                                                                                                                                                                                   ],
                                                                                                                                                                                                   "navigateFirstPage":1,
                                                                                                                                                                                                   "navigateLastPage":1
                                                                                                                                                                                               }
                                                                                                                                                                                           
                                                                                                                                                                                           }
                                                                                                                                                                                           </code></pre></details>
|搜索符合条件的music|/music/search|get|可选：musicName,musicSinger<br>样例：/music/search?musicName=1|{<br>&nbsp;&nbsp;&nbsp;&nbsp;"message":"success",<br>&nbsp;&nbsp;&nbsp;&nbsp;"code":200,<br>&nbsp;&nbsp;&nbsp;&nbsp;"data":<br>&nbsp;&nbsp;&nbsp;&nbsp;[<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;{<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"musicId":1,<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"userId":1,<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"musicName":"1",<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"musicSinger":"1",<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"musicTime":1,<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"musicLyrics":null,<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"musicPath":"1",<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"gmtCreated":null,<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"isDeleted":0<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;}<br><br>&nbsp;&nbsp;&nbsp;&nbsp;]<br><br>}<br>
|*上传本地音乐|/music/upload|post|todo|{<br>&nbsp;&nbsp;&nbsp;&nbsp;"message":"success",<br>&nbsp;&nbsp;&nbsp;&nbsp;"code":201,<br>&nbsp;&nbsp;&nbsp;&nbsp;"data":null<br>}<br>
|*添加音乐链接（必须是能直接搜索并播放出音乐的）|/music/uploadByUrl|post|样例：{<br>&nbsp;&nbsp;&nbsp;&nbsp;"musicName":&nbsp;"demoData",<br>&nbsp;&nbsp;&nbsp;&nbsp;"musicSinger":&nbsp;"demoData",<br>&nbsp;&nbsp;&nbsp;&nbsp;"musicPath":&nbsp;"http://www.wanyiyun.com/111"<br>}<br>|{<br>&nbsp;&nbsp;&nbsp;&nbsp;"message":"success",<br>&nbsp;&nbsp;&nbsp;&nbsp;"code":201,<br>&nbsp;&nbsp;&nbsp;&nbsp;"data":null<br>}<br>
|修改music信息|/music/update|put|参数二选一{<br>"musicName": "demoData",<br>"musicSinger": "demoData"<br>}<br>样例:{<br>&nbsp;&nbsp;&nbsp;&nbsp;"musicName":&nbsp;"demoData2",<br>&nbsp;&nbsp;&nbsp;&nbsp;"musicId":&nbsp;1<br>}<br>|{<br>&nbsp;&nbsp;&nbsp;&nbsp;"message":"success",<br>&nbsp;&nbsp;&nbsp;&nbsp;"code":201,<br>&nbsp;&nbsp;&nbsp;&nbsp;"data":null<br>}<br>
|删除music|/music/delete/{musicId}|delete|/music/delete/123|{<br>&nbsp;&nbsp;&nbsp;&nbsp;"message":"success",<br>&nbsp;&nbsp;&nbsp;&nbsp;"code":200,<br>&nbsp;&nbsp;&nbsp;&nbsp;"data":null<br>}<br>

## MusicList
>{userId}准备用token代替

|说明|url|请求方式|example|results
|---|---|---|---|---|
|得到用户的所有歌单|/list/{userId}|get|todo|todo|
|得到歌单的具体歌曲|/list/getDetails|get|params:<br>musicListId,歌单id，必传<br>type,排序方式,默认主键，不必须传参|todo
|添加歌单|/list/add|post|{<br>"musicListName": "demoData"<br>}|{<br>"message":"success",<br>"code":200,<br>"data":null<br>}
|修改歌单|/list/update|post|{<br>"musicListName": "demoData",<br>"musicListId": 123<br>}|{<br>"message":"success",<br>"code":200,<br>"data":null<br>}
|添加音乐进歌单|/list/addMusic|post|{<br>"musicId":123,<br>"musicListId":666}|{<br>"message":"success",<br>"code":200,<br>"data":null<br>}
|删除歌单的某个音乐|/list/deleteMusic|delete|{<br>"musicId":123,<br>"musicListId":666<br>}|{<br>"message":"success",<br>"code":200,<br>"data":null<br>}
|删除整个歌单|/list/delete/{musicListId}|delete|/list/delete/123|{<br>"message":"success",<br>"code":200,<br>"data":null<br>}

## BackgroundPicture
>{userId}用token代替

|说明|url|请求方式|example|results
|---|---|---|---|---|
|通过用户信息获得他的主页图片|/backgroundPicture/{userId}|get|123|todo
|上传自己的主页图片|/backgroundPicture/upload|post|MultipartFile|{<br>"message":"success",<br>"code":200,<br>"data":null<br>}
|删除自己的主页图片|/backgroundPicture/delete/{backgroundPictureId}|delete|123|{<br>"message":"success",<br>"code":200,<br>"data":null<br>}