layui.use(['table', 'form','jquery'], function () {
    let table = layui.table,
        form = layui.form,
        layer = layui.layer,
        $ = layui.$;
    const url = "/studentEntity";
    let stu = {
        roomId:0,
        index:null,
        init:()=>{
            let room = JSON.parse(sessionStorage.getItem("manager_index_room"));
            if (room){
                stu.roomId = room.id;
            }
            stu.initTable();
            stu.initTableEvent();
            stu.initEvent();
        },
        initTable:()=>{
            table.render({
                elem: '#datatable'
                , height: 'full-200'
                , url: url+'/student' //数据接口
                , method: 'get'
                , where:{"roomId":stu.roomId}
                , page: false //开启分页
                , cols: [[ //表头
                    {title: "选择", type: "checkbox"},
                    {field: 'id', hide: true}
                    , {field: 'name', title: '姓名'}
                    , {field: 'clasz', title: '班级'}
                    , {field: 'sex', title: '性别',templet: (d)=>{
                                return d.sex?"男":"女";
                            }}
                    , {field: 'birthday', title: '出生日期',templet: (d)=>{
                            const data = d.birthday;
                            return data?data.substring(0,10):"空记录";
                        }}
                    , {title: '操作', align: 'center', width: 300, toolbar: "#barDemo"}
                ]],
                response: {
                    statusName: 'code',
                    statusCode: 0,
                    msgName: 'msg',
                    countName: 'count',
                    dataName: 'data'
                },
                done: function (res, curr, count) {
                    layer.closeAll('loading');
                }
            });
        },
        initTableEvent:()=>{
            table.on('tool(datatable)', function(obj){ //注：tool是工具条事件名，test是table原始容器的属性 lay-filter="对应的值"
                const data = obj.data; //获得当前行数据
                const layEvent = obj.event; //获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）
                if(layEvent === 'del'){ //删除
                    layer.confirm('真的删除行么',(index)=>{
                        let ids = [];
                        ids.push(data.id);
                        stu.delAction(ids);
                        layer.close(index);
                    });
                }else if (layEvent ==="edit"){
                        stu.getRoomInfo(data.floorId,data.roomId);
                        $("#editmodel input[name='id']").val(data.id);
                        stu.index = layer.open({
                            type:1,
                            title:"转宿舍",
                            content:$("#editmodel"),
                            area: ['400px', '300px'],
                            closeBtn:2
                        });
                }else if (layEvent === "query"){
                    stu.getViolatInfo(data.id);
                    stu.index = layer.open({
                        type:1,
                        title:"违纪情况",
                        content:$("#wjmodel"),
                        area: ['400px', '300px'],
                        closeBtn:2,
                        btn:["确定"]
                    });
                }

            });
            form.on('submit(editfrom)',(data)=>{
                stu.putAction(data.field,"put");
                return false;
            });
        },
        initEvent:()=>{
            $("#batchdel").on("click",(e)=>{
                let checkStatus = table.checkStatus('datatable');
                if (checkStatus.data.length > 0) {
                    layer.confirm('真的删除行么',(index)=>{
                        let list = checkStatus.data;
                        let ids = [];
                        $.each(list, function (idx, obj) {
                            ids.push(obj.id);
                        });
                        stu.delAction(ids);
                        layer.close(index);
                    });
                }else {
                    layer.msg("没有选中任何数据!",{icon:7,time:1500});
                }
            });
        },
        delAction:(id)=>{
            $.ajax({
                type: "delete",
                url:url+"/student",
                data:JSON.stringify(id),
                success:(res)=>{
                    drmsMsg(res);
                    table.reload("datatable");
                }
            });
        },
        putAction:(data)=>{
            $.ajax({
                type: "put",
                url:url+"/student",
                data:JSON.stringify(data),
                success:(res)=>{
                    if (res.code===0){
                        try {
                            layer.close(stu.index);
                        }catch (e) {
                            console.log(e);
                        }
                    }
                    drmsMsg(res);
                    table.reload("datatable");
                }
            });
        },
        getRoomInfo:(floorId,roomId)=>{
            const room = JSON.parse(sessionStorage.getItem("manager_student_rooms"));
            if (room && room.length >0){
                stu.setRoomSelect(room,roomId);
                return false;
            }
            $.get("/dormitoryRoomEntity/getroombyfloorid?floorId="+floorId,(res=>{
                if (res.code===0){
                    stu.setRoomSelect(res.data,roomId);
                    sessionStorage.setItem("manager_student_rooms",JSON.stringify(res.data));
                }
            }));
        },
        setRoomSelect:(rooms,roomId)=>{
            let html = '<option value="">请选择一个宿舍</option>';
            rooms.forEach(x=>html+='<option value="'+x.id+'">'+x.roomName+'</option>');
            $("#roomId").html(html).val(roomId);
            form.render();
        },
        getViolatInfo:(id)=>{
            $.get("/violationEntity/violatsbystuid?sId="+id,res=>{
                if (res.code===0) {
                    let html = "无违纪记录";
                    try {
                        res.data.forEach(x=>{
                            x.createTime
                        });
                    }catch (e) {
                        html = "无违纪记录";
                        console.log(e);
                    }

                    $("#wjmodel").html(html);
                }
            });
        }
    };

    $(function () {
        stu.init();
    });
});
