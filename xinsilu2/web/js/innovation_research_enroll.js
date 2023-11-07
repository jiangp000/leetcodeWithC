alert("hello")
let intervalID = 0;
function showProgressBar(data){
    let uploaded = (parseFloat(data.uploaded)/1024/1024).toFixed(2);
    let totalBytes = (parseFloat(data.total)/1024/1024).toFixed(2);
    $("#readBytes").html("已上传文件大小：" + uploaded + " MB");
    $("#totalBytes").html("文件总大小：" + totalBytes + " MB");
    let percent = (uploaded/totalBytes*100).toFixed(2);
    $("#progress").attr("style","width:" + percent + "%; background:forestgreen");
    $("#progress").html(percent + "%");
}
function submitForm(){
    $("#bar_bg").attr("style","border: 1px solid forestgreen")
    intervalID = setInterval(progressBar,500);
}
function progressBar(){
    // console.log("询问进度");
    $.ajax({
        type:"GET",
        url:"loadBar",
        success:function(result){
            // console.log(result);
            if(result.isStarted){
                if(result.completed){
                    clearInterval(intervalID);
                    $("#readBytes").attr("style","display:none");
                    $("#totalBytes").attr("style","display:none");
                    $("#bar_bg").attr("style","display:none");
                    $("#progress").attr("style","display:none");
                    $("#myModal").modal();
                }else{
                    // console.log("已上传:"+result.uploaded);
                    // console.log("总大小:"+result.total);
                    // console.log("上传进度:"+result.percent);
                    // console.log("文件序号:"+result.fileCount);
                    showProgressBar(result);
                }
            }else{
                // console.log("文件还未提交");
            }
        },
        error:function(XMLHttpRequest,textStatus,errorThrown){
            console.log(XMLHttpRequest+"---"+textStatus+"---"+errorThrown);
        }
    });
}

// 获取java service中的文件绝对路径，进行文件路径虚拟映射
// 从file域替换为当前的域，以绕过同源策略限制
function findStrIndex(str,cha,num){
    let x = str.indexOf(cha);
    for(let i=0;i<num;i++){
        x = str.indexOf(cha,x+1);
    }
    return x;
}
// function getUploadedFile(index){
//     let labelID = "uploadFile"+index;
//     let deleteLabelID = "fileDeleted"+index;
//     document.getElementById(deleteLabelID).value = "no";
//     document.getElementById(deleteLabelID).setAttribute("style","display:none");
//     let file_path = "";
//     switch (index){
//         case 0: file_path = "<%= path0%>"; break;
//         case 1: file_path = "<%= path1%>"; break;
//         case 2: file_path = "<%= path2%>"; break;
//         case 3: file_path = "<%= path3%>"; break;
//         case 4: file_path = "<%= path4%>"; break;
//         case 5: file_path = "<%= path5%>"; break;
//         case 6: file_path = "<%= path6%>"; break;
//         case 7: file_path = "<%= path7%>"; break;
//         case 8: file_path = "<%= path8%>"; break;
//         case 9: file_path = "<%= path9%>"; break;
//         case 10: file_path = "<%= path10%>"; break;
//         case 11: file_path = "<%= path11%>"; break;
//         case 12: file_path = "<%= path12%>"; break;
//         case 13: file_path = "<%= path13%>"; break;
//         case 14: file_path = "<%= path14%>"; break;
//         case 15: file_path = "<%= path15%>"; break;
//     }// 对应index找不到文件 直接return
//     if(file_path==="")return;
//     let require_flag = false;
//     if(document.getElementById(labelID).getAttribute("required")=="required"){
//         require_flag = true;
//         document.getElementById(labelID).removeAttribute("required");
//     }
//     // 创建a标签在对应上传窗口旁
//     //let virtual_path = "http://localhost:8080/file/"+file_path.slice(findStrIndex(file_path,"/",1)+1);
//     let virtual_path = file_path;
//     let a = document.createElement("a");
//     a.href=virtual_path;
//     let fileName = file_path.slice(file_path.lastIndexOf("/")+1);
//     a.setAttribute("download",fileName);
//     a.innerText = fileName;
//
//     let textLabel = document.createElement("div");
//     textLabel.innerText = "* 已上传文件:";
//     textLabel.innerHTML = "&nbsp;&nbsp;&nbsp;&nbsp"+textLabel.innerHTML+"&nbsp;&nbsp;";
//     textLabel.setAttribute("style","font-weight:bold");
//     textLabel.append(a);
//     let parentNode = document.getElementById(labelID).parentNode.parentNode.parentNode.parentNode.parentNode;
//
//     let deleteButton = document.createElement("button");
//     deleteButton.setAttribute("type","button");
//     let buttonID = "deleteButton"+index;
//     deleteButton.setAttribute("id",buttonID);
//     deleteButton.value = "删除文件";
//     deleteButton.innerText = "删除文件";
//     deleteButton.setAttribute("class","btn btn-default btn-secondary fileinput-remove fileinput-remove-button");
//     deleteButton.setAttribute("style","display:inline;margin:0 0 5px 10px;");
//     textLabel.append(deleteButton);
//     parentNode.append(textLabel);
//     textLabel.previousElementSibling.setAttribute("style","display:none");
//     if($("#submitStageControl").val()==2&&index<14){
//         deleteButton.setAttribute("style","display:none");
//     }
//
//     deleteButton.onclick=(()=>{
//         if(!confirm("是否删除该文件？")) return;
//         textLabel.setAttribute("style","display:none");
//         textLabel.previousElementSibling.setAttribute("style","display:block");
//         if(require_flag) document.getElementById(labelID).setAttribute("required","required");
//         document.getElementById(deleteLabelID).value = "yes";
//     });
// }
function getUploadedFileOrder(index,indexMap){
    let labelID = "uploadFile"+index;
    let uploadFile = document.getElementById(labelID);
    if(uploadFile.files.length==0){
        return "";
    }
    let fileName = uploadFile.files[0].name;
    let fileType = fileName.substring(fileName.lastIndexOf(".")+1);
    console.log(fileName);
    console.log(fileType);
    indexMap[index] = {"name":fileName,"type":fileType};
}

$(()=>{
    for(let i=0;i<16;i++){
        getUploadedFile(i);
    }
})

function submitValidate(){
    if(!confirm("提交后不可修改，是否确认提交？")){
        return false;
    }
    return myValidate();
}

myValidate = function (){
    alert("成功了！")
    let indexMap = {};
    for(let i=0;i<16;i++){
        getUploadedFileOrder(i,indexMap);
    }
    let fileInfoStr = JSON.stringify(indexMap);
    $("#filesNewName").value = fileInfoStr;
    // document.getElementById("filesNewName").value = fileInfoStr;
    // print(document.getElementById("filesNewName").value)
    return validate_enroll_exist();
}