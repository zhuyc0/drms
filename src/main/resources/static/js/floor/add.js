layui.use(['form', 'jquery'], function () {
    let form = layui.form,
        $ = layui.jquery;

    let add = {
        campus: [],
        init: () => {
            add.campus = getCampus();
            add.initFormEvent();
            add.initSelect(add.campus);
        },
        initFormEvent: () => {
            form.on('submit(addform)', (data) => {
                $.post("/dormitoryFloorEntity/floor", JSON.stringify(data.field), (res) => {
                    if (res.code === 0) {
                        layer.msg(res.msg, {icon: 6, time: 1500}, () => {
                            window.history.back();
                        });
                    } else {
                        layer.msg(res.msg, {icon: 5, time: 1500});
                    }
                });
                return false;
            });
        },
        initSelect: (arr) => {
            let html = '<option value="">请选择一个校区</option>';
            arr.forEach(item => html += '<option value="' + item.id + '">' + item.campus + '</option>');
            $("#campusId").html(html);
            form.render();
        }
    };

    $(function () {
        add.init();
    });
});
