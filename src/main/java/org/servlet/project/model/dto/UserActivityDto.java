package org.servlet.project.model.dto;

import org.servlet.project.model.entity.ActivityState;

import java.util.Objects;

public class UserActivityDto {
    private long id;

    private long userId;
    private String email;

    private long activityId;
    private String activityName;

    private long categoryId;
    private String categoryName;

    private ActivityState state;

    public UserActivityDto() {}

    public long getId() {
        return id;
    }

    public ActivityState getState() {
        return state;
    }

    public long getUserId() {
        return userId;
    }

    public String getEmail() {
        return email;
    }

    public long getActivityId() {
        return activityId;
    }

    public String getActivityName() {
        return activityName;
    }

    public long getCategoryId() {
        return categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    private UserActivityDto(UserActivityDtoBuilder builder) {
        this.id = builder.id;
        this.userId = builder.userId;
        this.email = builder.email;
        this.activityId = builder.activityId;
        this.activityName = builder.activityName;
        this.categoryId = builder.categoryId;
        this.categoryName = builder.categoryName;
        this.state = builder.state;
    }

    public static UserActivityDtoBuilder builder() {
        return new UserActivityDtoBuilder();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserActivityDto that = (UserActivityDto) o;
        return id == that.id &&
                userId == that.userId &&
                activityId == that.activityId &&
                categoryId == that.categoryId &&
                email.equals(that.email) &&
                activityName.equals(that.activityName) &&
                categoryName.equals(that.categoryName) &&
                state == that.state;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userId, email, activityId, activityName, categoryId, categoryName, state);
    }

    public static class UserActivityDtoBuilder {
        private long id;

        private long userId;
        private String email;

        private long activityId;
        private String activityName;

        private long categoryId;
        private String categoryName;

        private ActivityState state;

        public UserActivityDtoBuilder id(long id) {
            this.id = id;
            return this;
        }

        public UserActivityDtoBuilder userId(long id) {
            this.userId = id;
            return this;
        }

        public UserActivityDtoBuilder email(String email) {
            this.email = email;
            return this;
        }

        public UserActivityDtoBuilder activityId(long id) {
            this.activityId = id;
            return this;
        }

        public UserActivityDtoBuilder activityName(String name) {
            this.activityName = name;
            return this;
        }

        public UserActivityDtoBuilder categoryId(long id) {
            this.categoryId = id;
            return this;
        }

        public UserActivityDtoBuilder categoryName(String name) {
            this.categoryName = name;
            return this;
        }

        public UserActivityDtoBuilder state(ActivityState state) {
            this.state = state;
            return this;
        }

        public UserActivityDto build() {
            return new UserActivityDto(this);
        }
    }
}
