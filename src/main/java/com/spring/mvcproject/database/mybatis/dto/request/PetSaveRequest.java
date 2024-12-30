package com.spring.mvcproject.database.mybatis.dto.request;

import com.spring.mvcproject.database.mybatis.entity.Pet;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter @Setter @ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class PetSaveRequest {

    @NotBlank(message = "애완동물 이름은 필수입니다.")
    private String petName;

    @NotNull(message = "애완동물 나이는 필수입니다.")
    @Min(value = 0)
    @Max(value = 100)
    private Integer petAge;

    private boolean injection;

    // 엔터티로 변환하는 메서드
    public Pet toEntity() {
        return Pet.builder()
                .petAge(this.petAge)
                .petName(this.petName)
                .injection(this.injection)
                .build();
    }
}
