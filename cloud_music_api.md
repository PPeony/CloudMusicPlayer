# api
- [User](#User)
- [Music](#Music)
- [MusicList](#MusicList)
- [Background](#BackgroundPicture)
## User
|说明|url|请求方式|example|result|
|---|---|---|---|---|
|注册|/user/login|post|{"userPassword": "demoData","userName": "demoData"}|{"message":"success""code":200,"data":null}
|登录|/user/register|post|{<br/>"userPassword": "demoData",<br/>"userName": "demoData",<br>"userEmail": "demoData"<br>}|{<br>"message":"success",<br>"code":200,<br>"data":null<br>}
|找回密码<br>输入正确的用户名和邮箱就能重设密码|/user/findPassword|post|{<br>"userPassword": "newPassword",<br>"userName": "demoData",<br>"userEmail": "demoData"<br>}|{<br>"message":"success",<br>"code":200,<br>"data":null<br>}
|修改个人信息|/user/modify|put|{<br>"userPassword": "newPassword",<br>"userName": "demoData",<br>"userEmail": "demoData"<br>}|{<br>"message":"success",<br>"code":201,<br>"data":null<br>}

## Music
|说明|url|请求方式|example|results
|---|---|---|---|---|
|根据id得到music表的信息|/music/{musicId}|get|/music/123|todo
|根据用户信息得到该用户所有歌曲|/music|get|/music|todo
|搜索符合条件的music|/music/search|get|可选：musicName,musicSinger<br>必选：pageNum,pageSize|todo
|上传本地音乐|/music/upload|post|todo|{<br>"message":"success",<br>"code":200,<br>"data":null<br>}
|添加音乐链接（必须是能直接搜索并播放出音乐的）|/music/uploadByUrl|post|{<br>"url":"demoData"<br>}|{<br>"message":"success",<br>"code":200,<br>"data":null<br>}
|修改music信息|/music/update|put|参数二选一{<br>"musicName": "demoData",<br>"musicSinger": "demoData"<br>}|{<br>"message":"success",<br>"code":201,<br>"data":null<br>}
|删除music|/music/delete/{musicId}|delete|/music/delete/123|{<br>"message":"success",<br>"code":200,<br>"data":null<br>}

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

## backgroundPicture
>{userId}用token代替

|说明|url|请求方式|example|results
|---|---|---|---|---|
|通过用户信息获得他的主页图片|/backgroundPicture/{userId}|get|123|todo
|上传自己的主页图片|/backgroundPicture/upload|post|MultipartFile|{<br>"message":"success",<br>"code":200,<br>"data":null<br>}
|删除自己的主页图片|/backgroundPicture/delete/{backgroundPictureId}|delete|123|{<br>"message":"success",<br>"code":200,<br>"data":null<br>}