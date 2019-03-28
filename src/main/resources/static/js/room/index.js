layui.use(['table', 'form','jquery'], function () {
    let table = layui.table,
        form = layui.form,
        $ = layui.$;
    const url = "/dormitoryRoomEntity";

    let room = {
        campus:[],
        floors:[],
        index:null,
        init: () => {
            room.campus = getCampus();
            room.floors = getFloors();
            room.initTable();
            room.initFromEvent();
            room.initTableEvent();
            room.initEvent();
            room.initSelect(room.campus,"campus");
            room.initSelect(room.floors);
        },
        initTable: () => {
            //数据表格实例
            table.render({
                elem: '#datatable'
                , height: 'full-200'
                , url: url+'/rooms' //数据接口
                , method: 'get'
                , page: true //开启分页
                , cols: [[ //表头
                    {title: "选择", type: "checkbox"},
                    {field: 'id', hide: true}
                    , {field: 'roomName', title: '宿舍名',edit:true}
                    , {field: 'campusId', title: '校区',templet:(d)=>{
                            let arr = room.campus.filter(item=>item.id===d.campusId);
                            return arr[0].campus;
                        }}
                    , {field: 'floorId', title: '宿舍楼',templet: (d)=>{
                            let arr = room.floors.filter(item=>item.id===d.floorId);
                            return arr[0].floorName;
                        }}
                    , {field: 'open', title: '开放状态',templet: '#checkboxTpl'}
                    , {title: '操作', align: 'center', width: 300, toolbar: "#barDemo"}
                ]],
                request: {
                    pageName: 'page',
                    limitName: 'pageSize'
                },
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
        initFromEvent:()=>{
            //监听提交
            form.on('submit(queryform)',(data)=> {
                table.reload('datatable', {
                    where: data.field
                });
                return false;
            });

            form.on('submit(editfrom)',(data)=>{
                room.postOrPutAction(data.field,"put");
                return false;
            });

            //监听锁定操作
            form.on('checkbox(openDemo)', function(obj){
                $(obj.elem).attr("disabled", "disabled");
                let data = {};
                data.id = this.value;
                data[this.name]=obj.elem.checked?0:1;
                room.postOrPutAction(data,"put","tip",obj.othis);
            });

            form.on('select(editselect)', function(data){
                const id = parseInt(data.value); //得到被选中的值
                let arr = room.floors.filter(item=>item.campusId===id);
                room.initEditSelect(arr);
                return false;
            });

            form.on('select(queryselect)', function(data){
                const id = parseInt(data.value); //得到被选中的值
                let arr = [];
                if (id===-1){
                    arr = room.floors;
                } else{
                    arr = room.floors.filter(item=>item.campusId===id);
                }
                room.initSelect(arr);
                return false;
            });
        },
        initTableEvent:()=>{
            //操作栏
            table.on('tool(datatable)', function(obj){ //注：tool是工具条事件名，test是table原始容器的属性 lay-filter="对应的值"
                const data = obj.data; //获得当前行数据
                const layEvent = obj.event; //获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）

               if(layEvent === 'del'){ //删除
                    layer.confirm('真的删除行么',(index)=>{
                        let ids = [];
                        ids.push(data.id);
                        room.delAction(ids);
                        layer.close(index);
                    });
                }else{
                   if (layEvent ==="edit"){
                       $("#editmodel select[name='campusId']").val(data.campusId);
                       let arr = room.floors.filter(item=>item.campusId===data.campusId);
                       room.initEditSelect(arr);
                       $("#editmodel select[name='floorId']").val(data.floorId);
                       $("#editmodel input[name='id']").val(data.id);
                       form.render();
                       room.index = layer.open({
                           type:1,
                           title:"添加校区",
                           content:$("#editmodel"),
                           area: ['400px', '300px'],
                           closeBtn:2
                       });
                   }
               }
            });

            //单元格编辑
            table.on('edit(datatable)', function(obj){ //注：edit是固定事件名，test是table原始容器的属性 lay-filter="对应的值"
                let data = {};
                data[obj.field] = obj.value;
                data.id = obj.data.id;
                room.postOrPutAction(data,"put");
            });
        },
        initEvent:()=>{
            $("#addroom").on("click",(e)=>{
                location.href = "/room/add.html";
            });

            $("#batchdel").on("click",(e)=>{
                let checkStatus = table.checkStatus('datatable');
                if (checkStatus.data.length > 0) {
                    layer.confirm('真的删除行么',(index)=>{
                        let list = checkStatus.data;
                        let ids = [];
                        $.each(list, function (idx, obj) {
                            ids.push(obj.id);
                        });
                        room.delAction(ids);
                        layer.close(index);
                    });
                }else {
                    layer.msg("没有选中任何数据!",{icon:7,time:1500});
                }
            });
        },
        initSelect:(arr,type = "floor")=>{
            let html = '';
            if (type === "campus"){
                html = '<option value="-1">请选择一个校区</option>';
                arr.forEach(item=>html+='<option value="'+item.id+'">'+item.campus+'</option>');
                $("#campusId").html(html);
                $("#editmodel select[name='campusId']").html(html);
            }else{
                html = '<option value="-1">请选择一个楼栋</option>';
                arr.forEach(item=>html+='<option value="'+item.id+'">'+item.floorName+'</option>');
                $("#floorId").html(html);
            }
            form.render();
        },
        initEditSelect:(arr)=>{
            let html = '<option value="-1">请选择一个楼栋</option>';
            arr.forEach(item=>html+='<option value="'+item.id+'">'+item.floorName+'</option>');
            $("#editmodel select[name='floorId']").html(html);
            form.render();
        },
        delAction:(id)=>{
            $.ajax({
                type: "delete",
                url:url+"/room",
                data:JSON.stringify(id),
                success:(res)=>{
                    drmsMsg(res);
                    table.reload("datatable");
                }
            });
        },
        postOrPutAction:(data,type = "post",way = "msg",obj)=>{
            $.ajax({
                type: type,
                url:url+"/room",
                data:JSON.stringify(data),
                success:(res)=>{
                    if (res.code===0){
                        try {
                            layer.close(room.index);
                        }catch (e) {
                            console.log(e);
                        }
                    }
                    if (way === "tip"){
                        drmsTips(res,obj);
                    } else{
                        drmsMsg(res);
                    }
                    table.reload("datatable");
                }
            });
        }
    };


    $(function () {
        room.init();
    })
});
