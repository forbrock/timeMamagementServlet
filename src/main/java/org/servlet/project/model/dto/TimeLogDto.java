package org.servlet.project.model.dto;

import java.time.LocalDateTime;
import java.util.Objects;

public class TimeLogDto {
    private long id;
    private double duration;
    private long userActivityId;
    private LocalDateTime startDate;

    public TimeLogDto() {}

    public Long getId() {
        return id;
    }

    public Double getDuration() {
        return duration;
    }

    public Long getUserActivityId() {
        return userActivityId;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TimeLogDto that = (TimeLogDto) o;
        return id == that.id &&
                Double.compare(that.duration, duration) == 0 &&
                userActivityId == that.userActivityId &&
                startDate.equals(that.startDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, duration, userActivityId, startDate);
    }

    private TimeLogDto(TimeLogDtoBuilder builder) {
        this.id = builder.id;
        this.duration = builder.duration;
        this.userActivityId = builder.userActivityId;
        this.startDate = builder.startDate;
    }

    public static TimeLogDtoBuilder builder() {
        return new TimeLogDtoBuilder();
    }

    public static class TimeLogDtoBuilder {
        private Long id;
        private Double duration;
        private Long userActivityId;
        private LocalDateTime startDate;

        public TimeLogDtoBuilder id(long id) {
            this.id = id;
            return this;
        }

        public TimeLogDtoBuilder duration(double duration) {
            this.duration = duration;
            return this;
        }

        public TimeLogDtoBuilder userActivityId(long userActivityId) {
            this.userActivityId = userActivityId;
            return this;
        }

        public TimeLogDtoBuilder startDate(LocalDateTime startDate) {
            this.startDate = startDate;
            return this;
        }

        public TimeLogDto build() {
            return new TimeLogDto(this);
        }
    }
}
