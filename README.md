# java-chess

체스 미션 저장소

# 요구사항

## 1단계 요구사항

- 콘솔 UI에서 체스 게임을 할 수 있는 기능을 구현한다.
- 1단계는 체스 게임을 할 수 있는 체스판을 초기화한다.
- 체스판에서 말의 위치 값은 가로 위치는 왼쪽부터 a ~ h이고, 세로는 아래부터 위로 1 ~ 8로 구현한다.

### 요구사항 해석

- 킹, 퀸, 룩, 비숍, 나이트, 폰을 각각 `K`, `Q`, `R`, `B`, `N`, `P`로 출력한다.
- 빈 칸은 `.`으로 출력한다.

## 2단계 요구사항

- 체스 말의 이동 규칙을 찾아보고 체스 말이 이동할 수 있도록 구현한다.
- move source위치 target위치을 실행해 이동한다.
- 체스판의 위치 값은 가로 위치는 왼쪽부터 a ~ h이고, 세로는 아래부터 위로 1 ~ 8로 구현한다.

### 요구사항 해석

- 기물이 이동할 수 없는 경우 예외가 발생한다. 
- 백과 흑은 차례를 번갈아가며 기물을 이동한다. 
  - 백의 선공으로 게임을 시작한다. 
- 다른 기물이 있는 위치로는 이동할 수 없다. 
- 상대의 기물을 공격할 수 있다. 
- 기물은 빈칸으로 또는 상대 기물이 있는 칸으로 공격하며 이동할 수 있다.
  - 폰은 앞으로만 이동할 수 있다. 
  - 상대 기물이 있는 칸으로 이동할 경우 상대 기물을 공격해 없앤다. 
    - 폰은 대각선 앞에 상대 기물이 있는 경우에만 공격하고 이동할 수 있다. 
  - 나이트를 제외한 모든 기물은 다른 기물을 건너뛸 수 없다. 
- 체크를 당하는 위치로 기물을 이동할 수 없다.
- 이동 후 체크메이트인 경우 게임을 종료한다.  
- 특수 규칙
  - 캐슬링
  - 앙파상
  - 프로모션

## 3단계 요구사항

- 체스 게임은 상대편 King이 잡히는 경우 게임에서 진다. King이 잡혔을 때 게임을 종료해야 한다.
- 체스 게임은 현재 남아 있는 말에 대한 점수를 구할 수 있어야 한다.
- "status" 명령을 입력하면 각 진영의 점수를 출력하고 어느 진영이 이겼는지 결과를 볼 수 있어야 한다.

### 점수 계산 규칙

- 체스 프로그램에서 현재까지 남아 있는 말에 따라 점수를 계산할 수 있어야 한다.
- 각 말의 점수는 queen은 9점, rook은 5점, bishop은 3점, knight는 2.5점이다.
- pawn의 기본 점수는 1점이다. 하지만 같은 세로줄에 같은 색의 폰이 있는 경우 1점이 아닌 0.5점을 준다.
- king은 잡히는 경우 경기가 끝나기 때문에 점수가 없다.
- 한 번에 한 쪽의 점수만을 계산해야 한다.

### 요구사항 해석

- 'King이 잡히는 경우'는 체크메이트를 의미한다. 

## 프로그램 실행 결과

```
> 체스 게임을 시작합니다.
> 게임 시작 : start
> 게임 종료 : end
> 게임 이동 : move source위치 target위치 - 예. move b2 b3
start
RNBQKBNR
PPPPPPPP
........
........
........
........
pppppppp
rnbqkbnr

move b2 b3
RNBQKBNR
PPPPPPPP
........
........
........
.p......
p.pppppp
rnbqkbnr
```

# 기능 목록

- [x] 체스 게임 시작 메시지를 출력한다.
  - [x] 게임 시작, 종료, 이동 명령어 안내 메시지를 출력한다. 
- [x] 게임 시작 여부를 입력받는다.
  - [x] 입력이 start, end가 아닌 경우 예외가 발생한다.
- [x] 모든 체스 기물을 생성한다.
- [x] 체스판 위에 기물을 배치한다.
- [x] 체스판을 출력한다. 
  - [x] 흑은 대문자로, 백은 소문자로 출력한다. 
- [x] 이동 명령어를 입력받는다. 
  - [x] 명령어에 의해 체스판에서 기물을 이동한다.
    - [x] 상대 기물이 있는 칸으로 공격하며 이동할 수 있다.
    - [x] 킹은 모든 방향으로 한 칸 움직일 수 있다. 
    - [x] 퀸은 수평, 수직 및 대각선으로 원하는 만큼 움직일 수 있다.
    - [x] 비숍은 대각선으로 원하는 만큼 움직일 수 있다. 
    - [x] 나이트는 `L` 모양, 즉 수평으로 두 칸 이동하고 수직으로 한 칸 움직일 수 있다.
    - [x] 룩은 수평, 수직으로 원하는 만큼 이동할 수 있다.
    - [x] 폰은 앞으로만 이동할 수 있다. 
      - [x] 대각선 앞에 상대 기물이 있는 경우에는 상대 기물을 공격하고 해당 자리로 이동할 수 있다. 
  - [x] `end`를 입력할 경우 프로그램을 종료한다. 
- [x] 잘못된 명령어를 입력한 경우 에러 메시지를 출력하고 다시 입력받는다.
- [ ] 백과 흑은 차례를 번갈아가며 기물을 이동한다.
- [ ] "status" 명령을 입력하면 각 진영의 점수를 출력하고 어느 진영이 이겼는지 출력한다.
  - [ ] 각 진영의 점수를 계산한다.
