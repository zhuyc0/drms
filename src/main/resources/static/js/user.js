layui.use(['form','layer','upload','laydate'],function(){
	let layer = parent.layer === undefined ? layui.layer : parent.layer,
		$ = layui.jquery,
        form = layui.form,
        infoForm = {
            id:-1,
            username:'',
            role:'',
            name:'',
            floorName:''
        };
	    $("#username").val(sessionStorage.getItem("username"));

	    if ($("input[name='id']").val()==='-1'){
	        $.get("/userEntity/myslef",(res)=>{
	            if (res.code === 0){
	                const user = res.data.user;
	                const floor = res.data.floor;
                    Object.keys(infoForm).forEach((key)=>{
                        infoForm[key] = user[key]||floor[key]
                    });
                    form.val("formtest",infoForm);
                    form.render();
                }
            });
        }
        //添加验证规则
        form.verify({
            pwd : function(value, item){
                if(value.length < 5){
                    return "密码长度不能小于6位";
                }
            },
            repwd : function(value, item){
                if(!new RegExp($("#oldPwd").val()).test(value)){
                    return "两次输入密码不一致，请重新输入！";
                }
            }
        });


        //提交个人资料
        form.on("submit(changeUser)",function(data){
            if (data.field.name === infoForm.name){
                layer.msg("没有任何修改！",{icon:7,time:1000});
                return false;
            }
            $.ajax({
                type:"PUT",
                url:"/userEntity/putinfo",
                data:JSON.stringify({
                    id: data.field.id,
                    name:data.field.name
                }),
                success:(res)=>{
                    console.log(res);
                    if (res.code===0){
                        layer.msg(res.msg,{
                            icon:6,
                            time:1500
                        },()=>{
                            location.reload();
                        });
                    }else {layer.msg(res.msg,{icon:5,time:1500});}
                }
            });
        	return false; //阻止表单跳转。如果需要表单跳转，去掉这段即可。
        });

        //修改密码
        form.on("submit(changePwd)",function(data){
        	$.post("/userEntity/changepwd",data.field,(res)=>{
                if (res.code===0){
                    layer.msg(res.msg,{
                        icon:6,
                        time:1500
                    },()=>{
                        try {
                            top.$logout();
                        }catch (e) {
                            console.log(e);
                        }
                    });
                }else {layer.msg(res.msg,{icon:5,time:1500});}
            });
            //阻止表单跳转。如果需要表单跳转，去掉这段即可。
        	return false;
        });

});
