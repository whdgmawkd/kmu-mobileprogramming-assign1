# 모바일 프로그래밍 개인과제

## 20181619 박종흠

## 깃허브 주소 : https://github.com/whdgmawkd/kmu-mobileprogramming-assign1

# 구현 내용

## LoginActivity

* Room 데이터베이스를 사용하여 사용자 정보 저장
* SQLite보다 안전하게 데이터베이스 쿼리 가능
* username과 password가 빈칸일 경우 경고 메시지 표시
* username과 password가 빈칸이 아닌 경우, 존재하지 않는 username또는 password가 틀린 경우 AlertDialog를 화면에 표시하여 다시 로그인을 시도하도록 변경
* 로그인 정보가 정확한 경우에만 MainActivity 화면으로 전환

## RegisterActivity

* 사용자 정보 입력 EditText와 개인정보처리방침을 표시하기 위한 TextView, 동의 여부를 확인하기 위한 RadioGroup, RadioButton을 포함하는 레이아웃
* 화면 크기보다 본문 내용이 더 길어지기 때문에 ScollView를 사용
* username과 password에 대해서 빈칸을 허용하지 않고, 이미 가입된 계정에 대해서는 새로 가입하지 못하도록 Room 데이터베이스를 사용하여 처리
* 기타 사용자 정보는 추가사항으로 별도 기재하지 않아도 등록 가능
* 개인정보처리방침에 동의하지 않을 경우 가입을 진행하지 못하도록 하며, 사용자에게 붉은 강조메시지로 동의후 가입을 진행하도록 유도
* 액션바에 뒤로가기 버튼을 표시하여 상위 액티비티로 되돌아 갈 수 있도록 변경

## MainActivity

* 틱택토 게임 구현
* 3x3크기의 보드가 화면에 표시되며, 화면 하단에 차례가 표시됨
* 보드의 한 칸을 선택할 시 해당 칸은 O또는 X표시가 생기며, 상대 차례가 됨
* 보드에 O또는 X가 놓일 때마다 승패여부 혹은 비김 여부를 확인하여 하단에 표시
* 액션바에 우측에 표시되는 다시시작 버튼을 통해 게임 재시작 가능
* 액션바 우측 메뉴를 선택하여 로그아웃 후 로그인 화면으로 돌아갈수 있음
* 뒤로가기 키를 누르면 로그인 화면으로 돌아가지 않고 어플리케이션 종료