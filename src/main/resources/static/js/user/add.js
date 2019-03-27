layui.use(['form','jquery'],function () {
    let form = layui.form,
        $ = layui.jquery;

    let add = {
        init:()=>{
            add.initFormEvent();
        },
        initFormEvent:()=>{

        }
    };

    $(function () {
        add.init();
    });
});
