layui.use(['form','jquery'],function () {
    let form = layui.form,
        $ = layui.jquery;

    let add = {
        campus: [],
        floors: [],
        init:()=>{
            add.campus = getCampus();
            add.floors = getFloors();
            add.initFormEvent();
            add.initSelect(add.campus, "campus");
        },
        initFormEvent:()=>{
            form.on('submit(addform)',(data)=>{
                $.post("/dormitoryRoomEntity/room",JSON.stringify(data.field),(res)=>{
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

            form.on('select(addselect)', function(data){
                const id = parseInt(data.value); //得到被选中的值
                let arr = add.floors.filter(item=>item.campusId===id);
                add.initSelect(arr);
                return false;
            });
        },
        initSelect: (arr, type = "floor") => {
            let html = '';
            if (type === "campus") {
                html = '<option value="">请选择一个校区</option>';
                arr.forEach(item => html += '<option value="' + item.id + '">' + item.campus + '</option>');
                $("#campusId").html(html);
                $("#editmodel select[name='campusId']").html(html);
            } else {
                html = '<option value="">请选择一个楼栋</option>';
                arr.forEach(item => html += '<option value="' + item.id + '">' + item.floorName + '</option>');
                $("#floorId").html(html);
            }
            form.render();
        }
    };

    $(function () {
        add.init();
    });
});
