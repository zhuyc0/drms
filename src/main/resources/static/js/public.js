layui.use(["layer"], function () {
    let layer = layui.layer,
        $ = layui.$;
    $.ajaxSetup({
        cache: false,
        timeout: 8000,
        type: "post",
        contentType: "application/json; charset=utf-8",
        dataType: 'json',
        beforeSend: function (xhr) {
            layer.load(1);
        },
        error: function (xhr) {
            let msg = "连接超时";
            try {
                let code = xhr.status;
                if (code == 403) {
                    msg = "权限不足";
                } else if (code == 500) {
                    msg = '服务器错误';
                } else if (code == 404) {
                    msg = '资源不存在';
                } else if (code == 401) {
                    if (xhr.responseJSON.message) {
                        msg = xhr.responseJSON.message;
                    } else {
                        msg = "请先登陆!"
                    }
                }
                layer.msg(msg, {
                    icon: 5,
                    time: 1500
                });
            } catch (e) {
                return false;
            } finally {
                layer.closeAll("loading");
            }
        },
        complete: function () {
            layer.closeAll("loading");
        }
    });
});
