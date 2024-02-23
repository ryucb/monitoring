# monitoring

1. 실행방법

	1)jar로 실행하기
		maven build(Goal: package)
		target -> monitoring-x.x.x-SNAPSHOT.jar 실행
	2)sts로 실행
		sts 내장 톰캣으로 실행
	
2. API Spec

	1)host
		- 조회
			전체조회: GET /host/all
			seq(id)로 조회: GET /host/seq/{seq}
			name으로 조회: GET /host/name/{name}
			ip로 조회: GET /host/ip/{ip}
			state 조회: POST /host/state - 조회시 state 확인 후 update동작
		- 생성: POST /host/admin/save 
		- 수정: POST /host/admin/update
		- 삭제: POST /host/remove/{seq}
		
	2)user
		- 회원가입: POST /sing-up
		- 로그인: GET /sing-in
		- 로그아웃: GET /sing-out
		
	3)log
		- 조회
			전체조회: GET /log/admin/all
			조건조회: GET /log/admin
			
3. 기타

	1) 1분마다 전체 host state update