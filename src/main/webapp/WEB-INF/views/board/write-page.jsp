<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="ko">

<head>

    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>스프링 연습프로젝트 사이트</title>

    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Single+Day&display=swap" rel="stylesheet">

    <!-- reset -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/reset-css@5.0.1/reset.min.css">

    <!-- fontawesome css: https://fontawesome.com -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.8.2/css/all.min.css">

    <!-- https://linearicons.com/free#cdn -->
    <link rel="stylesheet" href="https://cdn.linearicons.com/free/1.0.0/icon-font.min.css">

    <!-- bootstrap css -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">


    <link rel="stylesheet" href="/assets/css/main.css">

    <!-- bootstrap js -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" defer></script>

    <!-- side menu event js -->
    <script src="/assets/js/side-menu.js" defer></script>


    <!-- ck editor -->
    <link rel="stylesheet" href="https://cdn.ckeditor.com/ckeditor5/44.1.0/ckeditor5.css" />
    <script src="https://cdn.ckeditor.com/ckeditor5/44.1.0/ckeditor5.umd.js"></script>

    <style>
        .form-container {
            width: 500px;
            margin: auto;
            padding: 20px;
            background-image: linear-gradient(135deg, #a1c4fd, #fbc2eb);
            box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
            border-radius: 4px;
            font-size: 18px;
        }

        .form-container h1 {
            font-size: 40px;
            font-weight: 700;
            letter-spacing: 10px;
            text-align: center;
            margin-bottom: 20px;
            color: #ffffff;
        }

        label {
            display: block;
            margin-bottom: 5px;
            font-size: 20px;
        }

        input[type="text"],
        textarea {
            font-size: 18px;
            width: 100%;
            padding: 8px;
            box-sizing: border-box;
            border: 2px solid #ffffff;
            border-radius: 8px;
            margin-bottom: 10px;
            background-color: rgba(255, 255, 255, 0.8);
        }

        textarea {
            resize: none;
            height: 200px;
        }

        .buttons {
            display: flex;
            justify-content: flex-end;
            margin-top: 20px;
        }

        button {
            font-size: 20px;
            padding: 10px 20px;
            border: none;
            margin-right: 10px;
            background-color: #4CAF50;
            color: white;
            cursor: pointer;
            border-radius: 4px;
            box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
            transition: background-color 0.3s;
        }

        button.list-btn {
            background: #e61e8c;
        }

        button:hover {
            background-color: #3d8b40;
        }

        button.list-btn:hover {
            background: #e61e8c93;
        }
        .ck-editor__editable {
          height: 300px;
        }
        .ck-editor__editable p {
          margin: 0;
        }
    </style>
</head>

<body>

  <!-- header -->
<header>
<div class="inner-header">
  <h1 class="logo">
    <a href="/board/list">
      <img src="/assets/img/logo.png" alt="로고이미지">
    </a>
  </h1>

  <div class="profile-box">
    
  </div>

  <h2 class="intro-text">Welcome</h2>
  <a href="#" class="menu-open">
    <span class="menu-txt">MENU</span>
    <span class="lnr lnr-menu"></span>
  </a>
</div>

<nav class="gnb">
  <a href="#" class="close">
    <span class="lnr lnr-cross"></span>
  </a>
  <ul>
    <li><a href="/">Home</a></li>
    <li><a href="#">About</a></li>
    <li><a href="/board/list">Board</a></li>
    <li><a href="#">Contact</a></li>
    <li><a href="/members/sign-up">Sign Up</a></li>
    <li><a href="/members/sign-in">Sign In</a></li>
  </ul>
</nav>

</header>


        <div id="wrap" class="form-container">
            <h1>꾸러기 게시판 글쓰기</h1>
            <form id="board-form" novalidate>
                <label for="title">작성자</label>
                <input type="text" id="writer" name="writer" value="익명" readonly>
                <label for="title">제목</label>
                <input type="text" id="title" name="title" required>
                <label for="content">내용</label>
                <textarea id="content" name="content" maxlength="200" required></textarea>
                <div class="buttons">
                    <button class="list-btn" type="button"
                        onclick="window.location.href='/board/list'">목록</button>
                    <button type="submit">글쓰기</button>
                </div>
            </form>
        </div>
        <script>
            // CKEDITOR ...
            let editor;
            const {
                ClassicEditor,
                Essentials,
                Bold,
                Italic,
                Font,
                Paragraph
            } = CKEDITOR;

            ClassicEditor
                .create( document.querySelector( '#content' ), {
                    licenseKey: 'eyJhbGciOiJFUzI1NiJ9.eyJleHAiOjE3NjYxMDIzOTksImp0aSI6IjEzODIxZWU2LTY0OWItNGE1OC04ZTA2LThlNzNhM2RlMTg4NiIsInVzYWdlRW5kcG9pbnQiOiJodHRwczovL3Byb3h5LWV2ZW50LmNrZWRpdG9yLmNvbSIsImRpc3RyaWJ1dGlvbkNoYW5uZWwiOlsiY2xvdWQiLCJkcnVwYWwiXSwiZmVhdHVyZXMiOlsiRFJVUCIsIkJPWCJdLCJ2YyI6IjM4MTM3YjhjIn0.Vy670BGfDF08y8-WPxehiMxEdzZwv99XOYbmPweVv1NOisMc-GE3PnTEY6pwz6pmeooWe5lArtch1r9iykDmfQ',
                    plugins: [ Essentials, Bold, Italic, Font, Paragraph ],
                    toolbar: [
                        'undo', 'redo', '|', 'bold', 'italic', '|',
                        'fontSize', 'fontFamily', 'fontColor', 'fontBackgroundColor'
                    ],
                } )
                .then(newEditor => {
                  // editor = newEditor;
                })
                .catch(err => console.error(err));



        </script>

        <!-- custom script -->
         <script>
          const $form = document.getElementById('board-form');
          // form  안에 input type=submit 나 button type=submit가 있을 경우,
          // 버튼을 클릭하면 form submit 이벤트가 터짐
          $form.addEventListener('submit', e => {
            // 폼 데이터를 서버로 전달하고 페이지를 새로고침 하는 기능을 막음
            e.preventDefault();
             // formData로 바꾸기만 하면 'FormData {}' 이런 식으로 출력됨.
             // -> 내부를 보려면 entries()로 [key, value] 쌍으로 이루어진 배열의 형태로 바꿔야됨됨
             // -> 근데 또 entires()하면 iterable 객체로 변환해주는 것이기 때문에, 객체에서 쓰는 키 값으로 조회(Object.title)등 사용 불가
             // -> 객체로 바꿔줌줌
             // name 속성이 설정된 input, select, textarea, button 요소의 값을 가져옴옴
            const formData = new FormData($form);
            const formDataEntires = formData.entries();
            const boardObj = Object.fromEntries(formDataEntires);
            console.log(boardObj);
  
            // 새로 등록된 글 내용을 객체로 전달해주면, 이 내용을 boardList에 등록해주는 함수
            fetchPostBoard(boardObj);
          });

          // ============= api 관련 함수 =============== //
          // ## 새로 등록된 글 내용을 객체로 전달해주면, 이 내용을 boardList에 등록해주는 함수 ## //
          async function fetchPostBoard(boardObj) { // data : addEventListner에서 글쓰기 버튼 누르면 객체 형태로 전달해 줌
            console.log(boardObj);
            // 서버로 POST 요청 보내기
            const response = await fetch("/api/v1/boards", {
                method: 'POST',
                headers: { 'Content-Type' : 'application/json'},
                body: JSON.stringify(boardObj)
            })
            const data = await response.json();
            if (response.status === 200) {
               // 성공 시 window.location.href = '/board/list';
               console.log(data);
               window.location.href = '/board/list';
            } else {
                alert('에러 발생');
            }

          }
         </script>

</body>

</html>


</body>

</html>