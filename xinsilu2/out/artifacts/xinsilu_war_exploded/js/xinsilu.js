
function validate_enroll_exist() {

    var str1 = $('.u_id:eq(0)').val()+","+$('.u_name:eq(0)').val()+","+$('.u_dept:eq(0) option:selected').val();
    var str2 = $('.u_id:eq(1)').val()+","+$('.u_name:eq(1)').val()+","+$('.u_dept:eq(1) option:selected').val();
    var str3 = $('.u_id:eq(2)').val()+","+$('.u_name:eq(2)').val()+","+$('.u_dept:eq(2) option:selected').val();
    var str4 = $('.u_id:eq(3)').val()+","+$('.u_name:eq(3)').val()+","+$('.u_dept:eq(3) option:selected').val();


    // var str1 = $("#u_id1").val()+","+$("#u_name1").val()+","+$("#u_dept1").val();
    // var str2 = $("#u_id2").val()+","+$("#u_name2").val()+","+$('.u_dept2 option:selected').val();
    // var str3 = $("#u_id3").val()+","+$("#u_name3").val()+","+$('.u_dept3 option:selected').val();
    // var str4 = $("#u_id4").val()+","+$("#u_name4").val()+","+$('.u_dept4 option:selected').val();

    var str = str1 + ";" +str2 + ";" +str3 + ";" +str4;
    $('.u_teamMember').val(str);

    //上方内容为teamMember表单项内容拼接
    var uploadFilePath = $("#uploadFile").val();

    if ("" == uploadFilePath) {
        $("#error_msg").text("请上传附件!");
        $("#error_msg").show();
        return false;
    }
    /*var deptValue = $("#deptId").val();
    alert(deptValue)
    if(deptValue == "无"){
        $("#error_msg").text("院系不能为空！");
        $("#error_msg").show();
        return false;
    }*/
    return true;
}

function disableButtons() {
    // 获取保存按钮和提交按钮的引用
    var saveButton = document.getElementById("saveId");
    var submitButton = document.getElementById("submitId");

    // 禁用按钮
    if (saveButton) {
        saveButton.disabled = true;
    }

    if (submitButton) {
        submitButton.disabled = true;
    }
}

// 调用这个函数以在提交后禁用按钮
// 你可以将这个函数与表单的onsubmit事件关联，以在提交时调用它

function  mySubmit(){
    if(validate_enroll()){
        disableButtons();
        return true;
    }
    return false;
}
function validate_paperEnroll_exist() {

    var str1 = $('.u_id:eq(0)').val()+","+$('.u_name:eq(0)').val()+","+$('.u_dept:eq(0) option:selected').val()+","+$('.u_author:eq(0) option:selected').val();
    var str2 = $('.u_id:eq(1)').val()+","+$('.u_name:eq(1)').val()+","+$('.u_dept:eq(1) option:selected').val()+","+$('.u_author:eq(1) option:selected').val();
    var str3 = $('.u_id:eq(2)').val()+","+$('.u_name:eq(2)').val()+","+$('.u_dept:eq(2) option:selected').val()+","+$('.u_author:eq(2) option:selected').val();
    var str4 = $('.u_id:eq(3)').val()+","+$('.u_name:eq(3)').val()+","+$('.u_dept:eq(3) option:selected').val()+","+$('.u_author:eq(3) option:selected').val();
    var str5 = $('.u_id:eq(4)').val()+","+$('.u_name:eq(4)').val()+","+$('.u_dept:eq(4) option:selected').val()+","+$('.u_author:eq(4) option:selected').val();

    // var str1 = $("#u_id1").val()+","+$("#u_name1").val()+","+$("#u_dept1").val();
    // var str2 = $("#u_id2").val()+","+$("#u_name2").val()+","+$('.u_dept2 option:selected').val();
    // var str3 = $("#u_id3").val()+","+$("#u_name3").val()+","+$('.u_dept3 option:selected').val();
    // var str4 = $("#u_id4").val()+","+$("#u_name4").val()+","+$('.u_dept4 option:selected').val();

    var str = str1 + ";" +str2 + ";" +str3 + ";" +str4 + ";" +str5;
    $('.u_teamMember').val(str);

    //上方内容为teamMember表单项内容拼接
    var uploadFilePath = $("#uploadFile").val();

    if ("" == uploadFilePath) {
        $("#error_msg").text("请上传附件!");
        $("#error_msg").show();
        return false;
    }
    /*var deptValue = $("#deptId").val();
    alert(deptValue)
    if(deptValue == "无"){
        $("#error_msg").text("院系不能为空！");
        $("#error_msg").show();
        return false;
    }*/
    return true;
}

function validate_enroll() {
    var uploadFilePath = $("#uploadFile").val();
    if ("" != uploadFilePath) {
        var fileType = uploadFilePath.substring(uploadFilePath.lastIndexOf(".")).toLowerCase();
        //判断附件类型
        if (".zip" != fileType) {
            $("#file").val("");
            $("#error_msg").text("您上传的附件类型错误,请重新上传!");
            $("#error_msg").show();
            return false;
        }

        var fileSize = document.getElementById("uploadFile").files[0].size / 1024 / 1024;
        // 判断文件大小
        if (fileSize > 2048) {
            $("#file").val("");
            $("#error_msg").text("附件大于2GB,请重新上传!");
            $("#error_msg").show();
            return false;
        }
    }
    return true;
}

function validate_researchScoreForm() {
    var score = $("#score").val();
    if (Math.floor(score) === score || score < 0 || score > 100) {
        $("#score").val("");
        alert("您输入的分数为:" + score + ", 但是评分范围为[0-100]的整数");
        return false;
    }
    return true;
}

function validate_projectScoreForm() {
    var score = $("#score").val();
    if (Math.floor(score) === score || score < 0 || score > 100) {
        $("#score").val("");
        alert("总分为:" + score + ", 但是评分范围为[0-100]的整数");
        return false;
    }
    return true;

}

function validate_judgeScoreForm() {
    var comment = $("#comment").val();
    var scores = "";
    for (i = 1; i < 5; i++) {
        id = "#value" + i;
        scores = scores + "," + $(id).val();
    }
    $("#comment").val(scores + ";" + comment);
}


function isDelete() {
    var msg = "您确定要删除吗？";
    if (confirm(msg) == true) {
        return true;
    } else {
        return false;
    }
}

function initFileInput(ctrlName) {
    var control = $('#' + ctrlName);
    control.fileinput({
        language: 'zh', //设置语言
        uploadUrl: "", //上传的地址
        // allowedFileExtensions: ['zip', 'pdf','rar'],//接收的文件后缀
        maxFilesNum: 1,//上传最大的文件数量
        uploadAsync: false, //默认异步上传
        showUpload: false, //是否显示上传按钮
        showRemove: true, //显示移除按钮
        showCaption: false,//是否显示标题
//            showPreview: false,
        browseClass: "btn btn-primary", //按钮样式
        maxFileSize: 2048000,//单位为kb，如果为0表示不限制文件大小
        //minFileCount: 0,
        //maxFileCount: 10, //表示允许同时上传的最大文件个数
        dropZoneEnabled: false, //是否显示拖拽区域
        enctype: 'multipart/form-data',
        validateInitialCount: true,
//            previewFileIcon: "<i class='glyphicon glyphicon-king'></i>",
        msgFilesTooMany: "选择上传的文件数量({n}) 超过允许的最大数值{m}！",

    }).on('filepreupload', function (event, data, previewId, index) {     //上传中
        var form = data.form, files = data.files, extra = data.extra,
            response = data.response, reader = data.reader;
        console.log('文件正在上传');
    }).on("fileuploaded", function (event, data, previewId, index) {    //一个文件上传成功
        console.log('文件上传成功！' + data.id);

    }).on('fileerror', function (event, data, msg) {  //一个文件上传失败
        console.log('文件上传失败！' + data.id);
    })
}

function send_ajax(action) {
    var year = $('#yearSelect').val();
    window.location.href = action + "?year=" + year;
}
