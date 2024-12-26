package com.spring.mvcproject.database.mybatis.repository;

import com.spring.mvcproject.database.mybatis.entity.Pet;
import org.apache.ibatis.annotations.Mapper;

import java.sql.DriverManager;
import java.util.List;

// MyBatis를 이용하여 쿼리를 처리하는 과정 중 일부분으로,
// Interface를 생성하여 @Mapper 어노테이션을 붙인다.
//   - 의미 : 이 인터페이스에 정의된 메소드가 호출되면, resources>application.yml 파일의
//            MyBatias:Configuration:mapper-locations에서 지정된 경로에서 매핑된 SQL를 찾아 실행한다.
@Mapper
public interface PetRepository {


    // Create: spring이나 vanilla code와 달리, boolean 이 반환됨
    boolean save(Pet pet);

    // Read - Single Matching
    Pet findById(Long id);

    // Read - Multiple Matching
    List<Pet> findAll();

    // Read - Count
    int petCount();

    // Update : spring이나 vanilla code와 달리, boolean 이 반환됨
    boolean updatePet(Pet pet);

    // Delete - Single : spring이나 vanilla code와 달리, boolean 이 반환됨
    boolean deleteById(Long id);



}
