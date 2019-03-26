layui.use(['table','form'], function(){
    let table = layui.table,
        form = layui.form;

    //第一个实例
    table.render({
        elem: '#demo'
        ,height: 'full-200'
        ,url: '' //数据接口
        ,method:'get'
        ,page: true //开启分页
        ,cols: [[ //表头
            {title:"序号",type:"numbers"},
            {field: 'id', hide: true}
            ,{field: 'campus', title: '校区'}
            ,{field: 'createTime', title: '创建时间', sort: true}
            ,{title: '操作', align:'center',width: 300, toolbar: "#barDemo"}
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

});
