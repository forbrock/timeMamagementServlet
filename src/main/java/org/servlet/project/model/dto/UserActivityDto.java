package org.servlet.project.model.dto;

import org.servlet.project.model.entity.ActivityState;

import java.util.Objects;

public class UserActivityDto {
    private long id;
    private String email;
    private String category;
    private String activity;
    private ActivityState state;
    private double duration;

    public UserActivityDto() {}

    public double getDuration() {
        return duration;
    }

    public long getId() {
        return id;
    }

    public ActivityState getState() {
        return state;
    }

    public String getEmail() {
        return email;
    }

    public String getActivity() {
        return activity;
    }

    public String getCategory() {
        return category;
    }

    private UserActivityDto(UserActivityDtoBuilder builder) {
        this.id = builder.id;
        this.email = builder.email;
        this.activity = builder.activity;
        this.category = builder.category;
        this.state = builder.state;
        this.duration = builder.duration;
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
                Double.compare(that.duration, duration) == 0 &&
                email.equals(that.email) &&
                category.equals(that.category) &&
                activity.equals(that.activity) &&
                state == that.state;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, email, category, activity, state, duration);
    }

    public static class UserActivityDtoBuilder {
        private long id;
        private String email;
        private String category;
        private String activity;
        private ActivityState state;
        private double duration;

        public UserActivityDtoBuilder id(long id) {
            this.id = id;
            return this;
        }

        public UserActivityDtoBuilder email(String email) {
            this.email = email;
            return this;
        }

        public UserActivityDtoBuilder activity(String activity) {
            this.activity = activity;
            return this;
        }

        public UserActivityDtoBuilder category(String category) {
            this.category = category;
            return this;
        }

        public UserActivityDtoBuilder state(ActivityState state) {
            this.state = state;
            return this;
        }

        public UserActivityDtoBuilder duration(double duration) {
            this.duration = duration;
            return this;
        }

        public UserActivityDto build() {
            return new UserActivityDto(this);
        }
    }
}
