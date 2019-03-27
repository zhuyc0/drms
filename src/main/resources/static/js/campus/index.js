layui.use(['table', 'form','jquery','laydate'], function () {
    let table = layui.table,
        laydate = layui.laydate,
        form = layui.form,
        $ = layui.$;

    let campus = {
        index:null,
        init: () => {
            campus.initTable();
            campus.initLaydate();
            campus.initFromEvent();
            campus.initTableEvent();
            campus.initEvent();
        },
        initTable: () => {
            //数据表格实例
            table.render({
                elem: '#datatable'
                , height: 'full-200'
                , url: '/campusEntity/campuses' //数据接口
                , method: 'get'
                , page: true //开启分页
                , cols: [[ //表头
                    {title: "序号", type: "numbers"},
                    {field: 'id', hide: true}
                    , {field: 'campus', title: '校区',edit:true}
                    , {field: 'createTime', title: '创建时间', sort: true,templet:(d)=>{
                        return d.createTime.substring(0,10);
                        }}
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
        initLaydate: ()=>{
            //laydate实例
            laydate.render({
                elem: '#start',
                max: 0
            });
            laydate.render({
                elem: '#end',
                max: 0
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

            form.on('submit(addfrom)',(data)=>{
                campus.postOrPutAction(data.field);
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
                        campus.delAction(data.id);
                        layer.close(index);
                    });
                }
            });

            //单元格编辑
            table.on('edit(datatable)', function(obj){ //注：edit是固定事件名，test是table原始容器的属性 lay-filter="对应的值"
                let data = {};
                data[obj.field] = obj.value;
                data.id = obj.data.id;
                campus.postOrPutAction(data,"put");
            });
        },
        initEvent:()=>{
            $("#addcampus").on("click",(e)=>{
                campus.index = layer.open({
                    type:1,
                    title:"添加校区",
                    content:$("#addmodel"),
                    area: ['400px', '200px'],
                    closeBtn:2
                });
            });
        },
        delAction:(id)=>{
            $.ajax({
                type: "delete",
                url:"/campusEntity/campus",
                data:JSON.stringify(id),
                success:(res)=>{
                    drmsMsg(res);
                    table.reload("datatable");
                }
            });
        },
        postOrPutAction:(data,type = "post")=>{
            $.ajax({
                type: type,
                url:"/campusEntity/campus",
                data:JSON.stringify(data),
                success:(res)=>{
                    if (res.code===0 && type ==="post"){
                        layer.close(campus.index);
                    }
                    drmsMsg(res);
                    table.reload("datatable");
                }
            });
        }
    };


    $(function () {
        campus.init();
    })
});
