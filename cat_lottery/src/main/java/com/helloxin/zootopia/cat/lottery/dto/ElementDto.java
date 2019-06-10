package com.helloxin.zootopia.cat.lottery.dto;

import lombok.Data;

import java.util.Objects;

/**
 * Created by nandiexin on 2019/5/23.
 */
@Data
public class ElementDto {

    private String userId;

    private String userName;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }
        ElementDto that = (ElementDto) o;
        return Objects.equals(userId, that.userId) &&
                Objects.equals(userName, that.userName);
    }

    @Override
    public int hashCode() {

        return Objects.hash(super.hashCode(), userId, userName);
    }
}
