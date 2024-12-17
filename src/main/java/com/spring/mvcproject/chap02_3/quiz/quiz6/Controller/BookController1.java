package com.spring.mvcproject.chap02_3.quiz.quiz6.Controller;

import com.spring.mvcproject.chap02_3.quiz.quiz6.entity.Book1;
import org.springframework.web.bind.annotation.*;

import java.awt.print.Book;
import java.util.*;

@RestController
@RequestMapping("/api/v1/books")
public class BookController1 {

    // 데이터베이스 대용으로 책들을 모아서 관리
    // list를 안 쓰고 Map으로 하는 이유 : 조회할 때 list는 index나 for문으로 가져와야 함
    private Map<Long, Book1> bookList = new HashMap<>();
    private Long nextId = 1L; // id를 자동으로 1, 2, 3 이런식으로 생성

    public BookController1() {
        Book1 book1 = new Book1(nextId++, "밥 먹는 법", "미돌", 13000);
        Book1 book2 = new Book1(nextId++, "쌀 먹는 법", "미순", 43000);
        Book1 book3 = new Book1(nextId++, "피자 먹는 법", "미훙", 55000);
        bookList.put(book1.getId(), book1);
        bookList.put(book2.getId(), book2);
        bookList.put(book3.getId(), book3);
    }

    /*
    [문제 0] 전체 목록 조회
     */
    @GetMapping("") // 위의 RequestMapping 주소로 GET 요청이 오면
    public ArrayList<Book1> getAllBook() {
        //bookList(Map)에서 values만 추출해서 List로만들어라
        ArrayList<Book1> bookListinArrayList
                = new ArrayList<>(bookList.values());
        return bookListinArrayList;
    }

    /* [문제 1]
      - **URL**: `/api/v1/books/{id}`
      - **설명**: ID를 사용하여 특정 도서 정보를 조회하세요.
      - **응답**: 해당 도서가 존재하면 도서 정보를 반환하고, 존재하지 않으면 에러 메시지를 반환하세요.
     */
    // /{id} 로 GET 요청을 하면 아래 메소드를 호출하라.
    @GetMapping("/{id}")
    public String getSpecificBook(
            // 서버 요청 주소에서 /{id}에서 id의 값을 받아와서
            @PathVariable("id") Long id
    ) {
        // id 값으로 맵인 bookList에서 책을 조회해서
        Book1 foundBook = bookList.get(id);

        // 없는 id면 (= 없는 책이면)
        if (foundBook == null) {
            return "없는 도서입니다.";
        } else {
            // 있는 책이면
            // ! String + Object 를 하면, Object는 toString() 값이 출력됨
            System.out.println(foundBook);
            return "조회된 도서의 정보" + foundBook;
        }
    }

    /* [문제 2] # POST 요청: 도서 추가
     - **URL**: `/api/v1/books`
     - **설명**: `title`, `author`, `price` 값을 받아 새로운 도서를 추가하세요.
     - **응답**: 새로 추가된 도서 정보를 반환하세요.
     */
    @PostMapping("")
    public String createBook(
            // @RequestParam 단축 표현
            String title, String author, int price
    ) {
        // 스트링 쿼리에서 정보를 가져와서 책을 생성해서
       Book1 newBook = new Book1(nextId++, title, author, price);
        // 책 리스트를 담은 Map에 추가해라.
        bookList.put(newBook.getId(), newBook);
        return "책 추가 완료" + newBook.getId();
    }

    // 최대값 구하기
    @GetMapping("/test")
    public Optional<Integer> test() {
     ArrayList<Integer> a = new ArrayList<>(List.of(1, 2, 3, 4, 5));
        Optional<Integer> max = a.stream()
                .map(data -> data)
                .max((x, y) -> x.compareTo(y));
        return max;
    }


    /* [문제 3] PUT 요청: 도서 수정
      - **URL**: `/api/v1/books/{id}`
      - **설명**: ID에 해당하는 도서의 `title`, `author`, `price` 값을 수정하세요.
      - **응답**: 수정된 도서 정보를 반환하세요.
     */
    // 1. 사용자가 /3?price=3000 이런 식으로 요청을 하면 그 값을 받아서
    @PutMapping("/{id}")
    public String updateBook(
            @PathVariable("id") Long id,
            String title, String author, int price
    ) {
        // 2. 일단 bookList에서 그 Book 객체를 조회한 후에
        Book1 foundBook = bookList.get(id);

        // 3. 없는 책이면 없다고 뜨게 하기
        if (foundBook == null) {
            return id + "번 도서는 존재하지 않아!";
        }
        // 4. 있는 책이면 그 Book 객체에서 값 수정
        foundBook.updateBook(title, author, price);
        return "도서 수정 완료" + foundBook.getId();
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
        Book1 removedBook = bookList.remove(id);
        if (removedBook == null) {
            return "없는 책이라 못 지우는데요?";
        } else {
            return "이 책을 지웠어요 -" + id;
        }
    }
}
