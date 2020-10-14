$(document).ready(function(){

    var form = $("#loginForm");

	/* 로그인 버튼 클릭 */
    $("#btn_login").click(function(){
    
        var mb_id = $("#mb_id");
        var mb_pw = $("#mb_pw");

        if(mb_id.val()=="" || mb_id.val()==null){
            alert("아이디를 입력하세요.");
            mb_id.focus();
        } else if(mb_pw.val()=="" || mb_pw.val()==null){
            alert("비밀번호를 입력하세요.");
            mb_pw.focus();
        } else{
            form.submit();
        }
    });
});