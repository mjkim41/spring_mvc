<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="ko">

<head>


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
        .error {
          color: #f00;
          font-size: 0.9em;
          margin-left: 15px;
        }
        .label-container {
          display: flex;
          align-items: center;
        }
    </style>

    
<%@ include file="include/static-file.jsp" %>

</head>


<body>

  
     <%@ include file="include/header.jsp" %>

        <div id="wrap" class="form-container">
            <h1>꾸러기 게시판 글쓰기</h1>
            <form id="board-form" novalidate>
                <label for="title">작성자</label>
                <input type="text" id="writer" name="writer" value="익명">

                <div class="label-container">
                  <label for="title">제목</label> <span class="error" id="title"></span>
                </div>
                <input type="text" id="title" name="title" required>
                
                <div class="label-container">
                  <label for="content">내용 </label> 
                </div>
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
          // form  안에 input type=submit 나 button type=submit가 있을 경우,
          // 버튼을 클릭하면 form submit 이벤트가 터짐
          $form = document.getElementById('board-form');
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
            // 새로 등록된 글 내용을 객체로 전달해주면, 이 내용을 boardList에 등록해주는 함수
            fetchPostBoard(boardObj);
          });

          querySelector('label[v]')
          // =========== 일반 함수 ===============//
          // ## post 시 에러 메시지 생성 //
          function createErrorMessage(errorObj) {
            // 기존 에러 메시지 정리
            const $errors = document.querySelectorAll('.error');
            $errors.forEach($err => $err.textContent = '');
            // 새 에러 메시지 생성
            // ! 객체 키로 순환 : key in object
            for (const key in errorObj) {
              if (key === 'content') {
                const $errorSpan = document.createElement('span');
                $errorSpan.classList.add('error');
                $errorSpan.textContent = errorObj[key]; // 객체의 value 접근 : Object[key]
                document.querySelector('label[for=content]').after($errorSpan); // ! 특정 요소 뒤에 html 요소 생성
              }
              document.getElementById(key).textContent = errorObj[key];
              
            }
          }

 


          // ============= api 관련 함수 =============== //
          // ## 새로 등록된 글 내용을 객체로 전달해주면, 이 내용을 boardList에 등록해주는 함수 ## //
          async function fetchPostBoard(payload) { // data : addEventListner에서 글쓰기 버튼 누르면 객체 형태로 전달해 줌
            // 서버로 POST 요청 보내기
            const response = await fetch("/api/v1/boards", {
                method: 'POST',
                headers: { 'Content-Type' : 'application/json'},
                body: JSON.stringify(payload)
            })
   
            if (response.status === 200) {
               // 성공 시 아래 path로 이동;
               window.location.href = '/board/list';
            } else if (response.status === 400) {
                // 서버 에러 메시지 파싱
                const errorObj = await response.json();
                // 에러 메시지 표시
                createErrorMessage(errorObj);
            }

          }
         </script>

</body>

</html>


</body>

</html>