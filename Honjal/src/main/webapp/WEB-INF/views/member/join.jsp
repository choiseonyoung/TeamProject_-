<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="rootPath" value="${pageContext.request.contextPath}" />

<form id="join_form">
      <h2 class="board_title">회원 가입</h2>
      <fieldset>
        <div>
          <label>아이디</label>
          <input type="text" id="member_id" placeholder="6자 이상 영문과 숫자 조합"/>
          <button id="btn_id_check">중복확인</button>
        </div>
        <div>
          <label>비밀번호</label>
          <input type="password" id="member_password" placeholder="8자 이상 영문과 숫자 조합"/>
        </div>
        <div>
          <label>비밀번호 재입력</label>
          <input type="password" id="member_password2" placeholder="재입력 해주세요"/>
        </div>
        <div>
          <label>이름</label>
          <input type="text" id="member_name" />
        </div>
        <div>
          <label>닉네임</label>
          <input type="text" id="member_nickname" />
        </div>
        <div>
          <label>e-mail</label>
          <input type="email" id="member_email" />
        </div>
      </fieldset>
      
      <section id="join_btn">
        <button type="submit">회원가입</button>
        <button>취소</button>
      </section>
</form>

<script>
document.querySelector("#join_btn").addEventListener("click", (e) => {
    //  location.href = "${rootPath}"
    document.querySelector("form").submit()
})
let member_id = document.querySelector("input[name='member_id']")
let member_pw = document.querySelector("input[name='member_pw']")
let member_pw1 = document.querySelector("input[name='re_pw']")
let member_nickname = document.querySelector("input[name='member_nname']")
let member_email = document.querySelector("input[name='member_email']")
let msg_member_id = document.querySelector("div.member.id")
let msg_member_pw = document.querySelector("div.member.pw")
let msg_member_pw1 = document.querySelector("div.member.pw1")
let msg_member_nickname = document.querySelector("div.member.nickname")
let msg_member_email = document.querySelector("div.member.email")
let id_check = document.querySelector("#btn_id_check")
if(member_nickname){
	member_nickname.addEventListener("blur",(e)=>{
		let nickname = e.currentTarget.value
		msg_member_nickname.innerText = ""
		msg_member_nickname.style.padding="0"
		if(nickname === ""){
			msg_member_nickname.innerText = " * 닉네임을 반드시 입력하세요"
			member_nickname.focus()
			return false
		}
	})
}
if(member_email){
	member_email.addEventListener("blur",(e)=>{
		let email = e.currentTarget.value
		msg_member_email.innerText = ""
		msg_member_email.style.padding="0"
		if(email === ""){
			msg_member_email.innerText = " * e-mail을 반드시 입력하세요"
			member_email.focus()
			return false
		}
	})
}
if(member_pw){
	member_pw.addEventListener("blur",(e)=>{
		let password = e.currentTarget.value
		
		msg_member_pw.innerText = ""
		msg_member_pw.style.padding="0"
		if(password.length < 8){
			msg_member_pw.innerText = " * 비밀번호는 8자 이상 입력하세요"
			member_pw.focus()
			return false
		}
		if(member_pw1){
			member_pw1.addEventListener("blur",(e)=>{
				let password1 = e.currentTarget.value
				msg_member_pw1.innerText = ""
				msg_member_pw1.style.padding="0"
				if(password != password1){
					msg_member_pw1.innerText = " * 비밀번호가 일치하지 않습니다"
					member_pw1.focus()
					return false
				}	
			})
		}
	})
}
if(member_id){
		member_id.addEventListener("blur",(e)=>{
			let memberid = e.currentTarget.value
			
			msg_member_id.innerText = ""
			msg_member_id.style.padding="0"
			
			if(memberid === "" ){
				msg_member_id.innerText = " * 아이디는 반드시 입력하세요"
				member_id.focus()
				return false
			}else if(memberid.length < 6){
				msg_member_id.innerText = " * 아이디는 6자 이상 입력하세요"
				member_id.focus()
				return false
			}
			fetch("${rootPath}/member/id_check?member_id="+member_id)
			.then(response=>response.text())
			.then(result=>{
				if(result === "USE_ID"){
					msg_member_id.innerText = " * 이미 가입된 ID 입니다"
					msg_member_id.style.color="red"
					member_id.focus()
					return false
				}else if(result === "NOT_USE_ID"){
					msg_member_id.innerText = " * 가입 가능한 ID 입니다"
					msg_member_id.style.color="blue"
					document.querySelector("#member_password").focus()
				}
			})
	})
}
</script>