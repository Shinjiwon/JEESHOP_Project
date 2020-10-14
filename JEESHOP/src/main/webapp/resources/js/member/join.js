$(document).ready(function() {

    // form 변수
    var form = $("#joinForm");

    // 아이디 인증기능 사용여부 확인 변수
    var chkId = "false";

    // 이메일 인증 확인여부 변수
    var chkEmail = "false";

    /* 아이디 중복체크 버튼 클릭 */
    $("#userIdChk").click(function() {
        var mb_id = $("#mb_id").val();
    
        if($("#mb_id").val()=="" || $("#mb_id").val()==null) {
            $("#id_availability").html("아이디를 입력해주세요.");
            return;
        }

        // ajax방식은 제어의 흐름이 클라이언트시작 → 서버요청 → 클라이언트  종료
		// ajax방식은 서버요청에 실행되는 코드가 response.redirect,redirect: 주소이동 구문이 사용안하거나 
        // 동작되어서는 안된다.
        $.ajax({
            url: '/member/idDuplicateChk',
            type: 'post',
            dataType: 'text',
            data: {mb_id: mb_id}, // mb_id → MemberController idDuplicateChk 메서드의 @RequestParam("mb_id")
            success: function (data) {
                if (data == 'SUCCESS') { // 아이디 사용가능
                    $("#id_availability").css("color", "blue");
                    $("#id_availability").html("사용 가능한 아이디 입니다.");
                    
                    // 아이디 중복체크 완료 후 아이디 텍스트 박스, 중복체크 버튼 비활성화
                    $("#mb_id").attr("readonly", true);
                    $("#userIdChk").attr("disabled", true);

                    chkId = "true";
                } else {
                    // 아이디 사용 불가능
                    $("#id_availability").html("이미 존재하는 아이디입니다. \n다른 아이디를 입력하세요.");
                }
            }
        });
    });

	/* 비밀번호 일치여부 확인 */
    $(function(){
        $('#mb_pw').keyup(function(){
          $('#mbPwChk_status').html('');
        });
    
        $('#mb_pw_chk').keyup(function(){
    
            if($('#mb_pw').val() != $('#mb_pw_chk').val()){
              $('#mbPwChk_status').html('비밀번호 일치하지 않음');
              $('#mbPwChk_status').css('color', 'red');
            } else{
              $('#mbPwChk_status').html('비밀번호 일치함');
              $('#mbPwChk_status').css('color', 'blue');
            }
    
        });
    });
    
    /* 이메일 인증버튼 클릭 */
    $("#mbEmailChk").click(function(){

        var receiveEmail = $("#mb_email").val();

        // 이메일 입력란에 이메일 없을 경우
        if($("#mb_email").val()=="" || $("#mb_email").val()==null){
            $("#authcode_status").html("이메일을 입력해주세요.");
            return;
        }

        // 이메일 입력 후 인증코드 전송 진행 메세지
        $("#authcode_status").css("color", "green");
        $("#authcode_status").html("인증코드 전송 중...");
        
        $.ajax({

            url: '/email/sendEmail',
            type: 'post',
            dataType: 'text',
            data: {receiveEmail: receiveEmail},
            success: function(data){
                $("#email_authcode").show();
                $("#authcode_status").css("color", "grey");
                $("#authcode_status").html("메일이 전송되었습니다. 이메일에서 인증코드 확인 후 입력해주세요. ");
            }
        });
    });

    /* 인증번호 확인 버튼 클릭 */
    $("#btn_checkAuthCode").click(function(){

        var code = $("#mbAuthcode").val();

        $.ajax({

            url: '/member/emailAuthcodeChk',
            type: 'post',
            dataType: 'text',
            data: {code: code},
            success: function(data){
                
                if(data == 'SUCCESS'){
                    $("#email_authcode").hide();
                    $("#authcode_status").css("color", "blue");
                    $("#authcode_status").html("인증되었습니다.");
                    $("#mb_email").attr("readonly", true);
                    $("#mbEmailChk").attr("disabled", true);
                    chkEmail = "true";
                    return;
                    
                } else {
					$("#email_authcode").hide();
					$("#authcode_status").css("color", "red");
					$("#authcode_status").html('인증 실패. 다시 시도해주세요.');
					return;
				}
            }
        });
    });

    /* 회원가입 버튼 클릭 */
    $("#btn_submit").click(function(){

        var mb_id = $("#mb_id");
        var mb_pw = $("#mb_pw");
        var mb_pw_chk = $("#mb_pw_chk");
        var mb_name = $("#mb_name");
        var mb_nick = $("#mb_nick");
        var mb_email = $("#mb_email");
        var mbAuthcode = $("#mbAuthcode");
        var mb_phone = $("#mb_phone");
        var mb_postcode = $("input[name='mb_postcode']");
        var mb_addr = $("input[name='mb_addr']");
        var mb_deaddr = $("input[name='mb_deaddr']");

        /* 회원가입 전 유효성 검사 */
        if(mb_id.val()=="" || mb_id.val()==null){
            alert("아이디를 입력하세요.");
            mb_id.focus();

        } else if(chkId=="false"){
            alert("아이디 중복확인을 하세요.");
            $("#userIdChk").focus();

        } else if(mb_pw.val()=="" || mb_pw.val()==null){
            alert("비밀번호를 입력하세요.");
            mb_pw.focus();

        } else if(mb_pw_chk.val()=="" || mb_pw_chk.val()==null){
            alert("비밀번호 확인란을 입력하세요.");
            mb_pw_chk.focus();

        } else if(mb_name.val()=="" || mb_name.val()==null){
            alert("이름을 입력하세요.");
            mb_name.focus();

        } else if(mb_nick.val()=="" || mb_nick.val()==null){
            alert("닉네임을 입력하세요.");
            mb_nick.focus();

        } else if(mb_email.val()=="" || mb_email.val()==null){
            alert("이메일을 입력하세요.");
            mb_email.focus();

        } else if(mbAuthcode.val()=="" || mbAuthcode.val()==null){
            alert("이메일 인증코드를 입력하세요.");
            mbAuthcode.focus();

        } else if(chkEmail=="false"){
            alert("이메일인증을 진행하세요.")
            $("#mbEmailChk").focus();

        } else if(mb_phone.val()=="" || mb_phone.val()==null){
            alert("휴대폰 번호를 입력하세요.");
            mb_phone.focus();

        } else if(mb_postcode.val()=="" || mb_postcode.val()==null){
            alert("우편번호를 입력하세요.");
            $("#btn_postCode").focus();

        } else if(mb_addr.val()=="" || mb_addr.val()==null){
            alert("주소를 입력하세요.");
            $("#btn_postCode").focus();

        } else if(mb_deaddr.val()=="" || mb_deaddr.val()==null){
            alert("상세주소를 입력하세요.");
            mb_deaddr.focus();

        } else{
            form.submit();
        }
    });

    /* 가입취소 버튼 클릭 */
    $("#btn_cancle").click(function(){
        var result = confirm("가입을 취소하시겠습니까?");

        if(result){
            location.href = "/";
        }else{}
    });
});