<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<html>
<head>
	<meta charset="UTF-8">
	<title>주소록</title>
	
	<!-- JQuery Script -->
	<script src="https://code.jquery.com/jquery-1.12.4.js" integrity="sha256-Qw82+bXyGq6MydymqBxNPYTaUXXq7c8v3CwiYwLLNXU=" crossorigin="anonymous"></script>
	<script src="/resources/js/jquery.serializeObject.js"></script>
	
	<!-- Bootstrap  -->	
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
	
	
	<style>
		.container {
			width : 900px;
		}
		
		.list th, .list td{
			text-align: center;
		}
		
		.list th:nth-child(1){ width: 100px;}
		.list th:nth-child(2){ width: 100px;}
		.list th:nth-child(3){ width: 140px;}
		.list th:nth-child(4){ width: auto;}
		.list th:nth-child(5){ width: 80px;}
		
		.add {
			width : 400px;
		}
		
		.add th {
			width: 100xp;
			text-align: center;
		}
		
		.add input[name='name'],
		.add input[name='age'] {
			width: 120px;
		}
		
		.add input[name='tel']{
			width: 200px;
		}
	</style>

</head>
<body>

	<div class="container">
		<h1 class="page-header">주소록 <small>RESTful</small></h1>
		
		<div class="well well-sm">
			<!-- 
			<input type="text" class="form-control" placeholder="주소 검색" id="word" autocomplete="off">
			 -->
			 
			<div class="col-lg-6">
	            <div class="input-group">
	               <input type="text" class="form-control" placeholder="주소 검색" id="word" autocomplete="off">
	               <span class="input-group-btn">
	                  <button class="btn btn-default" type="button" onclick="clearSearch();">clear</button>
	               </span>
	            </div>
            </div>
		</div>
		
		
		<!-- 주소록 목록 테이블 -->
		<table class="table table-bordered list">
			<thead>
				<tr>
					<th>이름</th>
					<th>나이</th>
					<th>전화</th>
					<th>주소</th>
					<th>조작</th>
				</tr>
			</thead>
			<tbody>
			
			<!-- 
				<tr>
					<td colspan="5">데이터가 없습니다.</td>
				</tr>
				
				<tr>
					<td>홍길동</td>
					<td>20</td>
					<td>010-1234-1234</td>
					<td>서울시 강남구 역삼동</td>
					<td>
						<button type="button" class="btn btn-default btn-xs" onclick="">U</button>
						<button type="button" class="btn btn-default btn-xs" onclick="">D</button>
					</td>
				</tr>
			 -->
			</tbody>		
		</table>
		<hr>
		
		<!-- 주소록 추가 테이블 -->
		<form id="form1">
		<table class="table table-bordered add">
			<tr>
				<th>이름</th>
				<td><input type="text" name="name" class="form-control"></td>
			</tr>
			<tr>
				<th>나이</th>
				<td><input type="number" name="age" class="form-control"></td>
			</tr>
			<tr>
				<th>전화</th>
				<td><input type="tel" name="tel" class="form-control"></td>
			</tr>
			<tr>
				<th>주소</th>
				<td><input type="text" name="address" class="form-control"></td>
			</tr>
		</table>
		<div>
			<input type="button" value="추가하기" class="btn btn-default" id ="btn">
		</div>
		</form>
	</div>



	<script>
		
		/* CSR + REST API 개발스타일 */
	
		// 목록 가져오기
		// 1. 시작할 때 호출
		// 2. 추가하기 > 호출
		// 3. 수정하기 > 호출
		// 4. 삭제하기 > 호출
		
		
		/* 주소록 가져오기 */
		function load(word) {
			
			let url = 'http://localhost:8090/address';
			
			if (word != null && word != '') {
				url = url + '/' + word;
			}
			
			$.ajax({
				
				// request
				type: 'GET',
				url: url,
				
				// response
				dataType: 'json',
				success: function(list){
					
					$('.list tbody').html(''); // 테이블 초기화
					
					$(list).each((index, item) => {
						let tr = '<tr>'+
							'<td>' + item.name + '</td>'+
							'<td>'+ item.age +'</td>'+
							'<td>'+ item.tel +'</td>'+
							'<td>'+ item.address +'</td>'+
							'<td>'+
								'<button type="button" class="btn btn-default btn-xs" onclick="edit('+ item.seq +');">U</button>'+
								'<button type="button" class="btn btn-default btn-xs" onclick="del('+ item.seq +');">D</button>'+
							'</td>'+
						'</tr>';
						
						$('.list tbody').append(tr);
					});
				},
				
				// try-catch
				erro: function(a, b, c){
					console.log(a, b, c);
				}
				
			});
			
		}
		
		// 시작할 때 load 호출
		load();
	
		
		/* 주소록 추가 */
		$('#btn').on('click', add);
		
		function add() {
			let data = JSON.stringify($('#form1').serializeObject());
			alert(data);
			
			$.ajax({
				
				// request
				type: 'POST',
				url: 'http://localhost:8090/address',
				contentType: 'application/json;charset=UTF-8',
				data : data,
				
				// response
				// 추가 성공시, 폼 초기화
				dataType: 'json',
				success: function(result){
					if(result == 1) {
						load();
						$('#form1 input[class=form-control]').val('');
					}
				},
				
				// try-catch
				erro: function(a, b, c){
					console.log(a, b, c);
				}
				
			});
		}
		
		
		
		/* 주소록 수정 */
		function edit(seq){
			
			let name = $(event.srcElement).parent().parent().children().eq(0).text();
			let age = $(event.srcElement).parent().parent().children().eq(1).text();
			let tel = $(event.srcElement).parent().parent().children().eq(2).text();
			let address = $(event.srcElement).parent().parent().children().eq(3).text();
			
			// 클릭한 목록 폼에 데이터 채워넣기
			$('input[name=name]').val(name);
			$('input[name=age]').val(age);
			$('input[name=tel]').val(tel);
			$('input[name=address]').val(address);
			
			// 버튼 > '수정하기' 버튼으로 변경
			// 기존에 등록된 '추가하기' 기능 선제거
			// .click은 파라미터 넘기는 기능이 없어서, on으로 이벤트 등록. 수정할 seq를 넘김.
			$('#btn').val('수정하기');
			$('#btn').off('click', add); 
			$('#btn').on('click', {seq: seq}, editok); 
		}
		
		
		/* 주소록 수정 처리 */
		function editok(evt){
		
			let data = JSON.stringify($('#form1').serializeObject());
			
			$.ajax({
				
				// request
				type: 'PUT',
				url: 'http://localhost:8090/address/' + evt.data.seq,
				contentType: 'application/json;charset=UTF-8',
				data : data,
				
				// response
				// 수정 성공시, 폼 초기화
				dataType: 'json',
				success: function(result){
					if(result == 1) {
						load();
						$('#form1 input[class=form-control]').val('');
						
						// 버튼 > '추가하기' 버튼으로 변경
						// '수정하기' 기능은 제거
						// '추가하기' 기능 등록
						$('#btn').val('추가하기');
						$('#btn').off('click', editok); 
						$('#btn').on('click', add); 
					}
				},
				
				// try-catch
				erro: function(a, b, c){
					console.log(a, b, c);
				}
				
			});
		}
		
		
		/* 주소록 삭제 */
		function del(seq) {
		
			$.ajax({
				
				// request
				type: 'DELETE',
				url: 'http://localhost:8090/address/' + seq,
				
				// response
				dataType: 'json',
				success: function(result){
					if(result == 1) {
						load();
					}
				},
				
				// try-catch
				erro: function(a, b, c){
					console.log(a, b, c);
				}
				
			});
			
		}
		
		
		/* 
		
		$('#word').keydown((evt)=>{
			// 실시간 검색은 내용 지우고 keyup으로 변경
			// 하지만 초성검색, 오타 등등은 잡을 수 없다.. > 서버를 풀어헤쳐야함, 오라클 초성검색
			
			if(evt.keyCode == 13 && $('#word').val() !== ''){
				load($('#word').val());
			} 
		});

 		*/
 		
 		
 		/* 실시간 검색 감지 */
		$('#word').keyup((evt)=>{
			
			// 완성형 한글 일 때만 검색 
			// 초성체 검색 정도는 막을 수 있음
			let regex = /^[가-힣]{1,}$/gi;
			
			if(regex.test($('#word').val())){
				load($('#word').val());
			}
			
		});
		
		
		/* 검색기능 초기화 */
		function clearSearch() {
			$('#word').val('');
			load();
		}
		
	
	</script>



</body>


</html>


<%-- 

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>   
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>Document</title>
	<style>
	</style>
</head>
<body>

	<!-- index.jsp -->

	<main>
		<h1>REST Client Page</h1>
		
		<form id="form1">
		<table border width="300">
			<tr>
				<th>이름</th>
				<td><input type="text" name="name"></td>
			</tr>
			<tr>
				<th>나이</th>
				<td><input type="text" name="age"></td>
			</tr>
			<tr>
				<th>주소</th>
				<td><input type="text" name="address"></td>
			</tr>
			<tr>
				<th>전화번호</th>
				<td><input type="tel" name="tel"></td>
			</tr>
		</table>
		</form>
		<input type="button" value="추가하기" id="btn1">

	</main>

	<!-- JQuery Script -->
	<script src="https://code.jquery.com/jquery-1.12.4.js" integrity="sha256-Qw82+bXyGq6MydymqBxNPYTaUXXq7c8v3CwiYwLLNXU=" crossorigin="anonymous"></script>
	<script src="/resources/js/jquery.serializeObject.js"></script>
	
	<script>
	
		// 이건 String 형태, JSON형태로 보내야함
		// alert($('#form1').serialize());
		
		/* 
		let data = { 
				"name": "홍길동", 
				"age": 20, 
				"tel": "010-2222-3333", 
				"address": "서울시 마포구 연남동"
		};
		 */
		
		
		
		$("#btn1").click(()=>{
			
			// 사용가 입력한 값을 object로 반환
			// JSON.stringify : JSON Ojbect를 문자열로 변환하는 함수
			let data = JSON.stringify($('#form1').serializeObject());
			alert(data);
			
			$.ajax({
				type: 'POST',
				url: '/address',
				data: data,
				contentType : 'application/json;charset=UTF-8', // 클라이언트 -> 서버로 보내는 데이터 타입 : MINE
				dataType: 'json', // 서버 -> 클라이언트로 돌아오는 데이터 타입
				success: function(result){
					alert(result);
				},
				error: function(a, b, c) {
					console.log(a, b, c);
				}
			});
			
		});	
	</script>
	
</body>
</html>


 --%>


