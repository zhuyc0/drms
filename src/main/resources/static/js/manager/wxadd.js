layui.use(['form','jquery'],function () {
    let form = layui.form,
        $ = layui.jquery;

    let add = {
        init:()=>{
            try {
                add.initPage();
            }catch (e) {
                console.log("初始化出错:",e);
            }
            add.initFormEvent();
        },
        initFormEvent:()=>{
            form.on('submit(addform)',(data)=>{
                $.post("/repairEntity/repair",JSON.stringify(data.field),(res)=>{
                    if (res.code===0){
                        layer.msg(res.msg,{icon:6,time:1500},()=>{
                            window.history.back();
                        });
                    }else{
                        layer.msg(res.msg,{icon:5,time:1500});
                    }
                });
                return false;
            });
        },
        initPage: () => {
            let common = JSON.parse(sessionStorage.getItem("manager_index_common"));
            let room = JSON.parse(sessionStorage.getItem("manager_index_room"));
            let html = '<option value="'+common.campusId+'">'+common.campusName+'</option>';
            $("select[name='campusId']").html(html);
            $("input[name='campusName']").val(common.campusName);
            html = '<option value="'+common.floorId+'">'+common.floorName+'</option>';
            $("select[name='floorId']").html(html);
            $("input[name='floorName']").val(common.floorName);
            html = '<option value="'+room.id+'">'+room.roomName+'</option>';
            $("select[name='roomId']").html(html);
            $("input[name='roomName']").val(room.roomName);
            form.render();
        }
    };

    $(function () {
        add.init();
    });
});
