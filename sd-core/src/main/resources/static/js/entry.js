
$(document).ready(function () {
    var layer;
    layui.use('layer', function(){
        layer = layui.layer;
    });

    $("#resetBt1").click(function () {
        $("#encryptStr").val("");
    });
    $("#resetBt2").click(function () {
        $("#decryptStr").val("");
    });

    $("#encryptBt").click(function () {
        var param = new Object();
        param.plaintext = $("#encryptStr").val();

        if (param.plaintext == null || param.plaintext == ''){
            layer.alert('输入的明文不能为空', {icon: 5});
        }else {
            $.ajax({
                type: "POST",
                data: JSON.stringify(param
                ),
                url: "/public/encryption/encrypt",
                contentType: "application/json; charset=UTF-8",
                dataType: "json",
                success: function (respData) {

                    if (!respData.hasError) {
                        var body = respData.body;
                        if (body.code == '1000') {
                            $("#decryptStr").val(body.data);
                        } else {
                            layer.alert("加密失败:" + body.msg,{icon: 5});
                        }
                    }else {
                        layer.alert("加密失败:" + respData.errorMsg,{icon: 5});
                    }
                }
            });
        }
    });

    $("#decryptBt").click(function () {
        var param = new Object();
        param.plaintext = $("#decryptStr").val();

        if (param.plaintext == null || param.plaintext == ''){
            layer.alert('输入的密文不能为空', {icon: 5});
        }else {
            $.ajax({
                type: "POST",
                data: JSON.stringify(param
                ),
                url: "/public/encryption/decrypt",
                contentType: "application/json; charset=UTF-8",
                dataType: "json",
                success: function (respData) {

                    if (!respData.hasError) {
                        var body = respData.body;
                        if (body.code == '1000') {
                            $("#encryptStr").val(body.data);
                        } else {
                            layer.alert("解密失败" + body.msg,{icon: 5});
                        }
                    }else {
                        layer.alert("解密失败:" + respData.errorMsg,{icon: 5});
                    }
                }
            });
        }

    });
});