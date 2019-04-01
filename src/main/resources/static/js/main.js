layui.use(["upload","layer","jquery"],()=>{
    let upload = layui.upload,
        layer = layui.layer,
        $ = layui.jquery;

    $("#btn_jwc").on("click",(e)=>{
        layer.msg("教务处接口不可用",{icon:7,time:1500});
        return false;
    });

    //指定允许上传的文件类型
    upload.render({
        elem: '#test3'
        ,url: '/studentEntity/importexcel'
        ,accept: 'file' //普通文件
        ,exts:'xls|xlsx'
        ,size:20480
        ,acceptMime:'.xls,.xlsx'
        ,done: function(res){
            let type = res.code===0?6:5;
            layer.msg(res.msg,{icon:type});
        }
    });
});
