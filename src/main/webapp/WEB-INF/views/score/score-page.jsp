<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <!-- reset css -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/reset-css@5.0.1/reset.min.css">

    <!-- bootstrap css -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">


    <!-- bootstrap js -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" defer></script>

    <!-- jquery -->
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>

    <style>
        label {
            display: block;
        }

        .score-list>li {
            margin-bottom: 10px;
        }

        .score-list>li:first-child {
            font-size: 1.2em;
            color: blue;
            font-weight: 700;
            border-bottom: 1px solid skyblue;
        }

        .del-btn {
            width: 10px;
            height: 10px;
            background: red;
            color: #fff;
            border-radius: 5px;
            margin-left: 5px;
            text-decoration: none;
            font-size: 0.7em;
            padding: 6px;
        }

        .del-btn:hover {
            background: orangered;
        }

        section.score {
            padding: 50px 50px 100px;
            font-size: 1.5em;
        }

        .list-header {
            display: flex;
            justify-content: space-between;

            width: 50%;
        }
        .list-header .sort-link-group {
            display: flex;

        }
        .list-header .sort-link-group div {
            margin-right: 20px;
        }

    </style>
</head>

<body>

    <div class="wrap">

        <section class="score">
            <h1>시험 점수 등록</h1>
            <form id="score-form">
                <label>
                    # 이름: <input type="text" name="name">
                </label>
                <label>
                    # 국어: <input type="text" name="kor">
                </label>
                <label>
                    # 영어: <input type="text" name="eng">
                </label>
                <label>
                    # 수학: <input type="text" name="math">
                </label>
                <label>
                    <button id="createBtn" type="submit">확인</button>
                    <button id="go-home" type="button">홈화면으로</button>
                </label>
            </form>

            <hr>

            <ul class="score-list">
                <li class="list-header">
                    <div class="count">총 학생 수: <span id="count">0</span>명</div>
                    <div class="sort-link-group">
                        <div><a id="id" href="#">학번순</a></div>
                        <div><a id="name" href="#">이름순</a></div>
                        <div><a id="average" href="#">평균순</a></div>
                    </div>
                </li>

                <!-- 학생 성적정보가 들어갈 부분 -->
                <li>
                    <ul id="scores"></ul>
                </li>

            </ul>

        </section>
    </div>


    <script>
        const API_URL = '/api/v1/scores';

        // 화면에 성적목록을 렌더링하는 함수
        function renderScoreList(data) {

            const $scores = document.getElementById('scores');
            // 리셋
            $scores.innerHTML = '';

            // 총 학생 수 렌더링
            document.getElementById('count').textContent = data.length;

            // ({id}) : const id = data 객체(List<Score>)의 각 데이터 요소(Score 객체).id 라고 변수 저장하는 기능을 하는 deconstructuring
            data.forEach(({id, name, kor, eng, math}) => {
                $scores.innerHTML += `
                    <li data-score-id="\${id}">
                        # 이름: \${name}, 국어: \${kor}점, 
                        영어: \${eng}점, 수학: \${math}점
                        <a href='#' class='del-btn'>삭제</a>
                    </li>
                `;
            });
        }

        // ================== API 관련 메서드 ================= //
        // ## 서버에서 성적 정보를 가져오는 요청 메서드 ##
        //                      sortType='id'는 값을 안 넣으면 id를 넣어라 이 말임임
        async function fetchGetScores(sortType='id') {
            const res = await fetch(API_URL + `?sort=\${sortType}`);
            const data = await res.json(); // json 으로 받아온 내용을 객체로 변환환
            console.log(data);

            // 화면에 정보 렌더링
            renderScoreList(data);
        }

        // 서버로 성적 등록 post 요청을 전송하는 함수
        async function fetchPostScore(scoreObj) {
            // POST 요청은 단순히 요청만 보내는 게 아니라
            // 서버에 데이터도 제공해야 함
            const res = await fetch(API_URL, {
                method: 'POST',
                headers: { 'Content-Type' : 'application/json' },
                body: JSON.stringify(scoreObj)
            });
            if (res.status === 200) {
                // 등록 된 내용을 렌더링
                fetchGetScores();
                // 내용 지우기
                document.getElementById('score-form').reset();
            } else {
                alert('에러 발생. 에러 발생. 로보캅특공대 출동');
            }

        }

        async function fetchDeleteScore(id) {
            const res = await fetch(`\${API_URL}/\${id}`, {
                method: 'DELETE'
            });
            if (res.status === 200) {
                fetchGetScores();
            } else {
                alert('삭제 실패!');
            }
        }


        //==== 이벤트 리스너 등록 ====//
        // 정렬 처리 이벤트
        document.querySelector('.sort-link-group').addEventListener('click', e => {
            e.preventDefault();
            if (!e.target.matches('a')) return;
            const sortType = e.target.id;
            console.log('정렬기준: ', sortType);
            
            // 서버에 정렬기준을 가지고 목록 조회요청 전송
            fetchGetScores(sortType);
            

        });

        // 성적 정보 등록 이벤트
        document.getElementById('createBtn').addEventListener(e => {
            
            e.preventDefault(); // form의 submit 발생 시 새로고침

            const $form = document.getElementById('score-form');
           /* <form>을 FormDate객체로 바꿔주면,
          <form> 태그 내부의 name 값이 설정된 input, select, textarea, button
          요소의 값 추출 가능
           */
           /* formData로 바꾸기만 하면 'FormData {}' 이런 식으로 출력됨.
          -> 내부를 보려면 entries()로 [key, value] 쌍으로 이루어진 배열의 형태로 바꿔야됨
          -> 근데 entires()만 하면 iterable 객체로 변환해주는 것이기 때문에,
             객체에서 쓰는 키 값으로 조회(Object.title)등 사용 불가
          -> Object.fromEntries로 객체로 바꿔줌
           */
       
            const formData = new FormData($form); 
            const a = formData.entries(); 
            const b = Object.fromEntires(a); 
            console.log(b);
           
           
            /* 혹은 FormDate 객체로 변경해 준 후
              바로 formData.get('key name') 으로 추출 가능
             */
            // const name = formData.get('name');
            // const kor = formData.get('kor');
            // const eng = formData.get('eng');
            // const math = formData.get('math');
            
            // const scoreObj = {
            //     name: name,
            //     kor: kor,
            //     eng: eng,
            //     math: math
            // };


            // 서버로 POST 요청 전송
            fetchPostScore(scoreObj);

        })

        // 삭제 요청 이벤트 등록
        $scores.addEventListener('click', e => {
            e.preventDefault();
            if (!e.target.matches('.del-btn')) return;
            

            // 서버에 삭제요청 전송
            // 클릭한 요소가 가진 서버 id를 읽어내야 함
            const id = e.target.closest('li').dataset.scoreId;
                  })

            fetchDeleteScore(id);





        //============== 화면 진입 시 실행 코드 =============//
        // ## 화면에 목록 가져와서 렌더링 하는 함수 ##
        fetchGetScores();
    </script>

</body>

</html>