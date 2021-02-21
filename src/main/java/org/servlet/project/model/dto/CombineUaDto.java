package org.servlet.project.model.dto;

import org.servlet.project.model.entity.ActivityState;

import java.time.LocalDateTime;
import java.util.Objects;

public class CombineUaDto {
    private Long id;
    private long userId;
    private String email;
    private Long activityId;
    private String activityName;
    private long categoryId;
    private String categoryName;
    private ActivityState state;
    private double duration;
    private LocalDateTime startDate;

    public CombineUaDto() {}

    public Long getId() {
        return id;
    }

    public long getUserId() {
        return userId;
    }

    public String getEmail() {
        return email;
    }

    public Long getActivityId() {
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

    public ActivityState getState() {
        return state;
    }

    public double getDuration() {
        return duration;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CombineUaDto that = (CombineUaDto) o;
        return userId == that.userId &&
                categoryId == that.categoryId &&
                Double.compare(that.duration, duration) == 0 &&
                id.equals(that.id) &&
                email.equals(that.email) &&
                activityId.equals(that.activityId) &&
                activityName.equals(that.activityName) &&
                categoryName.equals(that.categoryName) &&
                state == that.state &&
                startDate.equals(that.startDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userId, email, activityId, activityName, categoryId, categoryName, state, duration, startDate);
    }

    private CombineUaDto(CombineUaDtoBuilder builder) {
        this.id = builder.id;
        this.userId = builder.userId;
        this.email = builder.email;
        this.activityId = builder.activityId;
        this.activityName = builder.activityName;
        this.categoryId = builder.categoryId;
        this.categoryName = builder.categoryName;
        this.state = builder.state;
        this.duration = builder.duration;
        this.startDate = builder.startDate;
    }

    public static class CombineUaDtoBuilder {
        private Long id;
        private long userId;
        private String email;
        private Long activityId;
        private String activityName;
        private long categoryId;
        private String categoryName;
        private ActivityState state;
        private double duration;
        private LocalDateTime startDate;

        public CombineUaDtoBuilder id(long id) {
            this.id = id;
            return this;
        }

        public CombineUaDtoBuilder userId(long id) {
            this.userId = id;
            return this;
        }

        public CombineUaDtoBuilder email(String email) {
            this.email = email;
            return this;
        }

        public CombineUaDtoBuilder activityId(long id) {
            this.activityId = id;
            return this;
        }

        public CombineUaDtoBuilder activityName(String name) {
            this.activityName = name;
            return this;
        }

        public CombineUaDtoBuilder categoryId(long id) {
            this.categoryId = id;
            return this;
        }

        public CombineUaDtoBuilder categoryName(String name) {
            this.categoryName = name;
            return this;
        }

        public CombineUaDtoBuilder state(ActivityState state) {
            this.state = state;
            return this;
        }

        public CombineUaDtoBuilder duration(double duration) {
            this.duration = duration;
            return this;
        }

        public CombineUaDtoBuilder startDate(LocalDateTime startDate) {
            this.startDate = startDate;
            return this;
        }

        public CombineUaDto build() {
            return new CombineUaDto(this);
        }
    }
}
