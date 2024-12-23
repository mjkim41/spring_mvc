<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko">

<head>
    <%@ include file="include/static-file.jsp" %>
    <link rel="stylesheet" href="/assets/css/detail.css">
</head>

<body>

    <%@ include file="include/header.jsp" %>

    <div id="wrap" class="form-container" >

        <h1><span id="id">1</span>번 게시물 내용~ </h1>
        <h2># 작성일자: <span id="date">2024-12-20</span></h2>
        <label for="writer">작성자</label>
        <input type="text" id="writer" name="writer" value="익명" readonly>
        <label for="title">제목</label>
        <input type="text" id="title" name="title" value="이마트 가자고~~~" readonly>
        <label for="content">내용</label>
        <div id="content">
            하하호호히히흐흐히히히허허호오ㅗ오오오오오옹
        </div>
        
        <div class="buttons">
            <div class="reaction-buttons">
              <button id="like-btn">
                <i class="fas fa-thumbs-up"></i> 좋아요
                <span id="like-count">3</span>
              </button>
              <button
                id="dislike-btn"
                class="dislike-btn"
              >
                <i class="fas fa-thumbs-down"></i> 싫어요
                <span id="dislike-count">1</span>
              </button>
            </div>
    
            <button
              class="list-btn"
              type="button"
              onclick="window.location.href='/board/list'"
            >
              목록
            </button>
          </div>


        <!-- 댓글 영역 -->
        <div id="replies" class="row">
            <div class="offset-md-1 col-md-10">
                <!-- 댓글 쓰기 영역 -->
                <div class="card">
                    <div class="card-body">
                            <div class="row">
                                <div class="col-md-9">
                                    <div class="form-group">
                                        <label for="newReplyText" hidden>댓글 내용</label>
                                        <textarea rows="3" id="newReplyText" name="replyText" class="form-control"
                                            placeholder="댓글을 입력해주세요."></textarea>
                                    </div>
                                </div>
                                <div class="col-md-3">
                                    <div class="form-group">

                                        <div class="profile-box">
                                            
                                        </div>

                                        <label for="newReplyWriter" hidden>댓글 작성자</label>
                                        <input id="newReplyWriter" name="replyWriter" type="text" value="익명" readonly
                                            class="form-control" placeholder="작성자 이름" style="margin-bottom: 6px;">
                                        <button id="replyAddBtn" type="button" class="btn btn-dark form-control">등록</button>
                                    </div>
                                </div>
                            </div>
                    </div>
                </div> <!-- end reply write -->

                <!--댓글 내용 영역-->
                <div class="card">
                    <!-- 댓글 내용 헤더 -->
                    <div class="card-header text-white m-0" style="background: #343A40;">
                        <div class="float-left">댓글 (<span id="replyCnt">0</span>)</div>
                    </div>

                    <!-- 댓글 내용 바디 -->
                    <div id="replyCollapse" class="card">
                        <div id="replyData">
                            <!--
                                < JS로 댓글 정보 DIV삽입 >
                            -->
                        </div>

                        <!-- 댓글 페이징 영역 -->
                        <ul class="pagination justify-content-center">
                            <!--
                                < JS로 댓글 페이징 DIV삽입 >
                            -->
                        </ul>
                    </div>
                </div> <!-- end reply content -->
            </div>
        </div> <!-- end replies row -->

        <!-- 댓글 수정 모달 -->
        <div class="modal fade bd-example-modal-lg" id="replyModifyModal">
            <div class="modal-dialog modal-lg">
                <div class="modal-content">

                    <!-- Modal Header -->
                    <div class="modal-header" style="background: #343A40; color: white;">
                        <h4 class="modal-title">댓글 수정하기</h4>
                        <button type="button" class="close text-white" data-bs-dismiss="modal">X</button>
                    </div>

                    <!-- Modal body -->
                    <div class="modal-body">
                        <div class="form-group">
                            <input id="modReplyId" type="hidden">
                            <label for="modReplyText" hidden>댓글내용</label>
                            <textarea id="modReplyText" class="form-control" placeholder="수정할 댓글 내용을 입력하세요."
                                rows="3"></textarea>
                        </div>
                    </div>

                    <!-- Modal footer -->
                    <div class="modal-footer">
                        <button id="replyModBtn" type="button" class="btn btn-dark">수정</button>
                        <button id="modal-close" type="button" class="btn btn-danger" data-bs-dismiss="modal">닫기
                        </button>
                    </div>
                </div>
            </div>
        </div>

        <!-- end replyModifyModal -->

        <!-- 로딩 스피너 -->
        <!-- <div class="spinner-container" id="loadingSpinner">
            <div class="spinner-border text-light" role="status">
                <span class="visually-hidden">Loading...</span>
            </div>
        </div> -->



    </div>




    <script>    

       //   *************************** 상세 보기 관련 **********************

       // === 일반 함수 ===
       // ## (상세 보기 관련) fetchDetail 함수에서 DB 정보 가져와서 화면에 렌더링 하는 함수
       function renderBoardDetail({id, title, content, date}) { // data : fetchDetail() 함수에서 전달해줌
        const $id = document.getElementById('id');
        $id.textContent = id;
        // input 태그의 value 바꾸는 법 : $tag.value
        const $title = document.getElementById('title');
        $title.value = title;
        const $content = document.getElementById('content');
        $content.textContent = content;
        const $date = document.getElementById('date');
        $date.textContent = date;
       }

      // ===  API 통신 관련 ===
      //  ## (상세보기 관련) 서버에 게시물 상세조회 API 요청하는 함수  
      async function fetchDetail() {
        // backend에서 게시물 상세정보 가져오기
        //  - id 정보는 pageController에서 Model 객체를 통해 전달받음
        const res = await fetch(`/api/v1/boards/${id}`); 
        const data = await res.json();
        console.log(data);
        
        // 화면에 렌더링 하는 함수
        renderBoardDetail(data);
      }

      // *************************** 상세 보기 끝 *************************

 

 
    
      // ******************* 화면 진입 시 실행 *******************
      fetchDetail();


    </script>

        
</body>

</html>