<aside>
💡 **중요! Master branch는 배포용으로 사용할 예정입니다.**

</aside>

잘 이해 안가면 영상 확인!

[깃허브 브랜치 | 깃허브](https://youtu.be/RYfO6-hPBdw)

## Branch

---

1. 생성되어 있는 **Develop Branch**에서 새로 브랜치 만들어서 작업 부탁드립니다.
    1. Develop → git branch **Android** / git branch **Backend**
    
2. 각 기능(feature) 마다 branch 생성 바랍니다.
    
    ex)  Android → BottomNavigation, Splash, MainFeed  etc...
    
     Backend → Kakao, Naver, Google, SearchImgAWS, CompareImgDB  etc...
    
3. Merge는 Pull Request 요청하시면 팀장이 Merge 하겠습니다
    1. Backend의 경우 상현님이 pull request 올리시면 됩니다. 
    
4. Naming Convention - **UpperCamel case(PascalCase)**
    1. 맨 앞글자, 중간 이어지는 단어 첫 글자 대문자
        
        → SocialLogin, BottomNavbar....
        

## Commit

---

<aside>
💡 **기본 형식**

`타입(Type): 제목(Subject)`

(Blank Line) → 커밋할 때 큰 따옴표(”) 닫지 않고 엔터치면 \n 처리됩니다.

`본문(Body)` - 설명

ex) 

```
git commit -m 
"Fix: Kakao Social Login 기능 수정

RedirectURL 수정 - 기존 path에서 oauth/kakao로 path 변경"
```

</aside>

1. **타입(Type)**
    - Feat - 새로운 기능 추가
    - Fix - 버그 수정
    - Build - 빌드 관련 파일 수정, 라이브러리 추가/삭제 등
    - Style - 스타일 (코드 형식, 세미콜론 추가: 비즈니스 로직에 변경 없는 경우)
    - Refactor - 코드 리팩토링(완전히 코드 구조 수정할 때)

1. **제목(Subject)**
    - 제목은 50자를 넘기지 않고, **마침표를 붙이지 않습니다.**
    - 제목에는 commit 타입을 함께 작성합니다.
    - 과거 시제를 사용하지 않고 **명령조로 작성**합니다.
    - **제목과 본문은 한 줄 띄워 분리합니다.**
    - 제목의 첫 글자는 반드시 대문자로 씁니다.
    
2. **본문(Body) - 선택사항**
    - 필수는 아니지만  **최대한 적어주시면 commit 기록으로 Bug 잡을 수 있을 것 같습니다**
    - 무엇을, 왜(What, Why)에 맞춰 작성합니다.
    - 설명뿐만 아니라, commit의 이유를 작성할 때에도 씁니다.
