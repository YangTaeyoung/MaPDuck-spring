# MAPDUCK
## 보증기간 관리 시스템을 위한 API Server 입니다.

> 고장난 제품의 보증기간을 알려드립니다!

> AS를 받아야 하는데 미루고 계시진 않으신가요?

> 사용자의 제품을 직접/자동 등록하여 제품을 체계적으로 관리해 보아요

> 보증기간이 임박한 제품이 있다면, 메일로 알려드려요!

<hr/>

## DB Structure

> DB의 구성은 다음과 같습니다.

![Untitled](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/a6fff93b-aa1f-4921-b4c1-90ba76aeb07b/Untitled.png)

## 서비스 흐름도
> 전반적인 서비스의 흐름은 다음을 따릅니다.

#### 사용자가 직접 제품을 검색했을 때의 서비스 흐름
![image](https://user-images.githubusercontent.com/59782504/154843643-c747c5ba-0b8e-4a7e-a1df-50f3166add41.png)



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
