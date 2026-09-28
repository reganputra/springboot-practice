package org.javafundamental.taskmanagemet.dto;

public record TaskStatResponse(
                long totalTasks,
                long todoCount,
                long inProgressCount,
                long doneCount,
                long highPriorityCount,
                long mediumPriorityCount,
                long lowPriorityCount,
                long overdueCount) {

}
