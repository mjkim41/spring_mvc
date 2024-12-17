package com.spring.mvcproject.chap02_3.quiz.quiz6.Controller;

import com.spring.mvcproject.chap02_3.quiz.quiz6.entity.Book1;
import org.springframework.web.bind.annotation.*;

import java.awt.print.Book;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/books")
public class BookController1 {

    // 데이터베이스 대용으로 책들을 모아서 관리
    // 도서 정보 리스트 생성
    // list를 안 쓰고 Map으로 하는 이유 : 조회할 때 list는 index나 for문으로 가져와야 함
    private Map<Long, Book1> bookList = new HashMap<>();
    private long nextId = 1;


    public BookController1() {
        bookList.put(1L, new Book1(nextId, "왜 배가 고픈가?", "미돌", 3000));
        nextId++;
        bookList.put(2L, new Book1(nextId, "점심메뉴를 어떻게 잘 고를것인가?", "미순", 5000));
        nextId++;
    }

   /* [문제 1]
     - **URL**: `/api/v1/books/{id}`
     - **설명**: ID를 사용하여 특정 도서 정보를 조회하세요.
     - **응답**: 해당 도서가 존재하면 도서 정보를 반환하고, 존재하지 않으면 에러 메시지를 반환하세요.
    */
    @GetMapping("/{id}")
    public String getBookDetail(
            @PathVariable("id") Long id
    ) {
        Book1 foundBook = bookList.get(id);
        if (foundBook == null) {
            return "없는 도서입니다.";
        } else {
            // !!! jAVA에서 문자열과 다른 객체를 더할 때, toString() 메서드가 자동으로 호출됨!!!
            return "도서정보" + foundBook;
        }
    }

    /* [문제 2] # POST 요청: 도서 추가
     - **URL**: `/api/v1/books`
     - **설명**: `title`, `author`, `price` 값을 받아 새로운 도서를 추가하세요.
     - **응답**: 새로 추가된 도서 정보를 반환하세요.
     */
    @PostMapping("")
    public Map<Long, Book1> addBook(
            @RequestParam("title") String title,
            @RequestParam("author") String author,
            @RequestParam("price") int price
    ) {
        // id를 삭제했을 때를 대비해서 현재 있는 id 값 중 가장 큰 값에서 +1한 값으로 바꿈
        Long nextId = bookList.keySet()
                .stream()
                .max((a, b) -> a.compareTo(b))
                .orElse(1L);

        Book1 newBook = new Book1(nextId, title, author, price);
        bookList.put(this.nextId, newBook);
        this.nextId++;
        return bookList;
    }

    /* [문제 3] PUT 요청: 도서 수정
      - **URL**: `/api/v1/books/{id}`
      - **설명**: ID에 해당하는 도서의 `title`, `author`, `price` 값을 수정하세요.
      - **응답**: 수정된 도서 정보를 반환하세요.
     */
    @PutMapping("/{id}")
    public String editBookDetails(
        @PathVariable("id") Long id,
        @RequestParam(value="title", required=false) String title,
        @RequestParam(value="author", required=false)String author,
        @RequestParam(value="price", required=false)Integer price
    ) {
        Book1 foundBook = bookList.get(id);
        if (price != null) {
            foundBook.setPrice(price);
        }
        if (title != null) {
            foundBook.setTitle(title);
        }
        if (author != null) {

        }
        foundBook.setAuthor(author);
        return "도서정보가 수정되었습니다 -" + foundBook;
    }

    /* [문제 4] 도서 삭제
      - **URL**: `/api/v1/books/{id}`
      - **설명**: ID에 해당하는 도서를 삭제하세요.
      - **응답**: 삭제된 도서 정보를 반환하세요. 존재하지 않으면 에러 메시지를 반환하세요
     */
    @DeleteMapping("/{id}")
    public String deleteBook(
            @PathVariable("id") Long id
    ) {
        Book1 foundBook = bookList.get(id);
        if (foundBook == null) {
            return "없는 책이라 못 지우는데요?";
        } else {
            bookList.remove(id);
            return "이 책을 지웠어요 -" + foundBook;
        }
    }
}
