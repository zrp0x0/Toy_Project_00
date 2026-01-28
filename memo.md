# Toy Project 00
- 이 프로젝트는 스프링을 전체적으로 공부하기 위해서 실행하는 프로젝트
- AI와 협업하되, 코드의 전체적인 작성을 물어보는 것이 아니라, 피드백 및 추가적인 내용을 위해 활용



---
# Day 1


### 어떻게 프로젝트를 나누는 것이 좋을까에 대한 고민
- 흔히 말하는 전통적인 MVC가 처음에 와닿았는데 폴더도 많이 안 만들어도 되고 ㅋㅋ
- 근데 이게 개발을 직접 하다보니깐 Domain별로 만드는 것이 주관적인 생각으로 더 좋을 것 같음
- 그래서 다음과 같이 기본적인 프로젝트 골격을 만들기로 했음
```
domain
 ├─ controller
 │   └─ DomainController.java
 ├─ service
 │   ├─ DomainService.java          // 인터페이스
 │   └─ DomainServiceImpl.java      // 구현체
 ├─ repository
 │   └─ DomainRepository.java
 ├─ entity
 │   └─ Domain.java
 └─ dto
     ├─ DomainCreateRequest.java
     ├─ DomainResponse.java
     └─ DomainUpdateRequest.java
```
