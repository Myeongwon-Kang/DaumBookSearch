# DaumBookSearch

## 기능요구사항

  * 카카오 도서 검색 API를 이용하여 도서 검색
    * https://developers.kakao.com/docs/latest/ko/daum-search/dev-guide#search-book
    * 단, size param은 50으로 설정해, page당 50권이 검색되도록 개발
  * 스크롤 시 연속 Paging 기능을 제공해 연속적으로 검색하는 기능 제공
  * 검색 리스트 결과(메인화면) 및 상세 화면으로 구성
  * 상단에 도서명을 입력 할 수 있는 입력창을 제공해 도서명이 변경되면 해당 도서명으로 검색 결과 
  * 메인 리스트와 상세 화면은 모두 Fragment로 구성
  * 메인 리스트에서 특정 Item(도서) 클릭시 상세화면으로 fragmnet로 이동
  
## 과제수행 전략

### Package Structure
```
/com/kang6264
│
└── daumbooksearch   ---------> # project 
    ├── data               ---------> # data layer
        ├── api
        ├── datasource
        ├── repository
        ├── response
    ├── di               ---------> # dependency injection
    ├── domain               ---------> # domain layer
        ├── data
        ├── repository
        ├── usecase
    ├── presentation               ---------> # presentation layer
        ├── base
        ├── pagingnation
        ├── ui
            ├── search
                ├── detail
                ├── home
        ├── util
    
```

### Architecture
![screenshot_](https://user-images.githubusercontent.com/10939456/92988469-bf851400-f506-11ea-8c84-38345cabccef.png)

### Libraries
- Kotlin
- Dagger Hilt
- Coroutine
- MVVM
- Clean Architecture
- Repository Pattern
- Glide
- Retrofit
- Android Support Library(KTX, Android Paging ... etc)
- Gson

