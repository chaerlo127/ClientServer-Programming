# 클라이언트 서버 프로그래밍

## Client and Server [RMI 통신/Process]

### Sequence Diagram

* Logging
* Token 암호화

<details>
<summary>Student: 로그인</summary>

![image](https://user-images.githubusercontent.com/90203250/208695665-666828da-6943-46c9-9acd-afa71f2ce25e.png)
</details>
<details>
<summary>Student: 회원가입</summary>

![image](https://user-images.githubusercontent.com/90203250/208695782-254b1f1c-d6ec-457e-bc7b-f6f4de80a370.png)
</details>
<details>
<summary>Student: 리스트</summary>

![image](https://user-images.githubusercontent.com/90203250/208696095-966056f9-7d2e-4f5a-81d0-a8e1dcf72ab7.png)
</details>
</details>
<details>
<summary>Student: 등록</summary>

![image](https://user-images.githubusercontent.com/90203250/208696095-966056f9-7d2e-4f5a-81d0-a8e1dcf72ab7.png)
</details>
<details>
<summary>Student: 삭제</summary>

![image](https://user-images.githubusercontent.com/90203250/208696095-966056f9-7d2e-4f5a-81d0-a8e1dcf72ab7.png)
</details>

<details>
<summary>Course: 리스트</summary>

![image](https://user-images.githubusercontent.com/90203250/208699273-a9276ae9-47d7-43f5-beb7-915a0511a781.png)
</details>
<details>
<summary>Course: 등록</summary>

![image](https://user-images.githubusercontent.com/90203250/208700052-cd455c62-13fb-4ff4-a9c5-9a1580cc8387.png)

</details>
<details>
<summary>Course: 삭제</summary>

![image](https://user-images.githubusercontent.com/90203250/208700475-81382fea-7d89-4576-a36d-28f38617ff57.png)

</details>

<details>
<summary>Reservation: 수강신청</summary>

![image](https://user-images.githubusercontent.com/90203250/208699959-d11fb0cd-b6fa-4938-8e49-330909ff5096.png)

</details>

<details>
<summary>Reservation: 리스트</summary>

![image](https://user-images.githubusercontent.com/90203250/208699919-851df8a2-2576-4806-99bd-548e9f3b201a.png)

</details>

<details>
<summary>Reservation: 수강신청 철회</summary>

![image](https://user-images.githubusercontent.com/90203250/208699856-b06169e4-4579-47e7-a6ba-3f0065736726.png)


</details>



<hr>

## Pipe and Filter [Thread]

### 기능
* 모든 CS 학생들은 모두 12345과 23456 과목을 필수 수강. 수강 신청하지 않은 학생들을 골라 과목 ID를 추가
* 모든 EE 학생 들은 23456 과목을 필수 수강. 수강 신청하지 않은 학생들을 골라 과목 ID를 추가
* 13학번 학생 + CS가 아닌 사람은 17651이나 17652 과목을 들을 수 없음. CS가 아닌 학생 중에 수강한 학생들을 골라 과목 ID 삭제

### Project Design Structure
<img src="https://user-images.githubusercontent.com/90203250/208683087-a1d4a71f-27f3-4169-b343-dff83f0bff22.png" width="500" height="250">

### Sequence Diagram
<details>
<summary>Sequence Diagram</summary>
<div float = left>
<img src="https://user-images.githubusercontent.com/90203250/208686509-c99f6d5c-15b3-4227-aa79-c6e928457fc4.png" width="500" height="250">
<img src="https://user-images.githubusercontent.com/90203250/208686546-15469220-61b3-4b17-941b-378520d882d1.png" width="500" height="250">
</div>
</details>

<hr>

## Event Bus [RMI 통신/Process]

### 기능
* 학생
  + 리스트, 추가, 삭제

* 강의
  + 리스트, 추가, 삭제

* 수강신청 
  + 리스트, 수강신청
  
 <br>

### Project Design Structure
<img src="https://user-images.githubusercontent.com/90203250/208690072-e73c5037-766a-4ca0-9f92-df4f824f4d83.png" width="700" height="250">

### Sequence Diagram

<details>
<summary>Sequence Diagram</summary>
<div float = left>
<img src="https://user-images.githubusercontent.com/90203250/208690489-98b6645b-0980-4161-a411-3b5039b0dd8a.png" width="500" height="300">
<img src="https://user-images.githubusercontent.com/90203250/208690514-69abf173-51fa-4d22-ae59-bbd8fb360d3a.png" width="500" height="300">
</div>
<img src="https://user-images.githubusercontent.com/90203250/208690794-e6fa7469-ba29-4e5a-b8c0-ffa7be7ebc23.png" width="500" height="450">
</details>


