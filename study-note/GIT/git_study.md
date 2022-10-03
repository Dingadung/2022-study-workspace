# Git

- Git은 VCS중 하나이다.
    > VCS : Version Control System
- 협업할 팀원 추가
    > repository의 settings - Collaborators - Add People
---

### 0. Commands
##### Base
- `git status` : git의 상태 볼 수 있다.
- `git log` : 지난 commit 기록들을 볼 수 있고, j와 k로 스크롤 가능.
- `git commit -am 'blahblah'` : 새로 추가된 파일이 없을 때만 사용 가능한 add + commit 기능의 명령어 `-am`이다.
- `git rm (filename)` : git에서 file을 삭제한다. `rm : remove`
    > 즉시 `staging area`에 올라간다.
- `git mv (filename) (filename1)` : git에서 file의 이름을 변경한다. `mv : move`
    > 삭제+생성이 아니라 변경으로 인식하게 된다.
- `git restore --staged (filename)` : file을 `staged`된 상태에서 다시 `working directory`로 되돌리겠다.
- `git restore (filename)` : (add가 안 된 상태에서) `working directory`에 올라간 내용을 다시 복구하겠다. (변경사항을 되돌리겠다)
- 

---
##### Reset vs Revert
- `git reset --hard (hashcode)` : 특정시점의 hashcode를 통해 그 특정시점으로 `reset`한다.
- `git reset --mixed (hashcode)` : `add`가 되기 전으로 되돌린다.
    > 많은 `commit`내역 중 하나만 다시 수정하고 싶을 경우 사용.
    > default이므로 mixed를 안 써도 된다.
- `git reset --soft (hashcode)` : `commit`되기 전으로 되돌린다.
- `git revdert (hashcode)` : 특정 시점의 hashcode를 통해 그 특정시점으로 `revert`한다.
- `git revert --continue` : `revert`중 충돌문제 때문에 멈춰 있던 `revert`를 이어서 실행한다.
- `git revert --no-commit (hashcode)` : `revert`와 함께 다른 추가작업을 시행했다면 `commit`도 해줘야 하지만, 이 명령어는 `revert`와 `commit`을 동시에 할 수 있게 한다.

---
##### Branch & Switch
- `git branch (branch name)` : branch를 생성한다.
- `git branch` : branch 목록을 확인한다.
- `git switch (branch name)` : branch를 이동한다.
- `git switch -c (branch name)` : 새 branch를 생성하고 이동한다.
- `git branch -d (branch name)` : branch를 삭제한다. (-D (대문자)옵션을 사용할 경우 다른 branch로 적용되지 않은 커밋내용을 무시하고 강제 삭제한다.)
- `git branch -m (이름변경할branch) (변경될branch)` : branch이름을 변경한다.
- `git switch (branch name)` : 다른 branch로 이동

---
##### Merge vs Rebase
- `git merge (branch name)` : 현재 위치한 branch**로** (branch name)branch를 `merge`한다.
- `git rebase (branch name)` : 현재 위치한 branch**를** (branch name)branch를 rebase한다. **`merge`와 반대이다!**
- `git merge --abort` : `merge`중 충돌이 발생하여 더 이상 `merge`를 진행하고 싶지 않을 때 `merge`를 중단시킨다.
- `git rebase --abort` : 위와동
- `git rebase --continue` : 충돌내용을 수정한 후 `git add .`으로 변경사항을 추가하고 나머지 `rebase`과정을 계속 진행시킨다.

---
##### Github
- `git push -u (branch name)` : `-u` :: 현재 branch와 명시된 branch 기본연결, 그리고 `push`
- `git push --force` : **협업도중이 아닌 혼자 작업할 때만 사용할 것** 로컬의 작업 내용을 원격에 강제로 적용한다.
- `git push -u origin (new branch)` : 원격에 새 branch를 생성하고 거기에 현재 branch의 내역을 `push`까지 하겠다.
- `git branch -a` : 로컬뿐만 아니라 원격의 branch까지 볼 수 있다. (remote에만 있는 branch는 볼 수 없다.)
- `git fetch` : 원격의 변경사항을 확인 할 수 있다. 이 명령 이후라면 로컬에서 원격에만 있는 branch도 확인할 수 있다.
- `git switch -t origin/(from remote)` : 로컬의 현재 branch를 원격의 branch와 동일시 하겠다? (**부정확 정보!!**)
- `git push (origin) --delete (remote branch name)` : remote의 branch를 삭제하는 명령어.

---

### 1. Reset vs Revert
- `Reset` : 특정 과거 시점으로 돌아간 후 그 이후의 기록들을 모두 날려버리는 것.
- `Revert` : 특정 과거 시점(A)에 대한 역실행 commit 내역(-A)을 추가한 다음, 결과적으로 그 특정 시점(A)으로 돌아가는 것.
    > 이 경우, 그 시점(A) 이후(B, C, ...)의 다른 내역들은 유지된다.

---

### 2. Merge vs Rebase
- `Merge` : 두 가지를 이어붙이고 그 과정에서 `commit`하나가 더 생겨난다.
    - history에 branch의 흔적을 남긴다.
    - branch의 사용내역들을 남겨둘 필요가 있으면 `Merge`를 사용하라.
    - `merge`도 하나의 `commit`이기 때문에 `reset`으로 되돌리기가 가능하다.
    - 충돌 발생 시 충돌부분을 수정한 후 다시 `commit`한다.
![](./img/git_fig1.png)
    > <git_fig1>
- `Rebase` : 작업한 내용을 다른 곳의 뒤에 이어붙이는 작업이다.
    - history에 흔적을 남기지 않는다.
    - history를 깔끔하게 만드는 것이 중요하다면 `Rebase`를 사용하라.
    - 협업 중에는 `Rebase`를 사용하는 것을 지양하라.
    - `rebase`한 후에는 `merge`를 통해서 rebase branch를 합치고 제거해주어라.
    - 충돌 과정 해결한 후 원래 main과 비교하여 변경사항이 없다면 `commit`하지 않는다.
![](./img/git_fig2.png)
    > <git_fig2>

---

### 3. 협업 발생 시 충돌 상황 해결하기
- `git pull --no-rebase` : 팀원과 `push`과정에서 충돌이 났을 때 먼저 `pull`을 하는 과정에서 `merge`방식으로 충돌을 해결하고자 하는 것.
- `git pull --rebase` : 팀원과 `push`과정에서 충돌이 났을 때 먼저 `pull`을 하는 과정에서 `rebase`방식으로 충돌을 해결하고자 하는 것. **(협업시 사용 OK)** 
    > 로컬에서 작업할 때 이미 공유된 것들을 `rebase`를 사용하여 올리지 말라는 것
    > 뭔가 `pull`받는 상황에서 `rebase`는 괜찮다! 는 뜻

---
---
# 여기서부터 유료

---
---

# Git

##### Git의 특징
- 스냅샷방식
    > Github는 델타방식과 스냅샷방식 중 스냅샷방식(현재 최종파일의 변경사항 확인)을 채택했다.
- 분산 버전 관리
    > 중앙집중식 버전 관리 vs 분산 버전 관리
    > 중앙집중식은 원격저장소에 의존적이다. 중앙에서 다운받은 파일로만 작업가능
    > 분산 버전 관리는 모든 구성원들이 Git의 상태까지 공유한다.

---

##### Git의 3가지 공간
![](./img/git_fig3.png)
><git_fig3>
1. Working directory
    - 수정및 변화내용들이 즉시 반영된다.
> `add`를 통해 `Staging Area`로 넘어간다.

2. Staging Area
    - 이 변화를 반영한다. `Repository`로 이동하기 전의 상황
    - 필요한 파일만 `Repository`로 넘기기 위해
> `commit`을 통해 `Repository`로 넘어간다.

3. Repository
    - .git directory 라고도 한다.

---
### reset의 3가지 옵션
    1. soft
    2. mixed
    3. hard
