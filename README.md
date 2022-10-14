# Food Service
* 음식 주문 서비스

## 기술 스택
* Java 17
* Maven
* Spring Boot 2.7.1
* JPA
* Docker
* AWS EC2, RDS
* Github Actions


## DB 설계
https://viewer.diagrams.net/?page-id=68PQAUWc9NsF2iRY7r9o&highlight=0000ff&edit=_blank&layers=1&nav=1&page-id=68PQAUWc9NsF2iRY7r9o#G1giGTPTAR2ZjBE1llaCfjegKdboPPpSnT


## 서버 구조
<img src="./docs/images/infra-structure.png" title="infra_structure"/>


## 프로젝트 구조
```
food-serivce (project)
│   
├── common 
│   ├── pom.xml
│   └── target   
├── auth 
│   ├── pom.xml
│   └── target
├── user 
│   ├── pom.xml
│   └── target
├── store
│   ├── pom.xml
│   └── target
├── order
│   ├── pom.xml
│   └── target
├── app
│   ├── src  
│   │   ├── ... (하위 main() 메서드 포함)
│   ├── pom.xml
│   └── target
└── pom.xml
```

#### 공통사항
* Maven Project

#### food-service (project)
* Parent Pom: spring-boot-starter-parent
* Maven Packaging Type: pom (target 디렉토리 생성 X)
* 하위 모듈 (common, auth ... 등)을 <modules>를 통해 프로젝트에 포함
* main() 메서드 미포함

#### common, auth, user, store, order (modules)
* Parent Pom: food-service
* Maven Packaging Type: jar
* main() 메서드 미포함

#### app (module)
* Parent Pom: food-service
* Maven Packaging Type: jar
* sibling 모듈 (common, auth ... 등)을 dependency로 의존
* main() 메서드 포함
* 서버에 배포될 jar파일 생성
