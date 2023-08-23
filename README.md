# FoodyFoodyFoody

안드로이드 잭팩 라이브러리를 사용한 사이드 프로젝트입니다. 

기능 : 음식 리스트출력, 음식 상세보기, 음식 즐겨찾기

![기능이미지](https://github.com/BeeChang/FoodyFoodyFoody/assets/59998259/24e94464-f2cb-4ef9-8dc7-a393f5463a6b)


설명 : 
* Udemy의 Foody 강의의 Ui를 사용합니다. ([Foody](https://github.com/stevdza-san/Foody))
* 안드로이드 아키텍처 가이드에 따라 레이어드 아키텍처 형태로 구성했습니다. Ui -> Domain -> Data
* Ui Layer : 단반향 데이터 흐름으로 Ui Element는 Viewmodel을 호출하고 State를 이용해 뷰를 갱신합니다.
* Domain Layer : 클래스당 단순하고 가볍게 유지하기 위해서 단일책임을 지키고 있습니다.
* Data Layer : Repository에서 Network or Database 두 모듈에 필요한 데이터를 요청합니다
* Layer간의 데이터 이동은 Coroutine Flow를 이용해서 구축했습니다.
* 에러핸들링은 원인의 따른 제어를 위해서 Exception을 확장해서 예외처리합니다.

</br>
</br>

데이터 흐름 및 모듈구성 : 


![스크린샷 2023-08-23 오후 3 05 54](https://github.com/BeeChang/FoodyFoodyFoody/assets/59998259/6c01e504-2b1d-4cb4-9d68-6dde9ff85035)

</br>

사용 기술 :
* Kotlin
* App Archeitecture 
* Jetpack(Navigation, Lifecycle, AAC ViewModel, Room, Hilt)
* Coroutine + Flow
* Square(Retorift2, Okhttp3) + Sandwich
* Moshi
* JUnit, Turbine, Mockito
</br>

API KEY 만료 시 : [Spoonacular](https://spoonacular.com/food-api) 의 API키 사용, network 모듈 contants.kt에서 key 변경바랍니다


