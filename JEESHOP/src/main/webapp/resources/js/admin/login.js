$(document).ready(function(){

    $("#btn_login").click(function(){

        var form = $("#adminloginForm");

        var admin_id = $("#admin_id");
        var admin_pw = $("#admin_pw");

        if(admin_id.val()=="" || admin_id.val()==null){
            alert("아이디를 입력하세요.");
            admin_id.focus();

        } else if(admin_pw.val()=="" || admin_pw.val()==null){
            alert("비밀번호를 입력하세요");
            admin_pw.focus();

        } else {
            form.submit();
        }
    });
});