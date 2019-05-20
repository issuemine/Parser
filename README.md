HTML Parsing 및 Sort
=============

홈페이지 주소를 입력받아 해당 홈페이지 HTML내용의 숫자와 영문만을 뽑아내어 특정 규칙에 맞게 정렬하는 어플리케이션입니다.
정렬 규칙은 다음과 같습니다.

- 타입 
  - HTML 제외 - HTML 태그를 제외한 부분을 정렬한다.
  - TEXT 전체 -  HTML 문서 전체를 정렬한다.

- 정렬
  - 모든 문자 입력
  - 모든 문자 중 숫자 및 영문만 출력
  - 오름차순

- 출력 묶음
  - 출력 묶음 단위(입력 값에 따라 몫과 나머지의 문자열이 달라진다.)


### 개발 환경
해당 어플리케이션은 다음의 개발 환경에서 제작되었습니다.
  - Spring boot - v2.1.5.RELEASE

### 실행 방법
IDE에 해당 프로젝트 Import 후 스프링부트 실행