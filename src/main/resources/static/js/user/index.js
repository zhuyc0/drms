layui.use(['table', 'form', 'jquery'], function () {
    let table = layui.table,
        form = layui.form,
        $ = layui.$;
    const url = "/userEntity";

    let user = {
        campus: [],
        floors: [],
        index: null,
        init: () => {
            user.campus = getCampus();
            user.floors = getFloors();
            user.initTable();
            user.initFromEvent();
            user.initTableEvent();
            user.initEvent();
            user.initSelect(user.campus, "campus");
            user.initSelect(user.floors);
        },
        initTable: () => {
            //数据表格实例
            table.render({
                elem: '#datatable'
                , height: 'full-200'
                , url: url + '/users' //数据接口
                , method: 'get'
                , page: true //开启分页
                , cols: [[ //表头
                    {title: "选择", type: "checkbox"},
                    {field: 'id', hide: true}
                    , {field: 'name', title: '名称', edit: true}
                    , {field: 'username', title: '登陆名'}
                    , {
                        field: 'role', title: '类型', templet: (d) => {
                            return d.role === "sys" ? "系统管理员" : "管理员";
                        }
                    }
                    , {
                        field: 'campusId', title: '校区', templet: (d) => {
                            let arr = user.campus.filter(item => item.id === d.campusId);
                            try {
                                return arr[0].campus
                            }catch (e) {
                                return "";
                            }
                        }
                    }
                    , {
                        field: 'floorId', title: '宿舍楼', templet: (d) => {
                            let arr = user.floors.filter(item => item.id === d.floorId);
                            try {
                                return arr[0].floorName;
                            }catch (e) {
                                return "";
                            }
                        }
                    }
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
        initFromEvent: () => {
            //监听提交
            form.on('submit(queryform)', (data) => {
                table.reload('datatable', {
                    where: data.field
                });
                return false;
            });

            form.on('submit(editfrom)', (data) => {
                user.postOrPutAction(data.field, "put");
                return false;
            });

            form.on('select(editselect)', function (data) {
                const id = parseInt(data.value); //得到被选中的值
                let arr = user.floors.filter(item => item.campusId === id);
                user.initEditSelect(arr);
                return false;
            });

            form.on('select(queryselect)', function(data){
                const id = parseInt(data.value); //得到被选中的值
                let arr = [];
                if (id===-1){
                    arr = user.floors;
                } else{
                    arr = user.floors.filter(item=>item.campusId===id);
                }
                user.initSelect(arr);
                return false;
            });
        },
        initTableEvent: () => {
            //操作栏
            table.on('tool(datatable)', function (obj) { //注：tool是工具条事件名，test是table原始容器的属性 lay-filter="对应的值"
                const data = obj.data; //获得当前行数据
                const layEvent = obj.event; //获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）

                if (layEvent === 'del') { //删除
                    layer.confirm('真的删除行么', (index) => {
                        let ids = [];
                        ids.push(data.id);
                        user.delAction(ids);
                        layer.close(index);
                    });
                } else {
                    if (layEvent === "edit") {
                        $("#editmodel select[name='campusId']").val(data.campusId);
                        $("#editmodel select[name='role']").val(data.role);
                        let arr = user.floors.filter(item => item.campusId === data.campusId);
                        user.initEditSelect(arr);
                        $("#editmodel select[name='floorId']").val(data.floorId);
                        $("#editmodel input[name='id']").val(data.id);
                        form.render();
                        user.index = layer.open({
                            type: 1,
                            title: "添加校区",
                            content: $("#editmodel"),
                            area: ['400px', '300px'],
                            closeBtn: 2
                        });
                    }
                }
            });

            //单元格编辑
            table.on('edit(datatable)', function (obj) { //注：edit是固定事件名，test是table原始容器的属性 lay-filter="对应的值"
                let data = {};
                data[obj.field] = obj.value;
                data.id = obj.data.id;
                user.postOrPutAction(data, "put");
            });
        },
        initEvent: () => {
            $("#adduser").on("click", (e) => {
                location.href = "/user/add.html"
            });

            $("#batchdel").on("click", (e) => {
                let checkStatus = table.checkStatus('datatable');
                if (checkStatus.data.length > 0) {
                    layer.confirm('真的删除行么',(index)=>{
                        let list = checkStatus.data;
                        let ids = [];
                        $.each(list, function (idx, obj) {
                            ids.push(obj.id);
                        });
                        user.delAction(ids);
                        layer.close(index);
                    });
                } else {
                    layer.msg("没有选中任何数据!", {icon: 7, time: 1500});
                }
            });
        },
        initSelect: (arr, type = "floor") => {
            let html = '';
            if (type === "campus") {
                html = '<option value="-1">请选择一个校区</option>';
                arr.forEach(item => html += '<option value="' + item.id + '">' + item.campus + '</option>');
                $("#campusId").html(html);
                $("#editmodel select[name='campusId']").html(html);
            } else {
                html = '<option value="-1">请选择一个楼栋</option>';
                arr.forEach(item => html += '<option value="' + item.id + '">' + item.floorName + '</option>');
                $("#floorId").html(html);
            }
            form.render();
        },
        initEditSelect: (arr) => {
            let html = '<option value="-1">请选择一个楼栋</option>';
            arr.forEach(item => html += '<option value="' + item.id + '">' + item.floorName + '</option>');
            $("#editmodel select[name='floorId']").html(html);
            form.render();
        },
        delAction: (id) => {
            $.ajax({
                type: "delete",
                url: url + "/user",
                data: JSON.stringify(id),
                success: (res) => {
                    drmsMsg(res);
                    table.reload("datatable");
                }
            });
        },
        postOrPutAction: (data, type = "post", way = "msg", obj) => {
            $.ajax({
                type: type,
                url: url + "/user",
                data: JSON.stringify(data),
                success: (res) => {
                    if (res.code === 0) {
                        try {
                            layer.close(user.index);
                        } catch (e) {
                            console.log(e);
                        }
                    }
                    if (way === "tip") {
                        drmsTips(res, obj);
                    } else {
                        drmsMsg(res);
                    }
                    table.reload("datatable");
                }
            });
        }
    };


    $(function () {
        user.init();
    })
});
