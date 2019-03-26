layui.use(['layer', 'form', 'element', 'jquery'], function() {
    let layer = layui.layer,
        form = layui.form,
        $ = layui.$;

    //提交
    form.on('submit(LAY-user-login-submit)', function(data) {
        $.ajax({
            type:"post",
            url:"/login",
            contentType:"application/x-www-form-urlencoded",
            data:data.field,
            success:(result)=>{
                if(result.code === 0){
                    layer.msg(result.msg,{
                        icon: 6,
                        time: 1500
                    },()=>{parent.window.location="/index.html"});
                }else {
                    layer.msg(result.msg,{icon:5,time:1000});
                }
            }
        });
        return false;
    });
    //表单验证
    form.verify({
        username: function(value, item){ //value：表单的值、item：表单的DOM对象
            if(!new RegExp("^[a-zA-Z0-9_\u4e00-\u9fa5\\s·]+$").test(value)){
                return '用户名不能有特殊字符';
            }
            if(/(^\_)|(\__)|(\_+$)/.test(value)){
                return '用户名首尾不能出现下划线\'_\'';
            }
            if(/^\d+\d+\d$/.test(value)){
                return '用户名不能全为数字';
            }
        },
        password: [
            /^[\S]{5,12}$/
            ,'密码必须5到12位，且不能出现空格'
        ]
    });

});
