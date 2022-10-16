js-study-ch8-jimin
# 1. 예외와 기본 예외 처리
예외는 두 가지 방법으로 처리한다.
- 기본 예외 처리
- 고급 예외 처리

## 예외와 오류
### 예외(Exception)
프로그램을 실행하면서 발생하는 논리적 오류로,
예외 처리 방법으로 처리할 수 있다.

### 오류(Error)
큰 의미로는 오류와 예외를 모두 포함한다.
작은 의미로는 프로그램을 실행하기도 전에 발생하는 문법적 오류이다.
코드를 하나하나 실행하기도 전에 발생하므로 예외 처리 방법으로 처리할 수 없다.

## TypeError 기본 예외 처리
### 1. 예외 상황 확인하기
자바스크립트에는 undefined 자료형이 있는데, 
이러한 undefined 자료형을 일반적인 객체 또는 함수처럼 다루면 TypeError라는 예외가 발생한다.

### 2. 기본 예외 처리하기
사전에 해당 데이터가 함수인지 조건문으로 확인하기만 해도 예외를 예방할 수 있다.

---
# 2. 고급 예외 처리
>고급예외처리는 try 키워드, catch 키워드, finally 키워드로 예외를 처리하는 방법이다.

이를 try catch finally 구문이라고 한다.

try catch finally 구문의 기본형태
```js
try {
  // 예외가 발생하면
} catch (exception) {
  // 여기서 처리한다.
} finally {
  // 여기는 무조건 실행한다.
}
```

---
# 3. 예외 객체
>예외가 발생했을 때 어떤 예외가 발생했는지 정보를 함꼐 전달해주는 기능을 수행하는 객체

예외 객체의 속성
- name
- message

---
# 4. 예외 강제 발생
예외를 강제로 발생시킬 때는 `throw` 키워드를 사용한다.
`throw` 키워드 뒤에는 문자열 또는 `Error` 객체를 입력한다.
```js
try {
  // 예외 객체 만들기
  const error = new Error('메시지');
  error.name = '내 마음대로 오류';
  error.message = '오류의 메시지';
  
  // 예외를 발생시킨다.
  throw error;
} catch (exception) {
  // 예외 객체를 출력한다.
  console.log(`${exception.name} 예외가 발생했습니다.`);
  console.log(exception.message);
}
```