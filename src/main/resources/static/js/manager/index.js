layui.use(['table', 'form','jquery'], function () {
    let table = layui.table,
        form = layui.form,
        $ = layui.$;

    let index = {
        init:()=>{
            index.initQuery();
            index.initFormEvent();
            index.initTableEvent();
        },
        initQuery:()=>{
            /*let info = JSON.parse(sessionStorage.getItem("manager_index_common"));
            if (info){
                campusInnerHtml(info);
                return false;
            }*/
            $.get("/manager/getfloorandroominfo",(res=>{
                if (res.code ===0){
                    let data = res.data;
                    sessionStorage.setItem("manager_index_common",JSON.stringify(data));
                   campusInnerHtml(data);
                }
            }));
            function campusInnerHtml(data) {
                let html = '<option value="'+data.campusId+'" checked>'+data.campusName+'</option>';
                $("#campusId").html(html);
                html = '<option value="'+data.floorId+'" checked>'+data.floorName+'</option>';
                $("#floorId").html(html);
                form.render();
                index.initTable();
            }
        },
        initFormEvent:()=>{
            //监听提交
            form.on('submit(queryform)',(data)=> {
                table.reload('datatable', {
                    where: data.field
                });
                return false;
            });
        },
        initTableEvent:()=>{
            //操作栏
            table.on('tool(datatable)', function(obj){ //注：tool是工具条事件名，test是table原始容器的属性 lay-filter="对应的值"
                const data = obj.data; //获得当前行数据
                const layEvent = obj.event; //获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）
                sessionStorage.setItem("manager_index_room",JSON.stringify(data));
                if(layEvent === 'stu_edit'){
                    location.href = "/my_manager/student.html";
                }else if (layEvent ==="wj_add"){
                    location.href = "/my_manager/wjadd.html";
                }else if (layEvent ==="wj_show"){
                    location.href = "/my_manager/wjshow.html";
                }else if (layEvent ==="wx_add"){
                    location.href = "/my_manager/wxadd.html";
                }else if (layEvent ==="wx_show"){
                    location.href = "/my_manager/wxshow.html";
                }
            });

        },
        initTable:()=>{
            table.render({
                elem: '#datatable'
                , height: 'full-200'
                , url: '/dormitoryRoomEntity/rooms' //数据接口
                , method: 'get'
                , where:{"floorId":$("#campusId").val(),"campusId":$("#floorId").val()}
                , page: true //开启分页
                , cols: [[ //表头
                    {title: "选择", type: "checkbox"},
                    {field: 'id', hide: true}
                    , {field: 'roomName', title: '宿舍名'}
                    , {field: 'open', title: '开放状态',templet: (d)=>{
                        return d.open?"开放中":"以关闭";
                        }}
                    , {title: '学生信息', align: 'center', width: 300, toolbar: "#barDemo1"}
                    , {title: '违纪操作', align: 'center', width: 300, toolbar: "#barDemo2"}
                    , {title: '维修操作', align: 'center', width: 300, toolbar: "#barDemo3"}
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
    };

    $(function () {
        index.init();
    });
});
