layui.use(['table', 'form','jquery'], function () {
    let table = layui.table,
        form = layui.form,
        $ = layui.$;
    const url = "/repairEntity";

    let wxlist = {
        index:null,
        init: () => {
            wxlist.initTable();
            wxlist.initFromEvent();
            wxlist.initTableEvent();
            wxlist.initEvent();
        },
        initTable: () => {
            //数据表格实例
            table.render({
                elem: '#datatable'
                , height: 'full-200'
                , url: url+'/repairs' //数据接口
                , method: 'get'
                , page: true //开启分页
                , cols: [[ //表头
                    {title: "选择", type: "checkbox"},
                    {field: 'id', hide: true}
                    , {field: 'roomName', title: '宿舍名',width: 100}
                    , {field: 'regName', title: '登记人',width: 150}
                    , {field: 'createTime', title: '时间',templet:(d)=>{
                            return d.createTime.substring(0,10);
                        },width: 150}
                    , {field: 'type', title: '类型',templet:(d)=>{
                            return d.type?"统一排查":"人为损坏";
                        },width: 150}
                    , {field: 'principal', title: '描述',width: 200}
                    , {field: 'report', title: '上报',templet: (d)=>{
                            return d.report?"已上报":"暂存";
                        },width: 100}
                    , {field: 'description', title: '原因',edit:true}
                    , {field: 'report', title: '结案',templet: (d)=>{
                            return d.complete?"是":"否";
                        },width: 100}
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
                        wxlist.delAction(ids);
                        layer.close(index);
                    });
                }else if (layEvent ==="report"){
                       if (data.report){
                           layer.msg("已经上报，无需重复提交",{icon: 7,time: 1500});
                           return false;
                       }
                        let entity = {};
                        entity.id = data.id;
                        entity.report = 1;
                        wxlist.putAction(entity);
               }else if (layEvent ==="end"){
                   if (data.complete){
                       layer.msg("已经结案，无需重复提交",{icon: 7,time: 1500});
                       return false;
                   }
                   let entity = {};
                   entity.id = data.id;
                   entity.complete = 1;
                   wxlist.putAction(entity);
               }

            });

            //单元格编辑
            table.on('edit(datatable)', function(obj){ //注：edit是固定事件名，test是table原始容器的属性 lay-filter="对应的值"
                let data = {};
                data[obj.field] = obj.value;
                data.id = obj.data.id;
                wxlist.putAction(data);
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
                        wxlist.delAction(ids);
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
                url:url+"/repair",
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
                url:url+"/repair",
                data:JSON.stringify(data),
                success:(res)=>{
                    drmsMsg(res);
                    table.reload("datatable");
                }
            });
        }
    };


    $(function () {
        wxlist.init();
    })
});
