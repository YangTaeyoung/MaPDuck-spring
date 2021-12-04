# MAPDUCK
## 보증기간 관리 시스템을 위한 API Server 입니다.
MaPDuck-django와 server to server 통신을 수행하며, django에서는 크롤링 및 셀레늄을 통한 네이버 로그인을 진행하며
스프링에서는 DB와 클라이언트 요청간의 중계역할을 수행합니다.
<hr/>

## 해당 사이트에 배포되어 있으며, 누구나 회원가입을 통해 해당 API를 활용·사용할 수 있습니다

[바로가기](https://www.mapduck.shop/swagger-ui/)

*ID: test*   
*PW: test*

<hr/>

해당 API는 OPEN API Specification Framework, Swagger를 사용합니다.   
따라서 해당 API의 Sample JSON은 사이트에 접속한 후 확인할 수 있습니다.

<hr/>
## 기능   

1. 네이버 장바구니 연동을 통한 모델명 추출 및 구매일 크롤링   
- 해당 부분은 Chromedriver이 aws상에서 gui로 작동하지 않기 때문에 사용자 컴퓨터에서 직접적인 시연이 어려울 수 있습니다.   
- 로컬 서버에 데이터를 옮긴 후 테스트 하는 것을 권장합니다.   
2. 다나와 사이트 크롤링을 통한 모델명 추출   
3. 회원가입/로그인(HTTP Basic 기반)   


<hr/>
## 예정
추후 해당 API의 직접적인 활용을 위해 사용자 UI를 개발할 예정입니다.
