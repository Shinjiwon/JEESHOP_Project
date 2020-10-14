$(document).ready(function(){

    var form = $("#pwchangeForm");

    var mb_pw = $("#mb_pw");
    var mb_pw_change = $("#mb_pw_change");
    var mb_pw_chk = $("#mb_pw_chk");

    /* 비밀번호 일치여부 확인 */
    $(function(){
        $('#mb_pw_change').keyup(function(){
          $('#mbPwChk_status').html('');
        });
    
        $('#mb_pw_chk').keyup(function(){
    
            if($('#mb_pw_change').val() != $('#mb_pw_chk').val()){
              $('#mbPwChk_status').html('비밀번호 일치하지 않음');
              $('#mbPwChk_status').css('color', 'red');
            } else{
              $('#mbPwChk_status').html('비밀번호 일치함');
              $('#mbPwChk_status').css('color', 'blue');
            }
    
        });
    });

    /* 변경 버튼 클릭 */
    $("#btn_submit").click(function(){

        // 유효성 검사
        if(mb_pw.val()=="" || mb_pw.val()==null){
            alert("현재 비밀번호를 입력하세요.");
            mb_pw.focus();
            return;

        } else if(mb_pw_change.val()=="" || mb_pw_change.val()==null){
            alert("변경할 비밀번호를 입력하세요.");
            mb_pw_change.focus();
            return;

        } else if(mb_pw_chk.val()=="" || mb_pw_chk.val()==null){
            alert("비밀번호 확인란을 입력하세요.");
            mb_pw_chk.focus();
            return;
        }

        // 현재비밀번호 일치여부 검사
        var mb_pw_val = $("#mb_pw").val();

        $.ajax({

            url: '/member/checkAjax',
            type: 'post',
            datatype: 'text',
            data: {mb_pw: mb_pw_val},
            success: function(data){
                if(data=='SUCCESS'){
                    form.submit();

                } else{
                    alert("현재 비밀번호가 일치하지 않습니다.");
                    mb_pw.val("");
                    mb_pw.focus();
                }
            }

        });

    });
    
    /* 취소버튼 클릭 */
    $("#btn_cancle").click(function(){

        var result = confirm("회원정보 수정을 취소하시겠습니까?");

        if(result){
            location.href="/";
        } else{}
    });
});